package com.example.routing.service;

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
    void shouldReturnEmptyWhenNoLandRouteExists() {
        List<String> route = routingService.findRoute("ISL", "ITA");
        assertTrue(route.isEmpty());
    }

    @Test
    void shouldThrowForInvalidCountryCode() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                routingService.findRoute("XXX", "ITA")
        );

        assertTrue(ex.getMessage().contains("Invalid country code"));
    }
}