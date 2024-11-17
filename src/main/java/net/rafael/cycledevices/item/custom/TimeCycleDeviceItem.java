package net.rafael.cycledevices.item.custom;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeCycleDeviceItem extends Item {

    private static final Map<PlayerEntity, Integer> cooldowns = new HashMap<>();

    public TimeCycleDeviceItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (getCooldownForPlayer(player) > 0) {
                player.sendMessage(Text.of("Please wait " + getCooldownForPlayer(player) / 20 + " seconds to use again."), true);
                return ActionResult.FAIL;
            }

            setCooldownForPlayer(player, 200); // 10 seconds

            long currentTime = serverWorld.getTimeOfDay() % 24000;
            long newTime;
            if (currentTime < 6000) {
                newTime = 13000; // Switch to night
                player.sendMessage(Text.translatable("time_change.time-cycle-device.to_night"), true);
            } else if (currentTime < 13000) {
                newTime = 18000; // Switch to midnight
                player.sendMessage(Text.translatable("time_change.time-cycle-device.to_midnight"), true);
            } else {
                newTime = 1000; // Switch to day
                player.sendMessage(Text.translatable("time_change.time-cycle-device.to_day"), true);
            }
            serverWorld.setTimeOfDay(newTime);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.rafaels-cycle-devices.time_cycle_device"));
        super.appendTooltip(stack, context, tooltip, type);
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
