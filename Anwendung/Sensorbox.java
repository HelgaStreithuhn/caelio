/*
 * Klasse zur Verwaltung einer Sensorbox
 */

public class Sensorbox
{
    private String kennung;
    
    public Sensorbox(String kennung_)
    {
        // Eine Sensorbox hat eine einmalige Kennung
        // ITG: 606dabb74393eb001ca6a781
        String kennung = kennung_;
        
        // Eine Sensorbox hat mehrere Sensoren
        Sensor sensor1 = new Sensor();
        Sensor sensor2 = new Sensor();
    }
}