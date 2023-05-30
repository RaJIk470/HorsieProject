package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;
import xyz.rajik.collections.Bonuses;

import java.awt.*;

@Data
@NoArgsConstructor
public class Ball extends DisplayObject implements Moveable {
    private Direction direction;
    private int speed;
    private static final double SPEED_MULTIPLIER = 40;
    public Ball(int x, int y, int width, int height, int speed, GameColor color) {
        this.x = x;
        this.y = y;
        this.maxX = width + x;
        this.maxY = height + y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.gameColor = color;
        this.color = color.toAwtColor();
        direction = new Direction();
        direction.setUp();
        direction.setRight();
    }
    public void move(double dt) {
        int bonus = 0;
        if (Bonuses.isIncreaseSpeedActive) bonus += 5;
        if (Bonuses.isDecreaseSpeedActive) bonus -= 5;
        x += direction.getX() * (speed + bonus) * (dt * SPEED_MULTIPLIER);
        y += direction.getY() * (speed + bonus) * (dt * SPEED_MULTIPLIER);
        maxX = x + width;
        maxY = y + height;
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(gameColor.toAwtColor());
        g.fillOval((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public void handleCollisionEvent(CollisionEvent event) {
        DisplayObject displayObject = event.displayObject;
        if (displayObject instanceof Bonus) return;
        if (displayObject instanceof Brick brick) {
            if (Bonuses.isDisableCollisionActive) return;
            if (brick.isBroken) return;
        }
        double x2 = displayObject.x;
        double y2 = displayObject.y;
        double maxX2 = displayObject.maxX;
        double maxY2 = displayObject.maxY;

        if (y < maxY2 && maxY > maxY2)
            direction.setDown();
        if (x < maxX2 && maxX > maxX2)
            direction.setRight();
        if (maxY > y2 && y < y2)
            direction.setUp();
        if (maxX > x2 && x < x2)
            direction.setLeft();
    }
}
