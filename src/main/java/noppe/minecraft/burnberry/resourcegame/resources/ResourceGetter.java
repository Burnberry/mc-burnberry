package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;

import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceGetter {
    public static Dictionary<Res, GameResource> getResources(){
        Dictionary<Res, GameResource> resources = new Hashtable<>();
        resources.put(Res.STONE, new StoneResource(100));
        resources.put(Res.WOOD, new WoodResource(100));
        resources.put(Res.IRON, new IronResource(10));
        resources.put(Res.SOUL, new SoulResource(100));
        resources.put(Res.ARROWS, new ArrowResource(24));
        return resources;
    }
}
