package whattocook.Controller;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whattocook.services.SpoonacularApiService;
import whattocook.repositories.ItemRepository;

import java.io.IOException;
import java.util.LinkedList;

@RestController
@RequestMapping("/recipes")
public class SpoonacularController {
    private int nextRecepies=10;
    private int nextRecepiesForOneRandom=20;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SpoonacularApiService service;

    @GetMapping("forFridge")
    public HttpEntity<JSONArray> getForFridge() throws IOException, InterruptedException, JSONException {
        return new HttpEntity<JSONArray>(service.getForIngridients(itemRepository.findAll(), nextRecepies));
    }

    @GetMapping("random")
    public HttpEntity<JSONArray> getRandom() throws IOException, InterruptedException, JSONException {
        return new HttpEntity<JSONArray>(service.getRandom(new LinkedList<>(), nextRecepies));
        //when user has food preferences apply instead of linked list.
    }

    @GetMapping("oneFridge")
    public HttpEntity<JSONObject> getOneFridge() throws IOException, InterruptedException, JSONException {
        return new HttpEntity<JSONObject>(service.getOneForIngridients(itemRepository.findAll(), nextRecepiesForOneRandom));
    }

    public void setNextRecepies(int nextRecepies) {
        this.nextRecepies = nextRecepies;
    }
}
