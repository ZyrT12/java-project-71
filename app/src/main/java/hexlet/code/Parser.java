package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
        ObjectMapper mapper = switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        return mapper.readValue(content, new TypeReference<>() {

        });
    }
}
