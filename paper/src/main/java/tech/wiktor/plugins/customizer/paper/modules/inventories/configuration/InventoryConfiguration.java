package tech.wiktor.plugins.customizer.paper.modules.inventories.configuration;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.Material;
import tech.wiktor.plugins.customizer.paper.actions.ActionEntity;
import tech.wiktor.plugins.customizer.paper.utils.ItemCreator;
import tech.wiktor.plugins.customizer.paper.utils.YourItem;

import java.util.HashMap;
import java.util.List;

public class InventoryConfiguration extends OkaeriConfig {
    private String title = "Default gui";
    private List<String> pattern = List.of(
            "# # # # # # # # #",
            "# # # # # # # # #",
            "# # # # X # # # #"
    );
    private HashMap<Character, BaseItem> items = this.setupItems();
    private HashMap<Character, BaseItem> setupItems() {
        HashMap<Character, BaseItem> itemHashMap = new HashMap<>();
        itemHashMap.put('#', new BaseItem(new YourItem(null, new ItemCreator(Material.BLACK_STAINED_GLASS_PANE).title("&8").build()), List.of(
                new ActionEntity("SEND_MESSAGE", "testetwq wqe")
        )));
        itemHashMap.put('X', new BaseItem(new YourItem(null, new ItemCreator(Material.BARRIER).title("&c&lZAMKNIJ").build()), List.of(
                new ActionEntity("CLOSE_INVENTORY", null)
        )));
        return itemHashMap;
    }

    public String[] getPattern() {
        return this.pattern.toArray(new String[]{});
    }
    public String getTitle() {
        return this.title;
    }
    public HashMap<Character, BaseItem> getItems() {
        return this.items;
    }
}
