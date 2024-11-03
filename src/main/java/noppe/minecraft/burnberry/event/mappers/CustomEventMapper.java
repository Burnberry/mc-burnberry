package noppe.minecraft.burnberry.event.mappers;

import noppe.minecraft.burnberry.Burnberry;
import org.bukkit.event.Listener;

public class CustomEventMapper implements Listener {
    Burnberry burnberry;

    public CustomEventMapper(Burnberry plugin){
        burnberry = plugin;
    }
}
