package noppe.minecraft.burnberry.location;

import noppe.minecraft.burnberry.Burnberry;
import org.bukkit.Location;
import org.bukkit.World;

public class Loc {
    static Burnberry burnberry;
    static World world;

    public static Location spawn;


    public static void setPlugin(Burnberry burnberry){
        Loc.burnberry = burnberry;
        Loc.world = burnberry.getServer().getWorld("world");

        Loc.spawn = new Location(Loc.world, 0.5, 100, 0.5);
    }
}
