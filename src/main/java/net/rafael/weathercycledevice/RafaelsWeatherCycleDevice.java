package net.rafael.weathercycledevice;

import net.fabricmc.api.ModInitializer;

import net.rafael.weathercycledevice.components.ModComponentDataTypes;
import net.rafael.weathercycledevice.item.ModItemGroups;
import net.rafael.weathercycledevice.item.ModItems;
import net.rafael.weathercycledevice.item.cusom.WeatherCycleDeviceItem;
import net.rafael.weathercycledevice.util.ModModelPredicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RafaelsWeatherCycleDevice implements ModInitializer {
	public static final String MOD_ID = "rafaels-weather-cycle-device";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModComponentDataTypes.registerDataComponentTypes();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		WeatherCycleDeviceItem.registerCooldowns();
	}
}