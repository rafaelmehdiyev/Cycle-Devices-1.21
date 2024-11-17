package net.rafael.cycledevices.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rafael.cycledevices.RafaelsCycleDevices;


public class ModItemGroups {
    public static final ItemGroup CYCLE_DEVICES_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(RafaelsCycleDevices.MOD_ID,"weather_cycle_device"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.WEATHER_CYCLE_DEVICE))
                    .displayName(Text.translatable("itemgroup.rafaels-cycle-device.weather_cycle_device"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WEATHER_CYCLE_DEVICE);
                        entries.add(ModItems.TIME_CYCLE_DEVICE);
                    })
                    .build());
    public static void registerItemGroups() {
        RafaelsCycleDevices.LOGGER.info("Registering Item Groups for "+RafaelsCycleDevices.MOD_ID);
    }
}
