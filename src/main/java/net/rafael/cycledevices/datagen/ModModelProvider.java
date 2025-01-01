package net.rafael.cycledevices.datagen;


import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.rafael.cycledevices.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Register models for each state, with unique identifiers
        itemModelGenerator.register(ModItems.WEATHER_CYCLE_DEVICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIME_CYCLE_DEVICE  , Models.GENERATED);
    }
}