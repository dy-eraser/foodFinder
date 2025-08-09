package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchResponse extends BaseResponse {

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
