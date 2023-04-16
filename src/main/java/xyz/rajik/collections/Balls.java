package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.Ball;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Balls implements Iterable<Ball> {
    List<Ball> balls;

    public Balls() {
        balls = new ArrayList<>();
    }

    @Override
    public Iterator<Ball> iterator() {
        return balls.iterator();
    }
}
