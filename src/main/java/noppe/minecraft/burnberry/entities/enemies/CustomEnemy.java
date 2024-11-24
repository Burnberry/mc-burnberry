package noppe.minecraft.burnberry.entities.enemies;

import noppe.minecraft.burnberry.entities.CustomEntity;
import noppe.minecraft.burnberry.event.CustomEventListener;
import org.bukkit.entity.*;

public class CustomEnemy extends CustomEntity {
    public CustomEnemy(CustomEventListener origin, Mob enemy){
        super(origin, enemy);
        enemy.setRemoveWhenFarAway(false);
    }

    public Mob getMob(){
        return (Mob) entity;
    }

    public Boolean isEnemy(){
        return true;
    }

    public void setTarget(LivingEntity entity){
        ((Mob) getMob()).setTarget(entity);
    }
}
