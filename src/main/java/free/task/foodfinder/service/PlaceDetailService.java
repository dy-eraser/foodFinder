package free.task.foodfinder.service;

import java.util.List;

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

	private static final int AMENITIES_LIMIT = 10;

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

	public List<PlaceDetail> findAmenitiesNearby(PlaceDetail placeDetail, List<String> amenities) {
		return mapper.toPlaceDetail(geoapifyService.getNearbyPlaces(placeDetail.getPlaceId(), amenities, AMENITIES_LIMIT));
	}

	private PlaceDetail save(PlaceDetail placeDetail) {
		try {
			return placeDetailRepository.save(placeDetail);
		} catch (Exception exception) {
			log.error("Exception to save placeDetail: {} with message: {}", placeDetail, exception.getMessage());
		}
		return placeDetail;
	}

}
