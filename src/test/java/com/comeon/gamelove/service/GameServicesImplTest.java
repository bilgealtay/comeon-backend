package com.comeon.gamelove.service;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GameServicesImplTest {

    @TestConfiguration
    static class GameServicesImplTestContextConfiguration {

        @Bean
        public GameService gameService() {
            return new GameServicesImpl();
        }
    }

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        Game game = new Game("game1");

        Mockito.when(gameRepository.save(game))
                .thenReturn(game);
    }

    @Test
    public void saveGame() {
        String name = "game1";
        Game game = gameService.saveGame(new Game("game1"));
        assertEquals(name, game.getName());
    }
}