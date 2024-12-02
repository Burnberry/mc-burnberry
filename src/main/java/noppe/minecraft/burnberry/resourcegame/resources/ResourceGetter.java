package noppe.minecraft.burnberry.resourcegame.resources;

import noppe.minecraft.burnberry.resourcegame.GameResource;

import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceGetter {
    public static Dictionary<Res, GameResource> getDefaultResources(){
        Dictionary<Res, GameResource> resources = getResources();
        resources.get(Res.STONE).addAmount(100);
        resources.get(Res.WOOD).addAmount(100);
        resources.get(Res.IRON).addAmount(10);
        resources.get(Res.SOUL).addAmount(100);
        resources.get(Res.ARROWS).addAmount(2400);
        return resources;
    }

    public static Dictionary<Res, GameResource> getResources(){
        Dictionary<Res, GameResource> resources = new Hashtable<>();
        resources.put(Res.STONE, new StoneResource(0));
        resources.put(Res.WOOD, new WoodResource(0));
        resources.put(Res.IRON, new IronResource(0));
        resources.put(Res.SOUL, new SoulResource(0));
        resources.put(Res.ARROWS, new ArrowResource(0));
        return resources;
    }
}
