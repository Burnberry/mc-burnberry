package noppe.minecraft.burnberry.resourcegame.minigames;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.nodes.IronNode;
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
    public MiningGame(ResourceGame game, CustomPlayer player) {
        super(game, player, "Mines");
    }

    @Override
    public void setNodes() {
        nodes = new ArrayList<>();
        int x = ThreadLocalRandom.current().nextInt(1, 8);
        int y = ThreadLocalRandom.current().nextInt(1, 5);
        int p = ThreadLocalRandom.current().nextInt(0, 4);
        int slot = 9*y + x;
        if (p == 0){
            nodes.add(new IronNode(this, slot, 5));
        } else{
            nodes.add(new StoneNode(this, slot, 3));
        }
        nodes.add(new StoneNode(this, slot+1, 1));
        nodes.add(new StoneNode(this, slot-1, 1));
        nodes.add(new StoneNode(this, slot+9, 1));
        nodes.add(new StoneNode(this, slot-9, 1));
    }

    @Override
    public void viewMinigame() {
        game.viewMines(player);
    }
}
