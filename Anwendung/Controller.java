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

public class Controller
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
    private ChoiceBox<?> datenbank;

    @FXML
    private TextField tempAktuell;

    @FXML
    private TextField tempMin;

    @FXML
    private TextField tempMax;

    @FXML
    private TextField co2Aktuell;

    @FXML
    private TextField co2Max;

    @FXML
    private TextField co2Min;

    @FXML
    private TextField feuchtMin;

    @FXML
    private TextField feuchtMax;

    @FXML
    private TextField feuchtAktuell;

    @FXML
    private Tab druckAktuell;

    @FXML
    private TextField druckMax;

    @FXML
    private TextField druckMin;

    @FXML
    private Tab beleuchtungAktuell;

    @FXML
    private TextField beleuchtungMax;

    @FXML
    private TextField beleuchtungMin;

    @FXML
    private TextField uvMax;

    @FXML
    private TextField uvMin;

    @FXML
    private TextField uvAktuell;

    @FXML
    private TextField staubAktuell;

    @FXML
    private TextField staubMin;

    @FXML
    private TextField staubMax;

    @FXML
    private Label label_code;

    public void sensorboxLaden()
    {
        // Sensorbox ITG
        sensorbox = new Sensorbox("606dabb74393eb001ca6a781");
        try
        {
            // Aktuelle Daten im Tab Code
            sensorbox.datenLaden();
            String daten = "";
            Datensatz datensatz = sensorbox.datensatzFinden("Temperatur");
            for (int i = 0; i < datensatz.messwerteGeben().size(); i ++)
                daten += "\n" + datensatz.messwerteGeben().get(i).zuString();
            label_code.setText("Aktuelle Daten:" + daten);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @FXML
    void initialize()
    {
        assert kreisrot != null : "fx:id=\"kreisrot\" was not injected: check your FXML file 'view.fxml'.";
        assert kreisgelb != null : "fx:id=\"kreisgelb\" was not injected: check your FXML file 'view.fxml'.";
        assert kreisgrün != null : "fx:id=\"kreisgrün\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtStandort != null : "fx:id=\"uebersichtStandort\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtTemperatur != null : "fx:id=\"uebersichtTemperatur\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtLuftfeuchtigkeit != null : "fx:id=\"uebersichtLuftfeuchtigkeit\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtBeleuchtung != null : "fx:id=\"uebersichtBeleuchtung\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtFeinstaub != null : "fx:id=\"uebersichtFeinstaub\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtCo2 != null : "fx:id=\"uebersichtCo2\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtLuftdruck != null : "fx:id=\"uebersichtLuftdruck\" was not injected: check your FXML file 'view.fxml'.";
        assert uebersichtUV != null : "fx:id=\"uebersichtUV\" was not injected: check your FXML file 'view.fxml'.";
        assert datenbank != null : "fx:id=\"datenbank\" was not injected: check your FXML file 'view.fxml'.";
        assert tempAktuell != null : "fx:id=\"tempAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert tempMin != null : "fx:id=\"tempMin\" was not injected: check your FXML file 'view.fxml'.";
        assert tempMax != null : "fx:id=\"tempMax\" was not injected: check your FXML file 'view.fxml'.";
        assert co2Aktuell != null : "fx:id=\"co2Aktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert co2Max != null : "fx:id=\"co2Max\" was not injected: check your FXML file 'view.fxml'.";
        assert co2Min != null : "fx:id=\"co2Min\" was not injected: check your FXML file 'view.fxml'.";
        assert feuchtMin != null : "fx:id=\"feuchtMin\" was not injected: check your FXML file 'view.fxml'.";
        assert feuchtMax != null : "fx:id=\"feuchtMax\" was not injected: check your FXML file 'view.fxml'.";
        assert feuchtAktuell != null : "fx:id=\"feuchtAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert druckAktuell != null : "fx:id=\"druckAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert druckMax != null : "fx:id=\"druckMax\" was not injected: check your FXML file 'view.fxml'.";
        assert druckMin != null : "fx:id=\"druckMin\" was not injected: check your FXML file 'view.fxml'.";
        assert beleuchtungAktuell != null : "fx:id=\"beleuchtungAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert beleuchtungMax != null : "fx:id=\"beleuchtungMax\" was not injected: check your FXML file 'view.fxml'.";
        assert beleuchtungMin != null : "fx:id=\"beleuchtungMin\" was not injected: check your FXML file 'view.fxml'.";
        assert uvMax != null : "fx:id=\"uvMax\" was not injected: check your FXML file 'view.fxml'.";
        assert uvMin != null : "fx:id=\"uvMin\" was not injected: check your FXML file 'view.fxml'.";
        assert uvAktuell != null : "fx:id=\"uvAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert staubAktuell != null : "fx:id=\"staubAktuell\" was not injected: check your FXML file 'view.fxml'.";
        assert staubMin != null : "fx:id=\"staubMin\" was not injected: check your FXML file 'view.fxml'.";
        assert staubMax != null : "fx:id=\"staubMax\" was not injected: check your FXML file 'view.fxml'.";
        assert label_code != null : "fx:id=\"label_code\" was not injected: check your FXML file 'view.fxml'.";

        // Laden der Sensorbox
        sensorboxLaden();
    }
}