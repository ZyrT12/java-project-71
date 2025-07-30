package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

public class Formatter {
    public static String format(List<DiffItem> diff, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> throw new Exception("Unknown format: " + format);
        };
    }
}
