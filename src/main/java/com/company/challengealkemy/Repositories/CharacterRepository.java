package com.company.challengealkemy.Repositories;

import com.company.challengealkemy.Model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

    List<Character> findAllByNameContaining(String name);

    List<Character> findAllByAge(Integer age);




}
