import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
//        Car auticko = new Car("Volvo nejake","silver","P","PO 222ZE",20000);
//        database.addCar(auticko);
//        System.out.println("pridal som do databazy auto");

        List<Car> autickaaa = database.getCarsByPrice(11000000);
        for(Car i : autickaaa){
            System.out.println(i.getBrand()+" " + i.getColor() + " " +i.getFuel()+ " " +i.getSpz()+ " " +i.getPrice());
        }
        System.out.println("vypisal som auta podla max ceny");

        autickaaa = database.getCarsByBrand("skoda");
        for(Car i : autickaaa){
            System.out.println(i.getBrand()+" " + i.getColor() + " " +i.getFuel()+ " " +i.getSpz()+ " " +i.getPrice());
        }
        System.out.println("vypisal som auta podla znacky");

        autickaaa = database.getCarsByFuel('P');
        for(Car i : autickaaa){
            System.out.println(i.getBrand()+" " + i.getColor() + " " +i.getFuel()+ " " +i.getSpz()+ " " +i.getPrice());
        }
        System.out.println("vypisal som auta podla paliva");

        autickaaa = database.getCarsByRegion("KE");
        for(Car i : autickaaa){
            System.out.println(i.getBrand()+" " + i.getColor() + " " +i.getFuel()+ " " +i.getSpz()+ " " +i.getPrice());
        }
        System.out.println("vypisal som auta podla regionu");

        database.changeSPZ("PO 309JE","KK 123PO");
        System.out.println("zmenil som spz");

        autickaaa = database.getAllCars();
        for(Car i : autickaaa){
            System.out.println(i.getBrand()+" " + i.getColor() + " " +i.getFuel()+ " " +i.getSpz()+ " " +i.getPrice());
        }
        System.out.println("vypisal som vsetky auta");

        try {
            database.generateXML();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("vygeneroval som XML");

    }
}
