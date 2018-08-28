package com.comeon.gamelove.controller;


import com.comeon.gamelove.error.GameLikedAlreadyException;
import com.comeon.gamelove.error.GameNotFoundException;
import com.comeon.gamelove.error.GameNotLikeYetException;
import com.comeon.gamelove.error.PlayerNotFoundException;
import com.comeon.gamelove.model.requestAndResponseForApi.*;
import com.comeon.gamelove.service.GameService;
import com.comeon.gamelove.service.PlayerLikesGameService;
import com.comeon.gamelove.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/gameLove")
@Api(value="gameLove", description="Everythink For Love")
public class GameLoveController {

    @Autowired
    private PlayerLikesGameService gamerLoveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @ApiOperation(value = "Get most loved games by gamers with limit", response = MostLikedGamesResponse.class)
    @GetMapping(value = "/mostLovedGames/{limit}")
    public MostLikedGamesResponse getMostLovedGamesByLimit(@PathVariable(required = true) Integer limit){
        return gamerLoveService.getMostLovedGamesByLimit(limit);
    }

    @ApiOperation(value = "Get all games a player have loved by player identifier.", response = PlayerLikedGamesResponse.class)
    @GetMapping(value = "/playerLovedGames/{id}")
    public PlayerLikedGamesResponse getLovedGamesByPlayerId(@PathVariable(required = true) int id)  throws Exception {

        // check player
        isPlayerExist(id);

        return gamerLoveService.getLovedGamesByPlayerId(id);
    }

    @ApiOperation(value = "(Like) Save player love", response = GenericResponse.class)
    @PostMapping
    public GenericResponse savePlayerLove(@Valid @RequestBody(required = true) LikeGameRequest request) throws Exception{

        // check game and player
        isGameIdAndPlayerIdExist(request.getGame_id(), request.getPlayer_id());

        // When player likes game, insert data in "player_love" table.
        // First check table.
        // If data exists it's mean pleyer loved this game before, player can't love again (throw exeption).
        // If data doesn't exist, he can be like game.
        if(gamerLoveService.isGameLikedBeforeByPlayer(request.getPlayer_id(), request.getGame_id()))
            throw new GameLikedAlreadyException("The game already liked by player!");

        return gamerLoveService.savePlayerLikes(request);
    }

    @ApiOperation(value = "(UnLike) Delete player love", response = GenericResponse.class)
    @DeleteMapping
    public GenericResponse deletePlayerLove(@Valid @RequestBody(required = true) UnlikeGameRequest request) throws Exception{

        // check game and player
        isGameIdAndPlayerIdExist(request.getGame_id(), request.getPlayer_id());

        // Player must be liked the game before.
        // When player likes game, we should insert data in "player_love" table.
        // If data exists it's mean player loved this game before, we can delete.
        // If data doesn't exist, throw exception.
        if(!gamerLoveService.isGameLikedBeforeByPlayer(request.getPlayer_id(), request.getGame_id()))
            throw new GameNotLikeYetException("The game haven't like yet by player!");

        return gamerLoveService.deletePlayerLikes(request);
    }

    // purpose is client shouldn't be process with wrong parameters (game_id and player_id)
    // this method checks game and player,
    // if they or one of them not exist, method throws exeption
    private void isGameIdAndPlayerIdExist(int game_id, int player_id) throws Exception{

        //exeption for none exist player
        isPlayerExist(player_id);

        //exeption for none exist game
        isGameExist(game_id);

    }

    //exeption for none exist player
    private void isPlayerExist(int player_id) throws Exception{
        if(!playerService.isPlayerExist(player_id))
            throw new PlayerNotFoundException("Player doesn't exist! player_id :" + player_id);
    }

    //exception for none exist game
    private void isGameExist(int game_id) throws Exception{

        if(!gameService.isGameExist(game_id))
            throw new GameNotFoundException("Game doesn't exist! game_id :" + game_id );
    }


}
