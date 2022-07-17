
package spoonaccular.models.recipe_information;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "vegetarian",
    "vegan",
    "glutenFree",
    "dairyFree",
    "extendedIngredients",
    "id",
    "title",
    "readyInMinutes",
    "servings",
    "image",
    "spoonacularSourceUrl"
})
@Generated("jsonschema2pojo")
public class Recipe {

    @JsonProperty("vegetarian")
    private Boolean vegetarian;
    @JsonProperty("vegan")
    private Boolean vegan;
    @JsonProperty("glutenFree")
    private Boolean glutenFree;
    @JsonProperty("dairyFree")
    private Boolean diaryFree;
    @JsonProperty("extendedIngredients")
    private List<ExtendedIngredient> extendedIngredients = null;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("readyInMinutes")
    private Integer readyInMinutes;
    @JsonProperty("servings")
    private Integer servings;
    @JsonProperty("image")
    private String image;
    @JsonProperty("spoonacularSourceUrl")
    private String spoonacularSourceUrl;

    @JsonProperty("vegetarian")
    public Boolean getVegetarian() {
        return vegetarian;
    }

    @JsonProperty("vegetarian")
    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @JsonProperty("vegan")
    public Boolean getVegan() {
        return vegan;
    }

    @JsonProperty("vegan")
    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    @JsonProperty("glutenFree")
    public Boolean getGlutenFree() {
        return glutenFree;
    }

    @JsonProperty("glutenFree")
    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    @JsonProperty("extendedIngredients")
    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    @JsonProperty("extendedIngredients")
    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

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

    @JsonProperty("readyInMinutes")
    public Integer getReadyInMinutes() {
        return readyInMinutes;
    }

    @JsonProperty("readyInMinutes")
    public void setReadyInMinutes(Integer readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    @JsonProperty("servings")
    public Integer getServings() {
        return servings;
    }

    @JsonProperty("servings")
    public void setServings(Integer servings) {
        this.servings = servings;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("spoonacularSourceUrl")
    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    @JsonProperty("spoonacularSourceUrl")
    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

}
