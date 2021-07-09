package com.company.challengealkemy.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class CharacterDtoSave {
    private String image;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
}
