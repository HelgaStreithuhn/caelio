import java.util.ArrayList;

import org.json.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.lang.ref.*;

public class Sensor
{
    // Datensatz aller Unterklassen
    public Datensatz datensatz;
    public String name;
    public String id;
    private WeakReference<Sensorbox> parent;

    public Sensor(String einheit, String name, String _id, Sensorbox parent)
    {
        /* da der Sensor die Sensorbox informieren muss (z. B. wenn er neu gemessen hat)
        * benötigt jeder Sensor eine Referenz auf die Sensorbox.
        * Wenn jedoch die Sensorbox zurückgesetzt oder ersetzt wird, verhindert eben diese Referenz eine
        * Löschung der Sensorbox. Deshalb muss diese Verbindung als WeakReference 
        * erstellt sein; weak-Verbindungen reichen nämlich nicht aus um die Löschung zu verhindern */
        this.parent = new WeakReference(parent);
        this.name = name;
        this.id = _id;
        this.datensatz = new Datensatz(einheit);
        
        /* Die Sensorbox besitzt einen Intervalltimer, der alle dort angemeldeten Sensoren regelmäßig 
        * zum Messen auffordert. Zwar könnte man auch für jeden Sensor einen eigenen Timer erstellen, 
        * das wäre jedoch langsamer (da mehr Objekte insgesamt) als geteilte Nutzung.
        * Um regelmäßig aktuelle Daten zu erhalten, muss sich der Sensor also dort anmelden: */
        parent.anMessenErinnern(this);
        
    }

    
    
    public void messen() throws Exception{
        String rohdaten = InternetVerbinder.httpGetAnfrage("https://api.opensensemap.org/boxes/" + parent.get().getKennung() + "/sensors/" + id + "?format=json");
        //System.out.println(rohdaten);
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
            datensatz.einfuegen(new Messwert(value, unixzeit, datensatz.einheitGeben()));
        } catch (Exception e) {
            //datensatz.einfuegen(new Messwert(value));
            System.out.println(e + " (Klasse Sensor, Methode messwertHinzufuegen, während Versuch den String " + timestamp + " als Datum zu verwenden)");
        } finally {
            parent.get().sensorHatGemessen(localTime, this.name);
        }
    }
    
    public String nameGeben(){
        return name;
    }
    
    public Datensatz datensatzGeben(){
        return datensatz;
    }

    public void ausgeben(){
        System.out.println("Diese Methode ist veraltet und sollte nicht verwendet werden.");
        /*ArrayList<Messwert> messwerte = datensatz.messwerteGeben();
        for (int i = 0; i < messwerte.size(); i ++){
            System.out.println(messwerte.get(i).ausgeben());
        }*/
    }
}
