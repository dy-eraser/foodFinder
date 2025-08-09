package free.task.foodfinder.service;

import java.util.List;

import free.task.foodfinder.mapper.ServiceMapper;
import free.task.foodfinder.model.PlaceDetailDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService {

	private final ServiceMapper serviceMapper;

	private final PlaceDetailService placeDetailService;

	public List<PlaceDetailDto> getFoodNearby(String country, String city) {
		var placeDetail = placeDetailService.findByCountryAndCity(country, city);
		return serviceMapper.toPlaceDetailDtoList(placeDetailService.findFoodNearby(placeDetail));
	}

}
