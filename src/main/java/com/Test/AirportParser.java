package com.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class AirportParser
{
    private  static final String path = "/files/airports.csv";
    private AirportParser(){}

    public static Airport readFromConsole() throws IOException, NumberFormatException
    {
        try(InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bf = new BufferedReader(isr))
        {
            System.out.println("Введите широту: ");
            double latitude = Double.parseDouble(bf.readLine());
            System.out.println("Введите долготу: ");
            double longitude = Double.parseDouble(bf.readLine());
            return new Airport("Searchable", latitude, longitude);
        }catch(IOException e)
        {
            throw new IOException("Ошибка ввода/вывода");
        }catch(NumberFormatException e)
        {
            throw new NumberFormatException("Неправильный формат вводимых данных: введите число");
        }
    }

    public static HashSet<String> readFromCSV() throws NullPointerException, IOException
    {
        HashSet<String> information = new HashSet<>();
        if(AirportParser.class.getResourceAsStream(path) == null)
            throw new NullPointerException("Ошибка при получении ресура.");
        try(InputStream input = AirportParser.class.getResourceAsStream(path);
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            BufferedReader bf = new BufferedReader(isr))
        {
            while(bf.ready())
            {
                information.add(bf.readLine());
            }

        }
        catch(FileNotFoundException e)
        {
            throw new FileNotFoundException("Файл не найден");
        }
        catch(NullPointerException e)
        {
            throw new NullPointerException("Файл пуст");
        }
        catch(IOException e)
        {
            throw new IOException("Ошибка при чтении файла");
        }
        return information;
    }
    public static ArrayList<Airport> parseAirports(HashSet<String> CSVLines)
    {
        ArrayList<Airport> al = new ArrayList<>();
        for(String element : CSVLines)
        {
            String[] splited = element.split(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
            StringBuilder sb = new StringBuilder(splited[1]);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            splited[1] = sb.toString();
            al.add(new Airport(splited[1], Double.parseDouble(splited[6]), Double.parseDouble(splited[7])));
        }
        return al;
    }
}
