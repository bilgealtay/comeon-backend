package com.comeon.gamelove.converter;

import com.comeon.gamelove.domain.Game;
import com.comeon.gamelove.model.GameModel;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class GameToGameModelConverter implements Converter<Game, GameModel> {


    @Synchronized
    @Nullable
    @Override
    public GameModel convert(Game source) {
        if(source==null)
            return null;
        GameModel gm = new GameModel();
        gm.setId(source.getId());
        gm.setName(source.getName());
        return gm;
    }
}
