package noppe.minecraft.burnberry.event;

import noppe.minecraft.burnberry.Burnberry;
import noppe.minecraft.burnberry.event.mappers.*;

import java.util.ArrayList;
import java.util.List;

public class CustomEventHandler {
    Burnberry burnberry;
    List<CustomEventMapper> arenaEventMappers;

    public CustomEventHandler(Burnberry plugin){
        burnberry = plugin;
        this.arenaEventMappers = new ArrayList<>();

//        this.arenaEventMappers.add(new MapperOnTest(this.arena));
        this.arenaEventMappers.add(new BasicMapper(burnberry));
        this.arenaEventMappers.add(new DisableEvents(burnberry));
//
//        // player events
        this.arenaEventMappers.add(new MapperOnPlayerJoin(burnberry));
        this.arenaEventMappers.add(new MapperOnPlayerInteract(burnberry));

//        // inventory events
        this.arenaEventMappers.add(new MapperOnInventoryClick(burnberry));
//
//        // entity events
        this.arenaEventMappers.add(new MapperOnEntityTarget(burnberry));
        this.arenaEventMappers.add(new MapperOnEntityDeath(burnberry));
        this.arenaEventMappers.add(new MapperOnPlayerDeath(burnberry));
        this.arenaEventMappers.add(new MapperOnEntityShootBow(burnberry));
//        this.arenaEventMappers.add(new MapperOnEntityRemove(burnberry));
        this.arenaEventMappers.add(new MapperOnEntityDamage(burnberry));

        // extras
        arenaEventMappers.add(new MapperOnProjectileHit(burnberry));

        this.registerEvents();
    }

    public void registerEvents(){
        for (CustomEventMapper mapper: this.arenaEventMappers){
            burnberry.getServer().getPluginManager().registerEvents(mapper, burnberry);
        }
    }
}
