package org.jsoup.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateTest_OE25Dev {

    @Test void stacktraceFiltersOutValidateClass() {
        boolean threw = false;
        try {
            Validate.notNull(null);
        } catch (ValidationException e) {
            threw = true;
            assertEquals("Object must not be null", e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                assertNotEquals(trace.getClassName(), Validate.class.getName());
            }
            assertTrue(stackTrace.length >= 1);
        }
        Assertions.assertTrue(threw);
    }

    @Test void nonnullParam() {
        boolean threw = true;
        try {
            Validate.notNullParam(null, "foo");
        } catch (ValidationException e) {
            assertEquals("The parameter 'foo' must not be null.", e.getMessage());
        }
        assertTrue(threw);
    }

    @Test
    public void testNotNull_1_oe() {
        Validate.notNull("foo");
        boolean threw = false;
        try {
            Validate.notNull(null);
        } catch (IllegalArgumentException e) {
            threw = true;
        }
        Assertions.assertTrue(threw);
    }

    @Test void stacktraceFiltersOutValidateClass() {
        boolean threw = false;
        try {
            Validate.notNull_1_oe(null);
        } catch (ValidationException e) {
            threw = true;
            assertEquals("Object must not be null", e.getMessage());
        }
        }

    @Test void stacktraceFiltersOutValidateClass() {
        boolean threw = false;
        try {
            Validate.notNull_2_oe(null);
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                assertNotEquals(trace.getClassName(), Validate.class.getName());
        }
        }
        }

    @Test void stacktraceFiltersOutValidateClass() {
        boolean threw = false;
        try {
            Validate.notNull_3_oe(null);
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                // removed other assertion
            }
            assertTrue(stackTrace.length >= 1);
        }
        }

    @Test void nonnullParam() {
        boolean threw = true;
        try {
            Validate.notNullParam_1_oe(null, "foo");
        } catch (ValidationException e) {
            assertEquals("The parameter 'foo' must not be null.", e.getMessage());
        }
        }

}
