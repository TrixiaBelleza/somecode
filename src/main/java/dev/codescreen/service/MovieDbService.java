package dev.codescreen.service;

import dev.codescreen.model.MediaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MovieDbService {
    Logger logger = LoggerFactory.getLogger(MovieDbService.class.getName());

    @Value("${api.key}")
    private String apiKey; // This will be loaded from the application properties file.

    @Value("{themoviedb.api.base-url}")
    private String baseUrl;  // the base URL of The Movie Database API
    private final RestTemplate restTemplate;    // SpringBoot automatically creates & configures RestTemplate for us so we just need to inject this dependency

    public MovieDbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MediaObject> searchMovies(String title) {
        try {
            String url = baseUrl + "/search/movie";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)    // Construct the URL with the query params
                    .queryParam("api_key", apiKey)
                    .queryParam("query", title);

            MediaObject[] mediaObjects = restTemplate.getForObject(builder.toUriString(), MediaObject[].class);

            assert mediaObjects != null;
            return Arrays.asList(mediaObjects);
        } catch (Exception e) {
            logger.warn("An error occurred while searching for movies with title: " + title + ". Due to: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public MediaObject getMovieById(int id) {
        try {
            String url = baseUrl + "/movie/{id}";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("api_key", apiKey);
            return restTemplate.getForObject(builder.toUriString(), MediaObject.class, id);
        } catch (Exception e) {
            logger.warn("An error occurred in getting the movie for id: " + id + ". Due to: " + e.getMessage(), e);
            return null;
        }
    }

    public List<MediaObject> searchShows(String name) {
        try {
            String url = baseUrl + "/search/tv";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("api_key", apiKey)
                    .queryParam("query", name);

            MediaObject[] mediaObjects = restTemplate.getForObject(builder.toUriString(), MediaObject[].class);
            assert mediaObjects != null;
            return Arrays.asList(mediaObjects);
        } catch (Exception e) {
            logger.warn("An error occurred while searching for shows with name: " + name + ". Due to: " + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public MediaObject getShowById(int id) {
        try {
            String url = baseUrl + "/show/{id}";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("api_key", apiKey);
            return restTemplate.getForObject(builder.toUriString(), MediaObject.class, id);
        } catch (Exception e) {
            logger.warn("An error occurred in getting the show for id: " + id + ". Due to: " + e.getMessage(), e);
            return null;
        }

    }
}
