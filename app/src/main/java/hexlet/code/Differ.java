package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> data1 = Parser.parse(filePath1);
        Map<String, Object> data2 = Parser.parse(filePath2);
        return formatStylish(data1, data2);
    }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        return formatStylish(data1, data2);
    }

    private static String formatStylish(Map<String, Object> data1, Map<String, Object> data2) {
        var allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : allKeys) {
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
