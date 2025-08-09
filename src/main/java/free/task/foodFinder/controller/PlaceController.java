package free.task.foodFinder.controller;

import java.util.List;

import free.task.foodFinder.service.PlaceService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceController {

	private final PlaceService placeService;

	public List<PlaceDetails> getPlaceDetails() {
		placeService
	}
}
