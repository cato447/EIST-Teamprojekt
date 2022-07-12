package whattocook.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spoonaccular.RecipeInformation;
import spoonaccular.RecipeSearch;
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_by_ingredient.RecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;
import whattocook.repositories.ItemRepository;

import java.io.IOException;
import java.util.LinkedList;

@RestController()
@RequestMapping(path = "recipe")
public class SpoonacularController {
    private int nextRecepies=10;
    private int nextRecepiesForOneRandom=20;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RecipeInformation recipeInformation;
    @Autowired
    private RecipeSearch recipeSearch;

    @GetMapping("/forFridge")
    public RecipeByIngredient[] getForFridge() throws IOException {
        return recipeSearch.getForIngridients(itemRepository.findAll(), nextRecepies);
    }

    @GetMapping("/random")
    public Recipe[] getRandom() throws IOException, InterruptedException, JSONException {
        return recipeSearch.getRandom(new LinkedList<>(), nextRecepies);
        //when user has food preferences apply instead of linked list.
    }

    @GetMapping("/oneFridge")
    public ExtendedRecipeByIngredient getOneFridge() throws IOException {
        RecipeByIngredient recipe = recipeSearch.getOneForIngridients(itemRepository.findAll(), nextRecepiesForOneRandom);
        return recipeInformation.getRecepieByIngredientsExtended(recipe);
    }

    @GetMapping("/info/{id}")
    public Recipe getInfo(@PathVariable int id) throws IOException {
        return recipeInformation.getRecipeFromId(id);
    }
}
