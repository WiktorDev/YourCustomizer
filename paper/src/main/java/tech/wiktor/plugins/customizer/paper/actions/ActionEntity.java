package tech.wiktor.plugins.customizer.paper.actions;

import eu.okaeri.configs.OkaeriConfig;

public class ActionEntity extends OkaeriConfig {
    private String identifier;
    private String value;

    public ActionEntity(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValue() {
        return value;
    }
}
