package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.crafting.Recipe;
import noppe.minecraft.burnberry.resourcegame.crafting.Recipes;
import noppe.minecraft.burnberry.resourcegame.minigames.ForageGame;
import noppe.minecraft.burnberry.resourcegame.minigames.MiningGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import noppe.minecraft.burnberry.resourcegame.resources.ResourceGetter;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrade;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrades;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ResourceGame extends CustomEventListener {
    public DefenseGame game;
    public Dictionary<Res, GameResource> resources;

    public Upgrades upgrades;
    public List<Upgrade> upgradeSlotMap;
    public Inventory upgradeInventory;

    public Recipes recipes;
    public List<Recipe> recipeSlotMap;
    public Inventory recipeInventory;

    public Inventory menuInventory;

    public ItemStack resourcesAction = new ItemStack(Material.BARREL);
    public ItemStack upgradesAction = new ItemStack(Material.EMERALD);
    public ItemStack recipesAction = new ItemStack(Material.CRAFTING_TABLE);
    public ItemStack forestAction = new ItemStack(Material.STONE_AXE);
    public ItemStack minesAction = new ItemStack(Material.STONE_PICKAXE);

    public Dictionary<CustomPlayer, ForageGame> forageGames;
    public Dictionary<CustomPlayer, MiningGame> miningGames;

    public ResourceGame(DefenseGame game){
        this.game = game;
        restart();
    }

    public void restart(){
        upgrades = new Upgrades();
        recipes = new Recipes();
        resources = ResourceGetter.getResources();
        forageGames = new Hashtable<>();
        miningGames = new Hashtable<>();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev) {
        super.onInventoryClick(event, ev);
        Inventory inventory = event.getClickedInventory();
        int slot = event.getSlot();
        if (menuInventory == inventory){
            onMenuClick(slot, ev.player);
        } else if (miningGames.get(ev.player) != null && miningGames.get(ev.player).getInventory() == inventory){
            miningGames.get(ev.player).onSlotClicked(slot);
        } else if (forageGames.get(ev.player) != null && forageGames.get(ev.player).getInventory() == inventory){
            forageGames.get(ev.player).onSlotClicked(slot);
        } else if (upgradeInventory == inventory && slot < upgradeSlotMap.size() && upgradeSlotMap.get(slot).canBuy(this)) {
            upgradeSlotMap.get(event.getSlot()).buy(this);
            viewUpgrades(ev.player);
        } else if (recipeInventory == inventory && slot < recipeSlotMap.size() && recipeSlotMap.get(slot).canBuy(this)) {
            recipeSlotMap.get(event.getSlot()).buy(this);
            viewRecipes(ev.player);
        }
    }

    public void onMenuClick(int slot, CustomPlayer player){
        if (slot == 12){
            viewForest(player);
        } else if (slot == 13){
            viewMines(player);
        } else if (slot == 21){
            viewResources(player);
        } else if (slot == 22){
            viewUpgrades(player);
        } else if (slot == 23){
            viewRecipes(player);
        }
    }

    public Inventory getFinishedInventory(){
        Inventory inventory = Bukkit.createInventory(null, 54, "Menu");
        inventory.setItem(12, forestAction);
        inventory.setItem(13, minesAction);
        inventory.setItem(21, resourcesAction);
        inventory.setItem(22, upgradesAction);
        inventory.setItem(23, recipesAction);
        return inventory;
    }

    public void reload(Inventory inventory, CustomPlayer player){
        player.playerWrapped.openInventory(inventory);
    }

    public void viewMainMenu(CustomPlayer player){
        menuInventory = getFinishedInventory();
        reload(menuInventory, player);
    }

    public void viewResources(CustomPlayer player){
        Inventory inventory = Bukkit.createInventory(null, 54, "Resources");
        int slot = 0;
        for (Res res: Res.values()){
            if (resources.get(res).amount > 0){
                inventory.setItem(slot, resources.get(res).item);
                slot++;
            }
        }
        reload(inventory, player);
    }

    public void viewUpgrades(CustomPlayer player){
        upgradeInventory = Bukkit.createInventory(null, 54, "Upgrades");
        upgradeSlotMap = new ArrayList<>();
        int slot = 0;
        for (Upgrade upgrade: upgrades.upgrades){
            if (upgrade.isAvailable()){
                upgradeInventory.setItem(slot, upgrade.getItem());
                upgradeSlotMap.add(upgrade);
                slot++;
            }
        }
        reload(upgradeInventory, player);
    }

    public void viewRecipes(CustomPlayer player){
        recipeInventory = Bukkit.createInventory(null, 54, "Recipes");
        recipeSlotMap = new ArrayList<>();
        int slot = 0;
        for (Recipe recipe: recipes.recipes){
            if (recipe.isAvailable()){
                recipeInventory.setItem(slot, recipe.getItem());
                recipeSlotMap.add(recipe);
                slot++;
            }
        }
        reload(recipeInventory, player);
    }

    public void viewForest(CustomPlayer player){
        if (forageGames.get(player) == null){
            forageGames.put(player, new ForageGame(this, player));
        }
        reload(forageGames.get(player).getInventory(), player);
    }

    public void viewMines(CustomPlayer player){
        if (miningGames.get(player) == null){
            miningGames.put(player, new MiningGame(this, player));
        }
        reload(miningGames.get(player).getInventory(), player);
    }
}
