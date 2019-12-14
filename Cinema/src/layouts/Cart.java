package layouts;

import cinema.Cinema;
import javafx.scene.control.Label;

public class Cart {
    Cinema cinema;

    public static Label getLayout() {
        Label label;

        label = new Label("Cart area is under construction");
        return label;
    }

    public Cart (Cinema cinema) {
        this.cinema = cinema;
    }
}
