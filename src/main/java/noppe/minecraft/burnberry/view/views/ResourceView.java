package noppe.minecraft.burnberry.view.views;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.view.MenuElement;
import noppe.minecraft.burnberry.view.View;
import noppe.minecraft.burnberry.view.menuelements.MenuElementLeft;
import noppe.minecraft.burnberry.view.menuelements.MenuElementRight;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ResourceView extends View {
    MenuElement left;
    MenuElement right;

    public ResourceView(CustomPlayer player) {
        super(player);
        left = new MenuElementLeft();
        menuElements.add(left);
        right = new MenuElementRight();
        menuElements.add(right);
        reload();
    }

    @Override
    public void reload() {
        switchInventory(getInventory(), menuElements);
    }

    public Inventory getInventory(){
        Inventory inventory = Bukkit.createInventory(null, 54, "My Custom Inventory");
        inventory.setItem(6, left.itemStack);
        inventory.setItem(7, Menu.black.clone());
        M.setItemName(inventory.getItem(7), ""+page);
        inventory.setItem(8, right.itemStack);
        return inventory;
    }

    public ItemStack getPageCount(){
        ItemStack pageItem = Menu.black.clone();
        M.setItemName(pageItem, ""+page);
        return pageItem;
    }
}
