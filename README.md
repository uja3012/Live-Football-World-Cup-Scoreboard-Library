# Live-Football-World-Cup-Scoreboard-Library

## Intro:
Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.We have used in-memory solution to save & update match details using map data structure

### The scoreboard library supports the following operations:

 #### 1. Start a new match
        Match starts between home team vs away team & it assumes initial score 0 – 0.
        Adds it the scoreboard (temporary data storage i.e. Map as key = Match Id & Value : Match Entity) 
        & captures following parameters:
        a. Home team
        b. Away team
       for ex. scoreboardService.startMatch(homeTeam,awayTeam)
       Please note : you can also pass match id generated by yourself or default one will generate id for you.
       for ex. scoreboardService.startMatch("FW_2023_001",homeTeam,awayTeam)
  #### 3. Update score. 
       This receives a pair of absolute scores: home team score and away team score along with match id.
       for ex. scoreboardService.updateMatchScore(matchId, homeTeamScore, awayTeamScore)
 #### 5. Finishes match
      Finish match currently in progress. This removes a match from the scoreboard.
      for ex. scoreboardService.finishMatch(matchId)
 #### 7. Gets a summary of matches 
      Summary of matches shown in progress ordered by their total score.
      The matches with the same total score will be returned ordered by the most recently started match in the scoreboard.
      for ex.scoreboardService.getSummaryOfMatches()
      
For example, if following matches are started in the specified order and their scores respectively updated:
  1. Mexico 0 - Canada 5
  2. Spain 10 - Brazil 2
  3. Germany 2 - France 2
  4. Uruguay 6 - Italy 6
  5. Argentina 3 - Australia 1
  
The summary should be as follows:
  1. Uruguay 6 - Italy 6
  2. Spain 10 - Brazil 2
  3. Mexico 0 - Canada 5
  4. Argentina 3 - Australia 1
  5. Germany 2 - France 2

