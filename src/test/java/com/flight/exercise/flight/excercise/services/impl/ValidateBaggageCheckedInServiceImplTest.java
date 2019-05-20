package com.flight.exercise.flight.excercise.services.impl;

import com.flight.exercise.flight.excercise.dao.ValidateBaggageChecedInDao;
import com.flight.exercise.flight.excercise.dao.impl.ValidateBaggageChecedInDaoImpl;
import com.flight.exercise.flight.excercise.db.BaggageDb;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateBaggageCheckedInServiceImplTest {

    private ValidateBaggageCheckedInServiceImpl classUnderTest;

    @Before
    public void setUp(){
        BaggageDb baggageDb = new BaggageDb();
        ValidateBaggageChecedInDao validateBaggageChecedInDao = new ValidateBaggageChecedInDaoImpl(baggageDb);

        classUnderTest = new ValidateBaggageCheckedInServiceImpl(validateBaggageChecedInDao);
    }

    @Test
    public void checkBaggage_exists() {
        Boolean response = classUnderTest.validateBaggeCheckedIn(0,  "baggage_0");
        assertTrue(response);
    }

    @Test
    public void checkBaggage_cache() {
        classUnderTest.validateBaggeCheckedIn(1,  "baggage_1");
        Boolean response = classUnderTest.validateBaggeCheckedIn(1,  "baggage_1");
        assertFalse(response);
    }

    @Test
    public void checkBagge_notExists() {
        Boolean response = classUnderTest.validateBaggeCheckedIn(1,  "baggage_1");
        assertFalse(response);
    }

}