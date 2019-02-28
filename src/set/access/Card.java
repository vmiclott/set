package set.access;

import set.Main;

import java.util.ResourceBundle;

/**
 * Created by victor on 16.05.15.
 */
public class Card {

    private int[] properties;
    private ResourceBundle bundle;

    public Card(int[] properties) {
        this.bundle = Main.bundle;
        this.properties = properties;
    }

    public int[] getProperties() {
        return properties;
    }

    public void setProperties(int[] properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return  bundle.getString("amount") + "= " + Main.aantallen.get(properties[3]) +
                " " + bundle.getString("colour") + "= " + Main.kleuren.get(properties[0]) +
                " " + bundle.getString("shape") + "= " + Main.vormen.get(properties[1]) +
                " " + bundle.getString("filling") + "= " + Main.vullingen.get(properties[2]);
    }

    public boolean isEqualTo(Card card) {
        if (properties.length != card.getProperties().length) {
            return false;
        } else {
            for (int i = 0; i < properties.length; i++) {
                if (card.getProperties()[i] != properties[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
