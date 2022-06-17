package whattocook.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import whattocook.repositories.ItemRepository;
import whattocook.implementation.ApiServiceImpl;

import java.io.IOException;
import java.util.LinkedList;

@Controller
public class SpoonacularController {
    private int nextRecepies;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ApiServiceImpl service;

    @GetMapping("api/forFridge")
    public HttpEntity<String> getForFridge() throws IOException, InterruptedException {
        return new HttpEntity<>(service.getForIngridients(itemRepository.findAll(), nextRecepies));
    }

    @GetMapping("api/random")
    public HttpEntity<String> getRandom() throws IOException, InterruptedException {
        return new HttpEntity<>(service.getRandom(new LinkedList<>(), nextRecepies));
        //when user has food preferences apply instead of linked list.
    }

    public void setNextRecepies(int nextRecepies) {
        this.nextRecepies = nextRecepies;
    }
}
