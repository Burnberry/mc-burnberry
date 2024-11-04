package noppe.minecraft.burnberry.view.views;

import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.view.View;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ResourceView extends View {
    ResourceGame game;

    public ResourceView(ResourceGame game) {
        super(game.player);
        this.game = game;
        menuElements.add(left);
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

    @Override
    public void onInventoryClickHook(InventoryClickEvent event, EventInventoryClick ev) {
        game.onInventoryClick(event, ev);
    }
}
