package free.task.foodfinder.service;

import free.task.foodfinder.configuration.EnvironmentVariables;
import free.task.foodfinder.exception.BaseException;
import free.task.foodfinder.model.GeoResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeoapifyService {

	private final EnvironmentVariables env;

	private final RestTemplate restTemplate;

	public GeoResponse getSimpleSearchResult(String city, String country) throws BaseException {
		String url = String.format("%s?city=%s&country=%s&apiKey=%s",
				env.getGeoapifyBaseUrl() + env.getGeoapifySimpleSearchUrl(), city, country, env.getGeoapifyApiKey());

		GeoResponse geoResponse;
		try {
			geoResponse = restTemplate.getForObject(url, GeoResponse.class);
		} catch (RestClientException e) {
			throw new BaseException("Something bad happened while connecting to Geoapify!");
		}
		return geoResponse;
	}

}
