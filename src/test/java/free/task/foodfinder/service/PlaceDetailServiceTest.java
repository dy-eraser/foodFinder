package free.task.foodfinder.service;

import java.util.List;
import java.util.Optional;

import free.task.foodfinder.entity.PlaceDetail;
import free.task.foodfinder.mapper.ServiceMapper;
import free.task.foodfinder.repository.PlaceDetailRepository;
import free.task.foodfinder.util.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration
@ActiveProfiles({ "test" })
@Import({ TestConfig.class })
@ExtendWith({ SpringExtension.class })
class PlaceDetailServiceTest {

	private AutoCloseable closeable;

	private PlaceDetailService placeDetailService;

	@Mock
	private ServiceMapper mockMapper;

	@Mock
	private GeoapifyService mockGeoapifyService;

	@Mock
	private PlaceDetailRepository mockPlaceDetailRepository;

	@BeforeEach
	void setUp() {
		closeable = MockitoAnnotations.openMocks(this);
		placeDetailService = new PlaceDetailService(mockMapper, mockGeoapifyService, mockPlaceDetailRepository);
	}

	@AfterEach
	public void releaseMocks() throws Exception {
		closeable.close();
	}

	@Test
	@DisplayName("Given - searching by city and country, When - calling PlaceDetailService to find, Then - correct results should be returned and no Geoapify service calls happen")
	void findByCityAndCountryWhenPlaceDetailExists() {
		String city = "London";
		String country = "United Kingdom";
		when(mockPlaceDetailRepository.findPlaceDetailByCountryAndCity(country, city))
				.thenReturn(Optional.of(createSamplePlaceDetail(country, city)));

		PlaceDetail result = placeDetailService.findByCountryAndCity(country, city);

		assertNotNull(result);
		verify(mockPlaceDetailRepository).findPlaceDetailByCountryAndCity(country, city);
		verify(mockGeoapifyService, never()).getSimpleSearchResult(anyString(), anyString());
	}

	private PlaceDetail createSamplePlaceDetail(String country, String city) {
		PlaceDetail placeDetail = new PlaceDetail();
		placeDetail.setId(12L);
		placeDetail.setCity(city);
		placeDetail.setCountry(country);
		placeDetail.setLatitude(51.5074456);
		placeDetail.setLongitude(-0.1277653);
		placeDetail.setBbox(List.of(-0.5103751, 51.2867601, 0.3340155, 51.6918741));
		placeDetail.setPlaceId("51fd2488049d5ac0bf59e23f38faf3c04940f00101f9014600010000000000c00208");
		return placeDetail;
	}

}
