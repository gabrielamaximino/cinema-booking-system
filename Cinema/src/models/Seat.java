package models;

import javafx.scene.control.Button;

public class Seat extends Button {

    private char row;
    private int column, number;

    public Seat(char row, int column, int number){
        this.row = row;
        this.column = column;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%c%d", row, column);
    }

    public int getNumber() {
        return number;
    }
}
