package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffItem;
import java.util.List;

public class Json {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String format(List<DiffItem> diff) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(diff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON formatting error", e);
        }
    }
}
