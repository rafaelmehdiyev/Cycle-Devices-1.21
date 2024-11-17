package net.rafael.cycledevices.components;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.rafael.cycledevices.RafaelsCycleDevices;

import java.util.function.UnaryOperator;

public class ModComponentDataTypes {

    public static <T> ComponentType<T> register (String name, UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(RafaelsCycleDevices.MOD_ID,name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes(){
        RafaelsCycleDevices.LOGGER.info( "Registering Data Component Types for " + RafaelsCycleDevices.MOD_ID);
    }
}
