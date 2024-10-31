package com.weather.weather_app;

import java.text.DecimalFormat;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    private final String apiKey = "17fb297dc68b10a9a037435a701c9304";

    @GetMapping("/home")
    public String home() {
        return "index";  // Це поверне файл src/main/resources/templates/index.html
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            String response = restTemplate.getForObject(apiUrl, String.class);
            JSONObject json = new JSONObject(response);
            
            Weather weather = new Weather();
            WeatherCelsius celsius = new WeatherCelsius();
            WeatherFahrenheit fahrenheit = new WeatherFahrenheit();
            
            // Передаємо дані в модель для відображення в HTML
            model.addAttribute("temperature", weather.getTemperature(json.getJSONObject("main").getDouble("temp")));
            model.addAttribute("feelsLike", weather.getFeelsLike(json.getJSONObject("main").getDouble("feels_like")));
            model.addAttribute("maxTemp", weather.getTempMax(json.getJSONObject("main").getDouble("temp_max")));
            model.addAttribute("minTemp", weather.getTempMin(json.getJSONObject("main").getDouble("temp_min")));
            model.addAttribute("pressure", weather.getPressure(json.getJSONObject("main").getDouble("pressure")));

            model.addAttribute("temperature_C", new DecimalFormat("#.0").format(celsius.getTemperature(json.getJSONObject("main").getDouble("temp"))));
            model.addAttribute("feelsLike_C", new DecimalFormat("#.0").format(celsius.getFeelsLike(json.getJSONObject("main").getDouble("feels_like"))));
            model.addAttribute("maxTemp_C", new DecimalFormat("#.0").format(celsius.getTempMax(json.getJSONObject("main").getDouble("temp_max"))));
            model.addAttribute("minTemp_C", new DecimalFormat("#.0").format(celsius.getTempMin(json.getJSONObject("main").getDouble("temp_min"))));
            model.addAttribute("pressure_C", celsius.getPressure(json.getJSONObject("main").getDouble("pressure")));

            model.addAttribute("temperature_F", new DecimalFormat("#.0").format(fahrenheit.getTemperature(json.getJSONObject("main").getDouble("temp"))));
            model.addAttribute("feelsLike_F", new DecimalFormat("#.0").format(fahrenheit.getFeelsLike(json.getJSONObject("main").getDouble("feels_like"))));
            model.addAttribute("maxTemp_F", new DecimalFormat("#.0").format(fahrenheit.getTempMax(json.getJSONObject("main").getDouble("temp_max"))));
            model.addAttribute("minTemp_F", new DecimalFormat("#.0").format(fahrenheit.getTempMin(json.getJSONObject("main").getDouble("temp_min"))));
            model.addAttribute("pressure_F", fahrenheit.getPressure(json.getJSONObject("main").getDouble("pressure")));

        } catch (Exception e) {
            model.addAttribute("error", "Could not retrieve weather data for " + city);
        }
        
        return "index";
    }
}
