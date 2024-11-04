package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class MiniGame {
    public ResourceGame game;
    public Inventory inventory;
    public List<Integer> nodes;

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
    public abstract void onSlotClicked(int slot);
    public abstract Inventory getInventory();
}
