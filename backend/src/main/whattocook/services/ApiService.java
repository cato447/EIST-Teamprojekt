package whattocook.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import whattocook.models.Item;

public interface ApiService {
    JSONArray getForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException, JSONException;
    JSONObject getOneForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException, JSONException;

    JSONArray getRandom(java.util.List<String> tags, int number) throws java.io.IOException, InterruptedException, JSONException;
}
