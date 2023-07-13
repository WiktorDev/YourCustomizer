package tech.wiktor.plugins.customizer.paper.modules.inventories.exceptions;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String name) {
        super("Inventory " + name + " not found!");
    }
}
