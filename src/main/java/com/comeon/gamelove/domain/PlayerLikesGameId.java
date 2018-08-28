package com.comeon.gamelove.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class PlayerLikesGameId implements Serializable {

    @Column(name="player_id")
    private int player_id;

    @Column(name="game_id")
    private int game_id;

}
