package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.logging.ScoreboardLogger;
import com.sportradar.livescoreboard.repository.MapRepository;
import java.util.Collections;
import java.util.List;
import static java.util.Objects.isNull;

/**
 * Licensed Material - Property of Sportradar
 * Building Library With Java
 * (c) Copyright @
 * ScoreboardService to access all the necessary functions from MatchEntity and MapRepository
 * Date : 10.08.2023
 * Version : 1.0 (Initial Version)
 * @author Ujwala Vanve
 */

public class ScoreboardService implements ScoreboardServiceInterface{

    private MapRepository mapRepository = null;
    private static final ScoreboardLogger logger = new ScoreboardLogger(ScoreboardService.class);

    public ScoreboardService(MapRepository repository) {
        mapRepository = repository;
    }

    @Override
    public MatchEntity startMatch(String homeTeam, String awayTeam) {
        if (isNull(homeTeam) || isNull(awayTeam)) {
            logger.error("Illegal argument passed, null values passed");
            throw new NullPointerException("Null arguments passed in start match");
        }
        if (homeTeam.isEmpty() || awayTeam.isEmpty() || homeTeam.equalsIgnoreCase(awayTeam) || homeTeam.matches("-?\\d+(.\\d+)?") || awayTeam.matches("-?\\d+(.\\d+)?")) {
            logger.error("Illegal argument passed. Duplicate key found.");
            throw new IllegalArgumentException("Illegal argument passed. Duplicate key found.");
        }
        return mapRepository.save(new MatchEntity(homeTeam, awayTeam));
    }

    public MatchEntity updateMatchScore(String matchId, int homeTeamScore, int awayTeamScore){
        try{
            MatchEntity matchToUpdate = mapRepository.findById(matchId);
            matchToUpdate.setHomeTeamScore(homeTeamScore);
            matchToUpdate.setAwayTeamScore(awayTeamScore);
            return matchToUpdate;
        }catch (NullPointerException nullPointerException){
            logger.error("NullPointerException : "+ nullPointerException);
            throw new NullPointerException("No match found with "+ matchId);
        }
    }

    @Override
    public MatchEntity finishMatch(String matchId){
        if (isNull(matchId)) {
            logger.error("Null arguments passed");
            throw new NullPointerException("Null arguments passed");
        }
        return mapRepository.deleteById(matchId);
    }

    public List<MatchEntity> getSummaryOfMatches() {
        //Sorting of matches as per total score
        List<MatchEntity> matchList = mapRepository.getSummaryOfMatches();
        Collections.sort(matchList);
        return matchList;
    }
}
