package com.weather.weather_app;

public class Weather extends WeatherBase implements IWeather{
    
    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public double getFeelsLike() {
        return feelsLike;
    }

    @Override
    public double getTempMax() {
        return maxTemp;
    }

    @Override
    public double getTempMin() {
        return minTemp;
    }

}
