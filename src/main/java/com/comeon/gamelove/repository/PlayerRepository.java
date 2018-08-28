package com.comeon.gamelove.repository;

import com.comeon.gamelove.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    boolean existsById(Integer id);
}
