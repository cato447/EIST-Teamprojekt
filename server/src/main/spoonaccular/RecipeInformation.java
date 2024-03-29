package spoonaccular;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import spoonaccular.models.ingredients_by_id.Ingredient;
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_by_ingredient.RecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecipeInformation {

    private final static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
    private final static OkHttpClient client = new OkHttpClient();

    private RecipeInformation(){
    }

    public static List<Recipe> getRecipeFromIds(List<Integer> ids) throws IOException {
        String idsString = ids.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
        return new ObjectMapper().readValue(queryInformationBulk(idsString).body().string(), new TypeReference<>(){});
    }

    public static List<ExtendedRecipeByIngredient> getExtendedRecipeFromIds(List<Integer> ids) throws IOException {
        String idsString = ids.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
        return new ObjectMapper().readValue(queryInformationBulk(idsString).body().string(), new TypeReference<>() {});
    }

    public static List<Ingredient> getIngredientList(int recipeId) throws IOException, JSONException {
        Request request = APIAuthentication.addAuthHeaders(new Request.Builder()
                .url("https://" + dotenv.get("X-RapidAPI-Host") +
                        "/recipes/" + recipeId + "/ingredientWidget.json"))
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return new ObjectMapper().readValue(jsonObject.getJSONArray("ingredients").toString(), new TypeReference<>() {});
    }

    private static Response queryInformationBulk(String idsString) throws IOException {
        Request request = APIAuthentication.addAuthHeaders(new Request.Builder()
                        .url("https://" + dotenv.get("X-RapidAPI-Host") +
                                "/recipes/informationBulk?ids=" + idsString))
                .build();
        return client.newCall(request).execute();
    }

    public static List<ExtendedRecipeByIngredient> getRecepieByIngredientsExtended(List<RecipeByIngredient> recipeByIngredients) throws IOException {
        List<Integer> ids = recipeByIngredients.stream().map(RecipeByIngredient::getId).toList();
        List<ExtendedRecipeByIngredient> extendedRecipeByIngredients = getExtendedRecipeFromIds(ids);
        Iterator<RecipeByIngredient> recipeByIngredientIterator = recipeByIngredients.iterator();
        Iterator<ExtendedRecipeByIngredient> extendedRecipeByIngredientIterator = extendedRecipeByIngredients.iterator();
        while(recipeByIngredientIterator.hasNext() && extendedRecipeByIngredientIterator.hasNext()){
            extendedRecipeByIngredientIterator.next().addMissingInfo(recipeByIngredientIterator.next());
        }
        return extendedRecipeByIngredients;
    }

}
