package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MiningGame extends MiniGame{
    public ItemStack cobble = new ItemStack(Material.COBBLESTONE);
    public ItemStack stone = new ItemStack(Material.STONE);

    public MiningGame(ResourceGame game) {
        super(game);
        nodes.set(31, 3);
        nodes.set(30, 1);
        nodes.set(32, 1);
        nodes.set(40, 1);
        nodes.set(22, 1);
    }

    @Override
    public void onSlotClicked(int slot) {
        if (nodes.get(slot) > 0){
            nodes.set(slot, nodes.get(slot) - 1);
        }
        game.reload();
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Mines");
        for (int i=0; i<nodes.size(); i++){
            int node = nodes.get(i);
            if (node == 1){
                inventory.setItem(i, cobble);
            } else if (node > 1) {
                inventory.setItem(i, stone);
            }
        }
        return inventory;
    }
}
