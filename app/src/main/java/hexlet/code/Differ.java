package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.HashMap;

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
}