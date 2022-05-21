package backend.controller;

import backend.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import backend.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import backend.services.ItemService;

import java.util.List;

@Slf4j
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getProductList() {
        return itemService.findAll();
    }

    @GetMapping("/items/{itemId}")
    public Item getProduct(@PathVariable(value = "itemId") Long itemId) {
        return itemService.findById(itemId).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }

    @PostMapping("/items")
    public String createProduct(@RequestBody Item item) {
        itemService.save(item);
        return "Item added";
    }

    @PutMapping("/items/{itemId}")
    public String updateProduct(@PathVariable(value = "itemId") Long itemId, @RequestBody Item item) {
        return itemService.findById(itemId).map(i -> {
            i.setName(item.getName());
            i.setQuantity(item.getQuantity());
            i.setUnit(item.getUnit());
            itemService.save(i);
            return "Item updated";
        }).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }

    @DeleteMapping("/items/{itemId}")
    public String deleteProduct(@PathVariable(value = "itemId") Long itemId) {
        return itemService.findById(itemId).map(p -> {
            itemService.deleteById(itemId);
            return "Item deleted";
        }).orElseThrow(() -> new ItemNotFoundException("itemId " + itemId + " not found"));
    }
}
