package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.model.GameModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameToGameModelConverterTest {

    private static final Integer GAME_ID = 1;
    private static final String GAME_NAME = "test1";

    GameToGameModelConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new GameToGameModelConverter();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Game()));
    }

    @Test
    public void convert() {
        //given
        Game game = new Game();
        game.setId(GAME_ID);
        game.setName(GAME_NAME);

        //when
        GameModel gameModel = converter.convert(game);

        //then
        assertEquals(GAME_ID, gameModel.getId());
        assertEquals(GAME_NAME, gameModel.getName());
    }
}