package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.concurrent.ConcurrentLinkedDeque;

@Data
public abstract class DisplayObject implements Serializable {
    protected double x;
    protected double y;
    protected double maxX;
    protected double maxY;
    protected double width;
    protected double height;
    protected transient Color color;
    protected GameColor gameColor;


    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public double getRightBound() {
        return maxX;
    }
    public double getLeftBound() {
        return x;
    }
    public double getTopBound() {
        return y;
    }
    public double getBottomBound() {
        return maxY;
    }

    public boolean checkCollision(DisplayObject displayObject) {
        Double x2 = displayObject.x;
        Double y2 = displayObject.y;
        Double maxX2 = displayObject.maxX;
        Double maxY2 = displayObject.maxY;
        boolean result =  x < maxX2 && maxX > x2 && y < maxY2 && maxY > y2;
        return result;
    }

    public void handleCollisionEvent(CollisionEvent event) {

    }

    //public boolean handleEvent(Event event);

    protected abstract void draw(Graphics2D g);
}
