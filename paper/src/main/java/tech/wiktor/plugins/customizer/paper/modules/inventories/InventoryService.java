package tech.wiktor.plugins.customizer.paper.modules.inventories;

import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tech.wiktor.plugins.customizer.paper.modules.inventories.configuration.InventoryConfiguration;
import tech.wiktor.plugins.customizer.shared.factory.ConfigurationFactory;
import tech.wiktor.plugins.customizer.shared.factory.FactoryPlatform;
import xyz.xenondevs.invui.window.Window;

import java.util.HashMap;

public class InventoryService {
    @Inject private Injector injector;
    @Inject private Plugin plugin;
    private final HashMap<String, InventoryConfiguration> inventoryConfigurations = new HashMap<>();

    public void saveConfig(String name) {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.plugin.getDataFolder());
        if (this.inventoryConfigurations.containsKey(name)) return;
        this.inventoryConfigurations.put(name, configurationFactory.produce(FactoryPlatform.PAPER, InventoryConfiguration.class, "inventories/"+name));
    }

    public void reload(CommandSender sender) {
        this.inventoryConfigurations.forEach((name, configuration) -> {
            try {
                configuration.load(true);
            } catch (OkaeriException exception) {
                sender.sendMessage("Wystapil blad podczas ladowania pliku " + configuration.getBindFile().toString());
                exception.printStackTrace();
            }
        });
    }
    public InventoryConfiguration getInventory(String name) {
        return this.inventoryConfigurations.get(name);
    }
    public String getInventoryName(String name) {
        if (this.inventoryConfigurations.get(name) == null) return null;
        return name;
    }
    public HashMap<String, InventoryConfiguration> getInventories() {
        return this.inventoryConfigurations;
    }
    public void open(String name, Player player) {
        Window window = Window.single()
                .setViewer(player)
                .setGui(this.injector.createInstance(InventoryView.class).createGui(name, player))
                .setTitle(this.getInventory(name).getTitle())
                .build();
        window.open();
    }
}
