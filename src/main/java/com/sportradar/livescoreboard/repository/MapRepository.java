package com.sportradar.livescoreboard.repository;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.logging.ScoreboardLogger;
import com.sportradar.livescoreboard.service.ScoreboardService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Licensed Material - Property of Sportradar
 * Building Library With Java
 * (c) Copyright @
 * Used In-memory solution to store & update match details using map data structure
 * Date : 10.08.2023
 * Version : 1.0 (Initial Version)
 * @author Ujwala Vanve
 */

public class MapRepository implements CrudRepository<MatchEntity, String> {

    private static final Map<String,MatchEntity> tempStorage = new HashMap<>();
    private static final ScoreboardLogger logger = new ScoreboardLogger(ScoreboardService.class);

    // Save match entity details to map data structure as key-matchId & value-MatchEntity
    @Override
    public MatchEntity saveOrUpdate(MatchEntity matchEntity) {
        tempStorage.put(matchEntity.getMatchId(), matchEntity);
        return matchEntity;
    }

    public List<MatchEntity> getSummaryOfMatches() {
        return new ArrayList<>(tempStorage.values());
    }

    @Override
    public Optional<MatchEntity> deleteById(String matchId) {
        logger.info("Match is removed with id as :"+ matchId );
        return Optional.ofNullable(tempStorage.remove(matchId));
    }

    @Override
    public Optional<MatchEntity> findById(String matchId) {
        return Optional.ofNullable(tempStorage.get(matchId));
    }

}
