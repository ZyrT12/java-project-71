package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = parseFile(filePath1);
        Map<String, Object> data2 = parseFile(filePath2);
        List<DiffItem> diff = buildDiff(data1, data2);
        return Formatter.format(diff, format);
    }

    private static Map<String, Object> parseFile(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper mapper = filePath.endsWith(".yaml") || filePath.endsWith(".yml")
                ? new YAMLMapper()
                : new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() {});
    }

    private static List<DiffItem> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        TreeSet<String> allKeys = new TreeSet<>();
        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        List<DiffItem> diff = new ArrayList<>();
        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                diff.add(new DiffItem(DiffType.REMOVED, key, value1, null));
            } else if (!data1.containsKey(key)) {
                diff.add(new DiffItem(DiffType.ADDED, key, null, value2));
            } else if (isEqual(value1, value2)) {
                diff.add(new DiffItem(DiffType.UNCHANGED, key, value1, value2));
            } else {
                diff.add(new DiffItem(DiffType.CHANGED, key, value1, value2));
            }
        }
        return diff;
    }

    private static boolean isEqual(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        if (value1 == null || value2 == null) {
            return false;
        }
        return value1.equals(value2);
    }
}