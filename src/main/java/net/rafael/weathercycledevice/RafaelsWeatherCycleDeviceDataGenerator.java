package net.rafael.weathercycledevice;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.rafael.weathercycledevice.datagen.ModItemTagProvider;
import net.rafael.weathercycledevice.datagen.ModLootTableProvider;
import net.rafael.weathercycledevice.datagen.ModRecipeProvider;
import net.rafael.weathercycledevice.datagen.ModModelProvider;

public class RafaelsWeatherCycleDeviceDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModModelProvider::new);
	}
}
