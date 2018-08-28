package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.domain.Player;
import com.comeon.gamelove.domain.PlayerLikesGame;
import com.comeon.gamelove.model.requestAndResponseForApi.LikeGameRequest;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LikeGameRequestToPlayerLikesGameConverter implements Converter<LikeGameRequest, PlayerLikesGame> {

    @Synchronized
    @Nullable
    @Override
    public PlayerLikesGame convert(LikeGameRequest source) {

        if(source == null)
            return null;

        Game game = new Game();
        game.setId(source.getGame_id());

        Player player = new Player();
        player.setId(source.getPlayer_id());

        return new PlayerLikesGame(player, game);
    }

}
