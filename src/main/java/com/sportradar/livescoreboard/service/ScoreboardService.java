package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;
import com.sportradar.livescoreboard.logging.ScoreboardLogger;
import com.sportradar.livescoreboard.repository.MapRepository;
import com.sportradar.livescoreboard.util.MatchIdGenerator;

import java.time.LocalDate;
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

    private final MapRepository repository;
    private static final ScoreboardLogger logger = new ScoreboardLogger(ScoreboardService.class);

    public ScoreboardService(MapRepository repository) {
        this.repository = repository;
    }

    @Override
    public MatchEntity startMatch(String homeTeam, String awayTeam) {
        return startMatch(String.format("FW_%d_%s", LocalDate.now().getYear(), MatchIdGenerator.getInstance().getUniqueMatchId()), homeTeam, awayTeam);
    }

    @Override
    public MatchEntity startMatch(String matchId, String homeTeam, String awayTeam) {
        if (isNull(homeTeam) || isNull(awayTeam)) {
            logger.error("Illegal argument passed, null values passed");
            throw new NullPointerException("Null arguments passed in start match");
        }
        if (homeTeam.isEmpty() || awayTeam.isEmpty()
                || homeTeam.equalsIgnoreCase(awayTeam) || homeTeam.matches("-?\\d+(.\\d+)?")
                || awayTeam.matches("-?\\d+(.\\d+)?")) {
            logger.error("Illegal argument passed. Duplicate key found.");
            throw new IllegalArgumentException("Illegal argument passed. Duplicate key found.");
        }

        MatchEntity matchEntity= repository.saveOrUpdate(new MatchEntity(matchId, homeTeam, awayTeam));
        matchEntity.setStatus(MatchEntity.Status.IN_PROGRESS);
        logger.info("Match is started between : "+matchEntity.getHomeTeam()+"-0 Vs "+matchEntity.getAwayTeam()+"-0 with Id :"+matchEntity.getMatchId() );

        return matchEntity;
    }

    public MatchEntity updateMatchScore(String matchId, int homeTeamScore, int awayTeamScore){
        var matchToUpdate = repository.findById(matchId).orElseThrow(() -> {
            logger.info("No such match found with Id:"+matchId);
            throw new IllegalArgumentException("No such match found with Id:"+matchId);
        });
        matchToUpdate.setHomeTeamScore(homeTeamScore);
        matchToUpdate.setAwayTeamScore(awayTeamScore);
        return repository.saveOrUpdate(matchToUpdate);
    }

    @Override
    public MatchEntity finishMatch(String matchId){
        var matchToUpdate = repository.findById(matchId).orElseThrow(() -> {
            logger.info("No such match found with Id:"+matchId);
            throw new IllegalArgumentException("No such match found with Id:"+matchId);
        });

        matchToUpdate.setStatus(MatchEntity.Status.FINISHED);
        return repository.saveOrUpdate(matchToUpdate);
    }

    public List<MatchEntity> getSummaryOfMatches() {
        //Sorting of matches as per total score
        return repository.getSummaryOfMatches()
                .stream()
                .filter(matchEntity -> matchEntity.getStatus() == MatchEntity.Status.IN_PROGRESS)
                .sorted()
                .toList();
    }
}
