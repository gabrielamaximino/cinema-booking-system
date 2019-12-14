package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import resources.tools.Tools;

public class Cart {
    private BorderPane cartView;
    private Label label;
    private Cinema cinema;

    public void setLayout() {
        label = new Label("Cart");

        label.setId("layoutTitle");

        cartView.setTop(label);
        cartView.setCenter(Tools.getUnderConstructionLabel());
    }

    public BorderPane getLayout() {
        return cartView;
    }

    public Cart (Cinema cinema) {
        cartView = new BorderPane();
        this.cinema = cinema;

        setLayout();
    }
}
