package free.task.foodfinder.service;

import free.task.foodfinder.mapper.PlaceServiceMapper;
import free.task.foodfinder.model.SearchResponse;
import free.task.foodfinder.repository.PlaceDetailRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final GeoapifyService geoapifyService;

	private final PlaceServiceMapper placeServiceMapper;

	private final PlaceDetailRepository placeDetailRepository;

	public SearchResponse getPlaceDetails(String city, String country) {
		SearchResponse searchResponse = placeServiceMapper
				.toSearchResponse(geoapifyService.getSimpleSearchResult(city, country));
		if (searchResponse.getPlaceId() != null) {
			placeDetailRepository.save(placeServiceMapper.toPlaceDetail(searchResponse));
		}
		return searchResponse;
	}

}
