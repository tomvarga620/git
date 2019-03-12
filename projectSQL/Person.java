import java.util.Date;

public class Person {

    private String fname;
    private String lname;
    private Date dob;
    private String pin;

    public Person(String fname, String lname, Date dob, String pin) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.pin = pin;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public String getPin() {
        return pin;
    }
}
