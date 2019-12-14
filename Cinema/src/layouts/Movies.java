/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layouts;

import models.Movie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author gabri
 */
public class Movies {
    FlowPane flow = new FlowPane();
    Movie m1, m2, m3;
    
    public FlowPane getLayout() {
        flow.getChildren().clear();
        flow.getStyleClass().addAll("flowpane");
        flow.getChildren().addAll(new ImageView(m1.cartaz), new ImageView(m2.cartaz), new ImageView(m3.cartaz));
        
        return flow;
    }
    
    public Movies() {
        m1 = new Movie("Os Vingadores", 120, new Image("/resources/images/vingadores.jpg"));
        m2 = new Movie("Star-Wars", 180, new Image("/resources/images/star-wars.jpg"));
        m3 = new Movie("A Star is Born", 80, new Image("/resources/images/star-is-born.jpg")); 
    }
}