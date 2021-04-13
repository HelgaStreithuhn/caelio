/*
 * Klasse zur Verwaltung einer Sensorbox
 */

import org.json.*;

public class Sensorbox
{
    private String kennung;
    
    public Sensorbox(String kennung_)
    {
        // Eine Sensorbox hat eine einmalige Kennung
        // ITG: "606dabb74393eb001ca6a781"
        this.kennung = kennung_;
        
        // Eine Sensorbox hat mehrere Sensoren
        //Sensor sensor1 = new Sensor();
        //Sensor sensor2 = new Sensor();
    }
    
    public void datenLaden() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");
        System.out.println(rohdaten);
        //JSON.parse(rohdaten);
    }
}