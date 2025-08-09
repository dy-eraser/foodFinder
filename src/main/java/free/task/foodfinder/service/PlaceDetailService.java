package free.task.foodfinder.service;

import free.task.foodfinder.entity.PlaceDetail;
import free.task.foodfinder.exception.PlaceNotFoundException;
import free.task.foodfinder.mapper.ServiceMapper;
import free.task.foodfinder.repository.PlaceDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceDetailService {

	private final ServiceMapper mapper;

	private final GeoapifyService geoapifyService;

	private final PlaceDetailRepository placeDetailRepository;

	@Cacheable(cacheManager = "inMemoryCacheManager", cacheNames = "places", key = "#country + '_' + #city")
	public PlaceDetail findByCountryAndCity(String country, String city) {
		return placeDetailRepository.findPlaceDetailByCountryAndCity(country, city)
				.orElseGet(() -> {
							PlaceDetail placeDetail = mapper.toPlaceDetail(geoapifyService.getSimpleSearchResult(city, country));
							if (placeDetail == null) {
								throw new PlaceNotFoundException("Place not found!");
							}
							return save(placeDetail);
						}
				);
	}

	public void findFoodNearby(PlaceDetail placeDetail) {
		if (placeDetail.getBbox() != null && !placeDetail.getBbox().isEmpty()) {
			geoapifyService.getNearbyCatering(placeDetail.getBbox());
		} else {
			geoapifyService.getNearbyCatering(placeDetail.getPlaceId());
		}
	}

	private PlaceDetail save(PlaceDetail placeDetail) {
		try {
			return placeDetailRepository.save(placeDetail);
		} catch (Exception exception) {
			log.error("Exception to save placeDetail: {}", exception.getMessage());
		}
		return placeDetail;
	}

}
