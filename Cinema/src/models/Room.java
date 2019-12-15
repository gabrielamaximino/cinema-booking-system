package models;

import helpers.Parser;

import java.io.*;
import java.util.Scanner;

public class Room {

    private int id;
    private String path;
    private File file;
    private boolean[] seats = new boolean[60];

    Room(int id) {

        this.id = id;

        path = "salas/"+id;

        file = new File(path);
        Scanner reader;

        try{

            if(!this.file.exists()){
                FileWriter writer = new FileWriter(path);
                for(int i = 0; i<60; i++)writer.write("false\n");
                writer.close();
            }

            reader = new Scanner(file);

            for(int i = 0; i<60; i++){
                seats[i] = Boolean.parseBoolean(reader.next());
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean[] getSeats() {
        return seats;
    }

    public boolean pickSeat(int seat) throws IOException {

        if(seat <= Parser.size && !seats[seat-1]){

            seats[seat-1] = true;

            FileWriter writer = new FileWriter(path);
            for(int i = 0; i<60; i++)writer.write(seats[i]+"\n");
            writer.close();

            return true;

        }

        return false;
    }

}
