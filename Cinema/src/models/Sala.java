package models;

import helpers.Parser;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Sala {

    private int id;
    private String path;
    private File file;
    private boolean[] assentos;

    Sala(int id) {

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
            assentos = Parser.parseToArray(Integer.parseInt(reader.next()));

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean[] getAssentos() {
        return assentos;
    }

    public boolean reservaAssento(int assento) throws IOException {

        if(assento <= 30 && !assentos[assento-1]){

            assentos[assento-1] = true;

            FileWriter writer = new FileWriter(path);
            writer.write(String.valueOf(Parser.parseToInt(assentos)));
            writer.close();

            return true;

        }

        return false;
    }

}
