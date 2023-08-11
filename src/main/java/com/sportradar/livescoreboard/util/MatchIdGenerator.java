package com.sportradar.livescoreboard.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MatchIdGenerator implements IdGenerator {

    private static int sequence=0;
    private static final MatchIdGenerator matchIdGenerator = new MatchIdGenerator();

    public long generateSequenceId() {
        String uniqueId=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        Long longUniqueId=Long.valueOf(uniqueId);
        return ++sequence+longUniqueId;
    }

    public static String getUniqueMatchId(){
        return "SR_"+(matchIdGenerator.generateSequenceId());
    }

    public static Long getSequenceId(){
        return (matchIdGenerator.generateSequenceId());
    }

}
