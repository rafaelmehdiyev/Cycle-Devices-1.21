package net.rafael.cycledevices.item.custom;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherCycleDeviceItem extends Item {

    private static final Map<PlayerEntity, Integer> cooldowns = new HashMap<>();

    public WeatherCycleDeviceItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
            if (getCooldownForPlayer(player) > 0) {
                player.sendMessage(Text.of("Please wait " + getCooldownForPlayer(player) / 20 + " seconds to use again."), true);
                return TypedActionResult.fail(player.getStackInHand(hand));
            }

            setCooldownForPlayer(player, 200); // 10 seconds

            WeatherState nextState = getCurrentWeatherState(serverWorld).getNext();
            switch (nextState) {
                case CLEAR -> serverWorld.setWeather(6000, 0, false, false);
                case RAINY -> serverWorld.setWeather(0, 6000, true, false);
                case THUNDER -> serverWorld.setWeather(0, 6000, true, true);
            }
            player.sendMessage(Text.translatable("weather_change.weather-cycle-device.to_" + nextState.name().toLowerCase()), true);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        // Get the player's name dynamically
        String playerName = MinecraftClient.getInstance().player.getName().getString();

        // Add the tooltip with the player's name inserted
        tooltip.add(Text.translatable("tooltip.rafaels-cycle-devices.weather_cycle_device", playerName));

        super.appendTooltip(stack, context, tooltip, type);
    }

        private WeatherState getCurrentWeatherState(ServerWorld world) {
        if (world.isThundering()) return WeatherState.THUNDER;
        if (world.isRaining()) return WeatherState.RAINY;
        return WeatherState.CLEAR;
    }

    private int getCooldownForPlayer(PlayerEntity player) {
        return cooldowns.getOrDefault(player, 0);
    }

    private void setCooldownForPlayer(PlayerEntity player, int ticks) {
        cooldowns.put(player, ticks);
    }

    public static void registerCooldowns() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            cooldowns.replaceAll((player, cooldown) -> Math.max(0, cooldown - 1));
        });
    }
}

enum WeatherState {
    CLEAR, RAINY, THUNDER;

    public WeatherState getNext() {
        return switch (this) {
            case CLEAR -> RAINY;
            case RAINY -> THUNDER;
            case THUNDER -> CLEAR;
        };
    }
}
