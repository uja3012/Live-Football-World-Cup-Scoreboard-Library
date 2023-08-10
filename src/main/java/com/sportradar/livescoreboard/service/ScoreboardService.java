package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

public class ScoreboardService {

    private MapRepository mapRepository = null;

    public ScoreboardService(MapRepository repository) {
        mapRepository = repository;
    }

    public MatchEntity startMatch(String homeTeam, String awayTeam) {
        if (isNull(homeTeam) || isNull(awayTeam)) {
            throw new NullPointerException("Null arguments passed in start match");
        }
        if (homeTeam.isEmpty() || awayTeam.isEmpty() || homeTeam.equalsIgnoreCase(awayTeam) || homeTeam.matches("-?\\d+(.\\d+)?") || awayTeam.matches("-?\\d+(.\\d+)?")) {
            throw new IllegalArgumentException("Illegal argument passed. Duplicate key found.");
        }
        return mapRepository.save(new MatchEntity(homeTeam, awayTeam));
    }

    public MatchEntity updateMatchScore(String matchId, int homeTeamScore, int awayTeamScore){
        try{
            MatchEntity matchToUpdate = (MatchEntity) mapRepository.findById(matchId);
            matchToUpdate.setHomeTeamScore(homeTeamScore);
            matchToUpdate.setAwayTeamScore(awayTeamScore);
            return matchToUpdate;
        }catch (NullPointerException nullPointerException){
            throw new NullPointerException("No match found with "+ matchId);
        }
    }

    public MatchEntity finishMatch(String matchId){
        if (isNull(matchId)) {
            throw new NullPointerException("Null arguments passed");
        }
        return mapRepository.deleteByMatchId(matchId);
    }

    public List<MatchEntity> getSummaryOfMatches() {
        //Sorting of matches as per total score
        List<MatchEntity> matchList = mapRepository.getSummaryOfMatches();
        Collections.sort(matchList);
        return matchList;
    }
}
