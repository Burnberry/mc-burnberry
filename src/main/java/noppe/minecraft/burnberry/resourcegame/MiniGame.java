package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.resources.StoneResource;
import noppe.minecraft.burnberry.resourcegame.resources.WoodResource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MiniGame {
    public ResourceGame game;
    public String name;
    public Inventory inventory;
    public List<ResourceNode> nodes;

    public MiniGame(ResourceGame game, String name){
        this.game = game;
        this.name = name;
        restart();
    }

    public void restart(){
        inventory = getDefaultInventory();
        setNodes();
    }

    public abstract void setNodes();

    public void onSlotClicked(int slot){
        for (ResourceNode node: nodes){
            if (node.slot == slot){
                node.onHit(game.player);
                break;
            }
        }
        if (isFinished()){
            restart();
            game.viewMainMenu();
        }
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
        viewMinigame();
    }

    public abstract void viewMinigame();

    public Inventory getDefaultInventory(){
        return Bukkit.createInventory(null, 54, name);
    }

    public boolean isFinished(){
        for (ResourceNode node: nodes){
            if (!node.isFinished()){
                return false;
            }
        }
        return true;
    };

    public void finish(){
        restart();
        game.viewMines();
    }
}
