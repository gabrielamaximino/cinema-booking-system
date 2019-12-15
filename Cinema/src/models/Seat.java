package models;

import cinema.Cinema;

public class Seat {
    public int seatLine, seatColumn;
    public Movie movie;
    private Cinema cinema;

    public Seat(int i, int j, Movie movie, Cinema cinema) {
        seatLine = i;
        seatColumn = j;

        this.movie = movie;
        this.cinema = cinema;
    }
}
