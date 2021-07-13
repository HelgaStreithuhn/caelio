import java.util.ArrayList;

public class Messintervallgeber extends Thread{  
    private ArrayList<Sensor> zurueckzurufen;
    private long intervall;
    public void run(){ 
        try{
            Thread.sleep(intervall);
            for(Sensor sens : zurueckzurufen){
                sens.messen();
            }
        }catch(Exception e){
            System.out.println(e);
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
    
    public void jetztAlleMessenLassen() throws Exception{
        for(Sensor sens : zurueckzurufen){
                sens.messen();
            }
    }
}