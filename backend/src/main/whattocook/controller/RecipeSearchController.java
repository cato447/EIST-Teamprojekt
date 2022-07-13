package whattocook.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spoonaccular.RecipeSearch;
import spoonaccular.models.recipe_by_ingredient.ExtendedRecipeByIngredient;
import spoonaccular.models.recipe_information.Recipe;
import whattocook.repositories.ItemRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController()
@BasePathAwareController()
public class RecipeSearchController {
    private final int nextRecipes=10;
    private final int nextRecipesForOneRandom = 20;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/recipe/forFridge")
    public List<ExtendedRecipeByIngredient> getForFridge() throws IOException {
        return RecipeSearch.getForIngridients(itemRepository.findAll(), nextRecipes);
    }

    @GetMapping("/recipe/random")
    public List<Recipe> getRandom() throws IOException, JSONException {
        return RecipeSearch.getRandom(new LinkedList<>(), nextRecipes);
        //when user has food preferences apply instead of linked list.
    }

    @GetMapping("/recipe/oneFridge")
    public ExtendedRecipeByIngredient getOneFridge() throws IOException {
        return RecipeSearch.getOneForIngridients(itemRepository.findAll(), nextRecipesForOneRandom);
    }
}
