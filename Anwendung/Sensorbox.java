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
    private Messintervallgeber mig;

    public Sensorbox(String kennung_)
    {
        // Jede Sensorbox hat eine einmalige Kennung
        this.kennung = kennung_;
        sensoren = new ArrayList<Sensor>();

        // Beobachter werden über Aenderungen informiert
        beobachter = new ArrayList<Beobachter>();
        
        //Messzeitgeber lässt Sensoren daten nachladen
        mig = new Messintervallgeber(20000);
        mig.start();
    }
    
    public void anMessenErinnern(Sensor s){
        mig.sensorAnmelden(s);
    }
    
    public void datenLaden() throws Exception
    {
        //Diese Methode initialisiert die Sensoren der Sensorbox.

        //als erstes werden alle aktuellen Messungen der Sensoren im JSON-Format abgerufen:
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + kennung + "?format=json");

        JSONObject ergebnis = new JSONObject(rohdaten);
        this.name = ergebnis.getString("name");

        //Zeitzone und Zeitformat werden angepasst (ursprünglich UTC und komische Schreibweise)
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sourceFormat.setTimeZone(utc);
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");

        letzteMessung = destFormat.format(sourceFormat.parse(ergebnis.getString("updatedAt")));

        sensoren.clear();
        JSONArray sensorenJSON = ergebnis.getJSONArray("sensors");
        for (Object sensor : sensorenJSON){
            JSONObject sensorJSON = (JSONObject) sensor;

            Sensor neuerSensor = new Sensor(sensorJSON.getString("unit"),sensorJSON.getString("title"),sensorJSON.getString("_id"), this);
            JSONObject messwert = sensorJSON.getJSONObject("lastMeasurement");
            neuerSensor.messwertHinzufuegen(messwert.getString("createdAt"),messwert.getDouble("value"));

            sensoren.add(neuerSensor);
        }
    }
    
    
    public String nameGeben(){return name;}
    public String getKennung(){return kennung;}
    public String letzteMessungGeben(){return letzteMessung;}
    
    
    public String neuesteDatenGeben (String name) {
        try{
            return String.valueOf(datensatzFinden(name).neustenMesswerteGeben().wertGeben()) + String.valueOf(datensatzFinden(name).einheitGeben());
        }
        catch (Exception e){
            System.out.println(e + " (Klasse Sensorbox)");
        }
        return "N/A";
    }
    
    public String extremDatenGeben (String name, boolean max) {
        try{
            if (max) {
                return String.valueOf(datensatzFinden(name).maxMesswerteGeben().wertGeben()) + String.valueOf(datensatzFinden(name).einheitGeben());
            } else {
                return String.valueOf(datensatzFinden(name).minMesswerteGeben().wertGeben()) + String.valueOf(datensatzFinden(name).einheitGeben());
            }
        }
        catch (Exception e){
            System.out.println(e + " (Klasse Sensorbox)");
        }
        return "N/A";
    }
    
    public Datensatz datensatzFinden(String name) throws Exception
    {
        for (Sensor sensor : sensoren){
            if (sensor.nameGeben().equals(name)){
                return sensor.datensatzGeben();
            }
        }
        throw new Exception("Der angeforderte Datensatz konnte nicht gefunden werden: " + name);
    }
    


    public void sensorHatGemessen(String zeit, String welcherSensor)
    {
        letzteMessung = zeit;
        // Benachrichtigung der Beobachter
        for (int i = 0; i < beobachter.size(); i++){
            beobachter.get(i).aktualisieren(welcherSensor);
        }
    }

    public void interesseAnmelden(Beobachter beobachter_)
    {
        beobachter.add(beobachter_);
    }

}
