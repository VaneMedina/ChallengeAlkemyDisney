package com.company.challengealkemy.Controllers;

import com.company.challengealkemy.Dto.MovieDtoSave;
import com.company.challengealkemy.Model.Movie;
import com.company.challengealkemy.Servicies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies-v1/")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public List<Object> paraUrlDeCharacters(@RequestParam Optional<String> name, @RequestParam Optional<Integer> genre, @RequestParam Optional<String> order){
        return movieService.paraUrlDeCharacters(name, genre, order);
    }

    @GetMapping(value = "/details")
    public List<Movie> getAllMoviesDetailsMovies(){
        return movieService.getAllMoviesDetails();
    }


    @PostMapping("/save")
    public void saveMovie(@RequestBody MovieDtoSave movie){
        movieService.saveMovie(movie);
    }


    @PutMapping("/edit/{id}")
    public void editCharacter(@PathVariable String id, @RequestBody MovieDtoSave movieDtoSave){
        movieService.editMovie(id, movieDtoSave);
    }


    // TODO: 6/7/2021 No me borra las peliculas. Error con algo la foreing key.
    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
    }
}




























