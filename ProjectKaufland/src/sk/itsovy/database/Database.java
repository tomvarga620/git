package sk.itsovy.database;

import sk.itsovy.bill.Bill;
import sk.itsovy.items.Item;
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

                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
