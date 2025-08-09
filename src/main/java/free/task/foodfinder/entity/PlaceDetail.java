package free.task.foodfinder.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "place_details")
public class PlaceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	private String city;

	private String province;

	private String country;

	private String county;

	private String district;

	private Double latitude;

	private Double longitude;

	@Column(name = "place_id", nullable = false, unique = true)
	private String placeId;

	private List<Double> bbox;

}
