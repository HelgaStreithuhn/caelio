/**
 * Klasse zur Verwaltung des Sensors PM25
 * --------------------------------------
 * Datensatz  Datensatz public
 */

/*
 * Stand: 10.03.2021
 */

// Er will den folgenden Import nicht.
// import org.json.*;

public class PM25 extends Sensor
{    
    public PM25()
    {
        super();
    }

    public void messen() throws Exception
    {
        // Hinzufuegen eines Messwerts
        datensatz.einfuegen(new Messwert(1));
        /*String url = "https://api.opensensemap.org/boxes/5cae0ffec9e9ab001af27595?"
            + "format=json";
        String rohdaten = InternetVerbinder.httpGetAnfrage(url);
        JSONObject json = (JSONObject) new JSONParser().parse(rohdaten);
        System.out.println("_id");*/
    }
}