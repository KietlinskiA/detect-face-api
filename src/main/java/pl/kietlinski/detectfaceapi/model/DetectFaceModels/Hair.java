package pl.kietlinski.detectfaceapi.model.DetectFaceModels;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bald",
        "invisible",
        "hairColor"
})
public class Hair {

    @JsonProperty("bald")
    private Double bald;
    @JsonProperty("invisible")
    private Boolean invisible;
    @JsonProperty("hairColor")
    private List<HairColor> hairColor = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Hair() {
    }

    /**
     * @param bald
     * @param invisible
     * @param hairColor
     */
    public Hair(Double bald, Boolean invisible, List<HairColor> hairColor) {
        super();
        this.bald = bald;
        this.invisible = invisible;
        this.hairColor = hairColor;
    }

    @JsonProperty("bald")
    public Double getBald() {
        return bald;
    }

    @JsonProperty("bald")
    public void setBald(Double bald) {
        this.bald = bald;
    }

    @JsonProperty("invisible")
    public Boolean getInvisible() {
        return invisible;
    }

    @JsonProperty("invisible")
    public void setInvisible(Boolean invisible) {
        this.invisible = invisible;
    }

    @JsonProperty("hairColor")
    public List<HairColor> getHairColor() {
        return hairColor;
    }

    @JsonProperty("hairColor")
    public void setHairColor(List<HairColor> hairColor) {
        this.hairColor = hairColor;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
