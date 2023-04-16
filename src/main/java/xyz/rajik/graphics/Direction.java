package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    double x;
    double y;

    public void setUp() {
        y = -1;
    }

    public void setDown() {
        y = 1;
    }

    public void setLeft() {
        x = -1;
    }
    public void setRight() {
        x = 1;
    }

    public void invertY() {
        y = -y;
    }

    public void invertX() {
        x = -x;
    }

    public void reset() {
        x = 0;
        y = 0;
    }

    public void setAngle(double angle) {
        x = Math.cos(angle);
        y = Math.sin(angle);
    }
}
