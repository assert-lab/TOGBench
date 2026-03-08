package org.jsoup.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateTest_OE25Dev {
    @Test
    public void testNotNull() {
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

    @Test void stacktraceFiltersOutValidateClass_1_oe() {
        boolean threw = false;
        try {
            Validate.notNull(null);
        } catch (ValidationException e) {
            threw = true;
            assertEquals("Object must not be null", e.getMessage());
        }
        }

    @Test void stacktraceFiltersOutValidateClass_2_oe() {
        boolean threw = false;
        try {
            Validate.notNull(null);
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                assertNotEquals(trace.getClassName(), Validate.class.getName());
        }
        }
        }

    @Test void stacktraceFiltersOutValidateClass_3_oe() {
        boolean threw = false;
        try {
            Validate.notNull(null);
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

    @Test void stacktraceFiltersOutValidateClass_4_oe() {
        boolean threw = false;
        try {
            Validate.notNull(null);
        } catch (ValidationException e) {
            threw = true;
            // removed other assertion
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement trace : stackTrace) {
                // removed other assertion
            }
            // removed other assertion
        }
        Assertions.assertTrue(threw);
        }

    @Test void nonnullParam_1_oe() {
        boolean threw = true;
        try {
            Validate.notNullParam(null, "foo");
        } catch (ValidationException e) {
            assertEquals("The parameter 'foo' must not be null.", e.getMessage());
        }
        }

    @Test void nonnullParam_2_oe() {
        boolean threw = true;
        try {
            Validate.notNullParam(null, "foo");
        } catch (ValidationException e) {
            // removed other assertion
        }
        assertTrue(threw);
        }

}
