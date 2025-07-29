package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<DiffItem> diff, String format) {
        return switch (format) {
            case "stylish" -> formatStylish(diff);
            case "plain" -> formatPlain(diff);
            case "json" -> formatJson(diff);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }

    private static String formatStylish(List<DiffItem> diff) {
        StringBuilder result = new StringBuilder("{\n");
        for (DiffItem item : diff) {
            switch (item.getType()) {
                case ADDED -> result.append("  + ").append(item.getKey()).append(": ")
                        .append(valueToString(item.getNewValue())).append("\n");
                case REMOVED -> result.append("  - ").append(item.getKey()).append(": ")
                        .append(valueToString(item.getOldValue())).append("\n");
                case UNCHANGED -> result.append("    ").append(item.getKey()).append(": ")
                        .append(valueToString(item.getOldValue())).append("\n");
                case CHANGED -> {
                    result.append("  - ").append(item.getKey()).append(": ")
                            .append(valueToString(item.getOldValue())).append("\n");
                    result.append("  + ").append(item.getKey()).append(": ")
                            .append(valueToString(item.getNewValue())).append("\n");
                }
            }
        }
        return result.append("}").toString();
    }

    private static String formatPlain(List<DiffItem> diff) {
        StringBuilder result = new StringBuilder();
        for (DiffItem item : diff) {
            switch (item.getType()) {
                case ADDED -> result.append("Property '")
                        .append(item.getKey())
                        .append("' was added with value: ")
                        .append(formatValue(item.getNewValue()))
                        .append("\n");
                case REMOVED -> result.append("Property '")
                        .append(item.getKey())
                        .append("' was removed\n");
                case CHANGED -> result.append("Property '")
                        .append(item.getKey())
                        .append("' was updated. From ")
                        .append(formatValue(item.getOldValue()))
                        .append(" to ")
                        .append(formatValue(item.getNewValue()))
                        .append("\n");
            }
        }
        return result.toString().trim();
    }

    private static String formatJson(List<DiffItem> diff) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff);
        } catch (Exception e) {
            throw new RuntimeException("Error generating JSON output", e);
        }
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }

    private static String valueToString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Map || value instanceof List) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}