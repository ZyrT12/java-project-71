package hexlet.code;

import java.util.Map;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = Files.readString(Path.of(filePath1));
        String content2 = Files.readString(Path.of(filePath2));

        String extension1 = getExtension(filePath1);
        String extension2 = getExtension(filePath2);

        Map<String, Object> data1 = Parser.parse(content1, extension1);
        Map<String, Object> data2 = Parser.parse(content2, extension2);

        List<DiffItem> diff = DiffBuilder.build(data1, data2);
        return Formatter.format(diff, format);
    }

    private static String getExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filePath.length() - 1) {
            throw new IllegalArgumentException("Cannot determine file extension for: " + filePath);
        }
        return filePath.substring(dotIndex + 1);
    }
}
