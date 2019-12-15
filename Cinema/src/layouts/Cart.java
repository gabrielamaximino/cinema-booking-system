package layouts;

import cinema.Cinema;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import models.Acquisition;
import models.Seat;
import resources.tools.Tools;

import java.io.IOException;
import java.util.ArrayList;

public class Cart {
    private BorderPane cartView;
    private GridPane emptyCartGridPane;
    private Label layoutTitle, emptyMessage;
    private Cinema cinema;
    private Button moviesButton, cartButton;
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

            Button confirmButton = new Button("Confirm Acquisitions");
            confirmButton.setOnMouseClicked(e -> {
                acquisitions.clear();
                setLayout();
            });

            Button moviesButton = new Button("Continue Shopping");
            moviesButton.setOnMouseClicked(e -> cinema.setRootCenterLayout(cinema.movies.getLayout()));

            GridPane buttonsGridPane = new GridPane();
            buttonsGridPane.add(confirmButton, 0, 0);
            buttonsGridPane.add(moviesButton, 1, 0);
            buttonsGridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
            buttonsGridPane.setAlignment(Pos.BOTTOM_CENTER);

            cartView.setBottom(buttonsGridPane);
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
    public void removeAcquisition(Acquisition acquisition){
        acquisitions.remove(acquisition);
    }

    public void undoAcquisitions(){

        for(Acquisition acquisition : acquisitions){
            for(Seat seat : acquisition.seats) {
                try {
                    acquisition.getMovie().sala.undo(seat.getNumber());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }



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
