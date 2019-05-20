package com.flight.exercise.flight.excercise.dao;

public interface ValidateBaggageChecedInDao {
    Boolean validateBaggeCheckedIn(Integer destinationId, String baggageId);
}
