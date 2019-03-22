package sk.itsovy.items;

public class Fruit extends Food{

    private double weight;

    public Fruit(String name, double price, int callories, double weight) {
        super(name, price, callories);
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public double getTotalPrice() {
        return weight*getPrice();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
