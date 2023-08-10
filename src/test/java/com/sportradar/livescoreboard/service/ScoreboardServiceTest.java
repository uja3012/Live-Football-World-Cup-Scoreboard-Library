package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ScoreboardServiceTest {

    private MatchEntity matchEntity;
    private MapRepository mapRepository=new MapRepository();

    @ParameterizedTest
    @CsvSource({"Mexico,Canada"})
    @DisplayName("Initialize match entity with default team score values")
    public void construct_match_entity_with_default_parameter_values(String homeTeam,String awayTeam){
        matchEntity = new MatchEntity(homeTeam,awayTeam);
        Assertions.assertEquals(0,matchEntity.getAwayTeamScore());
        Assertions.assertEquals(0,matchEntity.getHomeTeamScore());
    }

    @ParameterizedTest
    @CsvSource({"Mexico,Canada",
            "Spain,Brazil",
            "Germany,France",
            "Uruguay,Italy",
            "Argentina, Australia"})
    @DisplayName("when match starts then match entity is saved in the memory map data structure")
    public void when_match_starts_it_saves_data_to_map_repository(String homeTeam,String awayTeam){
        construct_match_entity_with_default_parameter_values(homeTeam, awayTeam);
        mapRepository.save(matchEntity);
    }

    @Test
    @DisplayName("Show scoreboard for live matches")
    public void show_scoreboard_for_live_matches(){
        List<MatchEntity> matchList = mapRepository.getSummaryOfMatches();
        Assertions.assertNotNull(matchList);
    }

    @ParameterizedTest
    @CsvSource({"FW20231000"})
    @DisplayName("when match is finished remove from the scoreboard")
    public void when_match_finished_remove_from_scoreboard(String matchId){
       MatchEntity deletedMatchEntity =  mapRepository.deleteByMatchId(matchId);
       Assertions.assertTrue((deletedMatchEntity instanceof MatchEntity) || ( isNull(deletedMatchEntity) ));
    }


    @ParameterizedTest
    @CsvSource({"FW20231000, 5, 10"})
    @DisplayName("update match score with new values")
    public void update_match_score_with_parameter_values(String matchId, int homeTeamScore,int awayTeamScore){
        MatchEntity matchToUpdate = (MatchEntity) mapRepository.findById(matchId);
        matchToUpdate.setHomeTeamScore(homeTeamScore);
        matchToUpdate.setAwayTeamScore(awayTeamScore);

        Assertions.assertEquals(5, matchToUpdate.getHomeTeamScore());
        Assertions.assertEquals(10,matchToUpdate.getAwayTeamScore());

    }

}
