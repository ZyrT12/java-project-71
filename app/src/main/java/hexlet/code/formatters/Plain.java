package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import hexlet.code.DiffItem;

public class Plain {
    public static String format(List<DiffItem> diff) {
        StringBuilder result = new StringBuilder();

        for (DiffItem item : diff) {
            switch (item.getType()) {
                case ADDED:
                    result.append(String.format("Property '%s' was added with value: %s\n",
                            item.getKey(), formatValue(item.getNewValue())));
                    break;
                case REMOVED:
                    result.append(String.format("Property '%s' was removed\n", item.getKey()));
                    break;
                case CHANGED:
                    result.append(String.format("Property '%s' was updated. From %s to %s\n",
                            item.getKey(), formatValue(item.getOldValue()),
                            formatValue(item.getNewValue())));
                    break;
                default:
                    break;
            }
        }

        return result.toString().trim();
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
}