import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database implements carmethods{
    private final String username = "klaudia";
    private final String password = "1234";
    private final String url = "jdbc:mysql://localhost:3306/sova7";

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drived is loaded!");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void addCar(Car car) {
        Connection con = getConnection();
        try {
            PreparedStatement stmnt = con.prepareStatement("Insert into cars (brand, color, fuel, spz, price) values(?,?,?,?,?)");
            stmnt.setString(1, car.getBrand());
            stmnt.setString(2, car.getColor());
            stmnt.setString(3, String.valueOf(car.getFuel()));
            stmnt.setString(4, car.getSpz());
            stmnt.setInt(5, car.getPrice());

            int result = stmnt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> getCarsByPrice(int maxPrice) {
        Connection connection = getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement stmnt = connection.prepareStatement("select * from cars where price < ? order by price desc");
            stmnt.setInt(1,maxPrice);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                String brand = rs.getString("brand");
                String color = rs.getString("color");
                char fuel = rs.getString("fuel").charAt(0);
                String spz = rs.getString("spz");
                int price = rs.getInt("price");

                Car car = new Car(brand,color,fuel,spz,price);
                cars.add(car);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        Connection connection = getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement stmnt = connection.prepareStatement("select * from cars where brand like ?");
            stmnt.setString(1, brand + "%");
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                String brand1 = rs.getString("brand");
                String color = rs.getString("color");
                char fuel = rs.getString("fuel").charAt(0);
                String spz = rs.getString("spz");
                int price = rs.getInt("price");

                Car car = new Car(brand1,color,fuel,spz,price);
                cars.add(car);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByFuel(char fuel) {
        Connection connection = getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement stmnt = connection.prepareStatement("select * from cars where fuel = ?");
            stmnt.setString(1, String.valueOf(fuel));
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                String brand1 = rs.getString("brand");
                String color = rs.getString("color");
                char fuel1 = rs.getString("fuel").charAt(0);
                String spz = rs.getString("spz");
                int price = rs.getInt("price");

                Car car = new Car(brand1,color,fuel1,spz,price);
                cars.add(car);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public List<Car> getCarsByRegion(String spz) {
        Connection connection = getConnection();
        List<Car> cars = new ArrayList<>();
        try {
            PreparedStatement stmnt = connection.prepareStatement("select * from cars where spz like ?");
            stmnt.setString(1, spz+"%");
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()){
                String brand1 = rs.getString("brand");
                String color = rs.getString("color");
                char fuel1 = rs.getString("fuel").charAt(0);
                String spz1 = rs.getString("spz");
                int price = rs.getInt("price");

                Car car = new Car(brand1,color,fuel1,spz1,price);
                cars.add(car);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public void changeSPZ(String oldSPZ, String newSPZ) {
        Connection connection = getConnection();
        try {
            PreparedStatement stmnt = connection.prepareStatement("update cars set spz = ? where spz like ?");
            stmnt.setString(1,newSPZ);
            stmnt.setString(2, oldSPZ);
            int result = stmnt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars(){
        Connection conn = getConnection();
        String query = "SELECT * from cars";
        List<Car> cars = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            rs = stmnt.executeQuery();
            while (rs.next()){
                String brand1 = rs.getString("brand");
                String color = rs.getString("color");
                char fuel1 = rs.getString("fuel").charAt(0);
                String spz1 = rs.getString("spz");
                int price = rs.getInt("price");

                Car car = new Car(brand1,color,fuel1,spz1,price);
                cars.add(car);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    @Override
    public void generateXML() throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("cars");
        doc.appendChild(rootElement);

        Database database = new Database();
        database.getAllCars();
        List<Car> allPeople = database.getAllCars();

        for (Car aaa : allPeople) {
            Element car = doc.createElement("car");
            rootElement.appendChild(car);

            Element brand = doc.createElement("brand");
            brand.appendChild(doc.createTextNode(aaa.getBrand()));
            car.appendChild(brand);

            Element color = doc.createElement("color");
            color.appendChild(doc.createTextNode(aaa.getColor()));
            car.appendChild(color);

            Element fuel = doc.createElement("fuel");
            fuel.appendChild(doc.createTextNode(String.valueOf(aaa.getFuel())));
            car.appendChild(fuel);

            Element spz = doc.createElement("spz");
            spz.appendChild(doc.createTextNode(aaa.getSpz()));
            car.appendChild(spz);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(aaa.getPrice())));
            car.appendChild(price);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/klaudiakriva/Development/SchoolJavaProjects/SOVA7/src/cars.xml"));
        transformer.transform(source, result);

        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
