package set.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import set.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Victor
 */
public class HowToController implements Initializable {
    public ImageView img;
    public Button next;
    public Button prev;
    private int screenNumber;
    private int totalNumber = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screenNumber = 1;
        setImage();
    }

    private void setImage() {
        img.setImage(new Image(Main.class.getResourceAsStream("resources/images/howto/" + Main.bundle.getString("lang") + "/howto" + screenNumber + ".png")));
        disable();
    }


    public void next(ActionEvent actionEvent) {
        screenNumber++;
        setImage();
    }

    public void prev(ActionEvent actionEvent) {
        screenNumber--;
        setImage();
    }

    private void disable() {
        next.setDisable(screenNumber == totalNumber);
        prev.setDisable(screenNumber == 1);
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("fxml/start.fxml"), ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("start"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
        }
    }
}
