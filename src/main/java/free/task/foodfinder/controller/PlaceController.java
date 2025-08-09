package free.task.foodfinder.controller;

import free.task.foodfinder.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

	@GetMapping("/food-nearby")
	@Operation(summary = "Food search", description = "This method presents nearby restaurants from Geoapify")
	@ApiResponse(responseCode = "200", description = "success")
	@ApiResponse(responseCode = "4xx", description = "any missing data or if something goes wrong!")
	public ResponseEntity<?> getFoodNearby(
			@Parameter(example = "Iran", description = "country name") @RequestParam("country") String country,
			@Parameter(example = "Tehran", description = "city name") @RequestParam("city") String city
	) {
		log.info("Searching for food nearby, country {} and city {}", country, city);
		return ResponseEntity.ok(placeService.getFoodNearby(country, city));
	}

}
