package free.task.foodfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeoapifyLocationDetail {

	private String name;

	private String city;

	private String country;

	@JsonProperty("lat")
	private Double latitude;

	@JsonProperty("lon")
	private Double longitude;

	@JsonProperty("place_id")
	private String placeId;

	private BoundingBox bbox;

	@Data
	public static class BoundingBox {

		private double lon1;

		private double lat1;

		private double lon2;

		private double lat2;

	}
}
