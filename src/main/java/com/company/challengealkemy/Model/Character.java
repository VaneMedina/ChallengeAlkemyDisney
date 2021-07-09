package com.company.challengealkemy.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "characters")
public class Character{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_character;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private Integer weight;
    @Column(nullable = false)
    private String story;
    @ManyToMany(cascade= CascadeType.PERSIST, fetch = FetchType.LAZY) @JoinTable(name ="character_movie", joinColumns = @JoinColumn(name = "id_character"), inverseJoinColumns = @JoinColumn(name = "id_movie"))
    @JsonIgnoreProperties(value="characters")
    private List<Movie> movies = new ArrayList<>();
}




















