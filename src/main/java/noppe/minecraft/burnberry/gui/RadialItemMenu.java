package noppe.minecraft.burnberry.gui;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.location.Loc;
import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class RadialItemMenu {
    public CustomPlayer player;
    public List<ItemStack> items;
    public List<ItemDisplay> displays;
    public Location location;
    public Vector direction;
    float radius = 1f;
    float scale = 0.5f;

    public RadialItemMenu(CustomPlayer player, List<ItemStack> items){
        this.player = player;
        this.items = items;
        location = player.playerWrapped.getEyeLocation();
        direction = location.getDirection();
        createMenu();
    }

    private void createMenu() {
        displays = new ArrayList<>();
        int n = items.size();
        for (int i=0; i<n; i++){
            M.print("Create display");
            double alpha = i*2*Math.PI/n;
            float x = (float) Math.sin(alpha)*radius;
            float y = (float) Math.cos(alpha)*radius;
            ItemDisplay display = (ItemDisplay) M.spawnEntity(player, location, ItemDisplay.class);
            display.setItemStack(items.get(i));
            Transformation transform = new Transformation(new Vector3f(x, y, radius), new AxisAngle4f(0f, 0f, 0f, 0f), new Vector3f(scale, scale, scale), new AxisAngle4f(0f, 0f, 0f, 0f));
            display.setTransformation(transform);
            displays.add(display);
        }
    }
}
