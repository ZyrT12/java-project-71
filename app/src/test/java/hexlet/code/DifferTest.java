package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String RESOURCES = "src/test/resources/";

    @Test
    public void testJsonDiff() throws Exception {
        String expected = Files.readString(Path.of(RESOURCES + "stylish.txt")).trim();
        String actual = Differ.generate(
                RESOURCES + "file1.json",
                RESOURCES + "file2.json"
        );
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlDiff() throws Exception {
        String expected = Files.readString(Path.of(RESOURCES + "stylish.txt")).trim();
        String actual = Differ.generate(
                RESOURCES + "file1.yml",
                RESOURCES + "file2.yml"
        );
        assertEquals(expected, actual);
    }
}
