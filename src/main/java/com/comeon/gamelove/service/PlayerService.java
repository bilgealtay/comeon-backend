package com.comeon.gamelove.service;

import com.comeon.gamelove.domain.Player;

public interface PlayerService {
    boolean isPlayerExist(int player_id);
    Player savePlayer(Player player);
}
