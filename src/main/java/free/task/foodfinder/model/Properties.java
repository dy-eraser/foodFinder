package free.task.foodfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Properties {

	private String city;

	private String province;

	private String country;

	private String county;

	private String district;

	@JsonProperty("lat")
	private Double latitude;

	@JsonProperty("lon")
	private Double longitude;

	@JsonProperty("place_id")
	private String placeId;

}
