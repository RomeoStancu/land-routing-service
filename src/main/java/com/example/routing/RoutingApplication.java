package com.example.routing;


import com.example.routing.service.CountryService;
import com.example.routing.service.RoutingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoutingApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RoutingApplication.class, args);
		// Manual testing
		/*CountryService countryService = new CountryService();
		countryService.loadCountries();  // manually trigger @PostConstruct logic

		RoutingService routingService = new RoutingService(countryService);

		System.out.println("Testing routes...");

		System.out.println("CZE -> ITA = " +
				routingService.findRoute("CZE", "ITA"));

		System.out.println("DEU -> ESP = " +
				routingService.findRoute("DEU", "ESP"));

		System.out.println("ISL -> ITA = " +
				routingService.findRoute("ISL", "ITA"));*/

	}

}
