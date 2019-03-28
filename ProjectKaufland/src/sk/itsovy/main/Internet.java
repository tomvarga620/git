package sk.itsovy.main;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Internet {

    public static Double getUSDrate() throws IOException {

        double dolariky=0.0;


        URL urlForGetRequest = new URL("http://data.fixer.io/api/latest?access_key=60f8264e958c4738a3417bee5c412baf");

        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) connection.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        jsonobj = jsonobj.getAsJsonObject("rates");

        dolariky = jsonobj.get("USD").getAsDouble();

        return dolariky;
    }
}
