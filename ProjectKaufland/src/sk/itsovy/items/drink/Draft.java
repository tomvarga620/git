package sk.itsovy.items.drink;

public class Draft extends Drink implements DraftInterface {
    private double volume;

    public Draft(String name, double price, boolean sweet, double volume) {
        super(name, price, sweet);
        this.volume=volume;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public void setVolume(double volume) {
    }

    @Override
    public double getTotalPrice() {
        return volume*getPrice();
    }

    @Override
    public String getName() {
        return super.getName();
    }


}
