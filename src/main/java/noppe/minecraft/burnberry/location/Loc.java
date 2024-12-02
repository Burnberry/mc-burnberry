package noppe.minecraft.burnberry.location;

import noppe.minecraft.burnberry.Burnberry;
import org.bukkit.Location;
import org.bukkit.World;

public class Loc {
    static Burnberry burnberry;
    public static World world;

    public static Location spawn;

    public static Location tower;
    public static Location monsterSpawn;
    public static Location golemSpawn;
    public static Location anchor2;

    public static void setPlugin(Burnberry burnberry){
        Loc.burnberry = burnberry;
        Loc.world = burnberry.getServer().getWorld("world");

        Loc.spawn = new Location(Loc.world, 0.5, 100, 0.5);

        Loc.tower = new Location(Loc.world, 0.5, 100, 0.5);
//        Loc.tower = new Location(Loc.world, 42.5, 80, -156.5);
        Loc.monsterSpawn = new Location(Loc.world, 50.5, 100, 0.5);
        Loc.golemSpawn = new Location(Loc.world, 49.5, 100, 0.5);
        Loc.anchor2 = new Location(Loc.world, 20, 99.5, 0.5);
    }
}
