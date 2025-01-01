package net.rafael.cycledevices;

import net.fabricmc.api.ClientModInitializer;
import net.rafael.cycledevices.item.custom.TimeCycleDeviceItem;
import net.rafael.cycledevices.item.custom.WeatherCycleDeviceItem;

public class RafaelsCycleDevicesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
//        ModModelPredicates.registerModelPredicates();
        WeatherCycleDeviceItem.registerCooldowns();
        TimeCycleDeviceItem.registerCooldowns();
    }
}
