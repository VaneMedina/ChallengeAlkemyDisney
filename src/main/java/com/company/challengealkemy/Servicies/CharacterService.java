package com.company.challengealkemy.Servicies;

import com.company.challengealkemy.Dto.CharacterDtoSave;
import com.company.challengealkemy.Model.Character;
import com.company.challengealkemy.Model.Movie;
import com.company.challengealkemy.Repositories.CharacterRepository;
import com.company.challengealkemy.Dto.CharacterDto;
import com.company.challengealkemy.Dto.CharacterDtoItem6;
import com.company.challengealkemy.Repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    private ModelMapper modelMapper;
    private MovieRepository movieRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }

    public Character findCharacterById(String id) {
        try {
            return characterRepository.findById(Integer.parseInt(id)).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Character> getAllCharactersDetails() {
        return characterRepository.findAll();
    }

    /**
     * For save characters, all fields MUST be filled except movies.
     **/
    public void saveCharacter(CharacterDtoSave characterDto) {
        Character character = new Character();
        character.setImage(characterDto.getImage());
        character.setName(characterDto.getName());
        character.setAge(characterDto.getAge());
        character.setWeight(characterDto.getWeight());
        character.setStory(characterDto.getStory());
        characterRepository.save(character);
    }


    /**
     * For edit characters, all fields MUST be filled except movies.
     **/
    public void editCharacter(String id, CharacterDtoSave character) {
        Character characterEntity = findCharacterById(id);
        if (characterEntity == null)
            throw new NoSuchElementException("Not found character.");
        else {
            if (!character.getName().isEmpty() && character.getName() != characterEntity.getName())
                characterEntity.setName(character.getName());

            if (!character.getImage().isEmpty() && character.getImage() != characterEntity.getImage())
                characterEntity.setImage(character.getImage());

            if (character.getAge() != null && character.getAge() != characterEntity.getAge())
                characterEntity.setAge(character.getAge());

            if (!character.getStory().isEmpty() && character.getStory() != characterEntity.getStory())
                characterEntity.setStory(character.getStory());

            if (character.getWeight() != null && character.getWeight() != characterEntity.getWeight())
                characterEntity.setWeight(character.getWeight());

        }
        characterRepository.save(characterEntity);
    }

    public void deleteCharacter(String id) {
        if (findCharacterById(id) != null)
            characterRepository.deleteById(Integer.parseInt(id));
        else {
            throw new NullPointerException("No se pudo borrar.");
        }
    }

    public List<Character> findAllByNameContaining(String name) {
        return characterRepository.findAllByNameContaining(name);
    }

    public List<Character> findAllByAge(Integer age) {
        return characterRepository.findAllByAge(age);
    }


    /**
     * Show all characters but only with the fields image and name.
     **/
    public List<CharacterDto> findAll() {
        List<Character> characters = getAllCharactersDetails();
        List<CharacterDto> characterDtos = new ArrayList<>();
        characters.forEach(character -> characterDtos.add(modelMapper.map(character, CharacterDto.class)));
        return characterDtos;
    }


    public List<CharacterDtoItem6> characterDtosItem6ListByName(String name) {
        List<Character> charactersName = findAllByNameContaining(name);
        List<CharacterDtoItem6> characterDtos = new ArrayList<>();
        charactersName.forEach(character -> characterDtos.add(modelMapper.map(character, CharacterDtoItem6.class)));
        return characterDtos;
    }

    public List<CharacterDtoItem6> characterDtosItem6ListByAge(Integer age) {
        List<Character> charactersAge = findAllByAge(age);
        List<CharacterDtoItem6> characterDtos = new ArrayList<>();
        charactersAge.forEach(character -> characterDtos.add(modelMapper.map(character, CharacterDtoItem6.class)));
        return characterDtos;
    }

    public List<Character> characterDtosItem6ListByMovie(Integer idMovie){
        Movie movie = movieRepository.findById(idMovie).get();
        List<CharacterDtoItem6> characters2 = new ArrayList<>();
        List<Character> characters = movie.getCharacters();
        characters.forEach(character -> characters2.add(modelMapper.map(character, CharacterDtoItem6.class)));
        return characters;
    }


    public List<Object> paraUrlDeCharacters(Optional<String> name, Optional<Integer> age, Optional<Integer> movies){
        if (name.isPresent())
            return Collections.singletonList(this.characterDtosItem6ListByName(name.get()));
        else if(age.isPresent())
            return Collections.singletonList(this.characterDtosItem6ListByAge(age.get()));
        else if(movies.isPresent())
            return Collections.singletonList(this.characterDtosItem6ListByMovie(movies.get()));
        else
            return Collections.singletonList(findAll());
    }
}




















