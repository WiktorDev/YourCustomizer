package tech.wiktor.plugins.customizer.paper.actions.impl;

import org.bukkit.entity.Player;
import tech.wiktor.plugins.customizer.paper.actions.Action;

public class CloseInventoryAction implements Action {
    @Override
    public String getIdentifier() {
        return "CLOSE_INVENTORY";
    }

    @Override
    public void execute(Player player, String value) {
        player.closeInventory();
    }
}
