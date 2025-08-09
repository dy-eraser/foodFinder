package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class Feature {

    private Properties properties;

	private List<Double> bbox;

}
