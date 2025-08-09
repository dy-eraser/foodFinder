package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class PlaceDetailDto {

	private String city;

	private String province;

	private String country;

	private String county;

	private String district;

	private Double latitude;

	private Double longitude;

	private String placeId;

	private List<Double> bbox;

}
