/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layouts;

import cinema.Cinema;
import models.Movie;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class Movies {
    FlowPane flow = new FlowPane();
    ArrayList<Movie> movies;
    Cinema cinema;
    
    public FlowPane getLayout() {
        flow.getChildren().clear();
        flow.getStyleClass().addAll("flowpane");

        for (Movie movie : movies) flow.getChildren().add(movie.getPosterImageView());
        
        return flow;
    }
    
    public Movies(Cinema cinema) {
        this.cinema = cinema;
        Cart cart = new Cart();

        movies = new ArrayList<Movie>();

        movies.add(new Movie("Avengers: End-Game", 120, new Image("/resources/images/vingadores.jpg")));
        movies.add(new Movie("Star-Wars", 180, new Image("/resources/images/star-wars.jpg")));
        movies.add(new Movie("A Star is Born", 80, new Image("/resources/images/star-is-born.jpg")));

        for (Movie movie : movies) {
            movie.setOnMouseClicked(e -> cinema.root.setCenter(cart.getLayout()));
        }
    }
}