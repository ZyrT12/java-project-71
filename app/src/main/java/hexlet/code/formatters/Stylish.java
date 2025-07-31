package hexlet.code.formatters;

import hexlet.code.DiffItem;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<DiffItem> diff) {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffItem item : diff) {
            switch (item.getStatus()) {
                case added ->
                        result.append(String.format("  + %s: %s\n",
                                item.getKey(), formatValue(item.getNewValue())));
                case removed ->
                        result.append(String.format("  - %s: %s\n",
                                item.getKey(), formatValue(item.getOldValue())));
                case unchanged ->
                        result.append(String.format("    %s: %s\n",
                                item.getKey(), formatValue(item.getOldValue())));
                case updated -> {
                    result.append(String.format("  - %s: %s\n",
                            item.getKey(), formatValue(item.getOldValue())));
                    result.append(String.format("  + %s: %s\n",
                            item.getKey(), formatValue(item.getNewValue())));
                }
                default -> {
                    throw new IllegalStateException("Unexpected value: " + item.getStatus());
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
