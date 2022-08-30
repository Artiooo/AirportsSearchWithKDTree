package com.Test;

import java.util.Objects;

public class Airport
{
    private String name;
    private double latitude;
    private double longitude;

    public Airport(String name, double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Double.compare(airport.latitude, latitude) == 0 && Double.compare(airport.longitude, longitude) == 0 && Objects.equals(name, airport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    public String toString()
    {
        return name + ", Координаты: " + latitude + ", " + longitude;
    }

    public static double distSquared(Airport a, Airport b)
    {
        return Math.pow(a.getLatitude()- b.getLatitude(), 2)+Math.pow(a.getLongitude() - b.getLongitude(), 2);
    }

}
