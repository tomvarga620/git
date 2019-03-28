package sk.itsovy.items;

import sk.itsovy.items.Item;

public class Goods extends Item implements Pcsinterface{
    private int amount;
    private Category type;

    public Goods(String name, double price, int amount, Category category) {
        super(name, price);
        this.amount=amount;
        this.type=category;
    }

    public Goods(String name, double price, Category type) {
        super(name, price);
        this.type = type;
    }

    @Override
    public double getTotalPrice() {
        return amount*getPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
