package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = Files.readString(Paths.get("src/test/resources/expected_stylish.txt"));
        expectedPlain = Files.readString(Paths.get("src/test/resources/expected_plain.txt"));
        expectedJson = Files.readString(Paths.get("src/test/resources/expected_json.txt"));
    }

    @Test
    public void testJsonStylish() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonPlain() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonJsonFormat() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json", "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testJsonDefaultFormat() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        assertEquals(expectedJson, actual);
    }


    @Test
    public void testYamlStylish() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testYamlPlain() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testYamlJsonFormat() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml", "json");
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testYamlDefaultFormat() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.yml", "src/test/resources/file2.yml");
        assertEquals(expectedStylish, actual);
    }
}
