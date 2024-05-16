package dev.codescreen;

import dev.codescreen.controller.MovieController;
import dev.codescreen.model.MediaObject;
import dev.codescreen.service.MovieDbService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TheMovieApp.class)
public class MovieControllerTests {
    @Mock
    private MovieDbService movieDbService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testSearchMovies() {
        // Mock data
        MediaObject mediaObject1 = new MediaObject(1, "2022-01-01", "Movie 1", 8.5f);
        MediaObject mediaObject2 = new MediaObject(2, "2023-02-02", "Movie 2", 7.9f);
        List<MediaObject> mockMovies = Arrays.asList(mediaObject1, mediaObject2);

        // Mock service method
        when(movieDbService.searchMovies("action")).thenReturn(mockMovies);

        // Call controller method
        List<MediaObject> response = movieController.searchMovies("action");

        // Check response
        assertEquals(mockMovies.size(), response.size());
        assertEquals(mockMovies.get(0).getId(), response.get(0).getId());
        assertEquals(mockMovies.get(1).getId(), response.get(1).getId());
        assertEquals(mockMovies.get(0).getDate(), response.get(0).getDate());
        assertEquals(mockMovies.get(1).getDate(), response.get(1).getDate());
        assertEquals(mockMovies.get(0).getName(), response.get(0).getName());
        assertEquals(mockMovies.get(1).getName(), response.get(1).getName());
        assertEquals(mockMovies.get(0).getRating(), response.get(0).getRating());
        assertEquals(mockMovies.get(1).getRating(), response.get(1).getRating());
    }

    @Test
    public void testGetMovieById() {
        // Mock data
        MediaObject mockMovie = new MediaObject(1, "2022-01-01", "Movie 1", 8.5f);

        // Mock service method
        when(movieDbService.getMovieById(1)).thenReturn(mockMovie);

        // Call controller method
        MediaObject response = movieController.getMovieById(1);

        // Check response
        assertEquals(mockMovie.getId(), response.getId());
        assertEquals(mockMovie.getDate(), response.getDate());
        assertEquals(mockMovie.getName(), response.getName());
        assertEquals(mockMovie.getRating(), response.getRating());
    }

    @Test
    public void testSearchMovies_NotFound() {
        // Mock service method to return empty list for non-existing query
        when(movieDbService.searchMovies("non_existing_query")).thenReturn(Collections.emptyList());

        // Call controller method with non-existing query
        List<MediaObject> response = movieController.searchMovies("non_existing_query");

        // Check response is empty
        assertEquals(0, response.size());
    }

    @Test
    public void testGetMovieById_NotFound() {
        // Mock service method to return null for non-existing movie id
        when(movieDbService.getMovieById(999)).thenReturn(null);

        // Call controller method with non-existing movie id
        MediaObject response = movieController.getMovieById(999);

        // Check response is null
        assertNull(response);
    }
}
