package tech.wiktor.plugins.customizer.paper;

import eu.okaeri.injector.OkaeriInjector;
import org.bukkit.plugin.java.JavaPlugin;
import tech.wiktor.plugins.customizer.paper.actions.ActionService;
import tech.wiktor.plugins.customizer.paper.commands.CommandsModule;
import tech.wiktor.plugins.customizer.paper.modules.inventories.InventoryModule;
import tech.wiktor.plugins.customizer.paper.modules.messages.MessageService;

public final class PaperPlugin extends JavaPlugin {
    private final OkaeriInjector injector = OkaeriInjector.create();

    @Override
    public void onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);
        this.injector.registerInjectable(new MessageService());

        ActionService actionService = this.injector.createInstance(ActionService.class);
        this.injector.registerInjectable(actionService);

        this.injector.createInstance(InventoryModule.class);
        this.injector.createInstance(CommandsModule.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
