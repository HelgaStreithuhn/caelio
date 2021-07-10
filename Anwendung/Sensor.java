import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/*
 * Klasse zur Verwaltung eines Datensatzes
 */

public class Sensor
{
    // Datensatz aller Unterklassen
    public Datensatz datensatz;
    public String name;
    public String id;
    private Sensorbox parent;

    public Sensor(String einheit, String name, String _id, Sensorbox parent)
    {
        this.parent = parent;
        this.name = name;
        this.id = _id;
        this.datensatz = new Datensatz(einheit);
        
        // Die Sensorbox besitzt einen Intervalltimer, der alle dort angemeldeten Sensoren regelmäßig 
        // zum Messen auffordert. Zwar könnte man auch für jeden Sensor einen eigenen Timer erstellen, 
        // das wäre jedoch langsamer (da mehr Objekte insgesamt) als geteilte Nutzung.
        // Um regelmäßig aktuelle Daten zu erhalten, muss sich der Sensor also dort anmelden:
        parent.anMessenErinnern(this);
        
    }

    
    
    public void messen() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + parent.getKennung() + "/sensors/" + id + "?format=json");
        System.out.println(rohdaten);
        JSONObject messwert = new JSONObject(rohdaten).getJSONObject("lastMeasurement");
        messwertHinzufuegen(messwert.getString("createdAt"),messwert.getDouble("value"));
    }
    
    public void messwertHinzufuegen(String timestamp, double value){
        
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        sourceFormat.setTimeZone(utc);
        
        String localTime = "unknown";
        try{
            localTime = destFormat.format(sourceFormat.parse(timestamp));
            long unixzeit = sourceFormat.parse(timestamp).getTime();
            datensatz.einfuegen(new Messwert(value, unixzeit));
        } catch (Exception e) {
            //datensatz.einfuegen(new Messwert(value));
            System.out.println(e + " (Klasse Sensor, Methode messwertHinzufuegen, beim Versuch den String " + timestamp + " als Datum zu verwenden)");
        } finally {
            parent.sensorHatGemessen(localTime, this.name);
        }
    }
    
    public String nameGeben(){
        return name;
    }
    
    public Datensatz datensatzGeben(){
        return datensatz;
    }

    public void ausgeben(){
        System.out.println("Sensordaten:");
        ArrayList<Messwert> messwerte = datensatz.messwerteGeben();
        for (int i = 0; i < messwerte.size(); i ++){
            System.out.println(messwerte.get(i).ausgeben());
        }
    }
}