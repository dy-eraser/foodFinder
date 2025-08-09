package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class GeoapifyPlaceResponse {

	private List<Feature> features;

	@Data
	public static class Feature {

		private GeoapifyLocationDetail properties;

	}

}
