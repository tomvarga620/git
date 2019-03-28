package sk.itsovy.bill;

import sk.itsovy.exception.BillException;
import sk.itsovy.items.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sk.itsovy.items.Pcsinterface;
import sk.itsovy.items.drink.DraftInterface;
import sk.itsovy.items.food.Fruit;
import sk.itsovy.main.Globals;

public class Bill {
    private int count;
    private boolean open;
    private LocalDate datum = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private List<Item> list;
    public Bill(){
        this.list = new ArrayList<>();
        count = 0;
        open = true;
    }

    public void end() throws ParseException {
        if (open){
            System.out.println(datum + " " + time);
        }

        open = false;
    }

    public void addItem(Item item) throws BillException {
        if (count < Globals.MAXITEMS) {
            if (item != null) {
                if(open==false){
                    String message = "Bill is closed. It is not allowed to add new item.";
                    throw new BillException(message);
                }
                list.add(item);
                count++;
            }
        }
        else {
            String message = "Bill is full, max is 7 items";
            throw new BillException(message);
        }
    }

    public void removeItem(Item item){
        if(list.contains(item)) {
            list.remove(item);
            count--;
        }
    }

    public double getFinalPrice(){
        double finalPrice=0;
        for(Item item : list){
            finalPrice += item.getTotalPrice();
        }
        return finalPrice;
    }

    public double getFinalPriceDollars(){
        double finalP = getFinalPrice();
        return 0.0;

    }

    public int getCount(){
        return count;
    }

    public void print(){
        if (count == 0){
            System.out.println("Nothing to print. Bill is empty.");
        }
        else {
            for (Item item: list){
                if (item instanceof DraftInterface){
                    System.out.println(item.getName() + " " + ((DraftInterface) item).getVolume() + " "
                    + item.getPrice() + " " + item.getTotalPrice());
                }
                else if (item instanceof Fruit){
                    System.out.println(item.getName() + " " + ((Fruit) item).getWeight() + " "
                            + item.getPrice() + " " + item.getTotalPrice());
                }
                else if (item instanceof Pcsinterface){
                    System.out.println(item.getName() + " " + ((Pcsinterface) item).getAmount() + " "
                            + item.getPrice() + " " + item.getTotalPrice());
                }
            }


        }
    }


}
