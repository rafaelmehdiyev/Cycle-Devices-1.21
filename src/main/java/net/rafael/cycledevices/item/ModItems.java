package net.rafael.cycledevices.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.rafael.cycledevices.RafaelsCycleDevices;
import net.rafael.cycledevices.item.custom.TimeCycleDeviceItem;
import net.rafael.cycledevices.item.custom.WeatherCycleDeviceItem;

public class ModItems {

    // Declare the Weather Cycle Device item
    public static final Item WEATHER_CYCLE_DEVICE = registerItem("weather_cycle_device",
            new WeatherCycleDeviceItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,Identifier.of(RafaelsCycleDevices.MOD_ID,"weather_cycle_device")))
                    .maxCount(1).rarity(Rarity.EPIC)));

    public static final Item TIME_CYCLE_DEVICE = registerItem("time_cycle_device",
            new TimeCycleDeviceItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,Identifier.of(RafaelsCycleDevices.MOD_ID,"time_cycle_device")))
                    .maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(RafaelsCycleDevices.MOD_ID, name), item);
    }

    public static void registerModItems() {
        RafaelsCycleDevices.LOGGER.info("Registering Mod Items for "+RafaelsCycleDevices.MOD_ID );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(WEATHER_CYCLE_DEVICE);
            entries.add(TIME_CYCLE_DEVICE);
        });
    }
}
