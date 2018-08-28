package com.comeon.gamelove.controller;



import com.comeon.gamelove.model.GameModel;
import com.comeon.gamelove.model.requestAndResponseForApi.*;
import com.comeon.gamelove.service.GameService;
import com.comeon.gamelove.service.PlayerLikesGameService;
import com.comeon.gamelove.service.PlayerService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GameLoveController.class)
public class GameLoveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public PlayerLikesGameService service;

    @MockBean
    public GameService gameService;

    @MockBean
    public PlayerService playerService;

    @Test
    public void givenExistPlayerId_whenGetLovedGamesByPlayer_ThenReturn_PlayerLikedGamesResponse() throws Exception {

        PlayerLikedGamesResponse response = new PlayerLikedGamesResponse();
        response.getLovedGames().add(new GameModel());

        given(service.getLovedGamesByPlayerId(1))
                .willReturn(response);

        given(playerService.isPlayerExist(1))
                .willReturn(true);

        mockMvc.perform(get("/gameLove/playerLovedGames/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("success", is(response.isSuccess())))
                .andExpect(jsonPath("lovedGames", hasSize(1)));

    }

    @Test
    public void givenNoneExistPlayerId_whenGetLovedGamesByPlayer_ThenReturn_Exception() throws Exception {
        mockMvc.perform(get("/gameLove/playerLovedGames/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenStringPlayerId_whenGetLovedGamesByPlayer_ThenReturn_Exception() throws Exception {
        mockMvc.perform(get("/gameLove/playerLovedGames/{id}","str")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenLimit_whenGetMostLovedGames_ThenReturn_MostLikedGamesResponse() throws Exception {

        MostLikedGamesResponse response = new MostLikedGamesResponse();
        response.getLovedGames().add(new GameModel());
        response.getLovedGames().add(new GameModel());
        given(service.getMostLovedGamesByLimit(2)).willReturn(response);

        mockMvc.perform(get("/gameLove/mostLovedGames/{limit}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("lovedGames", hasSize(2)));
    }

    @Test
    public void givenStringLimit_whenGetMostLovedGames_ThenReturn_Exception() throws Exception {
        mockMvc.perform(get("/gameLove/mostLovedGames/{id}", "str")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenExistPlayerAndGame_whenLikeGame_ThenReturn_GenericResponse() throws Exception {

        GenericResponse response = new GenericResponse();

        given(service.savePlayerLikes(new LikeGameRequest()))
                .willReturn(response);

        given(playerService.isPlayerExist(1))
                .willReturn(true);

        given(gameService.isGameExist(1))
                .willReturn(true);

        mockMvc.perform(post("/gameLove")
                .content("{\"game_id\":1, \"player_id\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoneExistPlayerAndGame_whenLikeGame_ThenReturn_Exception() throws Exception {

        mockMvc.perform(post("/gameLove")
                .content("{\"game_id\":1, \"player_id\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenExistPlayerAndGame_and_gameLikedBefore_whenUnlikePlayerLove_ThenReturn_GenericResponse() throws Exception {
        GenericResponse response = new GenericResponse();

        given(service.deletePlayerLikes(new UnlikeGameRequest()))
                .willReturn(response);

        given(playerService.isPlayerExist(1))
                .willReturn(true);

        given(gameService.isGameExist(1))
                .willReturn(true);

        given(service.isGameLikedBeforeByPlayer(1,1))
                .willReturn(true);

        mockMvc.perform(delete("/gameLove")
                .content("{\"game_id\":1, \"player_id\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenExistPlayerAndGame_and_gameNotLikedBefore_whenUnlikePlayerLove_ThenReturn_Exception() throws Exception {
        GenericResponse response = new GenericResponse();

        given(service.deletePlayerLikes(new UnlikeGameRequest()))
                .willReturn(response);

        given(playerService.isPlayerExist(1))
                .willReturn(true);

        given(gameService.isGameExist(1))
                .willReturn(true);

        given(service.isGameLikedBeforeByPlayer(1,1))
                .willReturn(false);

        mockMvc.perform(delete("/gameLove")
                .content("{\"game_id\":1, \"player_id\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenNoneExistPlayerAndGame_whenUnlikePlayerLove_ThenReturn_Exception() throws Exception {

        given(playerService.isPlayerExist(1))
                .willReturn(true);

        given(gameService.isGameExist(1))
                .willReturn(true);

        mockMvc.perform(delete("/gameLove")
                .content("{\"game_id\":600, \"player_id\":700}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}