import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

public class Validations {

    String name;
    String surname;
    String rodneC;
    String datNar;


    public Validations(String name, String surname, String rodneC, String datNar) {
        this.name = name;
        this.surname = surname;
        this.rodneC = rodneC;
        this.datNar = datNar;
    }

    public boolean isValidRodne(String rodneC, String datNar){
        Pattern p = Pattern.compile(String.format("[0-9]{2}[0156][0-9]{3}/[0-9]{3,4}"));
        Matcher m = p.matcher(rodneC);


        Pattern r = Pattern.compile(String.format("(0?[1-9]|1[0-9]|2[0-9]|3[01]).(0?[1-9]|1[012]).(19[0-9]{2}|20[01][0-9])"));
        Matcher x = r.matcher(datNar);

        return (m.matches() && x.matches());
    }

    public boolean isValidRD(String rodneC){
        String noverod = rodneC.replaceAll("/", "");
        long rooood = Long.parseLong(noverod);
        return (rooood % 11 ==0);
    }

    public boolean isValidDigits(String rodneC, String datNar){
        if (rodneC.charAt(0) == datNar.charAt(datNar.length()-2) &&
                rodneC.charAt(1) == datNar.charAt(datNar.length()-1) &&
                (rodneC.charAt(2) == '0' ||
                rodneC.charAt(2) == '1' ||
                rodneC.charAt(2) == '5' ||
                rodneC.charAt(2) == '6')&&
                rodneC.charAt(3) == datNar.charAt(4) &&
                rodneC.charAt(4) == datNar.charAt(0) &&
                rodneC.charAt(5) == datNar.charAt(1)
                )
            return true;
        else
            return false;
    }


    //metoda  na porovnanie datumu a rodcisla podla cislic




}
