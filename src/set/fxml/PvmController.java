package set.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import set.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Victor
 */
public class PvmController implements Initializable {
    public Button easy;
    public Button med;
    public Button hard;
    public Button custom;
    public TextField seconds;
    public static int sec;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void easyMode(ActionEvent actionEvent) {
        sec = 60;
        startGame();
    }

    public void intermediateMode(ActionEvent actionEvent) {
        sec = 40;
        startGame();
    }

    public void hardMode(ActionEvent actionEvent) {
        sec = 20;
        startGame();
    }

    public void customMode(ActionEvent actionEvent) {
        if(Integer.parseInt(seconds.getText()) > 0) {
            sec = Integer.parseInt(seconds.getText());
            startGame();
        }
    }

    private void startGame() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("play"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
            e.printStackTrace();
        }
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
