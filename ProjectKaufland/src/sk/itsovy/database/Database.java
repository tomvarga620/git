package sk.itsovy.database;

import sk.itsovy.bill.Bill;
import sk.itsovy.items.Item;
import sk.itsovy.items.Pcsinterface;
import sk.itsovy.items.drink.DraftInterface;
import sk.itsovy.items.food.Fruit;
import sk.itsovy.main.Globals;

import java.sql.*;

public class Database {

    private static Database dat = new Database();
    private Database(){
    }
    public static Database getInstanceDatab(){
        return dat;
    }

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName(Globals.databClassforName);
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(Globals.url, Globals.username,Globals.password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public void insertData(Bill bill){
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bill (datetime, totalPrice) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, new java.sql.Timestamp(bill.getDatetime().getTime()));
            stmt.setDouble(2, bill.getFinalPrice());

            int result = stmt.executeUpdate();

            if (result == 0){
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if (generatedKeys.next()) {
//                    user.setId(generatedKeys.getLong(1));
                    System.out.println(generatedKeys.getLong(1));
                    for(Item item : bill.getList()){
                        PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO items (orderID, name, price, count, unit) values(?,?,?,?,?)");
                        stmt1.setString(1, String.valueOf(generatedKeys.getLong(1)));
                        stmt1.setString(2, item.getName());
                        stmt1.setDouble(3, item.getPrice());
                        if (item instanceof DraftInterface){
                            stmt1.setDouble(4, ((DraftInterface) item).getVolume());
                            stmt1.setString(5, "l");

                        }
                        else if (item instanceof Fruit){
                            stmt1.setDouble(4, ((Fruit) item).getWeight());
                            stmt1.setString(5, "kg");
                        }
                        else if (item instanceof Pcsinterface){
                            stmt1.setDouble(4, ((Pcsinterface) item).getAmount());
                            stmt1.setString(5, "pcs");
                        }


                        int ex = stmt1.executeUpdate();
                    }

                }
                else {
                    throw new SQLException("Creating bill failed, no ID obtained.");
                }
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
