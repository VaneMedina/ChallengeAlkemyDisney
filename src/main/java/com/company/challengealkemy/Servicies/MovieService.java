package com.company.challengealkemy.Servicies;

import com.company.challengealkemy.Dto.MovieDto;
import com.company.challengealkemy.Dto.MovieDtoItem10;
import com.company.challengealkemy.Dto.MovieDtoItem10Dos;
import com.company.challengealkemy.Dto.MovieDtoSave;
import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Model.Genre;
import com.company.challengealkemy.Model.Movie;
import com.company.challengealkemy.Repositories.CharacterRepository;
import com.company.challengealkemy.Repositories.GenreRepository;
import com.company.challengealkemy.Repositories.MovieRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private final CharacterRepository characterRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository , ModelMapper modelMapper, CharacterRepository characterRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.characterRepository = characterRepository;
        this.genreRepository = genreRepository;
    }


    public List<Movie> getAllMoviesDetails() {
        return movieRepository.findAll();
    }



    public Movie findMovieById(String id) {
        try {
            return movieRepository.findById(Integer.parseInt(id)).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void deleteMovie(String id){
        if (findMovieById(id) != null)
            movieRepository.deleteById(Integer.parseInt(id));
        else {
            throw new NullPointerException("No se pudo borrar.");
        }
    }

    /**
     * Show all movies but only with the fields image, title and creation date.
    **/
    public List<MovieDto> findAll() {
        List<Movie> movies = getAllMoviesDetails();
        List<MovieDto> moviesDtos = new ArrayList<>();
        movies.forEach(movie -> moviesDtos.add(modelMapper.map(movie, MovieDto.class)));
        return moviesDtos;
    }


    /**
     * For save movies, all fields MUST be filled.
    **/
    public void saveMovie(MovieDtoSave movieDtoSave) {
        Movie movie = new Movie();
        movie.setImage(movieDtoSave.getImage());
        movie.setTitle(movieDtoSave.getTitle());
        saveMovieWithCharacters(movieDtoSave,movie);
        saveMovieWithGenres(movieDtoSave,movie);
        movieRepository.save(movie);
    }


    public void saveMovieWithCharacters(MovieDtoSave movieDtoSave, Movie movie){
        for (Integer idCharacter : movieDtoSave.getCharacters()) {
            Character character = characterRepository.findById(idCharacter).orElse(null);
            if (character == null)
                throw new NullPointerException("The character doesn't exists.");
            else
                character.getMovies().add(movie);
                movie.getCharacters().add(character);
        }
    }

    public void saveMovieWithGenres(MovieDtoSave movieDtoSave, Movie movie){
        for (String genreName : movieDtoSave.getGenres()){
            Genre name = genreRepository.findByName(genreName);
            if (name == null)
                throw new NullPointerException("The genre doesn't exists.");
            else {
                name.getMovies().add(movie);
                movie.getGenres().add(name);
            }
        }
    }


    /**
     * For edit movies, the fields image and title must be filled.
     **/
    public void editMovie(String id, MovieDtoSave movie) {
        Movie movieEntity = findMovieById(id);
        if (movieEntity == null)
            throw new NoSuchElementException("Not found movie.");
        else
            if (!movie.getImage().isEmpty() && movie.getImage() != movieEntity.getImage()) movieEntity.setImage(movie.getImage());
            if (!movie.getTitle().isEmpty() && movie.getTitle() != movieEntity.getTitle()) movieEntity.setTitle(movie.getTitle());
        movieRepository.save(movieEntity);
    }

    private List<Movie> findAllByTitleContaining(String name) {
        return movieRepository.findAllByTitleContaining(name);
    }


    public List<MovieDtoItem10> movieDtoItem10sByTitle(String title) {
        List<Movie> moviesName = findAllByTitleContaining(title);
        List<MovieDtoItem10> moviesDtos = new ArrayList<>();
        moviesName.forEach(movie -> moviesDtos.add(modelMapper.map(movie, MovieDtoItem10.class)));
        return moviesDtos;
    }


    public List<MovieDtoItem10Dos> movieDtoItem10sByIdGenre(Integer idGenre){
        Genre genreFound = genreRepository.findById(idGenre).get();
        List<Movie> moviesFound = genreFound.getMovies();
        List<MovieDtoItem10Dos> movies = new ArrayList<>();
        moviesFound.forEach(movie -> movies.add(modelMapper.map(movie, MovieDtoItem10Dos.class)));
        return movies;
    }

    public List<MovieDtoItem10> movieDtoItem10sByDate(String order){
        List<Movie> moviesEntity = movieRepository.findAll();
        List<MovieDtoItem10> moviesDto = new ArrayList<>();
        moviesEntity.forEach(movie -> moviesDto.add(modelMapper.map(movie, MovieDtoItem10.class)));
        MovieDtoItem10 movieDtoItem10 = new MovieDtoItem10();
        if (order == "ASC") {
            moviesDto.sort((Comparator<? super MovieDtoItem10>) movieDtoItem10.getCreationDate());
        }
        // TODO: 7/7/2021 Realizar el ordenamiento inverso.
        else if (order == "DES") {
            Collections.reverse(moviesDto);
        }
        return moviesDto;
    }


    public List<Object> paraUrlDeCharacters(Optional<String> name, Optional<Integer> idGenre, Optional<String> order){
        if (name.isPresent())
            return Collections.singletonList(this.movieDtoItem10sByTitle(name.get()));
        else if(idGenre.isPresent())
            return Collections.singletonList(this.movieDtoItem10sByIdGenre(idGenre.get()));
        else if(order.isPresent())
            return Collections.singletonList(this.movieDtoItem10sByDate(order.get()));
        else
            return Collections.singletonList(findAll());
    }
}
























