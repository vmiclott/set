package set.fxml;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import set.Main;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static int numberOfProperties;
    //public TextField textField;
    public Button timeButton;
    public Button pcButton;
    public Button howTo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //numberOfProperties = Integer.parseInt(textField.getText());
        numberOfProperties = 4;
    }

    public void playerVersusTime(ActionEvent actionEvent) {
        try {
            GameController.pvm = false;
            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("play"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
        }
    }

    public void howTo(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("howTo.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("howTo"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
        }
    }

    public void playerVersusMachine(ActionEvent actionEvent) {
        try {
            GameController.pvm = true;
            Parent root = FXMLLoader.load(getClass().getResource("pvm.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
            Main.stage.setTitle(Main.bundle.getString("pvm"));
            Main.stage.setScene(new Scene(root, 600, 600));
        } catch (IOException e) {
            System.out.println(Main.bundle.getString("screenNotFound"));
        }
    }
}
