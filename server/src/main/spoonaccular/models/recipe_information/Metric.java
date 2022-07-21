
package spoonaccular.models.recipe_information;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "amount",
    "unitShort"
})
@Generated("jsonschema2pojo")
public class Metric {

    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("unitShort")
    private String unitShort;

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("unitShort")
    public String getUnitShort() {
        return unitShort;
    }

    @JsonProperty("unitShort")
    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

}
