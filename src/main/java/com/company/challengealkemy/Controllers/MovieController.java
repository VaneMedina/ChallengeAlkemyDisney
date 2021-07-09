package com.company.challengealkemy.Controllers;

import com.company.challengealkemy.Dto.MovieDtoSave;
import com.company.challengealkemy.Model.Movie;
import com.company.challengealkemy.Servicies.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveMovie(@RequestBody MovieDtoSave movie){
        try{
            movieService.saveMovie(movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("The movie was created.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The movie wasn't created.");
        }
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editCharacter(@PathVariable String id, @RequestBody MovieDtoSave movieDtoSave){
        try{
            movieService.editMovie(id, movieDtoSave);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The movie was edited.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The movie wasn't edited.");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        try{
            movieService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The movie was deleted.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The movie wasn't deleted.");
        }

    }
}




























