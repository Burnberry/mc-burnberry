package noppe.minecraft.burnberry.resourcegame;

import noppe.minecraft.burnberry.resourcegame.research.ResearchQuiver;
import noppe.minecraft.burnberry.resourcegame.research.Research;
import noppe.minecraft.burnberry.resourcegame.crafting.ArrowRecipe;
import noppe.minecraft.burnberry.resourcegame.crafting.Recipe;
import noppe.minecraft.burnberry.resourcegame.upgrades.Upgrade;
import noppe.minecraft.burnberry.resourcegame.upgrades.UpgradePickaxe;

import java.util.ArrayList;
import java.util.List;

public class Upgrades {
    public List<Upgrade> upgrades;
    public List<Recipe> recipes;
    public List<Research> researchList;

    public Research activeResearch;

    public int arrowCapacity = 12;
    public int pickaxePower = 1;

    public Upgrades(){
        addUpgrades();
        addRecipes();
        addResearch();
    }

    public void addUpgrades(){
        upgrades = new ArrayList<>();
        upgrades.add(new UpgradePickaxe());
    }

    public void addResearch(){
        researchList = new ArrayList<>();
        researchList.add(new ResearchQuiver());
    }

    public void addRecipes(){
        recipes = new ArrayList<>();
        recipes.add(new ArrowRecipe());
    }
}
