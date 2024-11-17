package net.rafael.cycledevices;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.rafael.cycledevices.datagen.ModItemTagProvider;
import net.rafael.cycledevices.datagen.ModLootTableProvider;
import net.rafael.cycledevices.datagen.ModRecipeProvider;
import net.rafael.cycledevices.datagen.ModModelProvider;

public class RafaelsCycleDevicesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelProvider::new);
	}
}
