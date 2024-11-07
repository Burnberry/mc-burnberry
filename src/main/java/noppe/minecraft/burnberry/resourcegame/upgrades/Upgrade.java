package noppe.minecraft.burnberry.resourcegame.upgrades;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Upgrade {
    public int level;
    public List<CostPair> costs = new ArrayList<>();

    public Upgrade(){
        level = 0;
        costs = getCosts();
    }

    public boolean canBuy(ResourceGame game){
        for (CostPair pair: costs){
            if (game.resources.get(pair.first).amount < pair.second){
                return false;
            }
        }
        return true;
    }

    public void buy(ResourceGame game){
        for (CostPair pair: costs){
            game.resources.get(pair.first).addAmount(-pair.second);
        }
        onBuy(game);
        level += 1;
        costs = getCosts();
    }

    public String[] getCostText(){
        String[] text = new String[costs.size()];
        int i = 0;
        for (CostPair pair: costs){
            text[i++] = pair.first.toString() + ": " + pair.second;
        }
        return text;
    }

    public ItemStack getItem(){
        ItemStack item = _getItem();
        M.setLore(item, getCostText());
        return item;
    }

    public abstract void onBuy(ResourceGame game);
    public abstract List<CostPair> getCosts();
    protected abstract ItemStack _getItem();
    public abstract boolean isAvailable();
}
