package noppe.minecraft.burnberry.resourcegame.research;

public class ResearchTimer {
    public Research research;
    public int workTime;
    public boolean researched=false;

    public ResearchTimer(Research research){
        this.research = research;
        workTime = research.workTime;
    }

    public void onTick(){
        workTime -= research.workRate;
        if (workTime <= 0 && !researched){
            researched = true;
            research.onResearched();
        }
    }
}
