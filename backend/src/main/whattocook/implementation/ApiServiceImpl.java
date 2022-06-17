package whattocook.implementation;


import org.springframework.stereotype.Service;
import whattocook.models.Item;
import whattocook.repositories.ApiService;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class ApiServiceImpl implements ApiService {
    private final String KEY = "85cc006d508b447a88e659cd748899db";
    private final String RANKING = "2";
    private final boolean IGNOREPANTRY = true;
    Random rnd=new Random();

    public String getForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException {
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
            return response.body();
        }
    }

    @Override
    public String getOneForIngridients(Iterable<Item> items, int number) throws IOException, InterruptedException {
        String recepies = getForIngridients(items, number);
        List<String> recepieList = splitToList(recepies);

        return recepieList.get(rnd.nextInt(20));
    }

    public String getRandom(java.util.List<String> tags, int number) throws java.io.IOException, InterruptedException {
        if (tags.isEmpty()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.spoonacular.com/recipes/random?apiKey=" + KEY + "&number=" + number))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
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
            return response.body();
        }
    }

    private List<String> splitToList(String recipies) {
        int startOfRecipie = 1;
        int bracketCounter = 0;
        List<String> recipieList = new LinkedList<>();
        for (int i = 0; i < recipies.length(); i++) {
            if (recipies.charAt(i) == '{') {
                bracketCounter++;
                if (bracketCounter == 1) {

                    startOfRecipie = i;
                }
            } else if (recipies.charAt(i) == '}') {
                bracketCounter--;
                if (bracketCounter == 0) {

                    recipieList.add(recipies.substring(startOfRecipie, i+1 ));

                }
            }

        }
        return recipieList;
    }

}
