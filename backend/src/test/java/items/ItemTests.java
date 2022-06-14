package items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import whattocook.models.Item;
import whattocook.models.Unit;
import whattocook.repositories.ItemRepository;


import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = whattocook.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
final class ItemTests {

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    private void resetItems() {
        itemRepository.deleteAll();
    }

    @Test
    public void saveTest() {
        Item item = new Item("kartoffel", Unit.GRAMMS, 5000);
        itemRepository.save(item);
        assertTrue(itemRepository.existsById(item.getId()));
    }

    @Test
    public void findByIDTest() {
        Item item = new Item("tortillias", Unit.GRAMMS, 5000);
        itemRepository.save(item);
        assertEquals(item, itemRepository.findById(item.getId()).get());
    }


    @Test
    public void findByIdNotPresentTest() {
        assertFalse(itemRepository.findById(77L).isPresent());
    }

    @Test
    public void findByNameTest(){
        Item item = new Item("tortillias", Unit.GRAMMS, 5000);
        itemRepository.save(item);
        assertEquals(item, itemRepository.findByName(item.getName()).get());
    }

    @Test
    public void findAllTest() {
        List<Item> savedItems = new ArrayList();
        savedItems.add( new Item("nachos", Unit.GRAMMS, 5000));
        savedItems.add( new Item("wurst", Unit.GRAMMS, 5000));
        savedItems.add( new Item("schinken", Unit.GRAMMS, 5000));
        savedItems.add( new Item("brokkoli", Unit.GRAMMS, 5000));
        savedItems.add( new Item("eiscreme", Unit.GRAMMS, 5000));

        itemRepository.saveAll(savedItems);

        List<Item> foundItems = (List<Item>)itemRepository.findAll();
        assertTrue(compListNotId(savedItems, foundItems));
    }

    @Test
    public void deleteTest() {
        Item item = new Item("elefantenfuß", Unit.GRAMMS, 5000);
        itemRepository.save(item);
        assertEquals(item, itemRepository.findById(item.getId()).get());
        itemRepository.delete(item);
        assertTrue(itemRepository.findById(item.getId()).isEmpty());
    }

    @Test
    public void updateTest() {
        Item item = new Item("schokoküsse", Unit.GRAMMS, 5000);
        itemRepository.save(item);
        long itemCount = itemRepository.count();
        item.setQuantity(4574);
        itemRepository.save(item);
        assertEquals(itemCount, itemRepository.count());
        assertEquals(4574, itemRepository.findById(item.getId()).get().getQuantity());
    }

    private boolean compNotId(Item item1, Item item2){
        return item1.getName().equals(item2.getName())&&item1.getQuantity()==item2.getQuantity()&&item1.getUnit().equals(item2.getUnit());
    }

    private boolean compListNotId(List<Item> expected, List<Item> actual){
        if (expected.size() != actual.size()){
            return false;
        }
        for (int i = 0; i < expected.size(); i++){
            if(!compNotId(expected.get(i), actual.get(i))){
                return false;
            }
        }
        return true;
    }
}
