package com.example.routing.service;


import com.example.routing.exception.CountryNotFoundException;
import com.example.routing.exception.RouteNotFoundException;
import com.example.routing.model.Country;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class RoutingService {
    private final CountryService countryService;

    public RoutingService(CountryService countryService){
        this.countryService=countryService;
    }

    public List<String> findRoute(String origin, String destination){
        //Because of the nature of the problem, I decided to resolve this with BFS search.
        Map<String,Country> countries = countryService.getCountries();

        if(!countries.containsKey(origin)){
            throw new CountryNotFoundException(origin);
        }
        if (!countries.containsKey(destination)) {
            throw new CountryNotFoundException(destination);
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(List.of(origin));
        visited.add(origin);

        while (!queue.isEmpty()){
            List<String> path = queue.poll();
            String last = path.get(path.size() -1 );
            if(last.equals(destination)){
                return path;
            }
            List<String> borders = countries.get(last).getBorders();
            if(borders == null ) continue;
            for (String neighbor : borders){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);

                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        throw new RouteNotFoundException(origin,destination);
    }
}
