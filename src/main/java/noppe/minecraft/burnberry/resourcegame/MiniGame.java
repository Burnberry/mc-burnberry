package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class MiniGame {
    public ResourceGame game;
    public Inventory inventory;
    public List<Integer> nodes;
    public ItemStack restart = new ItemStack(Material.HEART_OF_THE_SEA);

    public MiniGame(ResourceGame game){
        this.game = game;
        setNodes();
    }
    public void setNodes(){
        this.nodes = new ArrayList<>();
        for (int i=0; i<54; i++){
            nodes.add(0);
        }
    }

    public Inventory getFinishedInventory(){
        Inventory inventory = Bukkit.createInventory(null, 54, "Mines");
        inventory.setItem(22, restart);
        return inventory;
    }
    public abstract void onSlotClicked(int slot);
    public abstract Inventory getInventory();
    public abstract boolean isFinished();
}
