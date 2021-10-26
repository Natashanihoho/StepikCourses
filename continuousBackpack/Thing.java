package continuousBackpack;

public class Thing {
    private int price;
    private int volume;

    public Thing(int price, int volume) {
        this.price = price;
        this.volume = volume;
    }

    public int getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public double getPriceForOne () {
        return (double)price / volume;
    }
}
