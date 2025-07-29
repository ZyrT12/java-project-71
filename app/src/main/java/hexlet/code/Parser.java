package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filePath) throws IOException {
        ObjectMapper mapper = getMapper(filePath);
        return mapper.readValue(new File(filePath), Map.class);
    }

    private static ObjectMapper getMapper(String filePath) {
        if (filePath.endsWith(".json")) {
            return new ObjectMapper();
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory());
        } else {
            throw new RuntimeException("Unsupported file format: " + filePath);
        }
    }
}
