package xyz.rajik.graphics;

import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.Game;
import xyz.rajik.collections.Bonuses;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Brick extends DisplayObject {
    boolean isBroken; // can u hear the silence can u see the dark can u fix the broken can u feel my heart
    private List<Bonus> bonusList = new ArrayList<>();

    public Brick(int x, int y, int width, int height, GameColor color) {
        this.x = x;
        this.y = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.width = width;
        this.height = height;
        this.gameColor = color;
        this.color = gameColor.toAwtColor();
        this.isBroken = false;
    }

    @Override
    public void handleCollisionEvent(CollisionEvent event) {
        if (!isBroken && event.displayObject instanceof Ball) {
            isBroken = true;
            int points = 2;
            if (Bonuses.isX2Active) points *= 2;
            Game.game.handleEvent(new AddScoreEvent(points * Game.game.scoreMultiplier));
            getBonusList().stream().forEach(bonus -> bonus.handleEvent(new ActivateBonusEvent()));
            if (Game.game.checkIfGameOver()) {
                Game.game.handleEvent(new GameOverEvent());
            }
        }
    }

    @Override
    protected void draw(Graphics2D g) {
        if (!isBroken) {
            g.setColor(gameColor.toAwtColor());
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(4));
            g.drawRect(x, y, width, height);
        }
    }
}
