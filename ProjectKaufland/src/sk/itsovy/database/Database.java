package sk.itsovy.database;

import sk.itsovy.items.Item;
import sk.itsovy.main.Globals;

import java.sql.*;

public class Database {

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


    public void insertNewtem(Item item){
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items () values(?,?,?,?)");
            stmt.setString(1, item.getName());

            int result = stmt.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
