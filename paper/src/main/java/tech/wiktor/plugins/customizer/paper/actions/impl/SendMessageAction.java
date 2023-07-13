package tech.wiktor.plugins.customizer.paper.actions.impl;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import tech.wiktor.plugins.customizer.paper.actions.Action;
import tech.wiktor.plugins.customizer.paper.modules.messages.MessageService;

public class SendMessageAction implements Action {
    @Inject private MessageService messageService;

    @Override
    public String getIdentifier() {
        return "SEND_MESSAGE";
    }

    @Override
    public void execute(Player player, String value) {
        player.sendMessage(value);
    }
}
