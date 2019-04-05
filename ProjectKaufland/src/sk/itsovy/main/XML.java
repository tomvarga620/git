package sk.itsovy.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.itsovy.database.Database;
import sk.itsovy.items.Item;
import sk.itsovy.bill.Bill;
import sk.itsovy.items.Pcsinterface;
import sk.itsovy.items.drink.DraftInterface;
import sk.itsovy.items.food.Fruit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XML {

    public void createXML(Bill bill) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("bill");
        doc.appendChild(rootElement);

        Element id = doc.createElement("id");
        id.appendChild(doc.createTextNode(String.valueOf(bill.getId())));
        rootElement.appendChild(id);


        Element datum = doc.createElement("date");
        rootElement.appendChild(datum);

        Element datum1 = doc.createElement("dateOfPurchase");
        datum1.appendChild(doc.createTextNode(String.valueOf(bill.getDatetime())));
        datum.appendChild(datum1);

        Element items = doc.createElement("items");
        rootElement.appendChild(items);

//        Database database = new Database();
//        database.getAllPeople();


        for (Item aaa : bill.getList()) {
            Element item = doc.createElement("item");
            items.appendChild(item);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(aaa.getName()));
            item.appendChild(name);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(aaa.getPrice())));
            item.appendChild(price);

            Element amount = doc.createElement("amount");
            if (aaa instanceof DraftInterface){
                amount.appendChild(doc.createTextNode(String.valueOf(((DraftInterface) aaa).getVolume())));
                item.appendChild(amount);
            }
            else if (aaa instanceof Fruit){
                amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) aaa).getWeight())));
                item.appendChild(amount);
            }
            else if (aaa instanceof Pcsinterface){
                amount.appendChild(doc.createTextNode(String.valueOf(((Pcsinterface) aaa).getAmount())));
                item.appendChild(amount);
            }

            Element unit = doc.createElement("unit");
            if (item instanceof DraftInterface){
                unit.appendChild(doc.createTextNode("l"));
                item.appendChild(unit);
            }
            else if (item instanceof Fruit){
                unit.appendChild(doc.createTextNode("kg"));
                item.appendChild(unit);
            }
            else if (item instanceof Pcsinterface){
                unit.appendChild(doc.createTextNode("pcs"));
                item.appendChild(unit);
            }

        }

        Element price = doc.createElement("price");
        rootElement.appendChild(price);

        Element priceEur = doc.createElement("EUR");
        priceEur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
        price.appendChild(priceEur);

        Element priceDol = doc.createElement("USD");
        priceDol.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPriceDollars())));
        price.appendChild(priceDol);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("/Users/klaudiakriva/Development/SchoolJavaProjects/ProjectKaufland/bill.xml"));
        transformer.transform(source, result);

        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
