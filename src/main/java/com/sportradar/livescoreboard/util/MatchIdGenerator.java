package com.sportradar.livescoreboard.util;

import java.util.concurrent.atomic.AtomicLong;

public class MatchIdGenerator implements IdGenerator{

    private static final AtomicLong sequence= new AtomicLong();
    private static final MatchIdGenerator INSTANCE = new MatchIdGenerator();

    // preventing default constructor
    private MatchIdGenerator() {
    }

    public static MatchIdGenerator getInstance() {
        return INSTANCE;
    }

    @Override
    public String getUniqueMatchId(){
        return  String.format("SR_%d", sequence.incrementAndGet());
    }

}
