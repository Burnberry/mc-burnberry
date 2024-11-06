package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ResourceNode {
    public MiniGame game;
    public ItemStack item;
    public int slot;
    public int health;

    public ResourceNode(MiniGame game, int slot, int health){
        this.game = game;
        this.slot = slot;
        this.health = health;
        this.setItem(getItem());
    }

    public abstract void onHit(CustomPlayer player);
    public boolean isFinished(){
        return health <= 0;
    }
    public void setItem(ItemStack item){
        this.item = item;
        game.inventory.setItem(slot, item);
    }
    public abstract ItemStack getItem();
    public void playSound(CustomPlayer player, Sound sound){
        Player p = player.playerWrapped;
        p.playSound(p, sound, 1, 1);
    }
    public void remove(){
        setItem(null);
        game.nodes.remove(this);
    }
}
