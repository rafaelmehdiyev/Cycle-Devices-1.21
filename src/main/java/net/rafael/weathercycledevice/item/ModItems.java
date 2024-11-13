package net.rafael.weathercycledevice.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.rafael.weathercycledevice.RafaelsWeatherCycleDevice;
import net.rafael.weathercycledevice.item.cusom.WeatherCycleDeviceItem;

public class ModItems {

    // Declare the Weather Cycle Device item
    public static final Item WEATHER_CYCLE_DEVICE = registerItem("weather_cycle_device",
            new WeatherCycleDeviceItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(RafaelsWeatherCycleDevice.MOD_ID, name), item);
    }

    public static void registerModItems() {
        RafaelsWeatherCycleDevice.LOGGER.info("Registering Mod Items for "+RafaelsWeatherCycleDevice.MOD_ID );

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(WEATHER_CYCLE_DEVICE);
        });
    }
}
