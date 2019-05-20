package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.ValidateBaggageChecedInDao;
import com.flight.exercise.flight.excercise.services.ValidateBaggageCheckedInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateBaggageCheckedInServiceImpl implements ValidateBaggageCheckedInService {

    @Autowired
    private final ValidateBaggageChecedInDao validateBaggageChecedInDao;

    public ValidateBaggageCheckedInServiceImpl(ValidateBaggageChecedInDao validateBaggageChecedInDao) {
        this.validateBaggageChecedInDao = validateBaggageChecedInDao;
    }

    @Override
    public Boolean validateBaggeCheckedIn(Integer destinationId, String baggageId) {
        log.debug(":validateBaggeCheckedIn: validating if baggage checked in in DB");
        return validateBaggageChecedInDao.validateBaggeCheckedIn(destinationId, baggageId);
    }
}
