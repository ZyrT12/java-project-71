package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Objects;

public class DiffBuilder {
    public static List<DiffItem> build(Map<String, Object> data1, Map<String, Object> data2) {
        List<DiffItem> diff = new ArrayList<>();
        TreeSet<String> allKeys = new TreeSet<>();

        allKeys.addAll(data1.keySet());
        allKeys.addAll(data2.keySet());

        for (String key : allKeys) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                diff.add(new DiffItem(DiffType.removed, key, value1, null));
            } else if (!data1.containsKey(key)) {
                diff.add(new DiffItem(DiffType.added, key, null, value2));
            } else if (Objects.equals(value1, value2)) {
                diff.add(new DiffItem(DiffType.unchanged, key, value1, value2));
            } else {
                diff.add(new DiffItem(DiffType.updated, key, value1, value2));
            }
        }

        return diff;
    }
}
