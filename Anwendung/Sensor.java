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
        Timer timer = new Timer();
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
            }, 0, 20000);
    }

    
    
    public void messen() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + parent.getKennung() + "/sensors/" + id + "?format=json");
        //System.out.println(rohdaten);
        JSONObject messwert = new JSONObject(rohdaten).getJSONObject("lastMeasurement");
        messwertHinzufuegen(messwert.getString("createdAt"),messwert.getDouble("value"));
    }
    
    public void messwertHinzufuegen(String timestamp, double value){
        datensatz.einfuegen(new Messwert(value));
        
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat destFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        sourceFormat.setTimeZone(utc);
        
        try{
            parent.sensorHatGemessen(destFormat.format(sourceFormat.parse(timestamp)));
        }catch(Exception e){
            System.out.println(e + " (Klasse Sensor)");
        }

        
        //TODO: Die Zeit des eingefügten Messwertes stimmt noch nicht.
    }
    
    public String nameGeben()
    {
        return name;
    }
    
    public Datensatz datensatzGeben()
    {
        return datensatz;
    }

    public void ausgeben()
    {
        System.out.println("Sensordaten:");
        ArrayList<Messwert> messwerte = datensatz.messwerteGeben();
        for (int i = 0; i < messwerte.size(); i ++)
        {
            System.out.println(messwerte.get(i).ausgeben());
        }
    }
}