package dev.codescreen.controller;

import dev.codescreen.model.MediaObject;
import dev.codescreen.service.MovieDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final MovieDbService movieDbService;

    @Autowired
    public ShowController(MovieDbService movieDbService) {
        this.movieDbService = movieDbService;
    }

    /**
     * When provided a query parameter of a name, a TV show search against the movie database will be returned.
     * @param name name of the show to search
     * @return the list of shows - max length of 10
     */
    @GetMapping
    public List<MediaObject> searchShows(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            List<MediaObject> shows = movieDbService.searchShows(name);

            // Limit the result array to 10 items
            int endIndex = Math.min(shows.size(), 10);
            return shows.subList(0, endIndex);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * When provided a path parameter of id, a query for a specific TV show in the movie database will be returned.
     * @param id path parameter id of the specific TV show
     * @return the TV show that matches with the id provided.
     */
    @GetMapping("/{id}")
    public MediaObject getShowById(@PathVariable int id) {
        return movieDbService.getShowById(id);
    }
}
