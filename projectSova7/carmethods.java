import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;

public interface carmethods {

    public void addCar( Car car);

    public List<Car> getCarsByPrice(int maxPrice);

    public List<Car> getCarsByBrand(String brand);

    public List<Car> getCarsByFuel(char fuel);

    public List<Car> getCarsByRegion(String spz);

    public void changeSPZ(String oldSPZ, String newSPZ);

    public void generateXML() throws TransformerException, ParserConfigurationException;
}
