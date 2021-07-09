package com.company.challengealkemy.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class MovieDto{
    private String image;
    private String title;
    private Date creationDate = new Date();
}
