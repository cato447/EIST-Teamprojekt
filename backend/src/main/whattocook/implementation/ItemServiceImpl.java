package whattocook.implementation;

import whattocook.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whattocook.repositories.ItemRepository;
import whattocook.services.ItemService;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Optional<Item> findByNameIgnoreCaseContaining(String name) {
        return itemRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public Iterable<Item> findAll() {
        return itemRepository.findAll();
    }
}
