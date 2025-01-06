package com.weather.weather_app;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
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
        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + "Poznan" + "&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            String response = restTemplate.getForObject(apiUrl, String.class);
            JSONObject json = new JSONObject(response);
            
            JSONArray forecastList = json.getJSONArray("list");

            List<Weather> kelvins = new LinkedList<>();
            List<WeatherCelsius> ceclisus = new LinkedList<>();
            List<WeatherFahrenheit> foringetes = new LinkedList<>();

            for (int i = 0; i< forecastList.length(); i++) {
                JSONObject iterable_element = forecastList.getJSONObject(i);
                //String dateTime = iterable_element.getString("dt_txt");
                if (iterable_element.getString("dt_txt").contains("12:00:00"))
                {
                    Weather kelvin = new Weather();
                    kelvin.temperature = iterable_element.getJSONObject("main").getDouble("temp");
                    kelvin.feelsLike = iterable_element.getJSONObject("main").getDouble("feels_like");
                    kelvin.maxTemp = iterable_element.getJSONObject("main").getDouble("temp_max");
                    kelvin.minTemp = iterable_element.getJSONObject("main").getDouble("temp_min");
                    kelvin.pressure = iterable_element.getJSONObject("main").getDouble("pressure");
                    kelvin.date = iterable_element.getString("dt_txt").substring(0,11);
                    kelvins.add(kelvin);

                    WeatherCelsius cel = new WeatherCelsius();
                    cel.temperature = iterable_element.getJSONObject("main").getDouble("temp");
                    cel.feelsLike = iterable_element.getJSONObject("main").getDouble("feels_like");
                    cel.maxTemp = iterable_element.getJSONObject("main").getDouble("temp_max");
                    cel.minTemp = iterable_element.getJSONObject("main").getDouble("temp_min");
                    cel.pressure = iterable_element.getJSONObject("main").getDouble("pressure");
                    cel.date = iterable_element.getString("dt_txt").substring(0,11);
                    ceclisus.add(cel);

                    WeatherFahrenheit forin = new WeatherFahrenheit();
                    forin.temperature = iterable_element.getJSONObject("main").getDouble("temp");
                    forin.feelsLike = iterable_element.getJSONObject("main").getDouble("feels_like");
                    forin.maxTemp = iterable_element.getJSONObject("main").getDouble("temp_max");
                    forin.minTemp = iterable_element.getJSONObject("main").getDouble("temp_min");
                    forin.pressure = iterable_element.getJSONObject("main").getDouble("pressure");
                    forin.date = iterable_element.getString("dt_txt").substring(0,11);
                    foringetes.add(forin);
                }
            }
            
            model.addAttribute("kelvins", kelvins);
            for (Weather weather : kelvins){
                model.addAttribute("date", weather.date);
                model.addAttribute("temperature", weather.getTemperature());
                model.addAttribute("feelsLike", weather.getFeelsLike());
                model.addAttribute("maxTemp", weather.getTempMax());
                model.addAttribute("minTemp", weather.getTempMin());
                model.addAttribute("pressure", weather.getPressure());
            }

            // model.addAttribute("temperature");
            // model.addAttribute("feelsLike");
            // model.addAttribute("maxTemp");
            // model.addAttribute("minTemp");
            // model.addAttribute("pressure");




            
            // Передаємо дані в модель для відображення в HTML
            // model.addAttribute("temperature", weather.getTemperature(json.getJSONObject("main").getDouble("temp")));
            // model.addAttribute("feelsLike", weather.getFeelsLike(json.getJSONObject("main").getDouble("feels_like")));
            // model.addAttribute("maxTemp", weather.getTempMax(json.getJSONObject("main").getDouble("temp_max")));
            // model.addAttribute("minTemp", weather.getTempMin(json.getJSONObject("main").getDouble("temp_min")));
            // model.addAttribute("pressure", weather.getPressure(json.getJSONObject("main").getDouble("pressure")));

            // model.addAttribute("temperature_C", new DecimalFormat("#.0").format(celsius.getTemperature(json.getJSONObject("main").getDouble("temp"))));
            // model.addAttribute("feelsLike_C", new DecimalFormat("#.0").format(celsius.getFeelsLike(json.getJSONObject("main").getDouble("feels_like"))));
            // model.addAttribute("maxTemp_C", new DecimalFormat("#.0").format(celsius.getTempMax(json.getJSONObject("main").getDouble("temp_max"))));
            // model.addAttribute("minTemp_C", new DecimalFormat("#.0").format(celsius.getTempMin(json.getJSONObject("main").getDouble("temp_min"))));
            // model.addAttribute("pressure_C", celsius.getPressure(json.getJSONObject("main").getDouble("pressure")));

            // model.addAttribute("temperature_F", new DecimalFormat("#.0").format(fahrenheit.getTemperature(json.getJSONObject("main").getDouble("temp"))));
            // model.addAttribute("feelsLike_F", new DecimalFormat("#.0").format(fahrenheit.getFeelsLike(json.getJSONObject("main").getDouble("feels_like"))));
            // model.addAttribute("maxTemp_F", new DecimalFormat("#.0").format(fahrenheit.getTempMax(json.getJSONObject("main").getDouble("temp_max"))));
            // model.addAttribute("minTemp_F", new DecimalFormat("#.0").format(fahrenheit.getTempMin(json.getJSONObject("main").getDouble("temp_min"))));
            // model.addAttribute("pressure_F", fahrenheit.getPressure(json.getJSONObject("main").getDouble("pressure")));

        } catch (Exception e) {
            model.addAttribute("error", "Could not retrieve weather data for " + city);
        }
        
        return "index";
    }
}
