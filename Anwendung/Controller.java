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
    private ChoiceBox<String> datenbank;
    // Möglicher Ansatz zum auswählen neuer Datenbanken: 3 Textfelder (Id, Breitengrad, Längengrad)
    // Diese Abfrage: 
    
    @FXML
    void initialize()
    {
        // Laden der Sensorbox
        sensorboxLaden("607db857542eeb001cba21f0");
    }  
    
    public void sensorboxLaden(String id)
    {
        // Sensorbox ITG
        sensorbox = new Sensorbox(id);
        sensorbox.interesseAnmelden(this);
        try
        {
            // Sensorbox wird initialisiert
            sensorbox.datenLaden();

            // Oberfläche wird mit Daten der Sensorbox gefüllt
            aktualisieren();
        }
        catch (Exception e)
        {
            System.out.println(e + "(Fehler beim Initialisieren der Sensorbox)");
        }
    }
    
    public static String boxSuchen(float koordinateOst, float koordinateNord, int maxDist) throws Exception{
        if(maxDist >= 100000000) throw new Exception("Unable to find sensboxes in a wide radius");
        String adresse = "https://api.opensensemap.org/boxes?d&minimal=true&bbox=180,90,-180,-90" + //bbox enthält ganze Welt
        "near=" + String.valueOf(koordinateOst) + "," + String.valueOf(koordinateNord) + "&maxDistance=" + String.valueOf(maxDist);
        System.out.println(adresse);
        String ergebnis = InternetVerbinder.httpGetAnfrage(adresse);
        if(ergebnis.equals("[]")){
            return boxSuchen(koordinateOst, koordinateNord, maxDist*2);
        }
        return ergebnis;
    }
    
    
    
    public void aktualisieren(){
        aktualisieren("Temperatur");
        aktualisieren("rel. Luftfeuchte");
        aktualisieren("Beleuchtungsstärke");
        aktualisieren("PM10");
        aktualisieren("CO₂");
        aktualisieren("Luftdruck");
        aktualisieren("UV-Intensität");
    }
    public void aktualisieren(String wasAktualisieren){
        // wenn neue/andere Daten in der Sensorbox vorliegen, sollen diese auch in der Oberfläche erscheinen.
        //Diese Methode aktualisiert die Oberfläche in diesem Fall.

        //Standort und Uhrzeit der letzten Messung werden im Tab Übersicht angezeigt
        uebersichtStandort.setText(sensorbox.nameGeben());
        uebersichtMesszeit.setText(sensorbox.letzteMessungGeben());

        // nur in gefragte Felder aktuelle Daten einfüllen:
        switch(wasAktualisieren){
            
            case "Temperatur":
            String tempAkt  = sensorbox.neuesteDatenGeben("Temperatur");
            uebersichtTemperatur.setText(tempAkt);
            this.tempAktuell.setText(tempAkt);
            tempMax.setText(sensorbox.extremDatenGeben("Temperatur", true));
            tempMin.setText(sensorbox.extremDatenGeben("Temperatur", false));
            break;
            
            case "rel. Luftfeuchte":
            String luftfeucht = sensorbox.neuesteDatenGeben("rel. Luftfeuchte");
            uebersichtLuftfeuchtigkeit.setText(luftfeucht);
            this.feuchtAktuell.setText(luftfeucht);
            feuchtMax.setText(sensorbox.extremDatenGeben("rel. Luftfeuchte", true));
            feuchtMin.setText(sensorbox.extremDatenGeben("rel. Luftfeuchte", false));
            break;
            
            case "Beleuchtungsstärke":
            String leucht = sensorbox.neuesteDatenGeben("Beleuchtungsstärke");
            uebersichtBeleuchtung.setText(leucht);
            this.beleuchtungAktuell.setText(leucht);
            beleuchtungMax.setText(sensorbox.extremDatenGeben("Beleuchtungsstärke", true));
            beleuchtungMin.setText(sensorbox.extremDatenGeben("Beleuchtungsstärke", false));
            break;
            
            case "PM10":
            case "PM2.5":
            //Feinstaub wird in zwei verschiedenen Größen gemessen -> beide sollen angezeigt werden
            String staub10 = sensorbox.neuesteDatenGeben("PM10");
            String staub2 = sensorbox.neuesteDatenGeben("PM2.5");
            String staubGanz = staub10 + "(PM10), " + staub2 + "(PM2,5)";
            uebersichtFeinstaub.setText(staubGanz);
            this.staubAktuell.setText(staubGanz);
            String maxWerte = sensorbox.extremDatenGeben("PM10", true) + "(PM10), " + sensorbox.extremDatenGeben("PM2.5", true) + "(PM2.5)";
            this.staubMax.setText(maxWerte);
            String minWerte = sensorbox.extremDatenGeben("PM10", false) + "(PM10), " + sensorbox.extremDatenGeben("PM2.5", false) + "(PM2.5)";
            this.staubMin.setText(minWerte);
            //TODO: Extremwerte Staub testen
            break;
            
            case "CO₂":
            String kohlendiox = sensorbox.neuesteDatenGeben("CO₂");
            uebersichtCo2.setText(kohlendiox);
            this.co2Aktuell.setText(kohlendiox);
            co2Max.setText(sensorbox.extremDatenGeben("CO₂", true));
            co2Min.setText(sensorbox.extremDatenGeben("CO₂", false));
            // TODO: Test mit CO2 - Messreihen
            break;

            case "Luftdruck":
            String druck = sensorbox.neuesteDatenGeben("Luftdruck");
            uebersichtLuftdruck.setText(druck);
            this.druckAktuell.setText(druck);
            druckMax.setText(sensorbox.extremDatenGeben("Luftdruck", true));
            druckMin.setText(sensorbox.extremDatenGeben("Luftdruck", false));
            break;
            
            case "UV-Intensität":
            String intense = sensorbox.neuesteDatenGeben("UV-Intensität");
            uebersichtUV.setText(intense);
            this.uvAktuell.setText(intense);
            uvMax.setText(sensorbox.extremDatenGeben("UV-Intensität", true));
            uvMin.setText(sensorbox.extremDatenGeben("UV-Intensität", false));
            break;
        }
    } 
}
