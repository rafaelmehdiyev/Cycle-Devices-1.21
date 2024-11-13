package net.rafael.weathercycledevice.item.cusom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.rafael.weathercycledevice.components.ModComponentDataTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherCycleDeviceItem extends Item {

    // Map to track the time since the last weather change for each player
    private static final Map<PlayerEntity, Integer> cooldowns = new HashMap<>();

    public WeatherCycleDeviceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;

            int cooldownTime = getCooldownForPlayer(player);
            if (cooldownTime > 0) {
                player.sendMessage(Text.of("Please wait " + cooldownTime/20 + " seconds before using the Weather Cycle Device again."), true);
                return TypedActionResult.fail(player.getStackInHand(hand));
            }

            setCooldownForPlayer(player, 200); // Set cooldown for 10 seconds

            // Get the current weather state and cycle to the next
            WeatherState currentState = getCurrentWeatherState(serverWorld);
            WeatherState nextState = currentState.getNext();

            ItemStack stack = player.getStackInHand(hand);

            int newStateValue;
            // Apply the new weather state
            switch (nextState) {
                case CLEAR -> {
                    serverWorld.setWeather(6000, 0, false, false);
                    player.sendMessage(Text.translatable("weather_change.rafaels-weather-cycle-device.to_sunny"), true);
                    newStateValue = 1;
                }
                case RAINY -> {
                    serverWorld.setWeather(0, 6000, true, false);
                    player.sendMessage(Text.translatable("weather_change.rafaels-weather-cycle-device.to_rainy"), true);
                    newStateValue = 2;
                }
                case THUNDER -> {
                    serverWorld.setWeather(0, 6000, true, true);
                    player.sendMessage(Text.translatable("weather_change.rafaels-weather-cycle-device.to_thunderstorm"), true);
                    newStateValue = 3;
                }
                default -> newStateValue = 1;
            }
            stack.set(ModComponentDataTypes.WeatherState, newStateValue);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        // Get the player's name dynamically
        String playerName = MinecraftClient.getInstance().player.getName().getString();

        // Add the tooltip with the player's name inserted
        tooltip.add(Text.translatable("tooltip.rafaels-weather-cycle-device.weather_cycle_device", playerName));

        super.appendTooltip(stack, context, tooltip, type);
    }


    // Method to determine the current weather state based on server conditions
    private WeatherState getCurrentWeatherState(ServerWorld world) {
        if (world.isThundering()) {
            return WeatherState.THUNDER;
        } else if (world.isRaining()) {
            return WeatherState.RAINY;
        } else {
            return WeatherState.CLEAR;
        }
    }

    // Method to get the remaining cooldown for a player
    private int getCooldownForPlayer(PlayerEntity player) {
        return cooldowns.getOrDefault(player, 0);
    }

    // Method to set a cooldown for a player
    private void setCooldownForPlayer(PlayerEntity player, int ticks) {
        cooldowns.put(player, ticks);
    }

    // Register a callback to decrease cooldowns every tick
    public static void registerCooldowns() {
        // Register for server tick events
        ServerTickEvents.END_SERVER_TICK.register((server) -> {
            for (Map.Entry<PlayerEntity, Integer> entry : cooldowns.entrySet()) {
                PlayerEntity player = entry.getKey();
                int cooldownTime = entry.getValue();
                if (cooldownTime > 0) {
                    cooldowns.put(player, cooldownTime - 1);
                }
            }
        });
    }
}

enum WeatherState {
    CLEAR,
    RAINY,
    THUNDER;

    // Method to get the next state in the cycle
    public WeatherState getNext() {
        return switch (this) {
            case CLEAR -> RAINY;
            case RAINY -> THUNDER;
            case THUNDER -> CLEAR;
        };
    }
}

