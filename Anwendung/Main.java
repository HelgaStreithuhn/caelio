public class Main
{
    public static void main(String[] args){
        System.out.println("Willkommen in der Testumgebung von Caelio.");
        // Erstellen einer neuen Sensorbox
        Sensorbox sensorbox = new Sensorbox("606dabb74393eb001ca6a781");
        try{
            sensorbox.datenLaden();
        } catch (Exception err){
            System.out.println(err);
        }
    }
}
