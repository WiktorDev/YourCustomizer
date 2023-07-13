package tech.wiktor.plugins.customizer.paper.commands;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tech.wiktor.plugins.customizer.paper.commands.arguments.CreateInventoryArgument;
import tech.wiktor.plugins.customizer.paper.commands.arguments.InventoryArgument;

import java.util.List;

public class CommandsModule {
    @Inject private Plugin plugin;
    @Inject private Injector injector;

    @PostConstruct
    public void onConstruct() {
        LiteBukkitFactory.builder(this.plugin.getServer(), this.plugin.getName())
                .argument(Player.class, new BukkitPlayerArgument<>(this.plugin.getServer(), "&8&l>> &cTen gracz jest offline!"))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>("&8&l>> &cTa komenda jest tylko dla gracza!"))

                .argument(String.class, InventoryArgument.KEY, this.injector.createInstance(InventoryArgument.class))
                .argument(String.class, CreateInventoryArgument.KEY, this.injector.createInstance(CreateInventoryArgument.class))
                .commandInstance(this.injector.createInstance(CustomizerCommand.class))

                .invalidUsageHandler((sender, invocation, schematic) -> {
                    List<String> schematics = schematic.getSchematics();

                    if (schematics.size() == 1) {
                        sender.sendMessage("&cNie poprawne użycie komendy &8>> &7" + schematics.get(0));
                        return;
                    }
                    sender.sendMessage("&cNie poprawne użycie komendy!");
                    for (String sch : schematics) {
                        sender.sendMessage("&8 >> &7" + sch);
                    }
                })
                .permissionHandler((sender, invocation, requiredPermissions) -> {
                    sender.sendMessage("&cNie masz permisji do tej komendy! &7(" + String.join(", ", requiredPermissions.getPermissions()) + ")");
                })
                .register();
    }
}
