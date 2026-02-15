package com.example.routing.service;

import com.example.routing.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private static final String DATA_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";
    private Map<String, Country> countriesByCode;

    @PostConstruct
    public void loadCountries() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(DATA_URL,String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<Country> countries = mapper.readValue(json, new TypeReference<List<Country>>() {});

        countriesByCode = countries.stream()
                .filter(c ->c.getCca3() !=null)
                .collect(Collectors.toMap(
                        c ->c.getCca3().toUpperCase(), //make sure data is normalized
                        c->c,
                        (a,b) -> a //make sure there are no duplicates
                ));
        countriesByCode.values().forEach(country -> { //make sure all borders are real countries
            if(country.getBorders()!=null){
                country.getBorders().removeIf(border->!countriesByCode.containsKey(border));
            }
        });
    }

    public Map<String,Country> getCountries(){
        return countriesByCode;
    }
}
