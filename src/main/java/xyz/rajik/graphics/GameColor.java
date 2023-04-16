package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public class GameColor {
    int red;
    int green;
    int blue;

    public Color toAwtColor() {
        return new Color(red, green, blue);
    }
}
