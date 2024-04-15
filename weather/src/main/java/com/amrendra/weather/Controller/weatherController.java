package com.amrendra.weather.Controller;

import com.amrendra.weather.Entity.Weather;
import com.amrendra.weather.Repository.weatherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weatherApp")
public class weatherController {
    @Autowired
    private weatherRepository weatherRepo;

    @PostMapping
    public String addWeather(@RequestBody Weather weather){
        weatherRepo.save(weather);
        return "added weather successfully.";
    }
    @GetMapping
    public List<Weather> getAllWeather(){
        List<Weather> weathers= weatherRepo.findAll();
        return weathers;
    }

    @GetMapping("/{id}")
    public Weather getWeatherById(@PathVariable int id){
        return weatherRepo.findById(id).
                orElseThrow(()->new EntityNotFoundException("Weather not found by id:" +id));
    }

    @DeleteMapping("/{id}")
    public String deleteWeatherById(@PathVariable int id){
        weatherRepo.deleteById(id);
        return "Weather by id deleted successfully";
    }

    @PutMapping("/{id}")
    public String updatedWeatherById(@PathVariable int id, @RequestBody Weather weather) {

        Weather updateIt= weatherRepo.findById(id)
                .orElseThrow(()->new EntityNotFoundException( "WeatherId not found by id:"+id));

        updateIt.setId(weather.getId());
        updateIt.setCountryName(weather.getCountryName());
        updateIt.setCityName(weather.getCityName());
        updateIt.setTemp(weather.getTemp());

        Weather updatedWeather=weatherRepo.save(updateIt);

        return "Weather updated successfully";
    }


//  Update the weather in another way:Enhanced for loop;
//    @PutMapping("/{id}")
//    public String updatedWeather(@PathVariable int id, @RequestBody Weather weather){
//
//        List<Weather> allWeather=weatherRepo.findAll();
//
//        for(Weather updatedWeather:allWeather){
//
//            if (updatedWeather.getId()==id){
//
//             updatedWeather.setCountryName(weather.getCountryName());
//             updatedWeather.setCityName(weather.getCityName());
//             updatedWeather.setTemp(weather.getTemp());
//
//             weatherRepo.save(updatedWeather);
//            }
//        }
//        return "updated weather successfully: using for enhanced loop";
//    }


    //  Get country_Name using RequestParam
    @GetMapping("/country")
    public List<Weather> getWeatherByCountry(@RequestParam String countryName) {

        List<Weather> weatherList = weatherRepo.findByCountryName(countryName);

        return weatherList;
    }

    //  Get country_Name using PathVariable
    @GetMapping("/country/{countryName}")
    public List<Weather> getWeatherByCountr(@PathVariable String countryName) {

        List<Weather> weather= weatherRepo.findByCountryName(countryName);

        return weather;
    }

}

