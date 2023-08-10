package com.sportradar.livescoreboard.entity;

import com.sportradar.livescoreboard.util.MatchIdGenerator;

/**
 * Licensed Material - Property of Sportradar
 * Building Library With Java
 * (c) Copyright @
 * Defined match entity bean which holds match information
 * Date : 10.08.2023
 * Version : 1.0 (Initial Version)
 * @author Ujwala Vanve
 */

public class MatchEntity implements Comparable {

    private String matchId;
    private Long internalId;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    public MatchEntity(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchId = "FW2023"+ MatchIdGenerator.getUniqueMatchId();
        this.internalId = MatchIdGenerator.getSequenceId();
    }

    public MatchEntity(String matchId, String homeTeam, String awayTeam) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.internalId = MatchIdGenerator.getSequenceId();
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getMatchId() {
        return matchId;
    }

    @Override
    public int compareTo(Object matchEntity) {
        MatchEntity matchEntityNext = (MatchEntity) matchEntity;
        if(matchEntityNext == null) return 0;

        if((this.awayTeamScore + this.homeTeamScore) < (matchEntityNext.awayTeamScore + matchEntityNext.homeTeamScore)){
            return 1;
        }else if((this.awayTeamScore + this.homeTeamScore) == (matchEntityNext.awayTeamScore + matchEntityNext.homeTeamScore)) {
            return (this.internalId < matchEntityNext.internalId) ? 1 : -1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "matchId='" + internalId + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                '}';
    }
}
