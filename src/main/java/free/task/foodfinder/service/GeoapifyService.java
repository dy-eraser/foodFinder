package free.task.foodfinder.service;

import java.util.List;

import free.task.foodfinder.configuration.EnvironmentVariables;
import free.task.foodfinder.exception.BaseException;
import free.task.foodfinder.model.GeoapifyPlaceResponse;
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

	public GeoapifyPlaceResponse getNearbyCatering(String placeId) throws BaseException {
		String url = String.format("%s?categories=catering&filter=place:%s&limit=10&apiKey=%s",
				env.getGeoapifyBaseUrl() + env.getGeoapifyPlaceDetailsUrl(), placeId, env.getGeoapifyApiKey());

		GeoapifyPlaceResponse geoapifyPlaceResponse;
		try {
			log.info("Calling Geoapify place API for placeId {}", placeId);
			geoapifyPlaceResponse = restTemplate.getForObject(url, GeoapifyPlaceResponse.class);
		} catch (RestClientException exception) {
			log.error("Geoapify exception is: {}", exception.getMessage());
			throw new BaseException("Something bad happened while connecting to Geoapify!");
		}
		return geoapifyPlaceResponse;
	}

	public GeoapifyPlaceResponse getNearbyCatering(List<Double> bbox) {
		String url = String.format("%s?categories=catering&filter=rect:%s&limit=10&apiKey=%s",
				env.getGeoapifyBaseUrl() + env.getGeoapifyPlaceDetailsUrl(), bbox, env.getGeoapifyApiKey());

		GeoapifyPlaceResponse geoapifyPlaceResponse;
		try {
			log.info("Calling Geoapify place API for BoundingBox {}", bbox);
			geoapifyPlaceResponse = restTemplate.getForObject(url, GeoapifyPlaceResponse.class);
		} catch (RestClientException exception) {
			log.error("Geoapify exception is: {}", exception.getMessage());
			throw new BaseException("Something bad happened while connecting to Geoapify!");
		}
		return geoapifyPlaceResponse;
	}

}
