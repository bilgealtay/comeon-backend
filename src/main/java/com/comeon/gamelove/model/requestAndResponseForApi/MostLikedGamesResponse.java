package com.comeon.gamelove.model.requestAndResponseForApi;

import com.comeon.gamelove.model.GameModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MostLikedGamesResponse extends GenericResponse {
    @ApiModelProperty(notes = "Games")
    private List<GameModel> lovedGames = new ArrayList<>();
}
