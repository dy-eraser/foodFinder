package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class GeoapifySearchResponse {

	private List<GeoapifyLocationDetail> results;

}
