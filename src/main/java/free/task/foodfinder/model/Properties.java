package free.task.foodfinder.model;

import lombok.Data;

@Data
public class Properties {

	private String city;

	private String province;

	private String country;

	private String county;

	private String district;

	private Double lat;

	private Double lon;

	private String place_id;

}
