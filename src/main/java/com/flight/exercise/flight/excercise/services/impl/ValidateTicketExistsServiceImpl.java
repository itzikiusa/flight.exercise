package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.ValidateTicketExistsDao;
import com.flight.exercise.flight.excercise.services.ValidateTicketExistsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidateTicketExistsServiceImpl implements ValidateTicketExistsService {

    @Autowired
    private final ValidateTicketExistsDao validateTicketExistsDao;

    public ValidateTicketExistsServiceImpl(ValidateTicketExistsDao validateTicketExistsDao) {
        this.validateTicketExistsDao = validateTicketExistsDao;
    }

    @Override
    public Boolean validateTicketAvailable(Integer ticketId) {
        log.debug(":validateTicketAvailable: validating ticket existence in DB");
        return validateTicketExistsDao.validateTicketAvailable(ticketId);
    }
}
