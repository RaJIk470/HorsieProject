package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class DisplayObject implements Serializable {
    protected int x;
    protected int y;
    protected int maxX;
    protected int maxY;
    protected int width;
    protected int height;
    @JsonIgnore
    protected transient Color color;
    protected GameColor gameColor;
    protected boolean isCollisional = true;

    public boolean checkCollision(DisplayObject displayObject) {
        int x2 = displayObject.x;
        int y2 = displayObject.y;
        int maxX2 = displayObject.maxX;
        int maxY2 = displayObject.maxY;
        return  x < maxX2 && maxX > x2 && y < maxY2 && maxY > y2;
    }

    public void handleCollisionEvent(CollisionEvent event) {

    }

    //public boolean handleEvent(Event event);

    protected void draw(Graphics2D g) {

    };
}
