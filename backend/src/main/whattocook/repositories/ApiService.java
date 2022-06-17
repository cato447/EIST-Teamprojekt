package whattocook.repositories;

import whattocook.models.Item;

public interface ApiService {
    String getForIngridients(Iterable<Item> items, int number) throws java.io.IOException, InterruptedException;

    String getRandom(int number, java.util.List<String> tags) throws java.io.IOException, InterruptedException;
}
