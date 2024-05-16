package dev.codescreen.controller;

import dev.codescreen.model.MediaObject;
import dev.codescreen.service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieDbService movieDbService;

    @Autowired
    public MovieController(MovieDbService movieDbService) {
        this.movieDbService = movieDbService;
    }

    /**
     * When provided a query parameter of a title, a movie search against the movie database will be returned.
     * @param title title of the movie to search
     * @return the list of movies - max length of 10
     */
    @GetMapping
    public List<MediaObject> searchMovies(@RequestParam(required = false) String title) {
        if (title != null && !title.isEmpty()) {
            List<MediaObject> movies = movieDbService.searchMovies(title);

            // Limit the result array to 10 items
            int endIndex = Math.min(movies.size(), 10);
            return movies.subList(0, endIndex);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * When provided a path parameter of id, a query for a specific movie in the movie database will be returned.
     * @param id path parameter id of the specific movie
     * @return the movie that matches with the id provided.
     */
    @GetMapping("/{id}")
    public MediaObject getMovieById(@PathVariable int id) {
        return movieDbService.getMovieById(id);
    }
}
