package tech.wiktor.plugins.customizer.paper.commands;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.argument.By;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.wiktor.plugins.customizer.paper.commands.arguments.CreateInventoryArgument;
import tech.wiktor.plugins.customizer.paper.commands.arguments.InventoryArgument;
import tech.wiktor.plugins.customizer.paper.modules.inventories.InventoryService;
import tech.wiktor.plugins.customizer.paper.utils.MessageUtil;

@Route(name = "customizer")
public class CustomizerCommand {
    @Inject private InventoryService inventoryService;

    @Execute(route = "reload")
    public void reload(CommandSender sender) {
        this.inventoryService.reload(sender);
        sender.sendMessage("Pomyslnie przeladowano!");
    }
    @Execute(route = "open")
    public void openInventory(Player player, @Arg @By(InventoryArgument.KEY) String inventory) {
        this.inventoryService.open(inventory, player);
    }
    @Execute(route = "create-inventory")
    public void createInventory(Player player, @Arg @By(CreateInventoryArgument.KEY) String inventory) {
        this.inventoryService.saveConfig(inventory);
        MessageUtil.send(player, "&8&l>> &aPlik z gui zostal utworzony!");
    }
}
