package sk.itsovy.items;

public class Bottle extends Drink implements Pcsinterface{
    private int amount;

    public Bottle(String name, double price, boolean sweet, int amount) {
        super(name, price, sweet);
        this.amount=amount;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
