# Java Backend Engineer Coding Challenge

## Table of Contents
1. [Objective](#objective)
2. [The Movie Database API v3](#the-movie-database-api-v3)
3. [Specifications](#specifications)
4. [Allowed material](#allowed-materials)
5. [Evaluation](#evaluation)

## Objective
Create a maintainable and extensible [Spring](https://spring.io/projects/spring-boot) REST API that acts as a gateway to and transforms data received from a backend service. Imagine this is a greenfield development project that you are starting, knowing it will eventually have a larger
team dedicated to expanding the scope of the project.

To simulate a backend service, the API will be integrating with the free API provided by [themoviedb.org](https://www.themoviedb.org/?language=en-US).

**Time Constraint:** The REST API should be submitted within 24 hours of receiving access to this project. This exercise **should not** take more than a sum total of 3 hours.

## The Movie Database API v3
**API Key:** `41a6894ca93cb1c78657d9e799e164de`

The following endpoints are all that should be required to successfully integrate the REST API with `themoviedb.org`:

* [GET https://api.themoviedb.org/3/search/movie?api_key={api_key}&query={title}](https://developers.themoviedb.org/3/search/search-movies)

* [GET https://api.themoviedb.org/3/search/tv?api_key={api_key}&query={name}](https://developers.themoviedb.org/3/search/search-tv-shows)

* [GET https://api.themoviedb.org/3/movie/{movie_id}?api_key={api_key}](https://developers.themoviedb.org/3/movies/get-movie-details)

* [GET https://api.themoviedb.org/3/tv/{tv_id}?api_key={api_key}](https://developers.themoviedb.org/3/tv/get-tv-details)

Data retrieved from `themoviedb.org` will need to be transformed into an instance of `MediaObject`,
a generic representation of a movie or show:

**Media Object Properties**

* `id` - integer
    * id of movie
    * id of show
* `date` - string
    * release_date of movie
    * first_air_date of show
* `name` - string
    * title of movie
    * name of show
* `rating` - float
    * popularity of movie
    * popularity of show

## Specifications
Note that all code **must** reside under the dev.codescreen package. 

Using `Spring Boot`, create a REST API running on port `8080` that exposes the following routes:

* `/movies`
* `/shows`

MonetaGo engineers will assess the API by running `mvn install` (which should produce an executable JAR), followed by queries against the API.

Routes should be capable of handling the following requests:

### `/movies`
#### Generic Search
##### Request
When provided a query parameter of `title`, a movie search against the movie database should be executed.

##### Response
An array of `MediaObject` instances, max length 10

#### Search By ID
##### Request
When provided a path parameter of `id`, a query for a specific movie in the movie database should be executed.

##### Response
A single instance of a `MediaObject`

### `/shows`
#### Generic Search
##### Request
When provided a query parameter of `name`, a TV show search against the movie database should be executed.

##### Response
An array of `MediaObject` instances, max length 10

#### Search By ID
##### Request
When provided a path parameter of `id`, a query for a specific TV show in the movie database should be executed.

##### Response
A single instance of a `MediaObject`.

## Allowed Materials
### Required
* `Java 8+`
* `Spring`

### Discretionary
* Any public packages may be used to facilitate development

Feel free to contact MonetaGo with any questions, good luck!

## Evaluation

See the [rubric](RUBRIC.md) for metrics on how the code is evaluated.
## License

At CodeScreen, we strongly value the integrity and privacy of our assessments. As a result, this repository is under exclusive copyright, which means you **do not** have permission to share your solution to this test publicly (i.e., inside a public GitHub/GitLab repo, on Reddit, etc.). <br>

## Submitting your solution

Please push your changes to the `main branch` of this repository. You can push one or more commits. <br>

Once you are finished with the task, please click the `Submit Solution` link on <a href="https://app.codescreen.com/candidate/85efc2ed-1caa-4dc6-b468-dd8995f6e070" target="_blank">this screen</a>.