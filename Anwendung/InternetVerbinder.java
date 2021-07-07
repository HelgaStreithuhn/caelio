import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public abstract class InternetVerbinder {
    public static String httpGetAnfrage (String url) throws Exception {
        URL adresse = new URL(url);
        // Verbindung zum Internet wird hergestellt
        URLConnection verbindung = adresse.openConnection();
        // Website, zu der Verbindung hergestellt wurde, auslesen
        BufferedReader ein = new BufferedReader(new InputStreamReader(verbindung.getInputStream()));
        
        // Ganze Website auslesen und in String text speichern:
        String eingangsZeile;
        String text = "";
        while ((eingangsZeile = ein.readLine()) != null){
            text = text + "\n" + eingangsZeile;
        }
        //Verbindung wird nicht mehr benötigt -> wird geschlossen
        ein.close();
        
        return text;
    }
    
}
