import java.util.ArrayList;

public class Messintervallgeber extends Thread{  
    private ArrayList<Sensor> zurueckzurufen;
    private long intervall;
    public void run(){ 
        try{
            Thread.sleep(intervall);
            for(Sensor sens : zurueckzurufen){
                sens.messen();
                //System.out.println("Messaufruf an " + sens.toString());
            }
        }catch(Exception e){
            System.out.println(e);
            return;
        }
        run();
    }

    public Messintervallgeber(long msIntervall){
        zurueckzurufen = new ArrayList<Sensor>();
        intervall = msIntervall;
    }
    
    public void sensorAnmelden(Sensor s){
        zurueckzurufen.add(s);
    }
}