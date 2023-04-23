package xyz.rajik;

import java.io.File;

public enum FileType {
    JSON("Json"), TEXT("Text");
    String name;

    FileType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
