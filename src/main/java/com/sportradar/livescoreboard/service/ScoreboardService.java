package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;

import static java.util.Objects.isNull;

public class ScoreboardService {

    private MapRepository mapRepository = null;

    public ScoreboardService(MapRepository repository) {
        this.mapRepository = repository;
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

    public void startMatch(String homeTeam, String awayTeam) {

        if(homeTeam.isEmpty()||awayTeam.isEmpty()||homeTeam.equalsIgnoreCase(awayTeam) || homeTeam.matches("-?\\d+(.\\d+)?") || awayTeam.matches("-?\\d+(.\\d+)?")) {
            throw new IllegalArgumentException("Illegal argument passed. Duplicate key found.");
        }else if(isNull(homeTeam) || isNull(awayTeam)){
            throw new NullPointerException("Null arguments passed in start match");
        }
        mapRepository.save(new MatchEntity(homeTeam,awayTeam));
    }

}
