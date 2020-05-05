package pl.kietlinski.detectfaceapi.model;

import com.fasterxml.jackson.annotation.*;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.FaceAttributes;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.FaceRectangle;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "faceRectangle",
        "faceAttributes"
})
public class DetectFace {

    @JsonProperty("faceRectangle")
    private FaceRectangle faceRectangle;
    @JsonProperty("faceAttributes")
    private FaceAttributes faceAttributes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public DetectFace() {
    }

    /**
     * @param faceRectangle
     * @param faceAttributes
     */
    public DetectFace(FaceRectangle faceRectangle, FaceAttributes faceAttributes) {
        super();
        this.faceRectangle = faceRectangle;
        this.faceAttributes = faceAttributes;
    }

    @JsonProperty("faceRectangle")
    public FaceRectangle getFaceRectangle() {
        return faceRectangle;
    }

    @JsonProperty("faceRectangle")
    public void setFaceRectangle(FaceRectangle faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    @JsonProperty("faceAttributes")
    public FaceAttributes getFaceAttributes() {
        return faceAttributes;
    }

    @JsonProperty("faceAttributes")
    public void setFaceAttributes(FaceAttributes faceAttributes) {
        this.faceAttributes = faceAttributes;
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
