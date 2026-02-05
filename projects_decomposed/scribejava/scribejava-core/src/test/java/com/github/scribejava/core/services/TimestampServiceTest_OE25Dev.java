package com.github.scribejava.core.services;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TimestampServiceTest_OE25Dev {

    private TimestampServiceImpl service;

    @Before
    public void setUp() {
        service = new TimestampServiceImpl();
        service.setTimer(new TimerStub());
    }

    private static class TimerStub extends TimestampServiceImpl.Timer {

        @Override
        public Long getMilis() {
            return 1000000L;
        }

        @Override
        public Integer getRandomInteger() {
            return 42;
        }
    }

@Test
    public void shouldReturnTimestampInSeconds_1_oe() {
        final String expected = "1000";
        assertEquals(expected, service.getTimestampInSeconds());
    }

@Test
    public void shouldReturnNonce_1_oe() {
        final String expected = "1042";
        assertEquals(expected, service.getNonce());
    }

}
