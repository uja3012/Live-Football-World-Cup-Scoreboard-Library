package com.sportradar.livescoreboard.service;

import com.sportradar.livescoreboard.entity.MatchEntity;

public interface ScoreboardServiceInterface {
    MatchEntity startMatch(String T, String S);
    MatchEntity finishMatch(String T);
}
