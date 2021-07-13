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
    private final String einheit;
    public Messwert(double wert, long zeitmarke, String einheit)
    {
        this.wert = wert;
        zeitstempel = zeitmarke;
        this.einheit = einheit;
    }
    
    public String toString()
    {
        return wert + " bei " + zeitstempel;
    }
    
    public double wertGeben()
    {
        return wert;
    }
    public String getWert(){return String.valueOf(wertGeben()) + einheit;}
    
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
}