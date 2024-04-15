package com.amrendra.weather.Repository;

import com.amrendra.weather.Entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface weatherRepository extends JpaRepository<Weather,Integer> {

    List<Weather> findByCountryName(String countryName);
}
