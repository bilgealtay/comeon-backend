package com.comeon.gamelove.service;

import com.comeon.gamelove.domain.Game;

public interface GameService {
    boolean isGameExist(int game_id);
    Game saveGame(Game game);
}
