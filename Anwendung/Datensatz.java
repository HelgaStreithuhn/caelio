import java.util.ArrayList;

public class Datensatz
{
    private ArrayList<Messwert> messwerte;
    private String einheit;

    public Datensatz(ArrayList<Messwert> messwerte_, String einheit_)
    {
        messwerte = messwerte_;
        einheit = einheit_;
    }

    public Datensatz(String einheit_)
    {
        messwerte = new ArrayList<Messwert>();
        einheit = einheit_;
    }
    
    public void einfuegen(Messwert messwert)
    {
        messwerte.add(messwert);
    }

    public void ausgeben()
    {
        // alle Messwerte auf Konsole ausgeben
        System.out.println("Datensatz:");
        for (int i = 0; i < messwerte.size(); i ++)
            System.out.println(messwerte.get(i).toString());
    }
    
    public ArrayList<Messwert> messwerteGeben()
    {
        return messwerte;
    }
    
    public String einheitGeben()
    {
        return einheit;
    }
    
    public Messwert neustenMesswerteGeben()
    {
        return messwerte.get(0);
    }
    
    public Messwert maxMesswerteGeben()
    {
        Messwert max = messwerte.get(0);
        // wenn der untersuchte wert größer ist als der bisher größte, soll er als neuer größter gesetzt werden
        for(Messwert vergleichswert : messwerte){
            if(vergleichswert.wertGeben() > max.wertGeben()) max = vergleichswert; 
        }
        return max;
    }
    
    public Messwert minMesswerteGeben()
    {
        Messwert min = messwerte.get(0);
        // wenn der untersuchte wert kleiner ist als der bisher kleinste, soll er als neuer kleinster gesetzt werden
        for(Messwert vergleichswert : messwerte){
            if(vergleichswert.wertGeben() > min.wertGeben()) min = vergleichswert; 
        }
        return min;
    }
}
