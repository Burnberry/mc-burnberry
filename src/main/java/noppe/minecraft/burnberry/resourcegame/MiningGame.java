package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MiningGame extends MiniGame{
    public ItemStack cobble = new ItemStack(Material.COBBLESTONE);
    public ItemStack stone = new ItemStack(Material.STONE);

    public MiningGame(ResourceGame game) {
        super(game);
        int x = ThreadLocalRandom.current().nextInt(1, 8);
        int y = ThreadLocalRandom.current().nextInt(1, 6);
        x = 9*y + x;
        nodes.set(x, 3);
        nodes.set(x-1, 1);
        nodes.set(x+1, 1);
        nodes.set(x+9, 1);
        nodes.set(x-9, 1);
    }

    @Override
    public void onSlotClicked(int slot) {
        if (isFinished() && slot == 22){
            game.miningGame = new MiningGame(game);
        }

        if (nodes.get(slot) > 0){
            nodes.set(slot, nodes.get(slot) - 1);
            Player p = game.player.playerWrapped;
            p.playSound(p.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
        }
        game.reload();
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Mines");
        if (isFinished()){
            return getFinishedInventory();
        }
        for (int i=0; i<nodes.size(); i++){
            int node = nodes.get(i);
            if (node == 1){
                inventory.setItem(i, cobble);
            } else if (node > 1) {
                inventory.setItem(i, stone);
            }
        }
        return inventory;
    }

    @Override
    public boolean isFinished() {
        for (Integer node : nodes) {
            if (node > 0) {
                return false;
            }
        }
        return true;
    }
}
