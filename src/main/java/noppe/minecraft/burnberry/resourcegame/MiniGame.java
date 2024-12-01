package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.item.Menu;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import noppe.minecraft.burnberry.resourcegame.resources.ResourceGetter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Dictionary;
import java.util.List;

public abstract class MiniGame {
    public CustomPlayer player;
    public ResourceGame game;
    public String name;
    public Inventory inventory;
    public List<ResourceNode> nodes;
    public Dictionary<Res, GameResource> resourcesEarned;

    public MiniGame(ResourceGame game, CustomPlayer player, String name){
        this.game = game;
        this.player = player;
        this.name = name;
        restart();
    }

    public void restart(){
        inventory = getDefaultInventory();
        setNodes();
        setControls();
        resourcesEarned = ResourceGetter.getResources();
    }

    public abstract void setNodes();

    public void onSlotClicked(int slot){
        ItemStack item = inventory.getItem(slot);
        if (item != null && M.matches(item, Menu.resourceMenu)){
            game.viewMainMenu(player);
            return;
        } else if (item != null && M.matches(item, Menu.resourceMiniGameRestart)){
            restart();
            game.reload(inventory, player);
        }
        for (ResourceNode node: nodes){
            if (node.slot == slot){
                node.onHit(player);
                break;
            }
        }
        if (isFinished()){
            setFinishedInventory();
            game.reload(inventory, player);
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

    public Inventory getDefaultInventory(String name){
        return Bukkit.createInventory(null, 54, name);
    }

    public void setControls(){
        inventory.setItem(53, Menu.resourceMenu);
        if (isFinished()){
            inventory.setItem(52, Menu.resourceMiniGameRestart);
        }
    }

    public Inventory getDefaultInventory(){
        return getDefaultInventory(name);
    }

    public boolean isFinished(){
        for (ResourceNode node: nodes){
            if (!node.isFinished()){
                return false;
            }
        }
        return true;
    };

    public void addResource(Res res, int amount){
        game.resources.get(res).addAmount(amount);
        resourcesEarned.get(res).addAmount(amount);
    }

    public void setFinishedInventory(){
        inventory = getDefaultInventory("Resources Gathered");
        int slot = 0;
        for (Res res: Res.values()){
            int amount = resourcesEarned.get(res).amount;
            if (amount > 0){
                ItemStack item = resourcesEarned.get(res).item.clone();
                item.setAmount(resourcesEarned.get(res).amount);
                inventory.setItem(slot, item);
                slot++;
            }
        }
        setControls();
    }
}
