package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.PlayerLikesGame;
import com.comeon.gamelove.model.requestAndResponseForApi.LikeGameRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LikeGameRequestToPlayerLikesGameConverterTest {

    private static final Integer GAME_ID = 1;
    private static final Integer PLAYER_ID = 2;

    LikeGameRequestToPlayerLikesGameConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new LikeGameRequestToPlayerLikesGameConverter();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new LikeGameRequest()));
    }

    @Test
    public void convert() {

        //given
        LikeGameRequest likeGame = new LikeGameRequest();
        likeGame.setGame_id(GAME_ID);
        likeGame.setPlayer_id(PLAYER_ID);

        //when
        PlayerLikesGame plk = converter.convert(likeGame);

        //then
        assertEquals(GAME_ID, plk.getGame().getId());
        assertEquals(PLAYER_ID, plk.getPlayer().getId());

    }
}