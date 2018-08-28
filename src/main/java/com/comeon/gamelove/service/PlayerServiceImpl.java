package com.comeon.gamelove.service;

import com.comeon.gamelove.domain.Player;
import com.comeon.gamelove.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger log = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public boolean isPlayerExist(int player_id) {
        return playerRepository.existsById(player_id);
    }

    /**
     * Save Player
     * @param player
     * @return player
     */
    @Override
    public Player savePlayer(Player player) {
        Player newPlayer = playerRepository.save(player);
        log.info("New player created : {} ", newPlayer);
        return newPlayer;
    }
}
