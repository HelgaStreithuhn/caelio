import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.json.*;
import javafx.scene.control.SingleSelectionModel;

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
    private TextField langgr;
    @FXML
    private TextField breitgr;

    @FXML
    private ChoiceBox<String> datenbankwahlliste;
    private ObservableList<String> boxenauswahl; //Soll die aktuell im Auswahlbereich liegenden Boxen speichern, bis der Nutzer eine auswählt
    
    
    @FXML private TableView<Messwert> druckTabelle;
    @FXML private TableColumn<Messwert, String> druckMesswert;
    @FXML private TableColumn<Messwert, String> druckMesszeit;
    private ObservableList<Messwert> druckWerte;
    
    @FXML private TableView<Messwert> tempTabelle;
    @FXML private TableColumn<Messwert, String> tempMesswert;
    @FXML private TableColumn<Messwert, String> tempMesszeit;
    private ObservableList<Messwert> tempWerte;
    
    @FXML private TableView<Messwert> co2Tabelle;
    @FXML private TableColumn<Messwert, String> co2Messwert;
    @FXML private TableColumn<Messwert, String> co2Messzeit;
    private ObservableList<Messwert> co2Werte;
    
    @FXML private TableView<Messwert> feuchtTabelle;
    @FXML private TableColumn<Messwert, String> feuchtMesswert;
    @FXML private TableColumn<Messwert, String> feuchtMesszeit;
    private ObservableList<Messwert> feuchtWerte;
    
    @FXML private TableView<Messwert> beleuchtungTabelle;
    @FXML private TableColumn<Messwert, String> beleuchtungMesswert;
    @FXML private TableColumn<Messwert, String> beleuchtungMesszeit;
    private ObservableList<Messwert> beleuchtungWerte;
    
    @FXML private TableView<Messwert> uvTabelle;
    @FXML private TableColumn<Messwert, String> uvMesswert;
    @FXML private TableColumn<Messwert, String> uvMesszeit;
    private ObservableList<Messwert> uvWerte;
    
    @FXML private TableView<Messwert> staubTabelle;
    @FXML private TableColumn<Messwert, String> staubMesswert;
    @FXML private TableColumn<Messwert, String> staubMesszeit;
    private ObservableList<Messwert> staubWerte;
    
    @FXML private TableView<Messwert> staubTabelle1;
    @FXML private TableColumn<Messwert, String> staubMesswert1;
    @FXML private TableColumn<Messwert, String> staubMesszeit1;
    private ObservableList<Messwert> staubWerte1;
    

    @FXML
    void initialize(){
        assert datenbankwahlliste != null : "datenbankwahlliste fehlt!";
        boxenauswahl = FXCollections.observableArrayList();
        try{
            datenbankwahlliste.setItems(boxenauswahl);
            boxSuchen(11,48,100000);
        } catch (Exception e) { 
            // System.out.println("Konnte Boxenauswahlliste nicht vorbereiten: " + e);
        }
        // Laden der Sensorbox
        sensorboxLaden("607db857542eeb001cba21f0");
    }  

    
   public void tabellenEinbinden(){
        try{
            druckWerte = sensorbox.datensatzFinden("Luftdruck").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        druckMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        druckMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        druckTabelle.setItems(druckWerte);
        
        try{
            tempWerte = sensorbox.datensatzFinden("Temperatur").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        tempMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        tempMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        tempTabelle.setItems(tempWerte);
        
        try{
            co2Werte = sensorbox.datensatzFinden("CO₂").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        co2Messzeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        co2Messwert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        co2Tabelle.setItems(co2Werte);
        
        try{
            feuchtWerte = sensorbox.datensatzFinden("rel. Luftfeuchte").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        feuchtMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        feuchtMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        feuchtTabelle.setItems(feuchtWerte);
        
        try{
            beleuchtungWerte = sensorbox.datensatzFinden("Beleuchtungsstärke").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        beleuchtungMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        beleuchtungMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        beleuchtungTabelle.setItems(beleuchtungWerte);
        
        try{
            uvWerte = sensorbox.datensatzFinden("UV-Intensität").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        uvMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        uvMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        uvTabelle.setItems(uvWerte);
        
        try{
            staubWerte = sensorbox.datensatzFinden("PM10").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        staubMesszeit.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        staubMesswert.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        staubTabelle.setItems(staubWerte);
        
        
        try{
            staubWerte1 = sensorbox.datensatzFinden("PM2.5").messwerteGeben();
        } catch (Exception e) {
            // System.out.println(e);
        }
        staubMesszeit1.setCellValueFactory(new PropertyValueFactory<Messwert, String>("zeit"));
        staubMesswert1.setCellValueFactory(new PropertyValueFactory<Messwert, String>("wert"));
        staubTabelle1.setItems(staubWerte1);
    }
    
    public void sensorboxLaden(String id)
    {
        sensorbox = new Sensorbox(id);
        sensorbox.interesseAnmelden(this);
        try
        {
            // Sensorbox wird initialisiert
            sensorbox.datenLaden();

            // Oberfläche wird mit Daten der Sensorbox gefüllt
            tabellenEinbinden();
        }
        catch (Exception e)
        {
            // System.out.println(e + "(Fehler beim Initialisieren der Sensorbox)");
        }
    }

    private static String boxSuchenJSON(double koordinateOst, double koordinateNord, int maxDist) throws Exception{
        if(maxDist >= 100000000) throw new Exception("Unable to find sensboxes in a wide radius");
        String adresse = "https://api.opensensemap.org/boxes?minimal=true&bbox=180,90,-180,-90" + //bbox enthält ganze Welt
            "&near=" + String.valueOf(koordinateOst) + "," + String.valueOf(koordinateNord) + "&maxDistance=" + String.valueOf(maxDist);

        String ergebnis = InternetVerbinder.httpGetAnfrage(adresse);
        if(ergebnis.equals("[]")){
            return boxSuchenJSON(koordinateOst, koordinateNord, maxDist*2);
        }
        return ergebnis;
    }

    public void boxSuchen(double koordinateNord, double koordinateOst, int maxDist){
        try{
            String json = boxSuchenJSON(koordinateOst, koordinateNord, maxDist);
            JSONArray auswaehlbareBoxen = new JSONArray(json);
            for(Object einzelergebnis : auswaehlbareBoxen){
                String boxNameAufOberflaeche = ((JSONObject) einzelergebnis).getString("name") + " (" + ((JSONObject) einzelergebnis).getString("_id") + ")";
                boxenauswahl.add(boxNameAufOberflaeche);
            }
        } catch (Exception e) {
            // System.out.println(e + "(Klasse Controller, Methode boxSuchen)");
        }
    }

    @FXML
    public void boxWaehlen(){
        String name = datenbankwahlliste.getValue();
        if (name == null || name.equals(sensorbox.getShowName()) ) return;

        // id steht in klammern am ende des namens, wird wieder extrahiert, um damit eine neue SenseBox zu verbinden:
        String id = name.substring(name.lastIndexOf('(') + 1, name.length() - 1 );
        sensorbox = null;
        sensorboxLaden(id);
    }

    @FXML public void boxenSuchen(){
        try{
            boxSuchen(Double.valueOf(breitgr.getText()),Double.valueOf(langgr.getText()),100000);
        } catch (Exception e) {
            // System.out.println("Boxensuche fehlgeschlagen: " + e);
        }

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
    
    @FXML public void jetztMessen(){sensorbox.jetztMessen();}
}
