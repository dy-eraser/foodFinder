package free.task.foodfinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

	private String message;

	private Boolean success;

	public static BaseResponse notSuccess(String message) {
		return new BaseResponse(message, false);
	}

}
