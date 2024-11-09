package noppe.minecraft.burnberry.item;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Inv {
    public static Inventory lobby = Inv.createLobby();

    static Inventory createLobby(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER, "Inventory");
        inventory.setItem(0, Menu.startGame);
        inventory.setItem(1, Menu.controlResources);
        inventory.setItem(2, Menu.controlUpgrades);

        inventory.setItem(4, Menu.controlMines);

        inventory.setItem(8, Menu.controlGamemode);

        return inventory;
    }
}
