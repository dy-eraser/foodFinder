package free.task.foodfinder.service;

import free.task.foodfinder.mapper.PlaceServiceMapper;
import free.task.foodfinder.model.SearchResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final GeoapifyService geoapifyService;

	private final PlaceServiceMapper placeServiceMapper;

	public SearchResponse getPlaceDetails(String city, String country) {
		return placeServiceMapper.toSearchResponse(geoapifyService.getSimpleSearchResult(city, country));
	}

}
