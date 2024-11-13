package net.rafael.weathercycledevice.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.rafael.weathercycledevice.RafaelsWeatherCycleDevice;
import net.rafael.weathercycledevice.components.ModComponentDataTypes;
import net.rafael.weathercycledevice.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates() {

        ModelPredicateProviderRegistry.register(ModItems.WEATHER_CYCLE_DEVICE, Identifier.of(RafaelsWeatherCycleDevice.MOD_ID, "weather_state"),
                (stack, world, entity, seed) -> {
                    Integer weatherState = stack.get(ModComponentDataTypes.WeatherState);
                    return weatherState != null ? (float)weatherState : 1.0f;
                });
    }
}
