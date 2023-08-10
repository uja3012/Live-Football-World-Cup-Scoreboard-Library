package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreboardServiceTest {

    private MatchEntity matchEntity;

    @ParameterizedTest
    @CsvSource({"Mexico,Canada"})
    @DisplayName("Initialize match entity with default team score values")
    public void construct_match_entity_with_default_parameter_values(String homeTeam,String awayTeam){
        matchEntity = new MatchEntity(homeTeam,awayTeam);
        Assertions.assertEquals(0,matchEntity.getAwayTeamScore());
        Assertions.assertEquals(0,matchEntity.getHomeTeamScore());
    }

}
