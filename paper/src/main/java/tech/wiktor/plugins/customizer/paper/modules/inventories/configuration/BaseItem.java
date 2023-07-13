package tech.wiktor.plugins.customizer.paper.modules.inventories.configuration;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.inventory.ItemStack;
import tech.wiktor.plugins.customizer.paper.actions.ActionEntity;
import tech.wiktor.plugins.customizer.paper.utils.YourItem;

import java.util.List;

public class BaseItem extends OkaeriConfig {
    private YourItem item;
    private List<ActionEntity> actions;

    public BaseItem(YourItem yourItem, List<ActionEntity> actions) {
        this.item = yourItem;
        this.actions = actions;
    }

    public YourItem getItem() {
        return this.item;
    }
    public ItemStack getItemStack() {
        return this.item.getItem();
    }
    public List<ActionEntity> getActions() {
        return this.actions;
    }
}
