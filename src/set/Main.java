package set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Main extends Application {

    public static Stage stage;

    public static Map<String, Image> images;


    public static Map<Integer,String> kleuren;
    public static Map<Integer,String> vormen;
    public static Map<Integer,String> vullingen;
    public static Map<Integer,String> aantallen;
    public static ResourceBundle bundle;

    @Override
    public void start(Stage primaryStage) throws Exception{
        images = new HashMap<>();
        kleuren = new HashMap<>();
        vormen = new HashMap<>();
        vullingen = new HashMap<>();
        aantallen = new HashMap<>();
        bundle = ResourceBundle.getBundle("set/resources/i18n");
        kleuren.put(0,bundle.getString("green"));
        kleuren.put(1,bundle.getString("purple"));
        kleuren.put(2,bundle.getString("red"));
        vormen.put(0,bundle.getString("oval"));
        vormen.put(1,bundle.getString("square"));
        vormen.put(2,bundle.getString("tilde"));
        vullingen.put(0,bundle.getString("striped"));
        vullingen.put(1,bundle.getString("empty"));
        vullingen.put(2,bundle.getString("filled"));
        aantallen.put(0,"1");
        aantallen.put(1,"2");
        aantallen.put(2,"3");

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3 ; j++) {
                for (int k = 0; k < 3; k++) {
                    images.put("" + i + j + k, new Image(getClass().getResourceAsStream("resources/images/goodquality/" + i + j + k +".jpg")));
                }
            }
        }

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("fxml/start.fxml"),ResourceBundle.getBundle("set/resources/i18n"));
        primaryStage.setTitle(bundle.getString("start"));
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
