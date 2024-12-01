package noppe.minecraft.burnberry.resourcegame.research;

import noppe.minecraft.burnberry.helpers.M;
import noppe.minecraft.burnberry.resourcegame.ResourceGame;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrade;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public abstract class Research extends Upgrade {
    public int workTime;
    public int workRate;
    public ResearchTimer timer;

    public Research(int workTime, int workRate){
        super();
        this.workTime = workTime;
        this.workRate = workRate;
    }

    @Override
    public boolean canBuy(ResourceGame game, int amount) {
        return super.canBuy(game, amount) && game.upgrades.activeResearch == null;
    }

    @Override
    public void onBuy(ResourceGame game) {
        timer = new ResearchTimer(this);
        game.upgrades.activeResearch = this;
    }

    @Override
    public boolean isAvailable() {
        return timer == null && _isAvailable();
    }

    protected abstract boolean _isAvailable();

    public void onResearched(){
        timer = null;
        game.upgrades.activeResearch = null;
        level += 1;
        M.print("Finished Research: " + getName());
        _onResearched();
    }

    protected abstract void _onResearched();

    @Override
    public ItemStack getItem() {
        ItemStack item = super.getItem();
        if (timer != null){
            item.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            M.setItemName(item, getName() + " " + (timer.workTime/workRate)/20 + 's');
        }
        return item;
    }

//    Update level on research finish
    @Override
    public void onBuyUpdateLevel() {}
}
