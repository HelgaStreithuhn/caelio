import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Datensatz
{
    //private ArrayList<Messwert> messwerte;
    private String einheit;
    private ObservableList<Messwert> messwerte;
    
    public Datensatz(String einheit_){
        messwerte = FXCollections.observableArrayList();
        einheit = einheit_;
    }
    
    public void einfuegen(Messwert messwert)
    {
        //Todo: hier soll doppeltes Einfügen des selben Datensatzes verhindert werden
        if(neustenMesswerteGeben() == null)
        messwerte.add(messwert);
        if(neustenMesswerteGeben().toString().equals(messwert.toString()) == false)
        messwerte.add(messwert);
        
    }

    public void ausgeben()
    {
        // alle Messwerte auf Konsole ausgeben
        System.out.println("Datensatz:");
        for (int i = 0; i < messwerte.size(); i ++)
            System.out.println(messwerte.get(i).toString());
    }
    
    public ObservableList<Messwert> messwerteGeben(){return messwerte;}
    
    public String einheitGeben()
    {
        return einheit;
    }
    
    public Messwert neustenMesswerteGeben()
    {
        try{
            return messwerte.get(messwerte.size() - 1);
        } catch (Exception e) {
            //wenn noch keine Messwerte existieren, soll null zurückgeliefert werden
            return null;
        }
    }
    
    public Messwert maxMesswerteGeben()
    {
        Messwert max = messwerte.get(0);
        // wenn der untersuchte wert größer ist als der bisher größte, soll er als neuer größter gesetzt werden
        for(Messwert vergleichswert : messwerte){
            if(vergleichswert.wertGeben() > max.wertGeben()){
                max = vergleichswert;
            }
        }
        return max;
    }
    
    public Messwert minMesswerteGeben()
    {
        Messwert min = messwerte.get(0);
        // wenn der untersuchte wert kleiner ist als der bisher kleinste, soll er als neuer kleinster gesetzt werden
        for(Messwert vergleichswert : messwerte){
            if(vergleichswert.wertGeben() < min.wertGeben()) min = vergleichswert; 
        }
        return min;
    }
}
