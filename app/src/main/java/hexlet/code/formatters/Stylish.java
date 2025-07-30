package hexlet.code.formatters;

import hexlet.code.DiffItem;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<DiffItem> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffItem item : diff) {
            switch (item.getType()) {
                case ADDED ->
                        result.append(String.format("  + %s: %s\n",
                                item.getKey(), formatValue(item.getNewValue())));
                case REMOVED ->
                        result.append(String.format("  - %s: %s\n",
                                item.getKey(), formatValue(item.getOldValue())));
                case UNCHANGED ->
                        result.append(String.format("    %s: %s\n",
                                item.getKey(), formatValue(item.getOldValue())));
                case CHANGED -> {
                    result.append(String.format("  - %s: %s\n",
                            item.getKey(), formatValue(item.getOldValue())));
                    result.append(String.format("  + %s: %s\n",
                            item.getKey(), formatValue(item.getNewValue())));
                }
                default -> {
                    throw new IllegalStateException("Unexpected value: " + item.getType());
                }
            }
        }
        return result.append("}").toString();
    }

    private static Object formatValue(Object value) {
        return value instanceof Map || value instanceof List
                ? value.toString()
                : value;
    }
}
