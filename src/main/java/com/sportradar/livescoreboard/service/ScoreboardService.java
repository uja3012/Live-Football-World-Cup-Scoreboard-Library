package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.repository.MapRepository;

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
        }catch (NullPointerException e){
            throw new NullPointerException("No match found with"+ matchId);
        }
    }
}
