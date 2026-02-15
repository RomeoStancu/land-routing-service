package com.example.routing.controller;

import com.example.routing.service.RoutingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final RoutingService routingService;

    public RoutingController (RoutingService routingService){
        this.routingService = routingService;
    }
    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<?> route(
            @PathVariable  String origin,
            @PathVariable  String destination) {
        log.info("Routing request from {} to {}", origin, destination);
        List<String> route = routingService.findRoute(origin, destination);
        return ResponseEntity.ok(Map.of("route", route));
    }
}
