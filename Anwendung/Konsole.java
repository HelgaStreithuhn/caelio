public class Konsole implements Beobachter
{
    public Konsole()
    {
        // Registrieren
        Sensor sensor = new PM25();
        sensor.registrieren(this);
    }
    
    public void aktualisieren(Datensatz datensatz)
    {
        datensatz.ausgeben();
    }
}