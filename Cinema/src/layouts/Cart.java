package layouts;

import cinema.Cinema;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Seat;
import resources.tools.Tools;

import java.util.ArrayList;

public class Cart {
    private BorderPane cartView;
    private GridPane emptyCartGridPane;
    private Label layoutTitle, emptyMessage;
    private Cinema cinema;
    private Button moviesButton;
    public ArrayList<Seat> seats;

    public void setLayout() {
        layoutTitle = new Label("Cart");
        layoutTitle.setId("layoutTitle");

        cartView.setTop(layoutTitle);

        if (seats.isEmpty()) {
            emptyCartGridPane = new GridPane();
            emptyCartGridPane.setMinSize(800, 800);
            emptyMessage = new Label("Your cart is currently empty.");
            emptyMessage.setId("emptyMessage");
            emptyMessage.setMinWidth(500);
            emptyMessage.setAlignment(Pos.TOP_CENTER);

            emptyCartGridPane.setAlignment(Pos.CENTER);
            emptyCartGridPane.add(emptyMessage, 0, 0);
            emptyCartGridPane.add(moviesButton, 0, 1);

            emptyCartGridPane.setVgap(20);
            cartView.setCenter(emptyCartGridPane);
        }
        else {
            cartView.setCenter(Tools.getNotImplementedLabel());
        }
    }

    public BorderPane getLayout() {
        cinema.updateCartButton();
        setLayout();
        return cartView;
    }

    public int getCartSize() {
        return seats.size();
    }

    public Cart (Cinema cinema) {
        seats = new ArrayList<Seat>();
        moviesButton = new Button("Go to Movies");
        cartView = new BorderPane();
        this.cinema = cinema;

        moviesButton.setMinWidth(500);
        moviesButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

        setLayout();
    }
}
