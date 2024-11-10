package noppe.minecraft.burnberry.entities;

import noppe.minecraft.burnberry.event.CustomEventListener;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.entity.Entity;

public class CustomEntity extends CustomEventListener{
    public Entity entity;
    public CustomEventListener origin;

    public CustomEntity(CustomEventListener origin, Entity entity){
        this.origin = origin;
        this.entity = entity;
        M.setMetaData(entity, "wrapper", this);
    }

    public void remove(){
        this.entity.remove();
    }

    public String getType(){
        return "base";
    }

    public Boolean isEnemy(){
        return false;
    }

    public Boolean isPlayer(){
        return false;
    }
}
