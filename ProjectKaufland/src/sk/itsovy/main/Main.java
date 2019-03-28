package sk.itsovy.main;

import sk.itsovy.exception.BillException;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws BillException, ParseException, IOException {

        Application application = Application.getInstance();
        application.example();


    }
}
