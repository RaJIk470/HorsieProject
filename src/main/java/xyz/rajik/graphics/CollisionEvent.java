package xyz.rajik.graphics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CollisionEvent extends Event {
    public CollisionEvent(DisplayObject displayObject) {
       this.displayObject = displayObject;
    }
}
