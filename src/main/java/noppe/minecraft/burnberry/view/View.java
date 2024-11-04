package noppe.minecraft.burnberry.view;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.view.menuelements.MenuElementLeft;
import noppe.minecraft.burnberry.view.menuelements.MenuElementRight;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;
import java.util.List;

public abstract class View extends CustomEventListener {
    public CustomPlayer player;
    public Inventory inventory;
    public InventoryView inventoryView;
    public List<MenuElement> menuElements;
    public int page = 0;

    public MenuElement left = new MenuElementLeft();
    public MenuElement right = new MenuElementRight();

    public View(CustomPlayer player){
        this.player = player;
        player.view = this;
        menuElements = new ArrayList<>();
    }

    public abstract void reload();

    public void close(){
        player.playerWrapped.closeInventory();
        if (this == player.view){
            player.view = null;
        }
    }

    public void switchInventory(Inventory inventory, List<MenuElement> menuElements){
        this.inventory = inventory;
        this.inventoryView = player.playerWrapped.openInventory(inventory);
        this.menuElements = menuElements;
    }

    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){
        if (this.menuElements == null){
            return;
        }
        for (MenuElement menuElement: this.menuElements){
            if (M.matches(menuElement.itemStack, ev.itemClicked)){
                menuElement.onClick(this);
                return;
            }
        }
        onInventoryClickHook(event, ev);
        return;
    }

    public void onInventoryClickHook(InventoryClickEvent event, EventInventoryClick ev) {
        return;
    }
}