package com.company.challengealkemy.Repositories;

import com.company.challengealkemy.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);

    //SELECT idProducto, cantidad FROM productosxpedido WHERE idPedido = (?);
    //@Query("SELECT id_genre FROM Genre WHERE id_genre = :idGenre")
    //List<Genre> findAllById_genre(Integer idGenre);
}
