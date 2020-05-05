package pl.kietlinski.detectfaceapi.model.DetectFaceModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HairColor {

    private String color;
    private Double confidence;
}
