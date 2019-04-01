package sk.itsovy.items.food;

import sk.itsovy.items.Pcsinterface;
import sk.itsovy.items.food.Food;

public class Sweets extends Food implements Pcsinterface {
    private int amount;

    public Sweets(String name, double price, int callories, int amount) {
        super(name, price, callories);
        this.amount=amount;
    }

    public Sweets(String name, double price, int amount) {
        this(name, price, -1, amount);
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

    @Override
    public void setAmount(int amount) {

    }
}
