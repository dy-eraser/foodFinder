package free.task.foodfinder.service;

import free.task.foodfinder.model.GeoapifySearchResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final GeoapifyService geoapifyService;

	public GeoapifySearchResponse getPlaceDetails(String city, String country) {
		return geoapifyService.getSimpleSearchResult(city, country);
	}

}
