package com.sportradar.livescoreboard.repository;

import com.sportradar.livescoreboard.entity.MatchEntity;

import java.io.Serializable;
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

public class MapRepository implements CrudRepository<MatchEntity, String> {

    private static Map<String,MatchEntity> map = new HashMap<String,MatchEntity>();

    // Save match entity details to map data structure as key-matchId & value-MatchEntity
    @Override
    public MatchEntity save(MatchEntity matchEntity) {
        map.put(matchEntity.getMatchId(), matchEntity);
        return matchEntity;
    }

    public List<MatchEntity> getSummaryOfMatches() {
        return new ArrayList<>(map.values());
    }

    @Override
    public MatchEntity deleteById(String matchId) {
        return map.remove(matchId);
    }

    @Override
    public MatchEntity findById(String matchId) {
        return map.get(matchId);
    }

}
