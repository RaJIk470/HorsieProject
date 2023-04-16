package xyz.rajik.collections;

import lombok.Data;
import xyz.rajik.graphics.DisplayObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class DisplayObjects implements Iterable<DisplayObject> {
    List<DisplayObject> displayObjects;
    public DisplayObjects() {
        displayObjects = new ArrayList<>();
    }

    @Override
    public Iterator<DisplayObject> iterator() {
        return displayObjects.iterator();
    }
}
