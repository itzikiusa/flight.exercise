package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.ValidateTicketExistsDao;
import com.flight.exercise.flight.excercise.dao.impl.ValidateTicketExistsDaoImpl;
import com.flight.exercise.flight.excercise.db.AvailableTicketDb;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateTicketExistsServiceImplTest {

    private ValidateTicketExistsServiceImpl classUnderTest;

    @Before
    public void setUp() {
        AvailableTicketDb availableTicketDb = new AvailableTicketDb();
        ValidateTicketExistsDao validateTicketExistsDao = new ValidateTicketExistsDaoImpl(availableTicketDb);

        classUnderTest = new ValidateTicketExistsServiceImpl(validateTicketExistsDao);
    }

    @Test
    public void checkTicketExists() {
        Boolean response = classUnderTest.validateTicketAvailable(1);
        assertFalse(response);
    }

    @Test
    public void checkTicketExists_getFromCache() {
        classUnderTest.validateTicketAvailable(1);
        Boolean response = classUnderTest.validateTicketAvailable(1);
        assertFalse(response);
    }

    @Test
    public void checkTicketNotExists() {
        Boolean response = classUnderTest.validateTicketAvailable(999999);
        assertTrue(response);
    }




}