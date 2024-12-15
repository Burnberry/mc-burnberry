package noppe.minecraft.burnberry.resourcegame.minigames;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.MiniGame;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.minigames.generation.Gen;
import noppe.minecraft.burnberry.resourcegame.minigames.generation.Point;
import noppe.minecraft.burnberry.resourcegame.nodes.IronNode;
import noppe.minecraft.burnberry.resourcegame.nodes.StoneNode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MiningGame extends MiniGame {
    public MiningGame(ResourceGame game, CustomPlayer player) {
        super(game, player, "Mines");
    }

    @Override
    public void _setNodes() {
        for (Point point: Gen.generateNodes(9, 5, 1, 1, .2, .6)){
            if (point.k < 0){
                continue;
            }
            int slot = 9*point.y + point.x;
            if (nodes.get(slot) != null){
                continue;
            }
            nodes.set(slot, new StoneNode(this, slot, 5));
        }
        for (int i = 0; i<nodes.size(); i++){
            if (nodes.get(i) == null){
                nodes.set(i, new StoneNode(this, i, 1));
            }
        }
//        nodes = new ArrayList<>();
//        int x = ThreadLocalRandom.current().nextInt(1, 8);
//        int y = ThreadLocalRandom.current().nextInt(1, 4);
//        int p = ThreadLocalRandom.current().nextInt(0, 4);
//        int slot = 9*y + x;
//        if (p == 0){
//            nodes.add(new IronNode(this, slot, 5));
//        } else{
//            nodes.add(new StoneNode(this, slot, 3));
//        }
//        nodes.add(new StoneNode(this, slot+1, 1));
//        nodes.add(new StoneNode(this, slot-1, 1));
//        nodes.add(new StoneNode(this, slot+9, 1));
//        nodes.add(new StoneNode(this, slot-9, 1));
    }

    @Override
    public void viewMinigame() {
        game.viewMines(player);
    }
}
