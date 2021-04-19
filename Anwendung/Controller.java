import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
        assert label_code != null : "fx:id=\"label_code\" was not injected: check your FXML file 'view.fxml'.";

        // Laden der Sensorbox
        sensorboxLaden();
    }
}