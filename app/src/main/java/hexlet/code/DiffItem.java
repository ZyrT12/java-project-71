package hexlet.code;
public final class DiffItem {
    private final String key;
    private final Object oldValue;
    private final Object newValue;
    private final DiffType status;

    public DiffItem(final DiffType statusType,
                    final String itemKey,
                    final Object oldVal,
                    final Object newVal) {
        this.status = statusType;
        this.key = itemKey;
        this.oldValue = oldVal;
        this.newValue = newVal;
    }

    public String getKey() {
        return this.key;
    }

    public Object getOldValue() {
        return this.oldValue;
    }

    public Object getNewValue() {
        return this.newValue;
    }

    public DiffType getStatus() {
        return this.status;
    }
}
