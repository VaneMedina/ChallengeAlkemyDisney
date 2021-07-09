package com.company.challengealkemy.Dto;

import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Model.Genre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class MovieDtoItem10 {
    private String title;
    private String image;
    private Date creationDate;
    @JsonIgnoreProperties(value = "movies")
    private List<Genre> genres;
}
