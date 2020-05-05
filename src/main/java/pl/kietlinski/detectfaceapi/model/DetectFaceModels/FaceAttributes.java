package pl.kietlinski.detectfaceapi.model.DetectFaceModels;

import com.fasterxml.jackson.annotation.*;
import pl.kietlinski.detectfaceapi.model.FacialHair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "smile",
        "gender",
        "age",
        "facialHair",
        "glasses",
        "emotion",
        "makeup",
        "accessories",
        "hair"
})
public class FaceAttributes {

    @JsonProperty("smile")
    private Double smile;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("age")
    private Double age;
    @JsonProperty("facialHair")
    private FacialHair facialHair;
    @JsonProperty("glasses")
    private String glasses;
    @JsonProperty("emotion")
    private Emotion emotion;
    @JsonProperty("makeup")
    private Makeup makeup;
    @JsonProperty("accessories")
    private List<Object> accessories = null;
    @JsonProperty("hair")
    private Hair hair;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public FaceAttributes() {
    }

    /**
     * @param glasses
     * @param makeup
     * @param hair
     * @param facialHair
     * @param emotion
     * @param gender
     * @param accessories
     * @param age
     * @param smile
     */
    public FaceAttributes(Double smile, String gender, Double age, FacialHair facialHair, String glasses, Emotion emotion, Makeup makeup, List<Object> accessories, Hair hair) {
        super();
        this.smile = smile;
        this.gender = gender;
        this.age = age;
        this.facialHair = facialHair;
        this.glasses = glasses;
        this.emotion = emotion;
        this.makeup = makeup;
        this.accessories = accessories;
        this.hair = hair;
    }

    @JsonProperty("smile")
    public Double getSmile() {
        return smile;
    }

    @JsonProperty("smile")
    public void setSmile(Double smile) {
        this.smile = smile;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("age")
    public Double getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Double age) {
        this.age = age;
    }

    @JsonProperty("facialHair")
    public FacialHair getFacialHair() {
        return facialHair;
    }

    @JsonProperty("facialHair")
    public void setFacialHair(FacialHair facialHair) {
        this.facialHair = facialHair;
    }

    @JsonProperty("glasses")
    public String getGlasses() {
        return glasses;
    }

    @JsonProperty("glasses")
    public void setGlasses(String glasses) {
        this.glasses = glasses;
    }

    @JsonProperty("emotion")
    public Emotion getEmotion() {
        return emotion;
    }

    @JsonProperty("emotion")
    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    @JsonProperty("makeup")
    public Makeup getMakeup() {
        return makeup;
    }

    @JsonProperty("makeup")
    public void setMakeup(Makeup makeup) {
        this.makeup = makeup;
    }

    @JsonProperty("accessories")
    public List<Object> getAccessories() {
        return accessories;
    }

    @JsonProperty("accessories")
    public void setAccessories(List<Object> accessories) {
        this.accessories = accessories;
    }

    @JsonProperty("hair")
    public Hair getHair() {
        return hair;
    }

    @JsonProperty("hair")
    public void setHair(Hair hair) {
        this.hair = hair;
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
