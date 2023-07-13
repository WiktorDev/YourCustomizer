package tech.wiktor.plugins.customizer.paper.modules.inventories;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import org.bukkit.plugin.Plugin;
import tech.wiktor.plugins.customizer.paper.actions.ActionService;

import java.io.File;
import java.nio.file.Path;

public class InventoryModule {
    @Inject private Plugin plugin;
    @Inject private Injector injector;
    @Inject private ActionService actionService;

    @PostConstruct
    public void onConstruct() {
        InventoryService inventoryService = this.injector.createInstance(InventoryService.class);
        this.injector.registerInjectable(inventoryService);

        File directory = Path.of(this.plugin.getDataFolder().getPath(),"inventories").toFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }
        File[] files = directory.listFiles(file -> file.isFile() && file.getName().endsWith(".yml"));
        if (files == null) return;
        for (File file : files) {
            inventoryService.saveConfig(file.getName());
        }
    }
}
