package models;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Acquisition {

    public ArrayList<Seat> seats = new ArrayList<>();
    public boolean half = false;
    private Movie movie;

    public Acquisition(Movie movie){
        this.movie = movie;
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
        chooseSeat.setOnMouseClicked(e -> System.out.println("Canceling"));
        chooseSeat.setId("preview-choose-seat");
        info.getChildren().add(chooseSeat);

        flowPane.getChildren().add(info);

        return flowPane;

    }

}
