package pl.kietlinski.detectfaceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kietlinski.detectfaceapi.service.DetectFaceService;

import java.net.URISyntaxException;

@Controller
@RequestMapping
public class DetectFaceController {

    private DetectFaceService detectFaceService;

    private static final String defaultPic = "https://lh3.googleusercontent.com/proxy/daaOW4iLHc7Ek6kWCF6noW0Ez7geF6fwF-T-bcCKLXRH9jDIW3q-7GE_hSyu7KDkK_Zl4tcL-pLC5smjq4qOM5v7k9mjNcxSyylgyeLc1BOdaePrrSbzcAM35mAAOKb5Tbg";

    @Autowired
    public DetectFaceController(DetectFaceService detectFaceService) {
        this.detectFaceService = detectFaceService;
    }

    @GetMapping
    public String showFaceAttributes(Model model,
                                     @RequestParam(required = false, defaultValue = defaultPic) String userUrl
    ) throws URISyntaxException {
        detectFaceService.getFaceAttributes(userUrl);
        model.addAttribute("showAttributes", detectFaceService.getFaceAttributesList());
        model.addAttribute("picUrl", userUrl);
        model.addAttribute("userAge", detectFaceService.getUserAge());
        model.addAttribute("userGender", detectFaceService.getUserGender());
        model.addAttribute("infoSpan", detectFaceService.getInfoSpan());

        return "detectFace";
    }
}
