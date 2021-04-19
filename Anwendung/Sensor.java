import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * Klasse zur Verwaltung eines Datensatzes
 */

public class Sensor
{
    // Datensatz aller Unterklassen
    public Datensatz datensatz;
    private ArrayList<Beobachter> beobachter;
    private String kennung;
    public String name;
    public String _id;

    public Sensor(String einheit, String name, String _id)
    {
        this.name = name;
        this._id = _id;
        // Ein Sensor hat einen Datensatz
        datensatz = new Datensatz(einheit);

        // Initiieren der Beobachter
        beobachter = new ArrayList<Beobachter>();

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

                    // Senden an alle Beobachter
                    for (int i = 0; i < beobachter.size(); i ++)
                    {
                        beobachter.get(i).aktualisieren(datensatz);
                    }
                }
            }, 0, 10000);
    }

    public void registrieren(Beobachter beobachter_)
    {
        beobachter.add(beobachter_);
    }

    
    
    public void messen() throws Exception
    {
        /*// Hinzufuegen eines Messwerts
        datensatz.einfuegen(new Messwert(1));
        String url = "https://api.opensensemap.org/boxes/" + kennung
            + "?format=json";*/
            throw new Exception("Achtung: toter Code");
    }

    /* Methodenname ist recht selbstbeschreibend*/
    public void messwertHinzufuegen(String timestamp, double value){
        System.out.println(name + ": " + value + " (" + timestamp + ")");
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