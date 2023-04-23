package xyz.rajik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.rajik.collections.Balls;
import xyz.rajik.collections.Bricks;
import xyz.rajik.collections.Players;
import xyz.rajik.collections.Walls;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerializationData {
    private Balls balls;
    private Players players;
    private Walls walls;
    private Bricks bricks;
}
