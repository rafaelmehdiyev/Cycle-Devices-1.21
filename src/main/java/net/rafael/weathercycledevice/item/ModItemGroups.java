package net.rafael.weathercycledevice.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.rafael.weathercycledevice.RafaelsWeatherCycleDevice;


public class ModItemGroups {
    public static final ItemGroup WEATHER_CYCLE_DEVICE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(RafaelsWeatherCycleDevice.MOD_ID,"weather_cycle_device"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.WEATHER_CYCLE_DEVICE))
                    .displayName(Text.translatable("itemgroup.rafaels_weather_cycle_device.weather_cycle_device"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WEATHER_CYCLE_DEVICE);
                    })
                    .build());
    public static void registerItemGroups() {
        RafaelsWeatherCycleDevice.LOGGER.info("Registering Item Groups for "+RafaelsWeatherCycleDevice.MOD_ID);
    }
}
