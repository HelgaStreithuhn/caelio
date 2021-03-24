/**
 * Klasse zur Verwaltung eines Sensors
 * -----------------------------------
 * Datensatz  Datensatz
 */

/*
 * Stand: 03.03.2021
 */

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Sensor
{
    // Datensatz aller Unterklassen
    public Datensatz datensatz;
    private ArrayList<Beobachter> beobachter;

    public Sensor()
    {
        // Ein Sensor hat einen Datensatz
        datensatz = new Datensatz("ug/m^3");

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
            }, 0, 1000);
    }

    public void registrieren(Beobachter beobachter_)
    {
        beobachter.add(beobachter_);
    }

    public void messen() throws Exception
    {
        // Hinzufuegen eines Messwerts
        System.out.println("Es sollte ein erbender Sensor verwendet werden.");
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