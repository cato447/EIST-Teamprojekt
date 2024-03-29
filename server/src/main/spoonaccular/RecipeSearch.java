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
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_by_ingredient.RecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;
import whattocook.models.Item;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class RecipeSearch {
    private static final boolean IGNOREPANTRY = true;

    private static final Random rnd = new Random();
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

    private static final OkHttpClient client = new OkHttpClient();

    private RecipeSearch(){
    }

    public static List<ExtendedRecipeByIngredient> getForIngridients(Iterable<Item> items, int number) throws java.io.IOException {
        List<String> itemNames = new LinkedList<>();
        items.forEach(item -> itemNames.add(item.getName()));
        String ingridients = String.join(",", itemNames);

        Request request =
                APIAuthentication.addAuthHeaders(new Request.Builder()
                .url("https://" + dotenv.get("X-RapidAPI-Host") +
                        "/recipes/findByIngredients?ingredients="
                        + ingridients + "&number=" + number + "&ignorePantry="
                        + IGNOREPANTRY + "&ranking=" + dotenv.get("X-RapidAPI-SearchRanking")))
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();

        List<RecipeByIngredient> recipeByIngredients = new ObjectMapper().readValue(responseString, new TypeReference<>(){});
        return RecipeInformation.getRecepieByIngredientsExtended(recipeByIngredients);
    }

    public static ExtendedRecipeByIngredient getOneForIngridients(Iterable<Item> items, int number) throws IOException {
        return getForIngridients(items, number).get(rnd.nextInt(number));
    }

    public static List<Recipe> getRandom(List<String> tags, int number) throws java.io.IOException, JSONException {
        String tagString = String.join(",", tags);
        Request request = APIAuthentication.addAuthHeaders(new Request.Builder()
                .url("https://" + dotenv.get("X-RapidAPI-Host") +
                                "/recipes/random?number=" + number + "&tags=" + tagString))
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return new ObjectMapper().readValue(jsonObject.getJSONArray("recipes").toString(), new TypeReference<>(){});
    }
}
