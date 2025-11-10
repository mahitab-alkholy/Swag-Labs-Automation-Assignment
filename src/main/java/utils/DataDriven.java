package utils;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataDriven {
    private JsonObject root;

    public DataDriven(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JsonElement element = JsonParser.parseString(content);
            root = element.getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON: " + e.getMessage(), e);
        }
    }

    public JsonObject getObject(String key) {
        return root.getAsJsonObject(key);
    }

    public String getString(String parentKey, String childKey) {
        JsonObject parent = getObject(parentKey);
        return parent.get(childKey).getAsString();
    }

    public JsonArray getArray(String key) {
        return root.getAsJsonArray(key);
    }
}
