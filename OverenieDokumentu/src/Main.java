import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

        File file = new File("/Users/klaudiakriva/Development/SchoolJavaProjects/OverenieDokumentu/src/dokument.txt");
        Scanner sc = new Scanner(file);

        Database database = new Database();
        int index = 0;
        String meno = "";
        String priezvisko = "";
        String rodnecislo = "";
        String datumNar = "";

//        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date date1 = null;
//        try {
//            date1 = dateformat1.parse("2008-08-22");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        while (sc.hasNextLine()) {
            if (meno.equals("") && sc.hasNext())
                meno = sc.next();

            if (priezvisko.equals("") && sc.hasNext())
                priezvisko = sc.next();

            if (rodnecislo.equals("") && sc.hasNext())
                rodnecislo = sc.next();

            if (datumNar.equals("")){
                datumNar = sc.next();
                SimpleDateFormat dateformat1 = new SimpleDateFormat("dd.MM.yyyy");

                Date date1 = null;
                try {
                    date1 = dateformat1.parse(datumNar);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String str = meno+ " " + priezvisko + " " + rodnecislo + " " + datumNar;
                Validations clovek = new Validations(meno,priezvisko,rodnecislo,datumNar);

                if (clovek.isValidRodne(rodnecislo, datumNar) && clovek.isValidRD(rodnecislo) && clovek.isValidDigits(rodnecislo, datumNar)) {
//                    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/klaudiakriva/Development/SchoolJavaProjects/OverenieDokumentu/src/write.txt", true));
//                    writer.append(str + "\n");
//                    writer.close();
                    Person osoba = new Person(meno,priezvisko, date1, rodnecislo);
//                    database.insertNewPerson(osoba);
                }
                System.out.println(clovek.isValidRodne(rodnecislo, datumNar) + " " + clovek.isValidRD(rodnecislo) + " " + clovek.isValidDigits(rodnecislo, datumNar));
                System.out.println(meno +" "+ priezvisko + rodnecislo + datumNar + " " +index);
            }
            else {
                index++;
                meno = "";
                priezvisko = "";
                rodnecislo = "";
                datumNar = "";
            }

        }

        Person osobka = database.selectPersonbyLastName("Kriva");
        System.out.println(osobka.getFname() + " " + osobka.getLname() + " " + osobka.getDob() + " " + osobka.getPin());

        Person osobka2 = database.selectPersonbyPIN("991230/1234");
        System.out.println(osobka2.getFname() + " " + osobka2.getLname() + " " + osobka2.getDob() + " " + osobka2.getPin());

        System.out.println(database.getNumberOfWomen());

        List<Person> a = database.getAllMen();
//        for (int i = 0; i < a.size(); i++) {
//            System.out.println(a.get(i).getFname() + a.get(i).getLname() + a.get(i).getDob() + a.get(i).getPin());
//        }
        for(Person p : a){
            System.out.println(p.getFname() + p.getLname() + p.getDob() + p.getPin());
        }

        List<Person> adults = database.getAdults();
        for(Person pee : adults){
            System.out.println(pee.getFname() + pee.getLname() + pee.getDob() + pee.getPin());
        }

        Set<String> firstnames = database.getFirstNames();
        for (String ccc : firstnames){
            System.out.println(ccc);
        }
        System.out.println(firstnames);

        List<Person> allPeople = database.getAllPeople();
        for (Person osoba : allPeople){
            System.out.println(osoba.getFname() + " " + osoba.getLname() + " " + osoba.getDob() + " " + osoba.getPin());
        }



//
//
//        File file = new File("/Users/klaudiakriva/Development/SchoolJavaProjects/OverenieDokumentu/src/dokument.txt");
//        Scanner sc = new Scanner(file);
//
//        int index = 0;
//        String meno = "";
//        String priezvisko = "";
//        String rodnecislo = "";
//        String datumNar = "";
//
//        while (sc.hasNextLine()) {
//            if (meno.equals("") && sc.hasNext())
//                meno = sc.next();
//
//            if (priezvisko.equals("") && sc.hasNext())
//                priezvisko = sc.next();
//
//            if (rodnecislo.equals("") && sc.hasNext())
//                rodnecislo = sc.next();
//
//            if (datumNar.equals("")){
//                datumNar = sc.next();
//                String str = meno+ " " + priezvisko + " " + rodnecislo + " " + datumNar;
//                Validations clovek = new Validations(meno,priezvisko,rodnecislo,datumNar);
//
//                if (clovek.isValidRodne(rodnecislo, datumNar) && clovek.isValidRD(rodnecislo) && clovek.isValidDigits(rodnecislo, datumNar)) {
//                    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/klaudiakriva/Development/SchoolJavaProjects/OverenieDokumentu/src/write.txt", true));
//                    writer.append(str + "\n");
//                    writer.close();
//                }
//                System.out.println(clovek.isValidRodne(rodnecislo, datumNar) + " " + clovek.isValidRD(rodnecislo) + " " + clovek.isValidDigits(rodnecislo, datumNar));
//                System.out.println(meno +" "+ priezvisko + rodnecislo + datumNar + " " +index);
//            }
//            else {
//                index++;
//                meno = "";
//                priezvisko = "";
//                rodnecislo = "";
//                datumNar = "";
//            }
//
//        }
    }
}
