package com.company.challengealkemy.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movie;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    private Integer qualification; //(del 1 al 5).

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Column(nullable = false)
    @JsonIgnoreProperties(value="movies")
    private List<Character> characters = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY) @JoinTable(name ="movie_genre", joinColumns = @JoinColumn(name = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_genre"))
    @Column(nullable = false)
    @JsonIgnoreProperties(value="movies")
    private List<Genre> genres = new ArrayList<>();
}




















