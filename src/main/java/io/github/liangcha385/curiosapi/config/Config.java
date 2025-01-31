package io.github.liangcha385.curiosapi.config;

import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class Config implements SettingsHolder {
    // Commanapi
    public static final Property<String> COMMANDAPI_VERBOSEOUTPUT =
            newProperty("CommandAPI.verboseOutput", "true");

    private Config() {
        // 阻止实例化
    }
}
