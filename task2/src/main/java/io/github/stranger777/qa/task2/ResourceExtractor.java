package io.github.stranger777.qa.task2;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

public class ResourceExtractor {

    public ResourceExtractor() {
    }

    public static String getJsonStringFromFileByStringName(String JsonFilename, String jsonStringName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(JsonFilename)) {
            JsonReader jsr = Json.createReader(is);
            JsonObject jsonObject = jsr.readObject();
            //
            String quotedString = jsonObject.getJsonString(jsonStringName).toString();
            String unquotedString = quotedString.substring(1, quotedString.length() - 2);
            return unquotedString;
        }
    }
}
