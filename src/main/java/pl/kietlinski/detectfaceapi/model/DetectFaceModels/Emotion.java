package pl.kietlinski.detectfaceapi.model.DetectFaceModels;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "anger",
        "contempt",
        "disgust",
        "fear",
        "happiness",
        "neutral",
        "sadness",
        "surprise"
})
public class Emotion {

    @JsonProperty("anger")
    private Double anger;
    @JsonProperty("contempt")
    private Double contempt;
    @JsonProperty("disgust")
    private Double disgust;
    @JsonProperty("fear")
    private Double fear;
    @JsonProperty("happiness")
    private Double happiness;
    @JsonProperty("neutral")
    private Double neutral;
    @JsonProperty("sadness")
    private Double sadness;
    @JsonProperty("surprise")
    private Double surprise;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Emotion() {
    }

    /**
     * @param contempt
     * @param surprise
     * @param happiness
     * @param neutral
     * @param sadness
     * @param disgust
     * @param anger
     * @param fear
     */
    public Emotion(Double anger, Double contempt, Double disgust, Double fear, Double happiness, Double neutral, Double sadness, Double surprise) {
        super();
        this.anger = anger;
        this.contempt = contempt;
        this.disgust = disgust;
        this.fear = fear;
        this.happiness = happiness;
        this.neutral = neutral;
        this.sadness = sadness;
        this.surprise = surprise;
    }

    @JsonProperty("anger")
    public Double getAnger() {
        return anger;
    }

    @JsonProperty("anger")
    public void setAnger(Double anger) {
        this.anger = anger;
    }

    @JsonProperty("contempt")
    public Double getContempt() {
        return contempt;
    }

    @JsonProperty("contempt")
    public void setContempt(Double contempt) {
        this.contempt = contempt;
    }

    @JsonProperty("disgust")
    public Double getDisgust() {
        return disgust;
    }

    @JsonProperty("disgust")
    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }

    @JsonProperty("fear")
    public Double getFear() {
        return fear;
    }

    @JsonProperty("fear")
    public void setFear(Double fear) {
        this.fear = fear;
    }

    @JsonProperty("happiness")
    public Double getHappiness() {
        return happiness;
    }

    @JsonProperty("happiness")
    public void setHappiness(Double happiness) {
        this.happiness = happiness;
    }

    @JsonProperty("neutral")
    public Double getNeutral() {
        return neutral;
    }

    @JsonProperty("neutral")
    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    @JsonProperty("sadness")
    public Double getSadness() {
        return sadness;
    }

    @JsonProperty("sadness")
    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }

    @JsonProperty("surprise")
    public Double getSurprise() {
        return surprise;
    }

    @JsonProperty("surprise")
    public void setSurprise(Double surprise) {
        this.surprise = surprise;
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
