/*
 * Klasse zum Testen der Anwendung über die Konsole
 */

public class Konsole
{
    public Konsole()
    {
        // Erstellen einer neuen Sensorbox
        Sensorbox sensorbox = new Sensorbox("606dabb74393eb001ca6a781");
        try{
            sensorbox.datenLaden();
        } catch (Exception err){
            System.out.println(err);
        }
    }
    
}