package com.example.routing.service;

import com.example.routing.exception.CountryNotFoundException;
import com.example.routing.exception.RouteNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoutingServiceTest {

    @Autowired
    private RoutingService routingService;

    @Test
    void shouldFindRouteFromCzeToIta() {
        List<String> route = routingService.findRoute("CZE", "ITA");

        assertNotNull(route);
        assertFalse(route.isEmpty());
        assertEquals("CZE", route.get(0));
        assertEquals("ITA", route.get(route.size() - 1));
    }

    @Test
    void shouldThrowWhenNoLandRouteExists() {
        Exception ex = assertThrows(RouteNotFoundException.class, () ->
                routingService.findRoute("ISL", "ITA")
        );
        assertTrue(ex.getMessage().contains("No land route found from"));
    }

    @Test
    void shouldThrowForInvalidCountryCode() {
        Exception ex = assertThrows(CountryNotFoundException.class, () ->
                routingService.findRoute("XCV", "ITA")
        );
        assertTrue(ex.getMessage().contains("Country not found:"));
    }
}