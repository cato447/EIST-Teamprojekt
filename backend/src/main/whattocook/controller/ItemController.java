package whattocook.controller;

import whattocook.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import whattocook.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whattocook.models.Unit;
import whattocook.services.ItemService;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api/v1")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public Item getItem(@RequestParam String name){
        return itemService.findByName(name).orElseThrow(() -> new ItemNotFoundException("item " + name + " not found"));
    }

    @GetMapping("/items")
    public List<Item> getItemList() {
        return itemService.findAll();
    }

    @GetMapping("/items/{itemId}")
    public Item getItem(@PathVariable(value = "itemId") Long itemId) {
        return itemService.findById(itemId).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        itemService.save(item);
        return item;
    }

    @PutMapping("/items/{itemId}")
    public String updateItem(@PathVariable(value = "itemId") Long itemId, @RequestBody Item item) {
        return itemService.findById(itemId).map(i -> {
            i.setName(item.getName());
            i.setQuantity(item.getQuantity());
            i.setUnit(item.getUnit());
            itemService.save(i);
            return "Item updated";
        }).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }

    @DeleteMapping("/items/{itemId}")
    public String deleteItem(@PathVariable(value = "itemId") Long itemId) {
        return itemService.findById(itemId).map(p -> {
            itemService.deleteById(itemId);
            return "Item deleted";
        }).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }
}
