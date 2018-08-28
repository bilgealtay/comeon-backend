package com.comeon.gamelove.repository;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.domain.Player;
import com.comeon.gamelove.domain.PlayerLikesGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerLikesRepositoryTest {


    private Game game;
    private Game game2;
    private Player player;
    private Player player2;
    private PlayerLikesGame playerLikesGame;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  PlayerLikesRepository playerLikesRepository;


    @Before
    public void setUp() throws Exception {

        game = entityManager.persist(new Game("game1"));
        game2 = entityManager.persist(new Game("game2"));

        player = entityManager.persist(new Player("player1"));
        player2 = entityManager.persist(new Player("player2"));

        playerLikesGame = entityManager.persist(new PlayerLikesGame(player,game));

        entityManager.flush();
    }

    @Test
    public void deleteByPlayerIdAndGameId() {

        // when
        Integer deleted = playerLikesRepository.deleteByPlayer_IdAndGame_Id(player.getId(), game.getId());

        // then
        assertTrue(1==deleted);

    }

    @Test
    public void whenPlayerLikedGameBefore_thenReturn_True() {
        //when
        boolean isPlayerLiked = playerLikesRepository.existsByPlayer_IdAndGame_Id(player.getId(), game.getId());

        //then
        assertTrue(isPlayerLiked);
    }

    @Test
    public void whenPlayerNoteLikedGameBefore_thenReturn_False() {
        //when
        boolean isPlayerLiked = playerLikesRepository.existsByPlayer_IdAndGame_Id(player.getId(), game2.getId());

        //then
        assertFalse(isPlayerLiked);
    }

    @Test
    public void givePlayerId_whenGetPlayerLikedGamesBefore_thenReturn_PlayerLikedGamesList() {

        //when
        List<PlayerLikesGame> playerlikes = playerLikesRepository.findByPlayer_Id(player.getId());

        //then
        assertTrue(1==playerlikes.size());
    }

    @Test
    public void givePlayerId_whenPlayerNotLikedGamesBefore_thenReturn_EmptyPlayerLikedGamesList() {

        //when
        List<PlayerLikesGame> playerlikes = playerLikesRepository.findByPlayer_Id(player2.getId());

        //then
        assertTrue(playerlikes.isEmpty());
    }

    @Test
    public void givenLimit_whenGetMostLikedGames_thenReturn_List() {
        //when
        List<Object[]> mostLikes = playerLikesRepository.getMostLikedGamesByLimit(1);

        //then
        assertTrue(1==mostLikes.size());
    }
}