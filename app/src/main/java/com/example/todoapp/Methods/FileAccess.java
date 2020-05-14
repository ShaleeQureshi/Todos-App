package com.example.todoapp.Methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileAccess {

    public static ArrayList<String> readFile(ArrayList<String> data, File file) {
        try {

            FileReader fileR = new FileReader(file);
            BufferedReader read = new BufferedReader(fileR);
            String information = "";

            while ((information = read.readLine()) != null) {
                data.add(information);
            }
            fileR.close();
            read.close();

            return data;

        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String data, File file, boolean append) {

        try {
            FileWriter fileW = new FileWriter(file, append);
            PrintWriter output = new PrintWriter(fileW);
            if (data.equals("Close")){
                output.close();
            }
            else {
                output.println(data);
                output.close();
            }
            fileW.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileArray(ArrayList<String> data, File file) {

        try {
            FileWriter fileW = new FileWriter(file);
            PrintWriter output = new PrintWriter(fileW);

            for (String string: data){
                output.println(string);
            }
            output.close();
            fileW.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
