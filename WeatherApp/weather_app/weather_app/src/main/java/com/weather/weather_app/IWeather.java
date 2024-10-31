package com.weather.weather_app;

interface IWeather {

    public double getTemperature(double temp);
    public double getFeelsLike(double feels_like);
    public double getTempMax(double temp_max);
    public double getTempMin(double temp_min);

}