
package spoonaccular.models.recipe_by_ingredient;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "name"
})
@Generated("jsonschema2pojo")
public class MissedIngredient {

    @JsonProperty("name")
    private String name;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
