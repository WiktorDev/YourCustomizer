package tech.wiktor.plugins.customizer.paper.modules.messages;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MessageService {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();


    public void actionbar(Player player, String message) {
        player.sendActionBar(this.miniMessage.deserialize(message));
    }
    public void actionbar(Player player, Component component) {
        this.actionbar(player, this.miniMessage.serialize(component));
    }

    public Component deserialize(String string) {
        return this.miniMessage.deserialize(string);
    }
    public List<Component> deserialize(List<String> list) {
        return list.stream().map(this::deserialize).toList();
    }
    public String serialize(Component component) {
        return this.miniMessage.serialize(component);
    }
    public String setPlaceholders(Component component, Player player) {
        return PlaceholderAPI.setPlaceholders(player, this.serialize(component));
    }
    public List<String> setPlaceholders(List<Component> component, Player player) {
        return PlaceholderAPI.setPlaceholders(player, component.stream().map(this::serialize).toList());
    }
    public ItemStack implementPlaceholders(ItemStack itemStack, Player player) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return itemStack;
        meta.displayName(this.deserialize(this.setPlaceholders(meta.displayName(), player)));
        if (meta.lore() != null) {
            meta.lore(this.deserialize(this.setPlaceholders(meta.lore(), player)));
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
