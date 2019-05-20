package com.flight.exercise.flight.excercise.db;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class AvailableTicketDb {

    private final Set<Integer> TICKETS_PURCHASED;

    //wanted to use GUAVA cache but no libs available
    private Table<Integer, Boolean, Timestamp> TICKETS_CACHE = HashBasedTable.create();

    public AvailableTicketDb() {
        TICKETS_PURCHASED = initSet();
    }

    private Set<Integer> initSet() {
        Set<Integer> availableTicketsSet = new HashSet<>();
        for (int i = 0; i < 1000; i++)
            availableTicketsSet.add(i);
        return availableTicketsSet;
    }

    public boolean validateTicketAvailable(Integer ticket) {
        if (TICKETS_CACHE.row(ticket).size() != 0)
            return Lists.newArrayList(TICKETS_CACHE.row(ticket).keySet()).get(0);

        boolean response = !TICKETS_PURCHASED.contains(ticket);
        TICKETS_CACHE.put(ticket, response, Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
        return response;
    }


    @Scheduled(fixedRate = 60000)
    public void removedCachedVals() {
        for (Integer row:TICKETS_CACHE.rowKeySet()){
            if (Timestamp.valueOf(LocalDateTime.now()).after(Lists.newArrayList(TICKETS_CACHE.row(row).values()).get(0)))
                TICKETS_CACHE.remove(row, Lists.newArrayList(TICKETS_CACHE.row(row).keySet()).get(0));
        }
    }
}
