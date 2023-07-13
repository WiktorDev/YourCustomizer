package tech.wiktor.plugins.customizer.paper.modules.inventories;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;
import tech.wiktor.plugins.customizer.paper.actions.Action;
import tech.wiktor.plugins.customizer.paper.actions.ActionService;
import tech.wiktor.plugins.customizer.paper.modules.inventories.configuration.InventoryConfiguration;
import tech.wiktor.plugins.customizer.paper.modules.inventories.exceptions.InventoryNotFoundException;
import tech.wiktor.plugins.customizer.paper.modules.messages.MessageService;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.gui.structure.Markers;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;

public class InventoryView {
    @Inject private InventoryService inventoryService;
    @Inject private ActionService actionService;
    @Inject private MessageService messageService;

    public Gui createGui(String inventoryName, Player player) {
        InventoryConfiguration inventoryConfiguration = this.inventoryService.getInventory(inventoryName);
        if (inventoryConfiguration == null) throw new InventoryNotFoundException(inventoryName);

        var builder = Gui.normal()
                .setStructure(inventoryConfiguration.getPattern())
                .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL);

        inventoryConfiguration.getItems().forEach((key, baseItem) -> {
            builder.addIngredient(key, new AbstractItem() {
                @Override
                public ItemProvider getItemProvider() {
                    return new ItemBuilder(messageService.implementPlaceholders(baseItem.getItemStack(), player));
                }

                @Override
                public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
                    baseItem.getActions().forEach(actionEntity -> {
                        Action action = actionService.getAction(actionEntity.getIdentifier());
                        if (action == null) return;
                        String value = actionEntity.getValue();
                        if (value != null) value = value.replace("{player}", player.getName());
                        action.execute(player, value);
                    });
                }
            });
        });
        return builder.build();
    }
}
