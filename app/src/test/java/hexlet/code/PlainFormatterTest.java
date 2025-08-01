package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import hexlet.code.formatters.Plain;

public class PlainFormatterTest {
    @Test
    public void testFormat() {
        List<DiffItem> diff = List.of(
                new DiffItem(DiffType.added, "key", null, "value"),
                new DiffItem(DiffType.removed, "key2", "old", null),
                new DiffItem(DiffType.updated, "key3", "old", "new"),
                new DiffItem(DiffType.updated, "key4", List.of(1, 2), "simple")
        );

        String expected = """
            Property 'key' was added with value: 'value'
            Property 'key2' was removed
            Property 'key3' was updated. From 'old' to 'new'
            Property 'key4' was updated. From [complex value] to 'simple'""";

        assertEquals(expected, Plain.format(diff));
    }
}

