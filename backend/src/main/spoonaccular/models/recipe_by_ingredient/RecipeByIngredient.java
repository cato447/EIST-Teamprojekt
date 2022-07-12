
package spoonaccular.models.recipe_by_ingredient;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "id",
    "title",
    "image",
    "usedIngredientCount",
    "missedIngredientCount",
    "missedIngredients",
    "usedIngredients",
})
@Generated("jsonschema2pojo")
public class RecipeByIngredient {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("image")
    private String image;
    @JsonProperty("usedIngredientCount")
    private Integer usedIngredientCount;
    @JsonProperty("missedIngredientCount")
    private Integer missedIngredientCount;
    @JsonProperty("missedIngredients")
    private List<MissedIngredient> missedIngredients = null;
    @JsonProperty("usedIngredients")
    private List<UsedIngredient> usedIngredients = null;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("usedIngredientCount")
    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }

    @JsonProperty("usedIngredientCount")
    public void setUsedIngredientCount(Integer usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }

    @JsonProperty("missedIngredientCount")
    public void setMissedIngredientCount(Integer missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    @JsonProperty("missedIngredients")
    public List<MissedIngredient> getMissedIngredients() {
        return missedIngredients;
    }

    @JsonProperty("missedIngredients")
    public void setMissedIngredients(List<MissedIngredient> missedIngredients) {
        this.missedIngredients = missedIngredients;
    }

    @JsonProperty("usedIngredients")
    public List<UsedIngredient> getUsedIngredients() {
        return usedIngredients;
    }

    @JsonProperty("usedIngredients")
    public void setUsedIngredients(List<UsedIngredient> usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

}
