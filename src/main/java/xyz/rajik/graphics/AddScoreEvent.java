package xyz.rajik.graphics;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import xyz.rajik.Game;

@Data
public class AddScoreEvent extends Event {
    int score;

    public AddScoreEvent(int score) {
        this.score = score;
    }

    public void execute(Game game) {
        game.getGameField().getStatusBar().addScore(score);
    }
}
