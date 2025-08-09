package free.task.foodfinder.controller;

import free.task.foodfinder.model.GeoapifySearchResponse;
import free.task.foodfinder.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/place-details")
	@Operation(summary = "Simple search details", description = "This method retrieves data from simple search on Geoapify")
	@ApiResponse(responseCode = "200", description = "success")
	@ApiResponse(responseCode = "4xx", description = "any missing data or if something goes wrong!")
	public ResponseEntity<GeoapifySearchResponse> getPlaceDetails(@RequestParam String city, @RequestParam String country) {
		log.info("Get place details for city {} and country {}", city, country);
		return ResponseEntity.ok(placeService.getPlaceDetails(city, country));
	}

}
