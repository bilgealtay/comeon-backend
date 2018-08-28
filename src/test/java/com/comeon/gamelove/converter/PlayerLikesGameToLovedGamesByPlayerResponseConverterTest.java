package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.domain.Player;
import com.comeon.gamelove.domain.PlayerLikesGame;
import com.comeon.gamelove.model.requestAndResponseForApi.PlayerLikedGamesResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerLikesGameToLovedGamesByPlayerResponseConverterTest {

    private static final int GAME_ID = 1;
    private static final int PLAYER_ID = 1;
    private static final String GAME_NAME = "game1";
    private static final String PLAYER_NAME = "player1";
    PlayerLikesGameToLovedGamesByPlayerResponseConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new PlayerLikesGameToLovedGamesByPlayerResponseConverter(
                new GameToGameModelConverter());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new ArrayList<PlayerLikesGame>()));
    }

    @Test
    public void convert() {
        //given
        List<PlayerLikesGame> plgs = new ArrayList<>();

        PlayerLikesGame plg = new PlayerLikesGame();

        Game game = new Game();
        game.setId(GAME_ID);
        game.setName(GAME_NAME);
        plg.setGame(game);

        Player player = new Player();
        player.setUsername(PLAYER_NAME);
        player.setId(PLAYER_ID);
        plg.setPlayer(player);

        plgs.add(plg);


        System.out.println("plg : "+plg);

        //when
        PlayerLikedGamesResponse playerLikedGamesResponse = converter.convert(plgs);

        //then
        assertEquals(1, playerLikedGamesResponse.getLovedGames().size());
    }
}