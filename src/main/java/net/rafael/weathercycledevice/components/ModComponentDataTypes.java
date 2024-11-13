package net.rafael.weathercycledevice.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rafael.weathercycledevice.RafaelsWeatherCycleDevice;

import java.util.function.UnaryOperator;

public class ModComponentDataTypes {

    public static final ComponentType<Integer> WeatherState = register("weather_state",builder -> builder.codec(Codec.INT));

    public static <T> ComponentType<T> register (String name, UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(RafaelsWeatherCycleDevice.MOD_ID,name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes(){
        RafaelsWeatherCycleDevice.LOGGER.info( "Registering Data Component Types for " + RafaelsWeatherCycleDevice.MOD_ID);
    }
}
