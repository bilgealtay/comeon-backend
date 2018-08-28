package com.comeon.gamelove.repository;

import com.comeon.gamelove.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    boolean existsById(Integer id);
}
