package com.flight.exercise.flight.excercise.db;

import com.flight.exercise.flight.excercise.domain.BaggageRequestData;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class BaggageDb {

    private final Table<Integer, String, Boolean> BAGGAGE_DB;

    //wanted to use GUAVA cache but no libs available
    private Table<BaggageRequestData, Boolean, Timestamp> BAGGAGE_CACHE = HashBasedTable.create();

    public BaggageDb() {
        BAGGAGE_DB = initTable();
    }

    private Table<Integer, String, Boolean> initTable() {
        Table<Integer, String, Boolean> table = HashBasedTable.create();
        for (int i = 0; i < 1000; i++)
            table.put(i, "baggage_" + i, i % 3 == 0);
        return table;
    }

    public boolean checkBaggage(Integer destinationId, String baggageId) {

        if (BAGGAGE_CACHE.row(BaggageRequestData.builder()
                .destinationId(destinationId)
                .baggageId(baggageId)
                .build()).size() != 0)
            return Lists.newArrayList(BAGGAGE_CACHE.row(BaggageRequestData.builder()
                    .destinationId(destinationId)
                    .baggageId(baggageId)
                    .build()).keySet()).get(0);

        Boolean response = BAGGAGE_DB.get(destinationId, baggageId);
        if (response == null)
            response = false;
        BAGGAGE_CACHE.put(BaggageRequestData.builder()
                .destinationId(destinationId)
                .baggageId(baggageId)
                .build(), response, Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
        return response;
    }

    @Scheduled(fixedRate = 60000)
    public void removedCachedVals() {
        for (BaggageRequestData row: BAGGAGE_CACHE.rowKeySet()){
            if (Timestamp.valueOf(LocalDateTime.now()).after(Lists.newArrayList(BAGGAGE_CACHE.row(row).values()).get(0)))
                BAGGAGE_CACHE.remove(row, Lists.newArrayList(BAGGAGE_CACHE.row(row).keySet()).get(0));
        }
    }

}
