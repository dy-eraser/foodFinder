package free.task.foodfinder.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetAmenitiesResponse extends BaseResponse {

	private List<PlaceDetailDto> places;

	public static GetAmenitiesResponse success(String message, List<PlaceDetailDto> places) {
		GetAmenitiesResponse response = new GetAmenitiesResponse(places);
		response.setSuccess(true);
		response.setMessage(message);
		return response;
	}

}
