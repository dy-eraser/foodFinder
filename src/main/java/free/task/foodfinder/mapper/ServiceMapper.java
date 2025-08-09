package free.task.foodfinder.mapper;

import free.task.foodfinder.entity.PlaceDetail;
import free.task.foodfinder.model.Feature;
import free.task.foodfinder.model.GeoapifySearchResponse;
import free.task.foodfinder.model.PlaceDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

	@Mapping(target = "city", source = "feature.properties.city")
	@Mapping(target = "province", source = "feature.properties.province")
	@Mapping(target = "country", source = "feature.properties.country")
	@Mapping(target = "county", source = "feature.properties.county")
	@Mapping(target = "district", source = "feature.properties.district")
	@Mapping(target = "latitude", source = "feature.properties.latitude")
	@Mapping(target = "longitude", source = "feature.properties.longitude")
	@Mapping(target = "placeId", source = "feature.properties.placeId")
	@Mapping(target = "bbox", source = "feature.bbox")
	PlaceDetail toPlaceDetail(Feature feature);

	default PlaceDetail toPlaceDetail(GeoapifySearchResponse geoapifySearchResponse) {
		if (geoapifySearchResponse == null || geoapifySearchResponse.getFeatures() == null
				|| geoapifySearchResponse.getFeatures()
				.isEmpty()) {
			return null;
		}
		return toPlaceDetail(geoapifySearchResponse.getFeatures()
				.get(0));
	}

	PlaceDetailDto toPlaceDetailDto(PlaceDetail placeDetail);

}
