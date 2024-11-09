package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
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
import java.util.List;

public class ResourceGame extends CustomEventListener {
    public CustomPlayer player;
    public Dictionary<Res, GameResource> resources;
    public Upgrades upgrades;
    public List<Upgrade> upgradeSlotMap;
    public Inventory upgradeInventory;
    public Inventory menuInventory;

    public ItemStack resourcesAction = new ItemStack(Material.BARREL);
    public ItemStack upgradesAction = new ItemStack(Material.EMERALD);
    public ItemStack forestAction = new ItemStack(Material.STONE_AXE);
    public ItemStack minesAction = new ItemStack(Material.STONE_PICKAXE);

    public ForageGame forageGame;
    public MiningGame miningGame;

    public ResourceGame(CustomPlayer player){
        this.player = player;
        restart();
    }

    public void restart(){
        upgrades = new Upgrades();
        resources = ResourceGetter.getResources();
        miningGame = new MiningGame(this);
        forageGame = new ForageGame(this);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev) {
        super.onInventoryClick(event, ev);
        Inventory inventory = event.getClickedInventory();
        int slot = event.getSlot();
        if (menuInventory == inventory){
            onMenuClick(slot);
        } else if (miningGame.getInventory() == inventory){
            miningGame.onSlotClicked(slot);
        } else if (forageGame.getInventory() == inventory){
            forageGame.onSlotClicked(slot);
        } else if (upgradeInventory == inventory && slot < upgradeSlotMap.size() && upgradeSlotMap.get(slot).canBuy(this)) {
            upgradeSlotMap.get(event.getSlot()).buy(this);
            viewUpgrades();
        }
    }

    public void onMenuClick(int slot){
        if (slot == 12){
            viewForest();
        }
        if (slot == 13){
            viewMines();
        }
        if (slot == 21){
            viewResources();
        }
        if (slot == 22){
            viewUpgrades();
        }
    }

    public Inventory getFinishedInventory(){
        Inventory inventory = Bukkit.createInventory(null, 54, "Menu");
        inventory.setItem(12, forestAction);
        inventory.setItem(13, minesAction);
        inventory.setItem(21, resourcesAction);
        inventory.setItem(22, upgradesAction);
        return inventory;
    }

    public void reload(Inventory inventory){
        player.playerWrapped.openInventory(inventory);
    }

    public void viewMainMenu(){
        menuInventory = getFinishedInventory();
        reload(menuInventory);
    }

    public void viewResources(){
        Inventory inventory = Bukkit.createInventory(null, 54, "Resources");
        int slot = 0;
        for (Res res: Res.values()){
            if (resources.get(res).amount > 0){
                inventory.setItem(slot, resources.get(res).item);
                slot++;
            }
        }
        reload(inventory);
    }

    public void viewUpgrades(){
        Inventory inventory = Bukkit.createInventory(null, 54, "Upgrades");
        upgradeSlotMap = new ArrayList<>();
        int slot = 0;
        for (Upgrade upgrade: upgrades.upgrades){
            if (upgrade.isAvailable()){
                inventory.setItem(slot, upgrade.getItem());
                upgradeSlotMap.add(upgrade);
                slot++;
            }
        }
        upgradeInventory = inventory;
        reload(inventory);
    }

    public void viewForest(){
        reload(forageGame.getInventory());
    }

    public void viewMines(){
        reload(miningGame.getInventory());
    }
}
