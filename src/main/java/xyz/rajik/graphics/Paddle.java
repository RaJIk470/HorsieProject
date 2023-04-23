package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;
import xyz.rajik.graphics.Direction;
import xyz.rajik.graphics.DisplayObject;

import java.awt.*;

@Data
@NoArgsConstructor
public class Paddle extends DisplayObject implements Moveable {
    private Direction direction;
    private int speed;
    private static final double SPEED_MULTIPLIER = 100;
    public Paddle(int x, int y, int width, int height, int speed, GameColor color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.maxX = x + width;
        this.maxY = y + height;
        this.speed = speed;
        this.gameColor = color;
        this.color = color.toAwtColor();
        direction = new Direction();
    }
    public void move(double dt) {
        if (direction.getX() > 0 && maxX >= 790) return;
        if (direction.getX() < 0 && x <= 10) return;
            x += direction.getX() * speed * dt * SPEED_MULTIPLIER;
            maxX += direction.getX() * speed * dt * SPEED_MULTIPLIER;
        //y += direction.getY() * speed * dt * SPEED_MULTIPLIER;
    }
    @Override
    protected void draw(Graphics2D g) {
        g.setColor(gameColor.toAwtColor());
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(4));
        g.drawRect((int)x, (int)y, (int)width, (int)height);
    }
}
