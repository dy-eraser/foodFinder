package free.task.foodfinder.service;

import free.task.foodfinder.entity.PlaceDetail;
import free.task.foodfinder.exception.PlaceNotFoundException;
import free.task.foodfinder.repository.PlaceDetailRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceDetailService {

	private final PlaceDetailRepository placeDetailRepository;

	@Async
	public void save(PlaceDetail placeDetail) {
		try {
			placeDetailRepository.save(placeDetail);
		} catch (Exception ignored) {

		}
	}

	@Cacheable(cacheManager = "inMemoryCacheManager", cacheNames = "places", key = "#placeId")
	public PlaceDetail findByPlaceId(String placeId) {
		return placeDetailRepository.findPlaceDetailByPlaceId(placeId)
				.orElseThrow(() -> new PlaceNotFoundException("Place not found!"));
	}
}
