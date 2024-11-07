package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.event.events.EventInventoryClick;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.minigames.MiningGame;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import noppe.minecraft.burnberry.resourcegame.resources.ResourceGetter;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrade;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrades;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ResourceGame extends CustomEventListener {
    public CustomPlayer player;
    public MiningGame miningGame;
    public Dictionary<Res, GameResource> resources;
    public Upgrades upgrades;
    public List<Upgrade> upgradeSlotMap;
    public Inventory upgradeInventory;

    public ResourceGame(CustomPlayer player){
        this.player = player;
        restart();
    }

    public void restart(){
        upgrades = new Upgrades();
        resources = ResourceGetter.getResources();
        miningGame = new MiningGame(this);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev) {
        super.onInventoryClick(event, ev);
        Inventory inventory = event.getClickedInventory();
        int slot = event.getSlot();
        if (miningGame.getInventory() == inventory){
            miningGame.onSlotClicked(slot);
        } else if (upgradeInventory == inventory && slot < upgradeSlotMap.size() && upgradeSlotMap.get(slot).canBuy(this)) {
            upgradeSlotMap.get(event.getSlot()).buy(this);
            viewUpgrades();
        }
    }

    public void reload(){
        reload(miningGame.getInventory());
    }

    public void reload(Inventory inventory){
        player.playerWrapped.openInventory(inventory);
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
}
