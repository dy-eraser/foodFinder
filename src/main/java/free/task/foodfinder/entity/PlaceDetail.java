package free.task.foodfinder.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "place_details",
		uniqueConstraints = @UniqueConstraint(name = "uc_country_city", columnNames = { "country", "city" }))
public class PlaceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false)
	private String city;

	private String province;

	@Column(nullable = false)
	private String country;

	private String county;

	private String district;

	private Double latitude;

	private Double longitude;

	@Column(name = "place_id", nullable = false, unique = true)
	private String placeId;

	private List<Double> bbox;

}
