package com.github.scribejava.core.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThrows;
import org.junit.function.ThrowingRunnable;

public class ParameterListTest_OE25Dev {

    private ParameterList params;

    @Before
    public void setUp() {
        this.params = new ParameterList();
    }

    public void shouldThrowExceptionWhenAppendingNullMapToQuerystring() {
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                params.appendTo(null);
            }
        });
    }


}
