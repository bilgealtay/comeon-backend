package com.comeon.gamelove.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameModel  {
    private Integer id;
    private Integer liked_size;
    private String name;
}
