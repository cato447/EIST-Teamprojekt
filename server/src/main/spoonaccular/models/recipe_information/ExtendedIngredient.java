
package spoonaccular.models.recipe_information;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "name",
    "measures"
})
@Generated("jsonschema2pojo")
public class ExtendedIngredient {

    @JsonProperty("name")
    private String name;
    @JsonProperty("measures")
    private Measures measures;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("measures")
    public Measures getMeasures() {
        return measures;
    }

    @JsonProperty("measures")
    public void setMeasures(Measures measures) {
        this.measures = measures;
    }

}
