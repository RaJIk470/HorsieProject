package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameColor implements Serializable {
    int red;
    int green;
    int blue;

    public Color toAwtColor() {
        return new Color(red, green, blue);
    }
}
