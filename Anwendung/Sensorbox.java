/*
 * Klasse zur Verwaltung einer Sensorbox
 */

import org.json.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Sensorbox
{
    private String kennung, letzteMessung, name;
    public ArrayList<Sensor> sensoren;
    private ArrayList<Beobachter> beobachter;

    public Sensorbox(String kennung_)
    {
        // Eine Sensorbox hat eine einmalige Kennung
        // ITG: "606dabb74393eb001ca6a781"
        this.kennung = kennung_;

        // Eine Sensorbox hat mehrere Sensoren
        sensoren = new ArrayList<Sensor>();
        
        // Beobachter werden über Aenderungen informiert
        beobachter = new ArrayList<Beobachter>();
    }

    public String nameGeben()
    {
        return name;
    }

    public String neuesteDatenGeben(String name)
    {
        try
        {
            return String.valueOf(datensatzFinden(name).neustenMesswerteGeben().wertGeben()) + String.valueOf(datensatzFinden(name).einheitGeben());
        }
        catch (Exception e)
        {
            System.out.println(e + " (Klasse Sensorbox)");
        }
        return "N/A";
    }

    public void datenLaden() throws Exception
    {
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");

        JSONObject ergebnis = new JSONObject(rohdaten);
        name = ergebnis.getString("name");

        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sourceFormat.setTimeZone(utc);
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");
        

        letzteMessung = destFormat.format(sourceFormat.parse(ergebnis.getString("updatedAt")));

        sensoren.clear();
        JSONArray sensorenJSON = ergebnis.getJSONArray("sensors");
        for (Object sensor : sensorenJSON)
        {
            JSONObject sensorJSON = (JSONObject) sensor;

            Sensor neuerSensor = new Sensor(sensorJSON.getString("unit"),sensorJSON.getString("title"),sensorJSON.getString("_id"), this);
            JSONObject messwert = sensorJSON.getJSONObject("lastMeasurement");
            neuerSensor.messwertHinzufuegen(messwert.getString("createdAt"),messwert.getDouble("value"));

            sensoren.add(neuerSensor);
        }
    }

    public String getKennung()
    {
        return kennung;
    }
    
    public void datenNachladen() throws Exception
    {
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");
        JSONObject ergebnis = new JSONObject(rohdaten);
        
        //Setzt die letzte Messzeit fest
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sourceFormat.setTimeZone(utc);
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");
        letzteMessung = destFormat.format(sourceFormat.parse(ergebnis.getString("updatedAt")));
    }
    
    public Datensatz datensatzFinden(String name) throws Exception
    {
        for (Sensor sensor : sensoren)
        {
            if (sensor.nameGeben().equals(name))
            {
                return sensor.datensatzGeben();
            }
        }
        throw new Exception("Der angeforderte Datensatz konnte nicht gefunden werden: " + name);
    }
    
    public void sensorHatGemessen(String zeit)
    {
            letzteMessung = zeit;
            
            // Benachrichtigung der Beobachtern
            for (int i = 0; i < beobachter.size(); i ++)
            {
                beobachter.get(i).aktualisieren();
            }
    }
    
    public void interesseAnmelden(Beobachter beobachter_)
    {
        beobachter.add(beobachter_);
    }

    public String letzteMessung()
    {
        return letzteMessung;
    }
}
