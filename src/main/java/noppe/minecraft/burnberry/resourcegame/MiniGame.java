package noppe.minecraft.burnberry.resourcegame;

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
    public ItemStack restart = new ItemStack(Material.STONE_PICKAXE);
    public ItemStack resourcesAction = new ItemStack(Material.BARREL);
    public ItemStack upgradesAction = new ItemStack(Material.EMERALD);

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

    public Inventory getFinishedInventory(){
        Inventory inventory = Bukkit.createInventory(null, 54, name);
        inventory.setItem(22, restart);
        inventory.setItem(23, resourcesAction);
        inventory.setItem(24, upgradesAction);
        return inventory;
    }
    public void onSlotClicked(int slot){
        if (isFinished()){
            if (slot == 22){
                finish();
                return;
            }
            if (slot == 23){
                game.viewResources();
                return;
            }
            if (slot == 24){
                game.viewUpgrades();
                return;
            }
        }

        for (ResourceNode node: nodes){
            if (node.slot == slot){
                node.onHit(game.player);
                break;
            }
        }
        if (isFinished()){
            setInventory(getFinishedInventory());
        }
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
        game.reload();
    }

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
        game.reload();
    }
}
