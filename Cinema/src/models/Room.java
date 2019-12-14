package models;

import helpers.Parser;

import java.io.*;
import java.util.Scanner;

public class Room {

    private int id;
    private String path;
    private File file;
    private boolean[] seats;

    Room(int id) {

        this.id = id;

        path = "salas/"+id;

        file = new File(path);
        Scanner reader;

        try{

            if(!this.file.exists()){
                FileWriter writer = new FileWriter(path);
                writer.write("0");
                writer.close();
            }

            reader = new Scanner(file);
            seats = Parser.parseToArray(Integer.parseInt(reader.next()));

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean[] getSeats() {
        return seats;
    }

    public boolean pickSeat(int seat) throws IOException {

        if(seat <= 30 && ! seats[seat - 1]){

            seats[seat - 1] = true;

            FileWriter writer = new FileWriter(path);
            writer.write(String.valueOf(Parser.parseToInt(seats)));
            writer.close();

            return true;

        }

        return false;
    }

}
