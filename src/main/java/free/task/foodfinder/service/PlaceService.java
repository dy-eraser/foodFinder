package free.task.foodfinder.service;

import free.task.foodfinder.mapper.PlaceServiceMapper;
import free.task.foodfinder.model.PlaceDetailDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final GeoapifyService geoapifyService;

	private final PlaceServiceMapper placeServiceMapper;

	private final PlaceDetailService placeDetailService;

	public PlaceDetailDto getFoodNearby(String city, String country) {
		PlaceDetailDto placeDetails = getPlaceDetails(city, country);
		placeDetailService.findByPlaceId(placeDetails.getPlaceId());
	}

	private PlaceDetailDto getPlaceDetails(String city, String country) {
		PlaceDetailDto placeDetailDto = placeServiceMapper
				.toPlaceDetailDto(geoapifyService.getSimpleSearchResult(city, country));
		if (placeDetailDto.getPlaceId() != null) {
			placeDetailService.save(placeServiceMapper.toPlaceDetail(placeDetailDto));
		}
		return placeDetailDto;
	}

}
