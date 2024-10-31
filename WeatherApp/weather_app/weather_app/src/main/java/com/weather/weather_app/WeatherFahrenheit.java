package com.weather.weather_app;

public class WeatherFahrenheit extends WeatherBase implements IWeather{

    double kelvin = 273.15;
    double num = 1.8;
    double thirtyTwo = 32;

    @Override
    public double getTemperature(double temp) {
        return calc(temp);
    }

    @Override
    public double getFeelsLike(double feels_like) {
        return calc(feels_like);
    }

    @Override
    public double getTempMax(double temp_max) {
        return calc(temp_max);
    }

    @Override
    public double getTempMin(double temp_min) {
        return calc(temp_min);
    }
    
    private double calc(double variable){
        return (variable - kelvin) * num + thirtyTwo;
    }
}