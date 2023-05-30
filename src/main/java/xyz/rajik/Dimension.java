package xyz.rajik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dimension {
    int width;
    int height;

    public String toString() {
        return width + "x" + height;
    }
}
