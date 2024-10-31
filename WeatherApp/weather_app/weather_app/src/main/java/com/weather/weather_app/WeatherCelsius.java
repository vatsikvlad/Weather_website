package com.weather.weather_app;

public class WeatherCelsius extends WeatherBase implements IWeather{

    double kelvin = 273.15;

    @Override
    public double getTemperature(double temp) {
        return temp - kelvin; 
    }

    @Override
    public double getFeelsLike(double feels_like) {
        return feels_like - kelvin;
    }

    @Override
    public double getTempMax(double temp_max) {
        return temp_max - kelvin;
    }

    @Override
    public double getTempMin(double temp_min) {
        return temp_min - kelvin;
    }
    
}
