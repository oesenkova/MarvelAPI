package com.oesenkova.marvelapi.repository;

import com.oesenkova.marvelapi.domain.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends CrudRepository<Character, Long> {
}
