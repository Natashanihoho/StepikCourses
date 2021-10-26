package continuousBackpack;

public class Backpack {
    private int capacity;
    private int freeSpace;

    public Backpack(int capacity) {
        this.capacity = capacity;
        this.freeSpace = capacity;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.freeSpace = freeSpace;
    }
}
