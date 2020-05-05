package pl.kietlinski.detectfaceapi.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
class DetectFaceServiceTest {

    DetectFaceService detectFaceService = new DetectFaceService();

    @Test
    void should_get_api_uri() throws URISyntaxException {
        //when
        URI example = detectFaceService.getApiUri();
        //then
        Assert.assertEquals(example.toString(),
                "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect?" +
                        "returnFaceAttributes=age%2Cgender%2Csmile%2CfacialHair%2Cglasses%2Cemotion%2Chair%2Cmakeup%2Caccessories" +
                        "&detectionModel=detection_01");
    }

    @Test
    void should_get_body_and_headers() {
        String userUrl = "https://img.joemonster.org/images/vad/img_45218/2380c825635d80dda65fabecdd00413c.jpg";
        //when
        HttpEntity<HashMap<String, String>> bodyAndHeaders = detectFaceService.getBodyAndHeaders(userUrl);
        //then
        Assert.assertEquals(bodyAndHeaders.getBody().toString(),
                "{url=" + userUrl + "}");
        Assert.assertEquals(bodyAndHeaders.getHeaders().toString(),
                "[Content-Type:\"application/json\", Ocp-Apim-Subscription-Key:\"null\"]");
    }

    @Test
    void should_get_hairColor_info() {
        //when
        String hairColorInfo = detectFaceService.getHairColorInfo("brown");
        //then
        Assert.assertEquals(hairColorInfo,
                "Włosy brązowe");
    }

}