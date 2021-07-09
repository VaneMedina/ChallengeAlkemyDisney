package com.company.challengealkemy.Controllers;


import com.company.challengealkemy.Dto.CharacterDtoSave;
import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Servicies.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters-v1/")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }


    @GetMapping("/details")
    public List<Character> getAllCharactersDetails(){
        return characterService.getAllCharactersDetails();
    }


    @GetMapping("/characters")
    public List<Object> paraUrlDeCharacters(@RequestParam Optional<String> name, @RequestParam Optional<Integer> age, @RequestParam Optional<Integer> movies){
        return characterService.paraUrlDeCharacters(name, age, movies);
    }


    // TODO: 5/7/2021 Agregar personajes a la  pelicula en vez de peliculas a los personajes. 
    @PostMapping("/save")
    public void saveCharacter(@RequestBody CharacterDtoSave characterDto){
        characterService.saveCharacter(characterDto);
    }


    @PutMapping("/edit/{id}")
    public void editCharacter(@PathVariable String id, @RequestBody CharacterDtoSave character){
        characterService.editCharacter(id, character);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable String id){
        characterService.deleteCharacter(id);
    }
}























