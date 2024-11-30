package noppe.minecraft.burnberry.defensegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;
import noppe.minecraft.burnberry.helpers.M;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DefensePlayerState {
    public CustomPlayer player;
    public int arrowCount;
    public int arrowCapacity;

    public DefensePlayerState(CustomPlayer player){
        this.player = player;
        arrowCapacity = 12;
        arrowCount = arrowCapacity;
        updateWeapon();
    }

    public boolean hasArrow(){
        return arrowCount > 0;
    }

    public void useArrow(){
        if (hasArrow()){
            addArrows(-1);
        }
    }

    public int addArrows(int count){
        int taken = Math.min(count, arrowCapacity - arrowCount);
        arrowCount += taken;
        updateWeapon();
        return taken;
    }

    public void updateWeapon(){
        ItemStack bow = player.playerWrapped.getInventory().getItem(0);
        if (bow != null && bow.getType() == Material.BOW){
            M.setItemName(bow, "Bow (" + arrowCount + '/' + arrowCapacity + ")");
        }
    }
}