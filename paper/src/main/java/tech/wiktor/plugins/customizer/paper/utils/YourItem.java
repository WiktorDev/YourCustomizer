package tech.wiktor.plugins.customizer.paper.utils;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class YourItem extends OkaeriConfig {
    private String oraxenId;
    private ItemStack itemStack;

    public YourItem(String oraxenId, ItemStack itemStack) {
        this.oraxenId = oraxenId;
        this.itemStack = itemStack;
    }

    public ItemStack getItem() {
        if (!OraxenItems.exists(this.oraxenId)) return this.itemStack;
        ItemStack item = OraxenItems.getItemById(this.oraxenId).build();
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.setDisplayName(this.itemStack.getItemMeta().getDisplayName());
        meta.setLore(this.itemStack.getItemMeta().getLore());
        item.setItemMeta(meta);
        return item;
    }
    public String getOraxenId() {
        return this.oraxenId;
    }
}
