package com.comeon.gamelove.model.requestAndResponseForApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LikeGameRequest {

    @NotNull
    @ApiModelProperty(notes = "Game identifier", required = true)
    private Integer game_id;

    @NotNull
    @ApiModelProperty(notes = "Player identifier.", required = true)
    private Integer player_id;

}
