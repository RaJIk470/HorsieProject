package xyz.rajik;

import com.fasterxml.jackson.core.JsonProcessingException;
import xyz.rajik.collections.DisplayObjects;

import java.io.IOException;

public interface Serializer {
    void serialize(DisplayObjects displayObjects) throws IOException;

    DisplayObjects deserialize() throws IOException;
}
