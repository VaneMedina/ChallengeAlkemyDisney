package com.company.challengealkemy.Repositories;

import com.company.challengealkemy.Dto.MovieDto;
import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByTitleContaining(String name);

    List<Movie> findAllByOrderByCreationDateDesc();
    List<Movie> findAllByOrderByCreationDateAsc();
}
