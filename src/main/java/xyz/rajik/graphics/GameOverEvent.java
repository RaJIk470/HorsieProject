package xyz.rajik.graphics;

import xyz.rajik.Game;

public class GameOverEvent extends Event {

    @Override
    public void execute(Game game) {
        Game.game.gameOver();
    }
}
