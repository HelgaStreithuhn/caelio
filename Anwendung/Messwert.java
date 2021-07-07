/**
 * Klasse zur Speicherung eines Messwerts
 * --------------------------------------
 * Wert         double
 * Zeitstempel  int
 */


import java.time.Instant;

public class Messwert
{
    private double wert;
    private long zeitstempel;
    
    public Messwert(double wert_)
    {
        wert = wert_;
        zeitstempel = Instant.now().toEpochMilli();
    }
    
    public String toString()
    {
        return wert + " bei " + zeitstempel;
    }
    
    public double wertGeben()
    {
        return wert;
    }
    
    public long zeitstempelGeben()
    {
        return zeitstempel;
    }
    
    public String ausgeben()
    {
        return wert + " bei " + zeitstempel;
    }
    
    public String wertAlsTextGeben(){
        return String.valueOf(wert);
    }
    
}