package com.Test;

import com.Test.KDTree;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Main
{
    public static void main(String[] args) {

        try {
            KDTree kdTree = new KDTree();

            Airport needToFind = AirportParser.readFromConsole();
            HashSet<String> CSVLines = AirportParser.readFromCSV(); //read information from csv file
            ArrayList<Airport> airports = AirportParser.parseAirports(CSVLines); //from CSVLines parse to com.Test.Airport

            for(Airport element : airports)
            {
                kdTree.add(element);
            }
            LocalTime lt1 = LocalTime.now(); //Засекаем время, потраченное на поиск
            System.out.println(kdTree.nearestNeighbor(needToFind).getA());
            LocalTime lt2 = LocalTime.now();
            Duration duration = Duration.between(lt1, lt2);
            System.out.println("Затраченное время: " + duration.toMillis()); //Выводим затраченное время
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
