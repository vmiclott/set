package set.fxml;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;

/**
 * @author: Victor
 */
public class SetLabel extends Label implements InvalidationListener {
    private TableModel model;

    public SetLabel() {
    }

    public TableModel getModel() {
        return model;
    }

    public void setModel(TableModel model) {
        this.model = model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        if(model.isSet() == null) {
            setText("gefeliciteerd, uw tijd: " + model.getTimeText());
        } else {
            if(model.ThreeCardsSelected()) {
                if(model.isSet()) {
                    setText("correct :)");
                } else {
                    setText("Dit is geen set :(");
                }
            } else {
                setText("Geen 3 kaarten geselecteerd");
            }
        }
    }
}
