package whattocook.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import whattocook.repositories.ItemRepository;
import whattocook.implementation.ApiServiceImpl;

import java.io.IOException;

@Controller
public class SpoonacularController {
    private final int nextPages = 10;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApiServiceImpl service;

    @GetMapping("api/forFridge")
    public HttpEntity<String> getForFridge() throws IOException, InterruptedException {
        return new HttpEntity<>(service.getForIngridients(itemRepository.findAll(), 10));
    }
}
