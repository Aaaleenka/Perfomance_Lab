package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        String filename = args[0];
        //File newFile = new File(filename);

        File newFile = new File(filename);
        ArrayList<Integer> a = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(newFile))) {

            String s = input.readLine();
            while (s != null) {
                a.add(Integer.parseInt(s));
                s = input.readLine();
            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        Collections.sort(a);

        int sum = 0;
        int m = a.get(a.size() / 2);
        for (Integer x : a){
            sum += Math.abs(x - m);
        }

        System.out.println(sum);


    }
}