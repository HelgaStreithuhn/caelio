import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

/*
 * Klasse zum Verwalten der Anwendung
 */

public class Controller implements Beobachter
{
    // Attribute
    private Sensorbox sensorbox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Circle kreisrot;

    @FXML
    private Circle kreisgelb;

    @FXML
    private Circle kreisgrün;

    @FXML
    private TextField uebersichtStandort;

    @FXML
    private TextField uebersichtTemperatur;

    @FXML
    private TextField uebersichtLuftfeuchtigkeit;

    @FXML
    private TextField uebersichtBeleuchtung;

    @FXML
    private TextField uebersichtFeinstaub;

    @FXML
    private TextField uebersichtCo2;

    @FXML
    private TextField uebersichtLuftdruck;

    @FXML
    private TextField uebersichtUV;

    @FXML
    private TextField uebersichtMesszeit;

    @FXML
    private Tab tempTab;

    @FXML
    private TextField tempAktuell;

    @FXML
    private TextField tempMin;

    @FXML
    private TextField tempMax;

    @FXML
    private Tab co2Tab;

    @FXML
    private TextField co2Aktuell;

    @FXML
    private TextField co2Max;

    @FXML
    private TextField co2Min;

    @FXML
    private Tab feuchtTab;

    @FXML
    private TextField feuchtMin;

    @FXML
    private TextField feuchtMax;

    @FXML
    private TextField feuchtAktuell;

    @FXML
    private Tab druckTab;

    @FXML
    private TextField druckAktuell;

    @FXML
    private TextField druckMax;

    @FXML
    private TextField druckMin;

    @FXML
    private Tab beleuchtungTab;

    @FXML
    private TextField beleuchtungAktuell;

    @FXML
    private TextField beleuchtungMax;

    @FXML
    private TextField beleuchtungMin;

    @FXML
    private Tab uvTab;

    @FXML
    private TextField uvMax;

    @FXML
    private TextField uvMin;

    @FXML
    private TextField uvAktuell;

    @FXML
    private Tab staubTab;

    @FXML
    private TextField staubAktuell;

    @FXML
    private TextField staubMin;

    @FXML
    private TextField staubMax;

    @FXML
    private ChoiceBox<?> datenbank;

    public void sensorboxLaden()
    {
        // Sensorbox ITG
        //sensorbox = new Sensorbox("606dabb74393eb001ca6a781");
        sensorbox = new Sensorbox("607db857542eeb001cba21f0");
        sensorbox.interesseAnmelden(this);
        try
        {
            // Aktuelle Daten im Tab Code
            sensorbox.datenLaden();
            String daten = "";
            Datensatz datensatz = sensorbox.datensatzFinden("Temperatur");
            for (int i = 0; i < datensatz.messwerteGeben().size(); i ++)
                daten += "\n" + datensatz.messwerteGeben().get(i).zuString();
            oberflaecheAktualisieren();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void aktualisieren(){
        oberflaecheAktualisieren();
    }
    
    public void oberflaecheAktualisieren(){
        uebersichtStandort.setText(sensorbox.nameGeben());
        
        uebersichtMesszeit.setText(sensorbox.letzteMessung());
        
        String tempAkt  = sensorbox.neuesteDatenGeben("Temperatur");
        uebersichtTemperatur.setText(tempAkt);
        this.tempAktuell.setText(tempAkt);
        
        String luftfeucht = sensorbox.neuesteDatenGeben("rel. Luftfeuchte");
        uebersichtLuftfeuchtigkeit.setText(luftfeucht);
        this.feuchtAktuell.setText(luftfeucht);
        
        String leucht = sensorbox.neuesteDatenGeben("Beleuchtungsstärke");
        uebersichtBeleuchtung.setText(leucht);
        this.beleuchtungAktuell.setText(leucht);
        
        String staub10 = sensorbox.neuesteDatenGeben("PM10");
        String staub2 = sensorbox.neuesteDatenGeben("PM2.5");
        String staubGanz = staub10 + "(PM10), " + staub2 + "(PM2,5)";
        uebersichtFeinstaub.setText(staubGanz);
        this.staubAktuell.setText(staubGanz);
        
        String kohlendiox = sensorbox.neuesteDatenGeben("CO₂");
        uebersichtCo2.setText(kohlendiox);
        this.co2Aktuell.setText(kohlendiox);
        
        String druck = sensorbox.neuesteDatenGeben("Luftdruck");
        uebersichtLuftdruck.setText(druck);
        this.druckAktuell.setText(druck);
        
        String intense = sensorbox.neuesteDatenGeben("UV-Intensität");
        uebersichtUV.setText(intense);
        this.uvAktuell.setText(intense);
        
    } 
    
    
    @FXML
    void initialize()
    {
        // Laden der Sensorbox
        sensorboxLaden();
    }
}