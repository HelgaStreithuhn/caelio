import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

/*
 * Klasse zum Starten der Anwendung
 */

public class Anwendung extends Application
{
    // Methode, die das Programm startet
    public static void main(String[] argumente)
    {
        // Starten des Programms
        launch(argumente);
    }

    // Methode, die zum Start aufgerufen wird
    public void start(Stage stufe) throws Exception
    {
        // Laden der fxml-Datei
        FXMLLoader fxml = new FXMLLoader(getClass().getResource(
                    "view.fxml"));
        Parent uebergeordnet = fxml.load();

        // Darstellen der Obeflaeche
        Scene oberflaeche = new Scene(uebergeordnet);
        stufe.setScene(oberflaeche);
        stufe.setTitle("Caelio");
        stufe.getIcons().add(new Image("Icons/caelio.png"));
        stufe.show();
    }
}