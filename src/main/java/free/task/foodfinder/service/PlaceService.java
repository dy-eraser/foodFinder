package free.task.foodfinder.service;

import free.task.foodfinder.model.GeoResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final GeoapifyService geoapifyService;

	public GeoResponse getPlaceDetails(String city, String country) {
		return geoapifyService.getSimpleSearchResult(city, country);
	}

}
