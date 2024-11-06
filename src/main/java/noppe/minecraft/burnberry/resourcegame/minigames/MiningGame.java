package noppe.minecraft.burnberry.resourcegame.minigames;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.nodes.StoneNode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MiningGame extends MiniGame {
    public ItemStack cobble = new ItemStack(Material.COBBLESTONE);
    public ItemStack stone = new ItemStack(Material.STONE);

    public MiningGame(ResourceGame game) {
        super(game, "Mines");
    }

    @Override
    public void setNodes() {
        nodes = new ArrayList<>();
        int x = ThreadLocalRandom.current().nextInt(1, 8);
        int y = ThreadLocalRandom.current().nextInt(1, 5);
        int slot = 9*y + x;
        nodes.add(new StoneNode(this, slot, 3));
        nodes.add(new StoneNode(this, slot+1, 1));
        nodes.add(new StoneNode(this, slot-1, 1));
        nodes.add(new StoneNode(this, slot+9, 1));
        nodes.add(new StoneNode(this, slot-9, 1));
    }
}
