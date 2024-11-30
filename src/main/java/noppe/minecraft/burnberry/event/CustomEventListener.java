package noppe.minecraft.burnberry.event;

import noppe.minecraft.burnberry.event.events.*;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class CustomEventListener {
    public void onEntityDeath(EntityDeathEvent event, EventEntityDeath ev){}
    public void onPlayerDeath(PlayerDeathEvent event, EventPlayerDeath ev){}
//    public void onEntityRemove(EntityRemoveEvent event, EventEntityRemove ev){}
//    public void onEntityDamage(EntityDamageEvent event, EventEntityDamage ev){}
    public void onPlayerInteract(PlayerInteractEvent event, EventPlayerInteract ev){}
    public void onInventoryClick(InventoryClickEvent event, EventInventoryClick ev){}
    public void onPlayerJoin(PlayerJoinEvent event, EventPlayerJoin ev){}
    public void onPlayerShootBow(EntityShootBowEvent event, EventPlayerShootBow ev){}
}
