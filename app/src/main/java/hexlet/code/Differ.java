package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {

    public static Map<String, Object> parseJsonFile(String filePath) throws Exception {
        try {
            Path path = Path.of(filePath).normalize().toAbsolutePath();
            if (!Files.exists(path)) {
                throw new NoSuchFileException("File not found: " + path);
            }

            String content = Files.readString(path);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });

        } catch (NoSuchFileException e) {
            throw new Exception("File not found: " + filePath, e);
        } catch (Exception e) {
            throw new Exception("Error parsing file: " + filePath + ". " + e.getMessage(), e);
        }
    }
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        var allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : allKeys) {
            Object val1 = map1.get(key);
            Object val2 = map2.get(key);

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (Objects.equals(val1, val2)) {
                    result.append("    ").append(key).append(": ").append(val1).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(val1).append("\n");
                    result.append("  + ").append(key).append(": ").append(val2).append("\n");
                }
            } else if (map1.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(val1).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(val2).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

}