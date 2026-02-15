package com.example.routing.controller;

import com.example.routing.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService;

    public RoutingController (RoutingService routingService){
        this.routingService = routingService;
    }
    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<?> route(
            @PathVariable String origin,
            @PathVariable String destination) {

        List<String> route = routingService.findRoute(origin, destination);
        return ResponseEntity.ok(Map.of("route", route));
    }
}
