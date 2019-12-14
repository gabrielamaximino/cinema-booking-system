/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author gabri
 */
public class Movie extends Button {
    public String title;
    public int length;
    public Image poster;
    
    public Movie(String title, int length, Image poster) {
        this.title = title;
        this.length = length;
        this.poster = poster;
    }

    public ImageView getPosterImageView() {
        return new ImageView(poster);
    }
}
