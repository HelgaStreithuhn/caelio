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
        // Ein Sensor hat einen Datensatz
        datensatz = new Datensatz(einheit);

        // Initiieren der Beobachter

        // Messen
        parent.anMessenErinnern(this);
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask()
            {
                public void run()
                {
                    try
                    {
                        messen();
                    }
                    catch (Exception fehler)
                    {
                        System.out.println("Fehler beim Messen.");
                    }
                }
            }, 0, 20000);/**/
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
            datensatz.einfuegen(new Messwert(value));
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