package com.example.routing.service;


import com.example.routing.exception.CountryNotFoundException;
import com.example.routing.exception.RouteNotFoundException;
import com.example.routing.model.Country;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
@Service
public class RoutingService {
    private static final Logger log = LoggerFactory.getLogger(RoutingService.class);
    private final CountryService countryService;

    public RoutingService(CountryService countryService){
        this.countryService=countryService;
    }

    public List<String> findRoute(String origin, String destination){
        log.info("Calculating route from {} to {}", origin, destination);
        //Because of the nature of the problem, I decided to resolve this with BFS search.
        Map<String,Country> countries = countryService.getCountries();

        log.debug("Loaded {} countries for route calculation", countries.size());

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
            log.debug("Visiting country: {} | Current path: {}", last, path);
            if(last.equals(destination)){
                log.info("Route found from {} to {}: {}", path.get(0), destination, path);
                return path;
            }
            List<String> borders = countries.get(last).getBorders();
            if(borders == null ) {
                log.trace("No borders found for country: {}", last);
                continue;
            }
            for (String neighbor : borders){
                if(!visited.contains(neighbor)){
                    log.debug("Queueing neighbor: {} | Path so far: {}", neighbor, path);
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
