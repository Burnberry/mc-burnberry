package noppe.minecraft.burnberry.defensegame.enemies;

import noppe.minecraft.burnberry.defensegame.DefenseGame;
import noppe.minecraft.burnberry.event.events.EventEntityDamage;
import noppe.minecraft.burnberry.event.events.EventEntityTarget;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DefenseGiantGolem extends DefenseEnemy{
    public double scale = 4;
    public double healthScale = 6;
    public double baseHealth = 5;
    public boolean defenseMode = false;

    public DefenseGiantGolem(DefenseGame game, Location location) {
        super(game, (Mob) M.spawnEntity(game, location, IronGolem.class), 1);
        setGolemSize();
        getGolem().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
        getGolem().setCollidable(false);
    }

    public IronGolem getGolem(){
        return (IronGolem) entity;
    }

    @Override
    public void onTick() {
        super.onTick();
        setTarget(game.anchor);
        for (Entity ent: M.getWorld().getNearbyEntities(getGolem().getBoundingBox())){
            if (ent instanceof Arrow){
                getGolem().damage(((Arrow) ent).getDamage(), ent);
                ent.remove();
            }
        }
        if (!defenseMode && entity.getLocation().distance(game.anchor2.getLocation()) < 1){
            setDefenseMode();
        }
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event, EventEntityDamage ev) {
        super.onEntityDamage(event, ev);
        double damage = event.getDamage();
        if (defenseMode){
            damage /= 2;
        }
        if (damage >= getGolem().getHealth()){
            damage = golemBreak(damage);
        }
        event.setDamage(damage);
    }

    @Override
    public void onEntityTarget(EntityTargetEvent event, EventEntityTarget ev) {
        event.setTarget(getTarget());
    }

    public LivingEntity getTarget(){
        if (defenseMode){
            return game.anchor;
        }
        return game.anchor;
    }

    public double golemBreak(double damage){
        return golemBreak(damage, true);
    }

    public double golemBreak(double damage, boolean playsound){
        if (playsound){
            M.playWorldSound(Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, (float) scale/4);
        }
        if (damage + 1 >= getGolem().getHealth()){
            if (scale <= 1){
                return damage;
            }
            damage -= getGolem().getHealth();
            scale = 0.9*scale - 0.2;
            setGolemSize();
            getGolem().addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 3*20, 4));
            return golemBreak(damage, false);
        }
        return damage;
    }

    public void setGolemSize(){
        getMob().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(baseHealth + scale*healthScale);
        getMob().getAttribute(Attribute.GENERIC_SCALE).setBaseValue(scale);
        getGolem().setHealth(baseHealth + scale*healthScale);
    }

    public void setDefenseMode(){
        defenseMode = true;
        getGolem().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
        setTarget(getTarget());
        game.anchor2.setPassenger(getGolem());
    }
}
