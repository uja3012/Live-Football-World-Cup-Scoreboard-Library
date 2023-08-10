package com.sportradar.livescoreboard.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MatchIdGenerator implements IdGenerator {

    private static int sequence=0;

    public long generateSequenceId() {
        String uniqueId=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        Long longUniqueId=Long.valueOf(uniqueId);
        return ++sequence+longUniqueId;
    }

    public static String getUniqueMatchId(){
        final MatchIdGenerator matchIdGenerator = new MatchIdGenerator();
        return "SR_"+(matchIdGenerator.generateSequenceId());
    }

    public static Long getSequenceId(){
        final MatchIdGenerator matchIdGenerator = new MatchIdGenerator();
        return (matchIdGenerator.generateSequenceId());
    }

}
