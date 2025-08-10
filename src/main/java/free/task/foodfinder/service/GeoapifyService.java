package free.task.foodfinder.service;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoapifyService {

	private final EnvironmentVariables env;

	private final RestTemplate restTemplate;

	public GeoapifySearchResponse getSimpleSearchResult(String city, String country) throws BaseException {
		URI uri = UriComponentsBuilder.fromUriString(env.getGeoapifyBaseUrl())
				.pathSegment(env.getGeoapifySimpleSearchUrl())
				.queryParam("city", city)
				.queryParam("country", country)
				.queryParam("format", "json")
				.queryParam("apiKey", env.getGeoapifyApiKey())
				.build()
				.toUri();

		GeoapifySearchResponse geoapifySearchResponse = null;
		try {
			log.info("Calling Geoapify search API for country {} and city {}", country, city);
			geoapifySearchResponse = restTemplate.getForObject(uri, GeoapifySearchResponse.class);
		} catch (RestClientException exception) {
			logAndRethrow(exception);
		}
		return geoapifySearchResponse;
	}

	public GeoapifyPlaceResponse getNearbyPlaces(String placeId, List<String> categories, int limit) throws BaseException {
		URI uri = UriComponentsBuilder.fromUriString(env.getGeoapifyBaseUrl())
				.pathSegment(env.getGeoapifyPlaceDetailsUrl())
				.queryParam("categories", categories)
				.queryParam("filter", "place:" + placeId)
				.queryParam("limit", limit)
				.queryParam("apiKey", env.getGeoapifyApiKey())
				.build()
				.toUri();

		GeoapifyPlaceResponse geoapifyPlaceResponse = null;
		try {
			log.info("Calling Geoapify place API for placeId {}", placeId);
			geoapifyPlaceResponse = restTemplate.getForObject(uri, GeoapifyPlaceResponse.class);
		} catch (RestClientException exception) {
			logAndRethrow(exception);
		}
		return geoapifyPlaceResponse;
	}

	private static void logAndRethrow(RestClientException exception) {
		log.error("Geoapify exception is: {}", exception.getMessage());
		throw new BaseException("Something bad happened while connecting to Geoapify!");
	}

}
