package noppe.minecraft.burnberry.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Inv {
    public static Inventory lobby = Inv.createLobby();
    public static Inventory game = Inv.createGame();

    static Inventory createLobby(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER, "Inventory");
        inventory.setItem(0, Menu.startGame);
        inventory.setItem(6, Menu.controlRadialMenu);
        inventory.setItem(8, Menu.controlGamemode);
        return inventory;
    }

    static Inventory createGame(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER, "Inventory");
        inventory.setItem(0, Weapon.bow);
        inventory.setItem(27, new ItemStack(Material.ARROW));

        inventory.setItem(2, Menu.controlSpawnMonster);
        
        inventory.setItem(8, Menu.resourceMenu);
        return inventory;
    }
}
