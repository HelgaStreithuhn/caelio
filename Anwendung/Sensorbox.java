/*
 * Klasse zur Verwaltung einer Sensorbox
 */

import org.json.*;
import java.util.ArrayList;

public class Sensorbox
{
    private String kennung;
    public String name;
    public ArrayList<Sensor> sensoren;
    public Sensorbox(String kennung_)
    {
        // Eine Sensorbox hat eine einmalige Kennung
        // ITG: "606dabb74393eb001ca6a781"
        this.kennung = kennung_;
        
        // Eine Sensorbox hat mehrere Sensoren
        sensoren = new ArrayList<Sensor>();
        sensoren.add(new Sensor("niceheit"));
    }
    
    public void datenLaden() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");
        System.out.println("Rohdaten: " + rohdaten);
        JSONObject ergebnis = new JSONObject(rohdaten);
        System.out.println("----------------------------");
        name = ergebnis.getString("name");
        JSONArray sensorenJSON = ergebnis.getJSONArray("sensors");
        for(Object sensor : sensorenJSON){
            JSONObject sensorJSON = (JSONObject) sensor;
            System.out.println(sensorJSON.toString());
        }
    }
}