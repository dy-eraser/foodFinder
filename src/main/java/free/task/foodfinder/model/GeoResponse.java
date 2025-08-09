package free.task.foodfinder.model;

import java.util.List;

import lombok.Data;

@Data
public class GeoResponse {

    private String type;

    private List<Feature> features;

}
