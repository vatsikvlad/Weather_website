package com.weather.weather_app;

public class WeatherBase{

    public double temperature, feelsLike, maxTemp, minTemp, pressure;
    final double kelvin = 273.15;
    public String date;

    public double getPressure() {
        return pressure;
    }
    
}
