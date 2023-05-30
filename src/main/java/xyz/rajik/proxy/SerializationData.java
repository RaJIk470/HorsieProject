package xyz.rajik.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.graphics.StatusBar;
import xyz.rajik.collections.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializationData {
    private Balls balls;
    private Players players;
    private Walls walls;
    private Bricks bricks;
    private Bonuses bonuses;
    private StatusBar statusBar;
}
