import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

/*
 * Klasse zum Verwalten der Anwendung
 */

public class Controller
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Circle ampelRot;

    @FXML
    private Circle ampelGelb;

    @FXML
    private Circle ampelGruen;

    @FXML
    void initialize()
    {
        assert ampelRot != null : "fx:id=\"ampelRot\" was not injected: check your FXML file 'view1.fxml'.";
        assert ampelGelb != null : "fx:id=\"ampelGelb\" was not injected: check your FXML file 'view1.fxml'.";
        assert ampelGruen != null : "fx:id=\"ampelGruen\" was not injected: check your FXML file 'view1.fxml'.";
    }
}