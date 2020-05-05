package pl.kietlinski.detectfaceapi.service;

import lombok.Data;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.kietlinski.detectfaceapi.model.DetectFace;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.Emotion;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.FaceAttributes;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.Hair;
import pl.kietlinski.detectfaceapi.model.DetectFaceModels.HairColor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class DetectFaceService {

    @Value("${api-key}")
    private String apiKey;

    private static final String BASE_URL = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect";
    private RestTemplate restTemplate;
    private List<String> faceAttributesList;

    private String userAge;
    private String userGender;
    private String infoSpan;

    public DetectFaceService() {
        this.restTemplate = new RestTemplate();
        this.faceAttributesList = new ArrayList<>();
    }

    public void getFaceAttributes(String userUrl) throws URISyntaxException {
        ResponseEntity<DetectFace[]> detectFaceObject = restTemplate.exchange(
                getApiUri(),
                HttpMethod.POST,
                getBodyAndHeaders(userUrl),
                DetectFace[].class
        );
        if (!faceAttributesList.isEmpty()) {
            faceAttributesList.clear();
        }

        if (detectFaceObject.getBody() == null || detectFaceObject.getBody().length < 1) {
            getUncorrectUrlStatus();
        } else {
            FaceAttributes faceAttributes = detectFaceObject.getBody()[0].getFaceAttributes();
            setFaceAttributesList(faceAttributes);
            setInfoSpan("ATRYBUTY TWARZY");
        }
    }

    public URI getApiUri() throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(BASE_URL);
        uriBuilder.addParameter("returnFaceAttributes",
                "age,gender,smile,facialHair,glasses,emotion,hair,makeup,accessories");
        uriBuilder.addParameter("detectionModel", "detection_01");

        return uriBuilder.build();
    }

    public HttpEntity<HashMap<String, String>> getBodyAndHeaders(String userUrl) {
        //Body
        HashMap<String, String> body = new HashMap<>();
        body.put("url", userUrl);

        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Ocp-Apim-Subscription-Key", apiKey);

        return new HttpEntity<>(body, headers);
    }

    public void setFaceAttributesList(FaceAttributes faceAttributes) {
        //age
        setUserAge(BigDecimal.valueOf(faceAttributes.getAge()).setScale(0, RoundingMode.UP).toString());

        //gender
        if (faceAttributes.getGender().equals("male")) {
            setUserGender("Mężczyzna");
        } else {
            setUserGender("Kobieta");
        }

        //accessories
        List<Object> accessories = faceAttributes.getAccessories();
        faceAttributesList.add("Posiadane akcesoria: " + accessories.size());

        //smile
        Double smile = faceAttributes.getSmile();
        if (smile >= 0.3 && smile < 0.6) {
            faceAttributesList.add("Uśmiech");
        } else if (smile >= 0.6 && smile > 0.6) {
            faceAttributesList.add("Szeroki Uśmiech");
        }

        //facialHair.mustache
        Double facialHairMustache = faceAttributes.getFacialHair().getMoustache();
        if (facialHairMustache > 0.3 && facialHairMustache <= 0.6) {
            faceAttributesList.add("Wąsy");
        } else if (facialHairMustache > 0.6) {
            faceAttributesList.add("Spore wąsy");
        }

        //facialHair.beard
        Double facialHairBeard = faceAttributes.getFacialHair().getBeard();
        if (facialHairBeard > 0.3 && facialHairBeard <= 0.6) {
            faceAttributesList.add("Zarost");
        } else if (facialHairBeard > 0.6) {
            faceAttributesList.add("Spory zarost");
        }

        //facialHair.sideburns
        Double facialHairSineburns = faceAttributes.getFacialHair().getSideburns();
        if (facialHairSineburns > 0.3 && facialHairSineburns <= 0.6) {
            faceAttributesList.add("Bokodroby");
        } else if (facialHairSineburns > 0.6) {
            faceAttributesList.add("Spore bokobrody");
        }

        //glasses
        String glasses = faceAttributes.getGlasses();
        switch (glasses) {
            case "NoGlasses":
                faceAttributesList.add("Bez okularów");
                break;
            case "ReadingGlasses":
                faceAttributesList.add("Okulary do czytania");
                break;
            case "Sunglasses":
                faceAttributesList.add("Okulary przeciwsłoneczne");
                break;
            default:
                faceAttributesList.add("Okulary do pływania");
                break;
        }

        //emotion
        Emotion emotion = faceAttributes.getEmotion();
        if (emotion.getAnger() >= 0.3 && emotion.getAnger() < 0.6) {
            faceAttributesList.add("Trochę zły");
        } else if (emotion.getAnger() > 0.6) {
            faceAttributesList.add("Zły");
        }

        if (emotion.getContempt() >= 0.3 && emotion.getContempt() < 0.6) {
            faceAttributesList.add("Lekka pogarda");
        } else if (emotion.getContempt() > 0.6) {
            faceAttributesList.add("Pogarda");
        }

        if (emotion.getDisgust() >= 0.3 && emotion.getDisgust() < 0.6) {
            faceAttributesList.add("Lekki wstręt");
        } else if (emotion.getDisgust() > 0.6) {
            faceAttributesList.add("Wstręt");
        }

        if (emotion.getFear() >= 0.3 && emotion.getFear() < 0.6) {
            faceAttributesList.add("Lekki strach");
        } else if (emotion.getFear() > 0.6) {
            faceAttributesList.add("Strach");
        }

        if (emotion.getHappiness() >= 0.3 && emotion.getHappiness() < 0.6) {
            faceAttributesList.add("Trochę szczęśliwa");
        } else if (emotion.getHappiness() > 0.6) {
            faceAttributesList.add("Szczęśliwa");
        }

        if (emotion.getNeutral() >= 0.3 && emotion.getNeutral() < 0.6) {
            faceAttributesList.add("Trochę neutralna");
        } else if (emotion.getNeutral() > 0.6) {
            faceAttributesList.add("Neutralna");
        }

        if (emotion.getSadness() >= 0.3 && emotion.getSadness() < 0.6) {
            faceAttributesList.add("Trochę smutna");
        } else if (emotion.getSadness() > 0.6) {
            faceAttributesList.add("Smutna");
        }

        if (emotion.getSurprise() >= 0.3 && emotion.getSurprise() < 0.6) {
            faceAttributesList.add("Trochę zaskoczona");
        } else if (emotion.getSurprise() > 0.6) {
            faceAttributesList.add("Zaskoczona");
        }

        //makeup
        Boolean eyeMakeup = faceAttributes.getMakeup().getEyeMakeup();
        if (eyeMakeup) {
            faceAttributesList.add("Makijaż oczu");
        }
        Boolean lipMakeup = faceAttributes.getMakeup().getLipMakeup();
        if (lipMakeup) {
            faceAttributesList.add("Pomalowane usta");
        }

        //hair.bald
        Hair hair = faceAttributes.getHair();
        if (hair.getBald() >= 0.6 && hair.getBald() < 0.8) {
            faceAttributesList.add("Wyłysiały");
        } else if (hair.getBald() >= 0.8) {
            faceAttributesList.add("Łysy");
        }

        //hair.hairColor
        Optional<HairColor> optionalHairColor = hair.getHairColor().stream().findFirst();
        optionalHairColor.ifPresent(hairColor ->
                faceAttributesList.add(getHairColorInfo(hairColor.getColor()))
        );

    }

    public String getHairColorInfo(String color) {
        switch (color) {
            case "brown":
                color = "brązowe";
                break;
            case "blond":
                color = "blond";
                break;
            case "black":
                color = "czarne";
                break;
            case "gray":
                color = "szare";
                break;
            case "red":
                color = "czerwone";
                break;
            default:
                color = "inne";
                break;
        }

        return "Włosy " + color;
    }

    public void getUncorrectUrlStatus() {
        setInfoSpan("ZMIEŃ ZDJĘCIE");
        setUserAge("-");
        setUserGender("-");
    }

}
