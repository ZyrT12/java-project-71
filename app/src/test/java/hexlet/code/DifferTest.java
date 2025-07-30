package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedNested;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = Files.readString(Paths.get("src/test/resources/expected_stylish.txt")).trim();
        expectedNested = Files.readString(Paths.get("src/test/resources/expected_nested.txt")).trim();
    }

    @Test
    public void testFlatFiles() throws Exception {
        String filePath1 = "src/test/resources/file1.json";
        String filePath2 = "src/test/resources/file2.json";
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expectedStylish, actual.trim());
    }

    @Test
    public void testNestedFiles() throws Exception {
        String filePath1 = "src/test/resources/nested1.json";
        String filePath2 = "src/test/resources/nested2.json";
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expectedNested, actual.trim());
    }

    @Test
    public void testYamlFiles() throws Exception {
        String filePath1 = "src/test/resources/file1.yml";
        String filePath2 = "src/test/resources/file2.yml";
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expectedStylish, actual.trim());
    }
}
