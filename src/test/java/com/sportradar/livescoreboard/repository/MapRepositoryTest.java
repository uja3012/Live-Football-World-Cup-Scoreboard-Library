package com.sportradar.livescoreboard.repository;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MapRepositoryTest {

    public static final String MATCH_ID_1 = "1";
    public static final String MATCH_ID_2 = "2";
    public static final String MATCH_ID_3 = "3";
    private final MapRepository repository = new MapRepository();

    @BeforeEach
    void setup(){
        repository.saveOrUpdate(new MatchEntity(MATCH_ID_1, "Mexico", "Canada"));
        repository.saveOrUpdate(new MatchEntity(MATCH_ID_2, "Spain", "Brazil"));
        repository.saveOrUpdate(new MatchEntity(MATCH_ID_3, "Germany", "France"));
    }

    @Test
    void when_match_is_saved_it_must_be_found_later(){
        assertTrue(repository.findById(MATCH_ID_1).isPresent());
    }

    @Test
    void when_match_is_deleted_must_not_be_found_later(){
        repository.deleteById(MATCH_ID_1);
        assertTrue(repository.findById(MATCH_ID_1).isEmpty());
    }

    @Test
    void when_matches_exists_in_repository_the_summary_should_found(){
        assertEquals(3, repository.getSummaryOfMatches().size());
    }

    @ParameterizedTest
    @ValueSource(strings = { MATCH_ID_1, MATCH_ID_2, MATCH_ID_3})
    void when_match_already_exist_it_should_be_found(String matchId){
        assertTrue(repository.findById(matchId).isPresent());
    }
}
