package com.weather.weather_app;

public class WeatherCelsius extends WeatherBase implements IWeather{

    @Override
    public double getTemperature() {
        return  temperature - kelvin; 
    }

    @Override
    public double getFeelsLike() {
        return feelsLike - kelvin;
    }

    @Override
    public double getTempMax() {
        return maxTemp - kelvin;
    }

    @Override
    public double getTempMin() {
        return minTemp - kelvin;
    }
    
}
