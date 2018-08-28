package com.comeon.gamelove.repository;

import com.comeon.gamelove.domain.PlayerLikesGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerLikesRepository extends CrudRepository<PlayerLikesGame, Integer> {

    Integer deleteByPlayer_IdAndGame_Id(int player_id, int game_id);
    boolean existsByPlayer_IdAndGame_Id(int player_id,int game_id);
    List<PlayerLikesGame> findByPlayer_Id(int player_id);


    @Query(nativeQuery = true,
            value = "SELECT " +
                    " g.id, count(*) as liked_size, g.name " +
                    "FROM " +
                    " PLAYER_LIKES_GAME plg," +
                    " Game g  " +
                    "WHERE " +
                    " plg.game_id = g.id " +
                    "GROUP BY plg.game_id " +
                    "ORDER BY liked_size DESC " +
                    "LIMIT :limit"
    )
    List<Object[]> getMostLikedGamesByLimit(@Param("limit") int limit);

}
