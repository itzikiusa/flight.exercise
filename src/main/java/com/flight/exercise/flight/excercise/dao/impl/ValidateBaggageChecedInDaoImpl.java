package com.flight.exercise.flight.excercise.dao.impl;

import com.flight.exercise.flight.excercise.dao.ValidateBaggageChecedInDao;
import com.flight.exercise.flight.excercise.db.BaggageDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateBaggageChecedInDaoImpl implements ValidateBaggageChecedInDao {

    @Autowired
    private final BaggageDb baggageDb;

    public ValidateBaggageChecedInDaoImpl(BaggageDb baggageDb) {
        this.baggageDb = baggageDb;
    }

    @Override
    public Boolean validateBaggeCheckedIn(Integer destinationId, String baggageId) {
        return baggageDb.checkBaggage(destinationId, baggageId);
    }
}
