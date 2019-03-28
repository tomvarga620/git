package sk.itsovy.items.drink;

import sk.itsovy.items.Pcsinterface;

public class Bottle extends Drink implements Pcsinterface {
    private int amount;

    public Bottle(String name, double price, boolean sweet, int amount) {
        super(name, price, sweet);
        this.amount=amount;
    }

    public Bottle(String name, double price, int amount) {
        this(name, price,false, amount);
    }

    public Bottle(String name, double price, boolean sweet) {
        this(name, price, sweet,1);
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
