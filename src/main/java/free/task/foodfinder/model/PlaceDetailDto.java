package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class PlaceDetailDto {

	private String name;

	private String city;

	private String country;

	private Double latitude;

	private Double longitude;

	private String placeId;

	private List<Double> bbox;

}
