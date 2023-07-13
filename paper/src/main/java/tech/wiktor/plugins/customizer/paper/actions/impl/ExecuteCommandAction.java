package tech.wiktor.plugins.customizer.paper.actions.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tech.wiktor.plugins.customizer.paper.actions.Action;

public class ExecuteCommandAction implements Action {
    @Override
    public String getIdentifier() {
        return "EXECUTE_COMMAND";
    }

    @Override
    public void execute(Player player, String value) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), value);
    }
}
