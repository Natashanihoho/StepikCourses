package quickSort;

public class Points {
    private int value;
    private int entry;
    private int id;

    public Points(int id, int value) {
        this.value = value;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEntry(int entry) {
        this.entry = entry;
    }

    public int getEntry() {
        return entry;
    }

    public int getValue() {
        return value;
    }
}
