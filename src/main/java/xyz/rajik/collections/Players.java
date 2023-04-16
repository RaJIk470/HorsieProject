package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Players implements Iterable<Player> {
    List<Player> players;
    public Players() {
        players = new ArrayList<>();
    }


    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
