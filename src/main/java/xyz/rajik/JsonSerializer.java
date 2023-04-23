package xyz.rajik;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import xyz.rajik.collections.DisplayObjects;
import xyz.rajik.graphics.DisplayObject;

import java.io.File;
import java.io.IOException;

public class JsonSerializer implements Serializer {
    private ObjectMapper objectMapper;
    public JsonSerializer() {
        objectMapper = new ObjectMapper();
    }
    @Override
    public void serialize(DisplayObjects displayObjects) throws IOException {
        objectMapper.writeValue(new File("hello.json"), displayObjects);
    }

    @Override
    public DisplayObjects deserialize() throws IOException {
        return (DisplayObjects) objectMapper.readValue(new File("hello.json"), Object.class);
    }
}
