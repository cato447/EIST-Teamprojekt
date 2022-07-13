package whattocook.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spoonaccular.RecipeInformation;
import spoonaccular.RecipeSearch;
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;
import whattocook.repositories.ItemRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController()
@BasePathAwareController()
public class SpoonacularController {
    private int nextRecepies=10;
    private int nextRecepiesForOneRandom=20;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RecipeInformation recipeInformation;
    @Autowired
    private RecipeSearch recipeSearch;

    @GetMapping("/recipe/forFridge")
    public List<ExtendedRecipeByIngredient> getForFridge() throws IOException {
        return recipeSearch.getForIngridients(itemRepository.findAll(), nextRecepies);
    }

    @GetMapping("/recipe/random")
    public List<Recipe> getRandom() throws IOException, InterruptedException, JSONException {
        return recipeSearch.getRandom(new LinkedList<>(), nextRecepies);
        //when user has food preferences apply instead of linked list.
    }

    @GetMapping("/recipe/oneFridge")
    public ExtendedRecipeByIngredient getOneFridge() throws IOException {
        return recipeSearch.getOneForIngridients(itemRepository.findAll(), nextRecepiesForOneRandom);
    }
}
