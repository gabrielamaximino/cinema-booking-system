package models;

import javafx.scene.control.Button;

public class Seat extends Button {

    private char row;
    private int column;

    public Seat(char row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("%c%d", row, column);
    }
}
