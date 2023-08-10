package com.sportradar.livescoreboard.repository;

import com.sportradar.livescoreboard.entity.MatchEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Licensed Material - Property of Sportradar
 * Building Library With Java
 * (c) Copyright @
 * Used In-memory solution to store & update match details using map data structure
 * Date : 10.08.2023
 * Version : 1.0 (Initial Version)
 * @author Ujwala Vanve
 */

public class MapRepository {

    private static Map<String,MatchEntity> map = new HashMap<String,MatchEntity>();

    // Save match entity details to map data structure as key-matchId & value-MatchEntity
    public void save(MatchEntity matchEntity) {
        map.put(matchEntity.getMatchId(), matchEntity);
    }

    public List getSummaryOfMatches() {
        return new ArrayList<>(map.values());
    }

    public MatchEntity deleteByMatchId(String matchId) {
        return map.remove(matchId);
    }

    public MatchEntity findById(String matchId) {
        return map.get(matchId);
    }
}
