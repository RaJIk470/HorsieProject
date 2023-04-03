package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

@Data
public abstract class DisplayObject implements Serializable {
    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected Color color;


    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public double getRightBound() {
        return x + width;
    }
    public double getLeftBound() {
        return x;
    }
    public double getTopBound() {
        return y;
    }
    public double getBottomBound() {
        return y + height;
    }

    public boolean checkCollision(DisplayObject displayObject) {
        return getHitBox().intersects(displayObject.getHitBox());
    }

    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    protected abstract void draw(Graphics2D g);
}
