package xyz.rajik.graphics;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;
import xyz.rajik.collections.Bonuses;

import java.awt.*;
import java.util.Random;

@Data
@NoArgsConstructor
public class Bonus extends DisplayObject implements Moveable {
    private Direction direction;
    private int speed;
    private static final double SPEED_MULTIPLIER = 40;
    private int bonusValue;
    private boolean isActive;
    private
    BonusType bonusType;
    public Bonus(int x, int y, int width, int height, int speed, GameColor color) {
        isActive = false;
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
        direction.setDown();
        Random random = new Random();
        bonusValue = random.nextInt() % 10 + 10;
        BonusType[] bonusTypes = BonusType.values();
        bonusType = bonusTypes[Math.abs(random.nextInt()) % bonusTypes.length];
    }
    public void move(double dt) {
        if (!isActive) return;
        y += direction.getY() * speed * (dt * SPEED_MULTIPLIER);
        maxY = y + height;
    }

    public void handleEvent(Event event) {
        if (event instanceof ActivateBonusEvent) {
            setActive(true);
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void applyBonus() {
        switch (bonusType) {
            case ADD_SCORE -> {
                Game.game.handleEvent(new AddScoreEvent(bonusValue));
            }
            case INCREASE_BALL_SPEED -> {
                Bonuses.setIsIncreaseSpeedActive();
            }
            case DECREASE_BALL_SPEED -> {
                Bonuses.setIsDecreaseSpeedActive();
            }
            case DISABLE_COLLISIONS -> {
                Bonuses.setIsDisableCollisionActive();
            }
            case X2_SCORE -> {
                Bonuses.setIsX2Active();
            }
        }
    }

    @Override
    protected void draw(Graphics2D g) {
        if (!isActive) return;
        g.setColor(gameColor.toAwtColor());
        g.setFont(new Font("Sans", Font.PLAIN, 20));
        switch (bonusType) {

            case ADD_SCORE -> {
                g.setColor(new Color(150, 150, 200));
            }
            case INCREASE_BALL_SPEED -> {
                g.setColor(new Color(200, 120, 180));
            }
            case DECREASE_BALL_SPEED -> {
                g.setColor(new Color(100, 200, 160));
            }
            case DISABLE_COLLISIONS -> {
                g.setColor(new Color(70, 70, 220));
            }
            case X2_SCORE -> {
                g.setColor(new Color(225, 225, 220));
            }
        }
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        switch (bonusType) {

            case ADD_SCORE -> {
                g.drawString(String.valueOf(bonusValue), x + (int)(width / 2.5), y + 5 + height / 2);
            }
            case INCREASE_BALL_SPEED -> {
                g.drawString("inc", x + (int)(width / 2.5), y + 5 + height / 2);
            }
            case DECREASE_BALL_SPEED -> {
                g.drawString("dec", x + (int)(width / 2.5), y + 5 + height / 2);
            }
            case DISABLE_COLLISIONS -> {
                g.drawString("col", x + (int)(width / 2.5), y + 5 + height / 2);
            }
            case X2_SCORE -> {
                g.drawString("x2", x + (int)(width / 2.5), y + 5 + height / 2);
            }
        }
    }
}
