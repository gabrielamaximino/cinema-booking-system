/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.scene.image.Image;

/**
 *
 * @author gabri
 */
public class Movie {
    public String titulo;
    public int duracao;
    public Image cartaz;
    
    public Movie(String titulo, int duracao, Image cartaz) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.cartaz = cartaz;
    }
}
