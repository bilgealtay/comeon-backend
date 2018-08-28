package com.comeon.gamelove.model.requestAndResponseForApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UnlikeGameRequest {

    @ApiModelProperty(notes = "Game identifier.", required = true)
    @NotNull
    private Integer game_id;

    @ApiModelProperty(notes = "Player identifier.", required = true)
    @NotNull
    private Integer player_id;

}
