package hexlet.code;

public class DiffItem {
    private final DiffType type;
    private final String key;
    private final Object oldValue;
    private final Object newValue;

    public DiffItem(DiffType type, String key, Object oldValue, Object newValue) {
        this.type = type;
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Геттеры
    public DiffType getType() { return type; }
    public String getKey() { return key; }
    public Object getOldValue() { return oldValue; }
    public Object getNewValue() { return newValue; }
}