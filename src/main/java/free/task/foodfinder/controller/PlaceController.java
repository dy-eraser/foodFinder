package free.task.foodfinder.controller;

import java.util.List;

import free.task.foodfinder.model.GetAmenitiesResponse;
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

	@GetMapping("/amenities-nearby")
	@Operation(summary = "Amenities search", description = "This method presents nearby amenities from Geoapify")
	@ApiResponse(responseCode = "200", description = "success")
	@ApiResponse(responseCode = "4xx", description = "any missing data or if something goes wrong!")
	public ResponseEntity<GetAmenitiesResponse> getAmenitiesNearby(
			@Parameter(example = "Iran", description = "country name") @RequestParam("country") String country,
			@Parameter(example = "Tehran", description = "city name") @RequestParam("city") String city,
			@Parameter(example = "tourism,religion,beach,parking",
					description = "a list to query local points of interest and amenities")
			@RequestParam(value = "amenities", defaultValue = "catering") List<String> amenities
	) {
		log.info("Searching for amenities nearby, country {}, city {}, amenities {}", country, city, amenities);
		GetAmenitiesResponse success = GetAmenitiesResponse
				.success("SUCCESS", placeService.getAmenitiesNearby(country, city, amenities));
		return ResponseEntity.ok(success);
	}

}
