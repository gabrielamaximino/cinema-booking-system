package models;

import cinema.Cinema;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import layouts.SeatSelection;

public class Movie{
    public String title;
    public int id, length;
    public Image poster;
    Cinema cinema;
    public Sala sala;

    public Movie(String title, int id, int length, Image poster, Cinema cinema) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.poster = poster;
        this.cinema = cinema;
        this.sala = new Sala(id);
    }

    public FlowPane getMoviePreview(){

        FlowPane flowPane = new FlowPane();

        flowPane.setId("movie-preview");

        ImageView img = new ImageView(poster);
        //img.setOnMouseClicked(e -> setSelectSeatView());

        flowPane.getChildren().add(img);

        FlowPane info = new FlowPane(Orientation.VERTICAL);
        info.setMaxHeight(250);
        info.setId("info");

        Label titleLabel = new Label(title);
        titleLabel.setId("preview-title-label");
        info.getChildren().add(titleLabel);

        Label sinopseLabel = new Label("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        sinopseLabel.setId("preview-sinopse-label");
        sinopseLabel.setWrapText(true);
        sinopseLabel.setMaxWidth(500);
        info.getChildren().add(sinopseLabel);

        Pane empty = new Pane();
        empty.setPrefHeight(10);
        info.getChildren().add(empty);

        Button chooseSeat = new Button("Choose a Seat");
        chooseSeat.setOnMouseClicked(e -> setSelectSeatView());
        chooseSeat.setId("preview-choose-seat");
        info.getChildren().add(chooseSeat);

        flowPane.getChildren().add(info);

        return flowPane;

    }

    public ImageView getPosterImageView() {
        ImageView img = new ImageView(poster);
        img.setOnMouseClicked(e -> setSelectSeatView());
        return img;
    }

    private void setSelectSeatView() {
        SeatSelection seatSelection = new SeatSelection(this, cinema);
        cinema.setRootCenterLayout(seatSelection.getLayout());
    }
}
