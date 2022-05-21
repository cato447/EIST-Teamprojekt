package backend.services;

import backend.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    void deleteById(Long id);
    Optional<Item> findById(long id);
    List<Item> findAll();
}
