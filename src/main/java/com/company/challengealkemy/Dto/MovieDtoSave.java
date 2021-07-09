package com.company.challengealkemy.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class MovieDtoSave {
    private String image;
    private String title;
    private Date creationDate = new Date();
    private List<Integer> characters = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
}
