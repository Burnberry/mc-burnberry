package noppe.minecraft.burnberry.defensegame;

import noppe.minecraft.burnberry.entities.CustomPlayer;

public class DefensePlayerState {
    public CustomPlayer player;
    public int arrowCount;
    public int arrowCapacity;

    public DefensePlayerState(CustomPlayer player){
        this.player = player;
        arrowCapacity = 7;
        arrowCount = arrowCapacity;
    }

    public boolean hasArrow(){
        return arrowCount > 0;
    }

    public void useArrow(){
        if (hasArrow()){
            arrowCount -= 1;
        }
    }

    public int addArrows(int count){
        int x = Math.min(count, arrowCapacity - arrowCount);
        arrowCount += x;
        return count - x;
    }
}
