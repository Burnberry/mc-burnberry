package noppe.minecraft.burnberry.resourcegame.crafting;

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    public List<Recipe> recipes;

    public Recipes(){
        recipes = new ArrayList<>();
        recipes.add(new ArrowRecipe());
    }
}
