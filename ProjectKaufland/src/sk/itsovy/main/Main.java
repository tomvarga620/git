package sk.itsovy.main;

import sk.itsovy.exception.BillException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws BillException, ParseException, IOException, TransformerException, ParserConfigurationException {

        Application application = Application.getInstance();
        application.example();


    }
}
