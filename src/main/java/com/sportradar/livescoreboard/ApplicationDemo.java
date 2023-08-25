package com.sportradar.livescoreboard;

import com.sportradar.livescoreboard.repository.MapRepository;
import com.sportradar.livescoreboard.service.ScoreboardService;

public class ApplicationDemo {
    public static void main(String[] args) {
        final MapRepository mapRepository= new MapRepository();
        ScoreboardService scoreboardService = new ScoreboardService(mapRepository);

        scoreboardService.startMatch("Mexico","Canada");

        scoreboardService.startMatch( "FW20231001","Mexico","Canada" );
        scoreboardService.startMatch( "FW20231002","Spain","Brazil" );
        scoreboardService.startMatch( "FW20231003","Germany","France" );
        scoreboardService.startMatch( "FW20231004","Uruguay","Italy" );
        scoreboardService.startMatch( "FW20231005","Argentina","Australia" );


        scoreboardService.updateMatchScore("FW20231001", 0, 5);
        scoreboardService.updateMatchScore("FW20231002", 10, 2);
        scoreboardService.updateMatchScore("FW20231003", 2, 2);
        scoreboardService.updateMatchScore("FW20231004", 6, 6);
        scoreboardService.updateMatchScore("FW20231005", 3, 1);
    }
}
