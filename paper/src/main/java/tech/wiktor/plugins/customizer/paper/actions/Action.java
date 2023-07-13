package tech.wiktor.plugins.customizer.paper.actions;

import org.bukkit.entity.Player;

public interface Action {
    String getIdentifier();
    void execute(Player player, String value);
}
