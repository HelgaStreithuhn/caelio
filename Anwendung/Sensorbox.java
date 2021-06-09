/*
 * Klasse zur Verwaltung einer Sensorbox
 */

import org.json.*;
import java.util.ArrayList;

public class Sensorbox
{
    private String kennung;
    private String name;
    public ArrayList<Sensor> sensoren;

    public Sensorbox(String kennung_)
    {
        // Eine Sensorbox hat eine einmalige Kennung
        // ITG: "606dabb74393eb001ca6a781"
        this.kennung = kennung_;

        // Eine Sensorbox hat mehrere Sensoren
        sensoren = new ArrayList<Sensor>();
    }

    public String nameGeben(){
        return name;
    }
    
    public String neuesteDatenGeben(String name){
        try{
            return String.valueOf(datensatzFinden(name).neustenMesswerteGeben().wertGeben()) + String.valueOf(datensatzFinden(name).einheitGeben());
        } catch (Exception e){System.out.println(e);}
        return "N/A";
    }
    
    public void datenLaden() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");

        JSONObject ergebnis = new JSONObject(rohdaten);
        name = ergebnis.getString("name");

        sensoren.clear();
        JSONArray sensorenJSON = ergebnis.getJSONArray("sensors");
        for(Object sensor : sensorenJSON){
            JSONObject sensorJSON = (JSONObject) sensor;

            Sensor neuerSensor = new Sensor(sensorJSON.getString("unit"),sensorJSON.getString("title"),sensorJSON.getString("_id"));
            JSONObject messwert = sensorJSON.getJSONObject("lastMeasurement");
            neuerSensor.messwertHinzufuegen(messwert.getString("createdAt"),messwert.getDouble("value"));

            sensoren.add(neuerSensor);
        }
    }

    public Datensatz datensatzFinden(String name) throws Exception{
        for (Sensor sensor : sensoren)
        {
            if (sensor.nameGeben().equals(name)) // String-Vergleich mit .equals()
            {
                return sensor.datensatzGeben();
            }
        }
        throw new Exception("Der angeforderte Datensatz konnte nicht gefunden werden: " + name);
    }
}