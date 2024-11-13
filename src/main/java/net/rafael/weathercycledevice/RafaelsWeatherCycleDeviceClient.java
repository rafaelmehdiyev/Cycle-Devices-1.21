package net.rafael.weathercycledevice;

import net.fabricmc.api.ClientModInitializer;
import net.rafael.weathercycledevice.util.ModModelPredicates;

public class RafaelsWeatherCycleDeviceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicates();

    }
}
