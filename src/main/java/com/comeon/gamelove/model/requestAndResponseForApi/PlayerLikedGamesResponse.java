package com.comeon.gamelove.model.requestAndResponseForApi;

import com.comeon.gamelove.model.GameModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerLikedGamesResponse extends GenericResponse {
    private List<GameModel> lovedGames= new ArrayList<>();
}
