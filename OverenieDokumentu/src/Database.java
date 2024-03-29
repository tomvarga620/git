import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {

    private final String username = "klaudia";
    private final String password = "1234";
    private final String url = "jdbc:mysql://localhost:3306/db1";

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public void insertNewPerson(Person person){
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO person (fname,lname,dob,pin) values(?,?,?,?)");
            stmt.setString(1, person.getFname());
            stmt.setString(2, person.getLname());
            stmt.setDate(3, new Date(person.getDob().getTime()));
            stmt.setString(4, person.getPin());

            int result = stmt.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person selectPersonbyLastName(String lastname) throws SQLException {
        Connection conn = getConnection();
        String fname = "";
        String lname = lastname;
        Date date = new Date(1998-10-10);
        String pin = "";
        try {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * from person where person.lname like ?")) {
                stmt.setString(1, lname);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    date = rs.getDate("dob");
                    pin = rs.getString("pin");
                    conn.close();
                    Person person = new Person(fname,lname,date,pin);
                    return person;
                }
                else {
                    conn.close();
                    return null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Person selectPersonbyPIN(String pin) throws SQLException {
        Connection conn = getConnection();
        String fname = "";
        String lname = "";
        Date date = new Date(1998-10-10);
        String PIN = pin;
        try {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * from person where person.pin like ?")) {
                stmt.setString(1, pin);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    fname = rs.getString("fname");
                    lname = rs.getString("lname");
                    date = rs.getDate("dob");
                    PIN = rs.getString("pin");
                    conn.close();
                    Person person = new Person(fname,lname,date,pin);
                    return person;
                }
                else {
                    conn.close();
                    return null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getNumberOfWomen() throws SQLException {
        Connection conn = getConnection();
        try {
            try (PreparedStatement stmt = conn.prepareStatement("SELECT count(*) as pocet from person where pin like '__5%' or pin like '__6%'")) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
//                    conn.close();
                    return rs.getInt("pocet");
                }
                else {
                    conn.close();
                    return 0;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Person> getAllMen(){
        Connection conn = getConnection();
        String query = "Select * from person where pin like '__0%' or pin like '__1%'";
        List<Person> men = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            rs = stmnt.executeQuery();
            while (rs.next()){
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Date date = rs.getDate("dob");
                String pin = rs.getString("pin");
                Person p = new Person(fname,lname,date,pin);
                men.add(p);
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return men;
    }

    public List<Person> getAdults(){
        Connection conn = getConnection();

        LocalDate localdate = LocalDate.now();

        String daaaatum = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localdate);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();

        String query = "SELECT * from person where dob <= ? - interval 18 year";
        List<Person> adults = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            stmnt.setString(1, daaaatum);
            System.out.println(daaaatum);
            rs = stmnt.executeQuery();
            while (rs.next()){
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Date date = rs.getDate("dob");
                String pin = rs.getString("pin");
                Person p = new Person(fname,lname,date,pin);
                adults.add(p);
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adults;
    }

    public Set<String> getFirstNames(){
        Connection conn = getConnection();
        String query = "select fname from person";
        Set<String> firstnames =  new HashSet<String>();
        ResultSet rs;

        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            rs = stmnt.executeQuery();
            while (rs.next()){
                String fname = rs.getString("fname");
                firstnames.add(fname);
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstnames;

    }

    public List<Person> getAllPeople(){
        Connection conn = getConnection();
        String query = "SELECT * from person";
        List<Person> adults = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmnt = conn.prepareStatement(query);
            rs = stmnt.executeQuery();
            while (rs.next()){
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                Date date = rs.getDate("dob");
                String pin = rs.getString("pin");
                Person p = new Person(fname,lname,date,pin);
                adults.add(p);
            }
            closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adults;
    }


    private void closeConnection(Connection conn){

    }

}
