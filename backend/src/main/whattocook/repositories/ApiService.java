package whattocook.repositories;

import whattocook.models.Item;

public interface ApiService {
    String getForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException;

    String getRandom(java.util.List<String> tags, int number) throws java.io.IOException, InterruptedException;
}
