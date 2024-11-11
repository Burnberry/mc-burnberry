package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;

import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceGetter {
    public static Dictionary<Res, GameResource> getResources(){
        Dictionary<Res, GameResource> resources = new Hashtable<>();
        resources.put(Res.STONE, new StoneResource());
        resources.put(Res.WOOD, new WoodResource());
        resources.put(Res.IRON, new IronResource());
        resources.put(Res.SOUL, new SoulResource());
        return resources;
    }
}
