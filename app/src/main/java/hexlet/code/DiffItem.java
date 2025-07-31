package hexlet.code;

public final class DiffItem {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final DiffType status;

    public DiffItem(DiffType status, String key, Object oldValue, Object newValue) {
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.status = status;
    }

    public String getKey() {
        return key;
    }
    public Object getOldValue() {
        return oldValue;
}

    public Object getNewValue() {
        return newValue;
    }
    public DiffType getStatus() {
        return status;
    }
}
