package com.weather.weather_app;

public class WeatherFahrenheit extends WeatherBase implements IWeather{

    double num = 1.8;
    double thirtyTwo = 32;

    @Override
    public double getTemperature() {
        return calc(temperature);
    }

    @Override
    public double getFeelsLike() {
        return calc(feelsLike);
    }

    @Override
    public double getTempMax() {
        return calc(maxTemp);
    }

    @Override
    public double getTempMin() {
        return calc(minTemp);
    }
    
    private double calc(double variable){
        return (variable - kelvin) * num + thirtyTwo;
    }
}