package tech.wiktor.plugins.customizer.paper.actions.impl;

import org.bukkit.entity.Player;
import tech.wiktor.plugins.customizer.paper.actions.Action;

public class OpenInventoryAction implements Action {
    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public void execute(Player player, String value) {

    }
//    @Inject private InventoryService inventoryService;
//
//    @Override
//    public String getIdentifier() {
//        return "OPEN_INVENTORY";
//    }
//
//    @Override
//    public void execute(Player player, String value) {
//        this.inventoryService.open(value, player);
//    }
}
