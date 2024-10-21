package com.movie_project.movie_Base.Controllers;


import com.movie_project.movie_Base.DTOs.MovieDTO;
import com.movie_project.movie_Base.Entity.Movie;
import com.movie_project.movie_Base.Services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private final ModelMapper modelMapper = new ModelMapper();




    @GetMapping
    public List<Movie> getAllMovie() {
        log.info("Get all movie");
         return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies/{title}")
    public ResponseEntity<MovieDTO> getMovieByTitle(@PathVariable String title) {
        Movie movie = movieService.getMovieByName(title);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(modelMapper.map(movie, MovieDTO.class));
    }


    @PostMapping("/add")
    public MovieDTO addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id,@RequestBody MovieDTO movieDTO) {
        Movie movieToUpdate = movieService.getMovieById(id);
        if (movieToUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieService.updateMovie(movieDTO), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
         movieService.deleteMovie(id);
    }
}
