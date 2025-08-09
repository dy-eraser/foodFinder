package free.task.foodfinder.controller;

import free.task.foodfinder.service.PlaceService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	@GetMapping("/place-details")
	public ResponseEntity<?> getPlaceDetails(
			@RequestParam String city,
			@RequestParam String country) {
		return placeService.getPlace(city, country);
	}
}
