package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction implements Serializable {
    int x;
    int y;

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

}
