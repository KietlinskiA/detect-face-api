package pl.kietlinski.detectfaceapi.model.DetectFaceModels;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "eyeMakeup",
        "lipMakeup"
})
public class Makeup {

    @JsonProperty("eyeMakeup")
    private Boolean eyeMakeup;
    @JsonProperty("lipMakeup")
    private Boolean lipMakeup;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Makeup() {
    }

    /**
     * @param eyeMakeup
     * @param lipMakeup
     */
    public Makeup(Boolean eyeMakeup, Boolean lipMakeup) {
        super();
        this.eyeMakeup = eyeMakeup;
        this.lipMakeup = lipMakeup;
    }

    @JsonProperty("eyeMakeup")
    public Boolean getEyeMakeup() {
        return eyeMakeup;
    }

    @JsonProperty("eyeMakeup")
    public void setEyeMakeup(Boolean eyeMakeup) {
        this.eyeMakeup = eyeMakeup;
    }

    @JsonProperty("lipMakeup")
    public Boolean getLipMakeup() {
        return lipMakeup;
    }

    @JsonProperty("lipMakeup")
    public void setLipMakeup(Boolean lipMakeup) {
        this.lipMakeup = lipMakeup;
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
