package whattocook.services;

import whattocook.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    void deleteById(Long id);
    Optional<Item> findById(long id);
    Optional<Item> findByName(String name);
    List<Item> findAll();
}
