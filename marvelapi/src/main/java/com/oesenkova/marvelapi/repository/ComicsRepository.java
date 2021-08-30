package com.oesenkova.marvelapi.repository;

import com.oesenkova.marvelapi.domain.Comics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicsRepository extends CrudRepository<Comics, Long> {
}
