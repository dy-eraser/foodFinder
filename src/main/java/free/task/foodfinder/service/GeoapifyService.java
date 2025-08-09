package free.task.foodfinder.service;

import free.task.foodfinder.configuration.EnvironmentVariables;
import free.task.foodfinder.exception.BaseException;
import free.task.foodfinder.model.GeoapifySearchResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GeoapifyService {

	private final EnvironmentVariables env;

	private final RestTemplate restTemplate;

	public GeoapifySearchResponse getSimpleSearchResult(String city, String country) throws BaseException {
		String url = String.format("%s?city=%s&country=%s&apiKey=%s",
				env.getGeoapifyBaseUrl() + env.getGeoapifySimpleSearchUrl(), city, country, env.getGeoapifyApiKey());

		GeoapifySearchResponse geoapifySearchResponse;
		try {
			geoapifySearchResponse = restTemplate.getForObject(url, GeoapifySearchResponse.class);
		} catch (RestClientException e) {
			throw new BaseException("Something bad happened while connecting to Geoapify!");
		}
		return geoapifySearchResponse;
	}

}
