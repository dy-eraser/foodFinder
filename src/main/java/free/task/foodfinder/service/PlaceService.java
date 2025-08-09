package free.task.foodfinder.service;

import free.task.foodfinder.entity.PlaceDetail;
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
		PlaceDetail placeDetail = placeDetailService.findByCountryAndCity(country, city);
		return serviceMapper.toPlaceDetailDto(placeDetail);
	}

}
