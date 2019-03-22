package sk.itsovy.items;

public class Sweets extends Food implements Pcsinterface{
    private int amount;

    public Sweets(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount=amount;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
