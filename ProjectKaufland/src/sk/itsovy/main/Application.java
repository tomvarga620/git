package sk.itsovy.main;

import sk.itsovy.bill.Bill;
import sk.itsovy.exception.BillException;
import sk.itsovy.items.Category;
import sk.itsovy.items.Goods;
import sk.itsovy.items.Item;
import sk.itsovy.items.drink.Bottle;
import sk.itsovy.items.drink.Draft;
import sk.itsovy.items.drink.Drink;
import sk.itsovy.items.food.Fruit;
import sk.itsovy.items.food.Pastry;

import java.io.IOException;
import java.text.ParseException;

public class Application {

    //singleton design pattern
    private static Application app = new Application();

    private Application(){
    }

    public static Application getInstance(){
        return app;
    }

    public void example() throws BillException, ParseException, IOException {
        Bill bill = new Bill();
//        bill.print();
        System.out.println("Name Price Callories Amount");
        Bottle milk = new Bottle("milk 1,5%", 0.59, 2);
        bill.addItem(milk);
        Item pizza = new Pastry("Gazdovska pizza", 1.10, 280, 2);
        bill.addItem(pizza);
        Item apple = new Fruit("Red apple", 0.59, .370);
        bill.addItem(apple);
        Goods pencil = new Goods("Pencil 0.5", 0.60, 1, Category.SCHOOL);
        bill.addItem(pencil);
        Draft vinea = new Draft("White vinea", 1.20, true, 0.3);
        bill.addItem(vinea);
        Bottle pivko = new Bottle("Pivo tmave", 0.99, false, 1);
        bill.addItem(pivko);
        bill.removeItem(pivko);
        System.out.println(bill.getCount());

        System.out.println(bill.getFinalPrice());

        bill.print();
        bill.end();
        Internet.getUSDrate();
    }

}
