package tech.wiktor.plugins.customizer.shared.factory;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.configurer.Configurer;
import eu.okaeri.configs.postprocessor.SectionSeparator;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import eu.okaeri.configs.yaml.bungee.YamlBungeeConfigurer;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;

import java.io.File;

public class ConfigurationFactory {
    private final File defaultDir;

    public ConfigurationFactory(File defaultDir) {
        this.defaultDir = defaultDir;
    }

    public <T extends OkaeriConfig> T produce(FactoryPlatform factoryPlatform, Class<T> clazz, String fileName) {
        return this.produce(factoryPlatform, clazz, new File(this.defaultDir, fileName));
    }

    public <T extends OkaeriConfig> T produce(FactoryPlatform factoryPlatform, Class<T> clazz, File file) {
        return ConfigManager.create(clazz, it-> {
            switch (factoryPlatform) {
                case BUNGEE -> this.configure(it, file, new YamlBungeeConfigurer("#", SectionSeparator.NEW_LINE));
                case VELOCITY -> this.configure(it, file, new YamlSnakeYamlConfigurer("#", SectionSeparator.NEW_LINE));
                case PAPER -> it.withConfigurer(new YamlBukkitConfigurer("#", SectionSeparator.NEW_LINE))
                        .withSerdesPack(new SerdesBukkit())
                        .withBindFile(file)
                        .saveDefaults()
                        .load(true);
            }
        });
    }

    private void configure(OkaeriConfig okaeriConfig, File file, Configurer configurer) {
        okaeriConfig.withConfigurer(configurer).withBindFile(file).saveDefaults().load(true);
    }
}
