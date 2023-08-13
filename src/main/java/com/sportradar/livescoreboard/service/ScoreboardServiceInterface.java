package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;

public interface ScoreboardServiceInterface {
    MatchEntity startMatch(String homeTeam, String awayTeam);
    MatchEntity startMatch(String matchId,String homeTeam, String awayTeam);
    MatchEntity updateMatchScore(String matchId,int homeTeamScore, int awayTeamScore);
    MatchEntity finishMatch(String matchId);
}
