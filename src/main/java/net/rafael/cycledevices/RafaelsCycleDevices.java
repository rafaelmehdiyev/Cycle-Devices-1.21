package net.rafael.cycledevices;

import net.fabricmc.api.ModInitializer;

import net.rafael.cycledevices.components.ModComponentDataTypes;
import net.rafael.cycledevices.item.ModItemGroups;
import net.rafael.cycledevices.item.ModItems;
import net.rafael.cycledevices.item.custom.TimeCycleDeviceItem;
import net.rafael.cycledevices.item.custom.WeatherCycleDeviceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RafaelsCycleDevices implements ModInitializer {
	public static final String MOD_ID = "rafaels-cycle-devices";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModComponentDataTypes.registerDataComponentTypes();
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}