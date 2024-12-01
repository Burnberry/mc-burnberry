package noppe.minecraft.burnberry.resourcegame.util;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Spendable {
    public static ResourceGame game;

    public List<CostPair> costs = new ArrayList<>();

    public Spendable(){
        update();
    }

    public void update(){
        updateCosts();
    }

    public boolean canBuy(ResourceGame game, int amount){
        for (CostPair pair: costs){
            if (game.resources.get(pair.first).amount < pair.second*amount){
                return false;
            }
        }
        return true;
    }

    public boolean canBuy(ResourceGame game){
        return canBuy(game, 1);
    }

    public int amountCanBuy(ResourceGame game){
        int amount = Integer.MAX_VALUE;
        for (CostPair pair: costs){
            amount = Math.min(amount, game.resources.get(pair.first).amount/pair.second);
        }
        return amount;
    }

    public boolean buy(ResourceGame game){
        if (canBuy(game) && isAvailable()){
            _buy(game);
            return true;
        }
        return false;
    }

    public void _buy(ResourceGame game){
        for (CostPair pair: costs){
            game.resources.get(pair.first).addAmount(-pair.second);
        }
        onBuy(game);
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

    public List<CostPair> getCosts(){
        return costs;
    }

    public abstract void onBuy(ResourceGame game);
    public abstract void updateCosts();
    protected abstract ItemStack _getItem();
    public abstract String getName();
    public abstract boolean isAvailable();
}
