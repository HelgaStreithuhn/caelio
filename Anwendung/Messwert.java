/**
 * Klasse zur Speicherung eines Messwerts
 * --------------------------------------
 * Wert         double
 * Zeitstempel  int
 */


import java.time.Instant;
import java.util.Date;

public class Messwert
{
    private double wert;
    private long zeitstempel;
    
    /*
    public Messwert(double wert_)
    {
        wert = wert_;
        zeitstempel = Instant.now().toEpochMilli();
    }/**/
    
    public Messwert(double wert, long zeitmarke)
    {
        this.wert = wert;
        zeitstempel = zeitmarke;
    }
    
    public String toString()
    {
        return wert + " bei " + zeitstempel;
    }
    
    public double wertGeben()
    {
        return wert;
    }
    public double getWert(){return wertGeben();}
    
    public long zeitstempelGeben()
    {
        return zeitstempel;
    }
    public String getZeit(){return String.valueOf(new Date(zeitstempelGeben()));}
    
    public String ausgeben()
    {
        return wert + " bei " + zeitstempel;
    }
    
    public String wertAlsTextGeben(){
        return String.valueOf(wert);
    }
    
    /*public StringProperty getZeitProp(){
    
    }
    
    public StringProperty getWertProp(){
    
    }*/
}