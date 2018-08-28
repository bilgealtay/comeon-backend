package com.comeon.gamelove.service;

import com.comeon.gamelove.model.requestAndResponseForApi.*;

public interface PlayerLikesGameService {

    boolean isGameLikedBeforeByPlayer(int player_id, int game_id);
    MostLikedGamesResponse getMostLovedGamesByLimit(int limit);
    PlayerLikedGamesResponse getLovedGamesByPlayerId(int player_id);
    GenericResponse savePlayerLikes(LikeGameRequest request);
    GenericResponse deletePlayerLikes(UnlikeGameRequest request);

}
