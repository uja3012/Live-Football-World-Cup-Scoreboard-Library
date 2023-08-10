package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

import static java.util.Objects.isNull;

public class ScoreboardServiceTest {

    private MatchEntity matchEntity;
    private final MapRepository mapRepository= new MapRepository();
    private ScoreboardService scoreboardService = new ScoreboardService(mapRepository);


    @ParameterizedTest
    @CsvSource({"Mexico,Canada"})
    @DisplayName("Initialize match entity with default team score values")
    public void construct_match_entity_with_default_parameter_values(String homeTeam,String awayTeam){
        matchEntity = scoreboardService.startMatch(homeTeam,awayTeam);
        Assertions.assertEquals(0,matchEntity.getAwayTeamScore());
        Assertions.assertEquals(0,matchEntity.getHomeTeamScore());
        Assertions.assertNotNull(matchEntity.getMatchId());
    }

    @ParameterizedTest
    @CsvSource({"Mexico,Canada",
                "Spain,Brazil",
                "Germany,France",
                "Uruguay,Italy",
                "Argentina, Australia"})
    @DisplayName("when match entity id is assigned by the user & saved in the map data structure")
    public void save_match_entity_with_user_generated_entityId(String homeTeam,String awayTeam){
        matchEntity = scoreboardService.startMatch(homeTeam,awayTeam);
        mapRepository.save(matchEntity);
    }

    @ParameterizedTest
    @CsvSource({"Mexico,Mexico",
                "Mexico,mexico",
                "0,1"})
    @DisplayName("when duplicate or null values passed to start match")
    public void verification_of_duplicate_or_null_constructor_values(String homeTeam,String awayTeam){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matchEntity = scoreboardService.startMatch(homeTeam, awayTeam);
        });
    }

    @ParameterizedTest
    @CsvSource({",Brazil",
            "Germany,"})
    @DisplayName("when illegal or null values passed to start match")
    public void verification_of_illegal_or_null_constructor_values(String homeTeam,String awayTeam){
        Assertions.assertThrows(NullPointerException.class, () -> {
            scoreboardService.startMatch(homeTeam, awayTeam);
        });
    }

    @Test
    @DisplayName("Show scoreboard for live matches")
    public void show_scoreboard_for_live_matches(){
        setUp();
        setUpForUpdate();
        List<MatchEntity> matchList = scoreboardService.getSummaryOfMatches();

        // verify sequence of the result
        for (MatchEntity m :matchList ) {
            System.out.println(m);
        }
        Assertions.assertNotNull(matchList);
    }

    @ParameterizedTest
    @CsvSource({"FW20231000",
            "FW20231005"})
    @DisplayName("when match is finished remove from the scoreboard")
    public void when_match_finished_remove_from_scoreboard(String matchId){
        setUp();
        MatchEntity deletedMatchEntity =  scoreboardService.finishMatch(matchId);
        Assertions.assertTrue((deletedMatchEntity instanceof MatchEntity) || ( isNull(deletedMatchEntity) ));
        Assertions.assertThrows(NullPointerException.class, () -> {
            scoreboardService.finishMatch(null);
       });
    }

    @Test
    @DisplayName("when null value is passed to finish match")
    public void when_failed_match_finished_remove_from_scoreboard(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            scoreboardService.finishMatch(null);
        });
    }

    @ParameterizedTest
    @CsvSource({"FW20231001, 5, 10"})
    @DisplayName("update match score with new values success")
    public void when_success_update_match_score_with_parameter_values(String matchId, int homeTeamScore,int awayTeamScore){
        setUp();
        MatchEntity updatedMatchEntity = scoreboardService.updateMatchScore(matchId, homeTeamScore, awayTeamScore);
        Assertions.assertNotNull(updatedMatchEntity);
        Assertions.assertEquals(5, updatedMatchEntity.getHomeTeamScore());
        Assertions.assertEquals(10,updatedMatchEntity.getAwayTeamScore());
    }

    @ParameterizedTest
    @CsvSource({"FW20231007, 5, 10"})
    @DisplayName("update match score with new values failure")
    public void when_failed_update_match_score_with_parameter_values(String matchId, int homeTeamScore,int awayTeamScore){
        Assertions.assertThrows(NullPointerException.class, () -> {
            scoreboardService.updateMatchScore(matchId, homeTeamScore, awayTeamScore);
        });
    }

    private void setUp() {
        mapRepository.save( new MatchEntity("FW20231001","Mexico","Canada") );
        mapRepository.save( new MatchEntity("FW20231002","Spain","Brazil") );
        mapRepository.save( new MatchEntity("FW20231003","Germany","France") );
        mapRepository.save( new MatchEntity("FW20231004","Uruguay","Italy") );
        mapRepository.save( new MatchEntity("FW20231005","Argentina","Australia") );
    }

    private void setUpForUpdate(){
        scoreboardService.updateMatchScore("FW20231001", 0, 5);
        scoreboardService.updateMatchScore("FW20231002", 10, 2);
        scoreboardService.updateMatchScore("FW20231003", 2, 2);
        scoreboardService.updateMatchScore("FW20231004", 6, 6);
        scoreboardService.updateMatchScore("FW20231005", 3, 1);
    }

}
