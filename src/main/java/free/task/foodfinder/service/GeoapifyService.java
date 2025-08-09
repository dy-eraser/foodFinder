package free.task.foodfinder.service;

import java.util.List;

import free.task.foodfinder.configuration.EnvironmentVariables;
import free.task.foodfinder.exception.BaseException;
import free.task.foodfinder.model.GeoapifySearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoapifyService {

	private final EnvironmentVariables env;

	private final RestTemplate restTemplate;

	public GeoapifySearchResponse getSimpleSearchResult(String city, String country) throws BaseException {
		String url = String.format("%s?city=%s&country=%s&apiKey=%s&format=json",
				env.getGeoapifyBaseUrl() + env.getGeoapifySimpleSearchUrl(), city, country, env.getGeoapifyApiKey());

		GeoapifySearchResponse geoapifySearchResponse;
		try {
			log.info("Calling Geoapify search API for country {} and city {}", country, city);
			geoapifySearchResponse = restTemplate.getForObject(url, GeoapifySearchResponse.class);
		} catch (RestClientException exception) {
			log.error("Geoapify exception is: {}", exception.getMessage());
			throw new BaseException("Something bad happened while connecting to Geoapify!");
		}
		return geoapifySearchResponse;
	}

	public void getNearbyCatering(String placeId) {

	}

	public void getNearbyCatering(List<Double> bbox) {


	}
}
