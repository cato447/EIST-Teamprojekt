package spoonaccular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_by_ingredient.RecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;

import java.io.IOException;
import java.net.URL;

@Component
public class RecipeInformation {

    private final Dotenv dotenv;

    public RecipeInformation(){
        dotenv = Dotenv.load();
    }

    public Recipe getRecipeFromId(int id) throws IOException {
        String urlString = dotenv.get("SPOONACCULAR_API_URL") + "/recipes/" + id + "/information?apiKey="+dotenv.get("SPOONACCULAR_API_KEY")+"&includeNutrition=false";
        URL url = new URL(urlString);
        return new ObjectMapper().readValue(url, Recipe.class);
    }

    public ExtendedRecipeByIngredient getExtendedRecipeFromId(int id) throws IOException {
        String urlString = dotenv.get("SPOONACCULAR_API_URL") + "/recipes/" + id + "/information?apiKey="+dotenv.get("SPOONACCULAR_API_KEY")+"&includeNutrition=false";
        URL url = new URL(urlString);
        return new ObjectMapper().readValue(url, ExtendedRecipeByIngredient.class);
    }

    public ExtendedRecipeByIngredient getRecepieByIngredientsExtended(RecipeByIngredient recipeByIngredient) throws IOException {
        ExtendedRecipeByIngredient extendedRecipeByIngredient = getExtendedRecipeFromId(recipeByIngredient.getId());
        extendedRecipeByIngredient.setMissedIngredients(recipeByIngredient.getMissedIngredients());
        return extendedRecipeByIngredient;
    }

}
