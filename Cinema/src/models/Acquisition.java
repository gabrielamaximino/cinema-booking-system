package models;

import cinema.Cinema;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class Acquisition {

    public ArrayList<Seat> seats = new ArrayList<>();
    public boolean half = false;
    private FlowPane flowPane;
    private GridPane buttonsGridPane;
    private BorderPane layoutView;
    private Movie movie;
    private Cinema cinema;

    public Acquisition(Movie movie, Cinema cinema){
        this.movie = movie;
        this.cinema = cinema;
    }

    public double getPrice(){

        double value = half ? 10 : 20;

        return seats.size() * value;

    }

    public FlowPane getLayout(){

        FlowPane flowPane = new FlowPane();

        flowPane.setId("movie-preview");

        ImageView img = new ImageView(movie.poster);
        //img.setOnMouseClicked(e -> setSelectSeatView());

        flowPane.getChildren().add(img);

        FlowPane info = new FlowPane(Orientation.VERTICAL);
        info.setMaxHeight(250);
        info.setId("info");

        Label titleLabel = new Label(movie.title);
        titleLabel.setId("preview-title-label");
        info.getChildren().add(titleLabel);

        Label infoLabel = new Label(String.format("%d %s tickets acquired", seats.size(), half ? "half" : ""));
        infoLabel.setId("preview-sinopse-label");
        info.getChildren().add(infoLabel);

        Label priceLabel = new Label(String.format("$%s", getPrice()));
        priceLabel.setId("preview-sinopse-label");
        info.getChildren().add(priceLabel);

        Pane empty = new Pane();
        empty.setPrefHeight(10);
        info.getChildren().add(empty);

        Button chooseSeat = new Button("Cancel acquisition");
        chooseSeat.setOnMouseClicked(e -> removeAcquisition());
        chooseSeat.setId("preview-choose-seat");
        info.getChildren().add(chooseSeat);

        flowPane.getChildren().add(info);

        return flowPane;

    }

    public Movie getMovie() {
        return movie;
    }

    public void removeAcquisition() {
        cinema.cart.removeAcquisition(this);

        for(Seat seat : seats) {
            try {
                movie.sala.undo(seat.getNumber());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        cinema.cart.updateCartButton();
        cinema.cart.setLayout();
    }

}
