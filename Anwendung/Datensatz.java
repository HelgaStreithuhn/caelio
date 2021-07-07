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
            System.out.println(messwerte.get(i).zuString());
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
}
