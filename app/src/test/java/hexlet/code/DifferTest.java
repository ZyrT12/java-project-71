package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String RESOURCES = "src/test/resources/";

    @Test
    public void testFlatJsonDiff() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> data1 = objectMapper.readValue(
                new File(RESOURCES + "file1.json"),
                new TypeReference<>() {}
        );

        Map<String, Object> data2 = objectMapper.readValue(
                new File(RESOURCES + "file2.json"),
                new TypeReference<>() {}
        );

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String actual = Differ.generate(data1, data2);

        assertEquals(expected, actual);
    }
}
