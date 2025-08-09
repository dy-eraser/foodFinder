package free.task.foodfinder.mapper;

import free.task.foodfinder.model.Feature;
import free.task.foodfinder.model.GeoapifySearchResponse;
import free.task.foodfinder.model.SearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaceServiceMapper {

	@Mapping(target = "city", source = "feature.properties.city")
	@Mapping(target = "province", source = "feature.properties.province")
	@Mapping(target = "country", source = "feature.properties.country")
	@Mapping(target = "county", source = "feature.properties.county")
	@Mapping(target = "district", source = "feature.properties.district")
	@Mapping(target = "latitude", source = "feature.properties.latitude")
	@Mapping(target = "longitude", source = "feature.properties.longitude")
	@Mapping(target = "placeId", source = "feature.properties.placeId")
	@Mapping(target = "bbox", source = "feature.bbox")
	@Mapping(target = "success", constant = "true")
	@Mapping(target = "message", constant = "SUCCESS")
	SearchResponse toSearchResponse(Feature feature);

	@Mapping(target = "success", constant = "true")
	@Mapping(target = "message", source = "message")
	SearchResponse toEmptySearchResponse(String message);

	default SearchResponse toSearchResponse(GeoapifySearchResponse geoapifySearchResponse) {
		if (geoapifySearchResponse == null || geoapifySearchResponse.getFeatures() == null
				|| geoapifySearchResponse.getFeatures()
				.isEmpty()) {
			return toEmptySearchResponse("No search results found");
		}
		return toSearchResponse(geoapifySearchResponse.getFeatures()
				.get(0));
	}

}
