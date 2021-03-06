package models;

import java.io.*;
import java.util.Scanner;

public class Sala {

    private int id;
    private String path;
    private File file;
    private boolean[] assentos = new boolean[60];

    Sala(int id) {

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
                assentos[i] = Boolean.parseBoolean(reader.next());
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean[] getSeats() {
        return assentos;
    }

    public boolean reservaAssento(int assento) throws IOException {

        if(assento <= 60 && !assentos[assento]){

            assentos[assento] = true;

            FileWriter writer = new FileWriter(path);
            for(int i = 0; i<60; i++)writer.write(assentos[i]+"\n");
            writer.close();

            return true;

        }

        return false;
    }

    public void undo(int assento) throws IOException {

        assentos[assento] = false;

        FileWriter writer = new FileWriter(path);
        for(int i = 0; i<60; i++)writer.write(assentos[i]+"\n");
        writer.close();


    }

}
