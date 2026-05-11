package task_2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfProperties {
    private static Map<String, String> jsonData;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = objectMapper.readValue(
                    new File("src/test/resources/user.json"),
                    Map.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return jsonData.get(key);
    }
}
