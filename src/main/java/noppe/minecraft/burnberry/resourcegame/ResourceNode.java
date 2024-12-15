package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.resources.Res;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ResourceNode {
    public MiniGame game;
    public ItemStack item;
    public int slot;
    public int health;
    public int damageCount=0;

    public int rewardCount=1;
    public Res rewardRes;
    public int DamagePerResource;
    public Res damageRes;

    public ResourceNode(MiniGame game, int slot, int health){
        this.game = game;
        this.slot = slot;
        this.health = health;
        setItem(getItem());
    }

    public abstract void onHit(CustomPlayer player);
    public int onDamage(int damage){
        damage = Math.min(health, damage);
        damageCount += damage;
        health -= damage;
        receiveBaseRewards();
        return damage;
    }
    public void receiveBaseRewards(){
        if (DamagePerResource <= 0 || damageRes == null){
            return;
        }
        int count = damageCount/DamagePerResource;
        damageCount %= DamagePerResource;
        game.addResource(damageRes, count);
    }
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

        if (rewardRes != null){
            game.addResource(rewardRes, rewardCount);
        }
    }
}
