package com.comeon.gamelove.service;

import com.comeon.gamelove.converter.LikeGameRequestToPlayerLikesGameConverter;
import com.comeon.gamelove.converter.PlayerLikesGameToLovedGamesByPlayerResponseConverter;
import com.comeon.gamelove.model.GameModel;
import com.comeon.gamelove.model.requestAndResponseForApi.*;
import com.comeon.gamelove.repository.PlayerLikesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
public class PlayerLikesGameServiceImpl implements PlayerLikesGameService {

    private static final Logger log = LoggerFactory.getLogger(PlayerLikesGameServiceImpl.class);

    @Autowired
    private PlayerLikesRepository playerLikesRepository;

    @Autowired
    private LikeGameRequestToPlayerLikesGameConverter loveGameRequestConverter;

    @Autowired
    private PlayerLikesGameToLovedGamesByPlayerResponseConverter playerLovesConverter;

    @Override
    public boolean isGameLikedBeforeByPlayer(int player_id, int game_id) {
        return playerLikesRepository.existsByPlayer_IdAndGame_Id(player_id, game_id);
    }

    @Override
    public MostLikedGamesResponse getMostLovedGamesByLimit(int limit) {
        MostLikedGamesResponse mostLikedGames = new MostLikedGamesResponse();

        List<Object[]> mostLikedGamesObj = playerLikesRepository.getMostLikedGamesByLimit(limit);

        mostLikedGamesObj.forEach( (Object[] obj) ->
                mostLikedGames.getLovedGames().add(
                        new GameModel(
                                (Integer) obj[0],                   //Game id
                                ((BigInteger) obj[1]).intValue(),   //Liked size
                                (String)obj[2]                      //Game name
                        )
                )
        );

        log.info("Get top {} games : {}",limit, mostLikedGames);
        return mostLikedGames;
    }

    // players can see their favorite games
    @Override
    public PlayerLikedGamesResponse getLovedGamesByPlayerId(int player_id) {

        // findByPlayer_Id() response is List<PlayerLikesGame>
        // List<PlayerLikesGame> converts to PlayerLikedGamesResponse
        return playerLovesConverter.convert(playerLikesRepository.findByPlayer_Id(player_id));
    }

    // player can like game,
    @Override
    public GenericResponse savePlayerLikes(LikeGameRequest request) {

        // insert player's like game
        playerLikesRepository.save(loveGameRequestConverter.convert(request));

        log.info("Player '{}', liked game '{}'",request.getPlayer_id(),request.getGame_id());

        return new GenericResponse();
    }

    // player can unlike game,
    @Transactional
    @Override
    public GenericResponse deletePlayerLikes(UnlikeGameRequest request) {

        // delete player liked. (Unlike)
        playerLikesRepository.deleteByPlayer_IdAndGame_Id(request.getPlayer_id(),request.getGame_id());

        log.info("Player '{}', unliked game '{}'",request.getPlayer_id(),request.getGame_id());

        return new GenericResponse();
    }


}