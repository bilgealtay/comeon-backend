package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.PlayerLikesGame;
import com.comeon.gamelove.model.GameModel;
import com.comeon.gamelove.model.requestAndResponseForApi.PlayerLikedGamesResponse;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerLikesGameToLovedGamesByPlayerResponseConverter implements Converter<List<PlayerLikesGame>, PlayerLikedGamesResponse> {

    private final GameToGameModelConverter gameConverter;

    public PlayerLikesGameToLovedGamesByPlayerResponseConverter(GameToGameModelConverter gameConverter) {
        this.gameConverter = gameConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public PlayerLikedGamesResponse convert(List<PlayerLikesGame> source) {
        if (source == null)
            return null;

        PlayerLikedGamesResponse response = new PlayerLikedGamesResponse();


        List<GameModel> gameModels = new ArrayList<>();
        source.forEach((PlayerLikesGame playerLove) ->
                gameModels.add(gameConverter.convert(playerLove.getGame())));

        response.setLovedGames(gameModels);

        return response;
    }
}