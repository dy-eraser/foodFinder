package free.task.foodfinder.service;

import free.task.foodfinder.mapper.ServiceMapper;
import free.task.foodfinder.model.PlaceDetailDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final ServiceMapper serviceMapper;

	private final PlaceDetailService placeDetailService;

	public PlaceDetailDto getFoodNearby(String country, String city) {
		return serviceMapper.toPlaceDetailDto(placeDetailService.findByCountryAndCity(country, city));
	}

}
