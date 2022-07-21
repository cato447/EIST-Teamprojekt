package spoonaccular.models.recipe_by_ingredient;

import com.fasterxml.jackson.annotation.JsonProperty;
import spoonaccular.models.recipe_information.Recipe;

import java.util.List;

public class ExtendedRecipeByIngredient extends Recipe {

    @JsonProperty("usedIngredientCount")
    private Integer usedIngredientCount;
    @JsonProperty("missedIngredientCount")
    private Integer missedIngredientCount;
    @JsonProperty("missedIngredients")
    private List<MissedIngredient> missedIngredients = null;
    @JsonProperty("usedIngredients")
    private List<UsedIngredient> usedIngredients = null;

    @JsonProperty("usedIngredientCount")
    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    @JsonProperty("usedIngredientCount")
    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    @JsonProperty("missedIngredients")
    public List<MissedIngredient> getMissedIngredients() {
        return missedIngredients;
    }

    @JsonProperty("missedIngredients")
    public void setMissedIngredients(List<MissedIngredient> missedIngredients) {
        this.missedIngredients = missedIngredients;
    }

    @JsonProperty("usedIngredients")
    public List<UsedIngredient> getUsedIngredients() {
        return usedIngredients;
    }

    @JsonProperty("usedIngredients")
    public void setUsedIngredients(List<UsedIngredient> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    public void addMissingInfo(RecipeByIngredient recipeByIngredient){
        this.setMissedIngredientCount(recipeByIngredient.getMissedIngredientCount());
        this.setUsedIngredientCount(recipeByIngredient.getUsedIngredientCount());
        this.setMissedIngredients(recipeByIngredient.getMissedIngredients());
        this.setUsedIngredients(recipeByIngredient.getUsedIngredients());
    }
}
