package free.task.foodfinder.mapper;

import java.util.List;

import free.task.foodfinder.entity.PlaceDetail;
import free.task.foodfinder.model.GeoapifyLocationDetail;
import free.task.foodfinder.model.GeoapifyLocationDetail.BoundingBox;
import free.task.foodfinder.model.GeoapifyPlaceResponse;
import free.task.foodfinder.model.GeoapifyPlaceResponse.Feature;
import free.task.foodfinder.model.GeoapifySearchResponse;
import free.task.foodfinder.model.PlaceDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

	List<PlaceDetailDto> toPlaceDetailDtoList(List<PlaceDetail> placeDetails);

	default List<PlaceDetail> toPlaceDetail(GeoapifyPlaceResponse geoapifyPlaceResponse) {
		if (geoapifyPlaceResponse.getFeatures()
				.isEmpty()) {
			return null;
		}
		return toPlaceDetail(geoapifyPlaceResponse.getFeatures());
	}

	List<PlaceDetail> toPlaceDetail(List<Feature> features);

	default PlaceDetail toPlaceDetail(GeoapifySearchResponse geoapifySearchResponse) {
		if (geoapifySearchResponse.getResults()
				.isEmpty()) {
			return null;
		}
		return toPlaceDetail(geoapifySearchResponse.getResults()
				.get(0));
	}

	@Mapping(target = "name", source = "locationDetail.name")
	@Mapping(target = "city", source = "locationDetail.city")
	@Mapping(target = "country", source = "locationDetail.country")
	@Mapping(target = "latitude", source = "locationDetail.latitude")
	@Mapping(target = "longitude", source = "locationDetail.longitude")
	@Mapping(target = "placeId", source = "locationDetail.placeId")
	@Mapping(target = "bbox", source = "locationDetail.bbox", qualifiedByName = "boundingBoxToList")
	PlaceDetail toPlaceDetail(GeoapifyLocationDetail locationDetail);

	@Named("boundingBoxToList")
	default List<Double> boundingBoxToList(BoundingBox boundingBox) {
		return List.of(boundingBox.getLon1(), boundingBox.getLat1(), boundingBox.getLon2(), boundingBox.getLat2());
	}

}
