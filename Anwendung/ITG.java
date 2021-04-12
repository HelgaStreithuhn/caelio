/**
 * Klasse zur Verwaltung des Sensors PM25
 * --------------------------------------
 * Datensatz  Datensatz public
 */

/*
 * Stand: 10.03.2021
 */

// Er will den folgenden Import nicht.
// import org.json.*;

public class ITG extends Sensor
{    
    public ITG()
    {
        super();
    }

    public void messen() throws Exception
    {
        // Hinzufuegen eines Messwerts
        datensatz.einfuegen(new Messwert(1));
        String hash = "606dabb74393eb001ca6a781";
        String url = "https://api.opensensemap.org/boxes/" + hash
            + "?format=json";
    }
}