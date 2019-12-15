package layouts;

import cinema.Cinema;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import models.Acquisition;
import resources.tools.Tools;

import java.util.ArrayList;

public class Cart {
    private BorderPane cartView;
    private GridPane emptyCartGridPane;
    private Label layoutTitle, emptyMessage;
    private Cinema cinema;
    private Button moviesButton;
    private ArrayList<Acquisition> acquisitions;

    public void setLayout() {

        layoutTitle = new Label("Cart");
        layoutTitle.setId("layoutTitle");

        cartView.setTop(layoutTitle);

        if (acquisitions.isEmpty()) {
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

            ListView<FlowPane> listView = new ListView<>();

            for(Acquisition acquisition : acquisitions){

                listView.getItems().add(acquisition.getLayout());

            }

            cartView.setCenter(listView);
        }
    }

    public BorderPane getLayout() {
        updateCartButton();
        return cartView;
    }

    public int getCartSize() {
        return acquisitions.size();
    }

    public void addAcquisition(Acquisition acquisition){
        acquisitions.add(acquisition);
    }

    public void updateCartButton() {
        cinema.cartButton.setText("Cart (" + cinema.cart.getCartSize() + ")");
    }

    public Cart (Cinema cinema) {
        acquisitions = new ArrayList<Acquisition>();
        moviesButton = new Button("Go to Movies");
        cartView = new BorderPane();
        this.cinema = cinema;

        moviesButton.setMinWidth(500);
        moviesButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

        setLayout();
    }
}
