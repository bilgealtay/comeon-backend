package com.comeon.gamelove.bootstrap;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.domain.Player;
import com.comeon.gamelove.domain.PlayerLikesGame;
import com.comeon.gamelove.repository.PlayerLikesRepository;
import com.comeon.gamelove.service.GameService;
import com.comeon.gamelove.service.PlayerService;
import com.comeon.gamelove.service.PlayerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


// create init data for gamers, game and player love
@Component
public class Bootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerLikesRepository playerLoveRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }


    private void initData(){

        log.info("****************");
        log.info("Dummy data");
        log.info("****************");

        //create players
        Player player1 = playerService.savePlayer(new Player("player1"));
        Player player2 = playerService.savePlayer(new Player("player2"));
        Player player3 = playerService.savePlayer(new Player("player3"));
        Player player4 = playerService.savePlayer(new Player("player4"));

        // create games
        Game game1 = gameService.saveGame(new Game("game1"));
        Game game2 = gameService.saveGame(new Game("game2"));
        Game game3 = gameService.saveGame(new Game("game3"));
        Game game4 = gameService.saveGame(new Game("game4"));
        Game game5 = gameService.saveGame(new Game("game5"));
        Game game6 = gameService.saveGame(new Game("game6"));
        Game game7 = gameService.saveGame(new Game("game7"));

        //create players like games

        //player 1 likes
        playerLoveRepository.save(new PlayerLikesGame(player1, game1));
        playerLoveRepository.save(new PlayerLikesGame(player1, game2));
        playerLoveRepository.save(new PlayerLikesGame(player1, game3));
        playerLoveRepository.save(new PlayerLikesGame(player1, game4));

        //player 2 likes
        playerLoveRepository.save(new PlayerLikesGame(player2, game3));
        playerLoveRepository.save(new PlayerLikesGame(player2, game4));
        playerLoveRepository.save(new PlayerLikesGame(player2, game5));

        //player 3 likes
        playerLoveRepository.save(new PlayerLikesGame(player3, game1));
        playerLoveRepository.save(new PlayerLikesGame(player3, game3));
        playerLoveRepository.save(new PlayerLikesGame(player3, game6));

        log.info("****************");
        log.info("Dummy data");
        log.info("****************");


    }
}
