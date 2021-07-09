package com.company.challengealkemy.Controllers;


import com.company.challengealkemy.Dto.CharacterDtoSave;
import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Servicies.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return characterService.forCharactersURL(name, age, movies);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCharacter(@RequestBody CharacterDtoSave characterDto){
        try{
            characterService.saveCharacter(characterDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("The character was created.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The character wasn't created.");
        }
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editCharacter(@PathVariable String id, @RequestBody CharacterDtoSave character){
        try{
            characterService.editCharacter(id, character);
            return ResponseEntity.status(HttpStatus.CREATED).body("The character was edit.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The character wasn't edit.");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable String id){
        try{
            characterService.deleteCharacter(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The character was deleted.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The character wasn't deleted.");
        }
    }
}























