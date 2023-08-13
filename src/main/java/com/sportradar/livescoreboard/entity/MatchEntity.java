package com.sportradar.livescoreboard.entity;

import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Defined match entity bean which holds match information
 * Date : 10.08.2023
 * Version : 1.0
 *
 * @author Ujwala Vanve
 */

public class MatchEntity implements Comparable<MatchEntity> {

    public enum Status{
        NOT_STARTED,
        IN_PROGRESS,
        FINISHED
    }

    private final String matchId;
    private final String homeTeam;
    private final String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private Status status = Status.NOT_STARTED;

    public MatchEntity(String matchId, String homeTeam, String awayTeam) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    private int getTotalScore(){
        return homeTeamScore + awayTeamScore;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int compareTo(MatchEntity that) {
        return Comparator.nullsLast(Comparator.comparing(MatchEntity::getTotalScore).thenComparing(MatchEntity::getMatchId)).reversed().compare(this, that);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("matchId='" + matchId + "'")
                .add("homeTeam='" + homeTeam + "'")
                .add("awayTeam='" + awayTeam + "'")
                .add("homeTeamScore='" + homeTeamScore + "'")
                .add("awayTeamScore='" + awayTeamScore + "'").toString();

    }
}
