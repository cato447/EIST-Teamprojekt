package spoonaccular;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import spoonaccular.models.recipe_by_ingredient.RecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;
import whattocook.models.Item;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class RecipeSearch {
    private static final boolean IGNOREPANTRY = true;

    private final Random rnd;
    private final Dotenv dotenv;

    public RecipeSearch(){
        rnd = new Random();
        dotenv = Dotenv.load();
    }

    public RecipeByIngredient[] getForIngridients(Iterable<Item> items, int number) throws java.io.IOException {
        List<String> itemNames = new LinkedList<>();
        items.forEach(item -> itemNames.add(item.getName()));
        String ingridients = String.join(",", itemNames);
        String urlString = dotenv.get("SPOONACCULAR_API_URL") + "/recipes/findByIngredients?apiKey=" + dotenv.get("SPOONACCULAR_API_KEY") + "&ingredients=" + ingridients + "&ranking=" + dotenv.get("SPOONACCULAR_API_SEARCH_RANKING") + "&ignorePantry=" + IGNOREPANTRY + "&number=" + number;
        URL url = new URL(urlString);
        return new ObjectMapper().readValue(url, RecipeByIngredient[].class);
    }

    public RecipeByIngredient getOneForIngridients(Iterable<Item> items, int number) throws IOException {
        return getForIngridients(items, number)[rnd.nextInt(number)];
    }

    public Recipe[] getRandom(List<String> tags, int number) throws java.io.IOException, InterruptedException, JSONException {
        String tagString = String.join(",", tags);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(dotenv.get("SPOONACCULAR_API_URL") + "/recipes/random?apiKey=" + dotenv.get("SPOONACCULAR_API_KEY") + "&number=" + number + "&tags=" + tagString))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonObject = new JSONObject(response.body());
        return new ObjectMapper().readValue(jsonObject.getJSONArray("recipes").toString(), Recipe[].class);
    }
}
