package whattocook.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spoonaccular.AmountConversion;
import spoonaccular.RecipeInformation;
import spoonaccular.models.ingredients_by_id.Ingredient;
import whattocook.models.Item;
import whattocook.services.ItemService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController()
@BasePathAwareController()
public class IngredientController {

    @Autowired
    ItemService itemService;

    @DeleteMapping("/ingredient/removeRecipeIngredients")
    public void removeRecipeIngredients(@RequestParam int id) throws IOException, JSONException {
        List<Ingredient> ingredientList = RecipeInformation.getIngredientList(id);
        for (Ingredient ingredient : ingredientList){
            Optional<Item> possibleItem = itemService.findByName(ingredient.getName());
            if (possibleItem.isPresent()){
                Item item = possibleItem.get();
                double newItemQuantity;
                //same unit
                if (item.getUnit().toString().equals(ingredient.getAmount().getMetric().getUnit())){
                    newItemQuantity = item.getQuantity() - ingredient.getAmount().getMetric().getValue();
                } else {
                    double ingridientAmount = AmountConversion.convertAmount(ingredient.getName(),
                            ingredient.getAmount().getMetric().getValue(),
                            ingredient.getAmount().getMetric().getUnit(),
                            item.getUnit().toString());
                    newItemQuantity = item.getQuantity() - ingridientAmount;
                }
                if (newItemQuantity > 0){
                    item.setQuantity(newItemQuantity);
                } else {
                    itemService.deleteById(item.getId());
                }
            }
        }
    }


}
