package com.comeon.gamelove.service;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.repository.GameRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameServicesImpl implements GameService {

    private static final Logger log = LoggerFactory.getLogger(GameServicesImpl.class);

    @Autowired
    private GameRepository repository;

    @Override
    public boolean isGameExist(int game_id) {
        return repository.existsById(game_id);
    }

    /**
     * Save Game
     * @param game
     * @return game
     */
    @Override
    public Game saveGame(Game game) {
        Game newGame = repository.save(game);
        log.info("New game created : {}", newGame);
        return newGame;
    }

}
