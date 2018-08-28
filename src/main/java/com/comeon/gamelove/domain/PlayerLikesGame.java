package com.comeon.gamelove.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name="player_likes_game")
public class PlayerLikesGame extends AbstractTimestampEntity implements Serializable {

    @EmbeddedId
    private PlayerLikesGameId id;

    @ManyToOne
    @MapsId("game_id")
    private Game game;

    @ManyToOne
    @MapsId("player_id")
    private Player player;


    public PlayerLikesGame(Player player, Game game) {
        this.player = player;
        this.game = game;
        this.id = new PlayerLikesGameId(player.getId(), game.getId());
    }

}
