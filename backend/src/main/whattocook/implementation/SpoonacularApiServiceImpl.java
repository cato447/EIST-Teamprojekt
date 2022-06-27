package whattocook.implementation;


import org.springframework.stereotype.Service;
import whattocook.models.Item;
import whattocook.services.SpoonacularApiService;
import org.json.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.URI;
import java.util.Iterator;
import java.util.Random;

@Service
public class SpoonacularApiServiceImpl implements SpoonacularApiService {
    private final String KEY = "85cc006d508b447a88e659cd748899db";
    private final String RANKING = "2";
    private final boolean IGNOREPANTRY = true;
    Random rnd=new Random();

    public JSONArray getForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException, JSONException {
        Iterator<Item> itemIterator = items.iterator();
        if (!itemIterator.hasNext()) {
            return getRandom(new java.util.LinkedList<String>(), number);
        } else {
            String ingridients = itemIterator.next().getName();
            for (Iterator<Item> it = itemIterator; it.hasNext(); ) {
                Item curryItem = it.next();


                ingridients += "," + curryItem.getName();
            }
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + KEY + "&ingredients=" + ingridients + "&ranking=" + RANKING + "&ignorePantry=" + IGNOREPANTRY + "&number=" + number))
                    .method("GET", java.net.http.HttpRequest.BodyPublishers.noBody())
                    .build();
            java.net.http.HttpResponse<String> response = java.net.http.HttpClient.newHttpClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());

                JSONArray array=new JSONArray(response.body());
                return array;


        }
    }

    @Override
    public JSONObject getOneForIngridients(Iterable<Item> items, int number) throws IOException, InterruptedException, JSONException {
        JSONArray array= getForIngridients(items, number);


        return array.getJSONObject(rnd.nextInt(20));
    }

    public JSONArray getRandom(java.util.List<String> tags, int number) throws java.io.IOException, InterruptedException, JSONException {
        if (tags.isEmpty()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.spoonacular.com/recipes/random?apiKey=" + KEY + "&number=" + number))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                JSONArray array=new JSONArray(response.body());
                return array;

        } else {
            String tagString = tags.get(0);
            for (int i = 1; i < tags.size(); i++) {
                tagString += "," + tags.get(i);
            }
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.spoonacular.com/recipes/random?apiKey=" + KEY + "&number=" + number + "&tags=" + tagString))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray array=new JSONArray(response.body());
            return array;
        }
    }




}
