package com.weather.weather_app;

public class Weather extends WeatherBase implements IWeather{
    
    @Override
    public double getTemperature(double temp) {
        return temp;
    }

    @Override
    public double getFeelsLike(double feels_like) {
        return feels_like;
    }

    @Override
    public double getTempMax(double temp_max) {
        return temp_max;
    }

    @Override
    public double getTempMin(double temp_min) {
        return temp_min;
    }

}
