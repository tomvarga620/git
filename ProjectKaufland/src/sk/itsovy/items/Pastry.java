package sk.itsovy.items;

public class Pastry extends Food implements Pcsinterface {
    private int amount;

    public Pastry(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount=amount;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public String getName() {
        return getName(); //alebo super.getname()
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
