package com.github.scribejava.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.Test;

public class TokenTest_OE25Dev {

    @Test
    public void shouldTestEqualityBasedOnTokenAndSecret_1_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret");
        final Token actual = new OAuth1AccessToken("access", "secret");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestEqualityBasedOnTokenAndSecret_2_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret");
        final Token actual = new OAuth1AccessToken("access", "secret");

        // removed other assertion
        assertEquals(actual, actual);
    }

    @Test
    public void shouldNotDependOnRawString_1_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret", "raw_string");
        final Token actual = new OAuth1AccessToken("access", "secret", "different_raw_string");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSameHashCodeForEqualObjects_1_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret");
        final Token actual = new OAuth1AccessToken("access", "secret");

        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    public void shouldNotBeEqualToNullOrOtherObjects_1_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret", "response");

        assertNotSame(expected, null);
    }

    @Test
    public void shouldNotBeEqualToNullOrOtherObjects_2_oe() {
        final Token expected = new OAuth1AccessToken("access", "secret", "response");

        // removed other assertion
        assertNotSame(expected, new Object());
    }

    @Test
    public void shouldReturnUrlParam_1_oe() {
        final Token actual = new OAuth1AccessToken("acccess", "secret",
                "user_id=3107154759&screen_name=someuser&empty=&=");
        assertEquals("someuser", actual.getParameter("screen_name"));
    }

    @Test
    public void shouldReturnUrlParam_2_oe() {
        final Token actual = new OAuth1AccessToken("acccess", "secret",
                "user_id=3107154759&screen_name=someuser&empty=&=");
        // removed other assertion
        assertEquals("3107154759", actual.getParameter("user_id"));
    }

    @Test
    public void shouldReturnUrlParam_3_oe() {
        final Token actual = new OAuth1AccessToken("acccess", "secret",
                "user_id=3107154759&screen_name=someuser&empty=&=");
        // removed other assertion
        // removed other assertion
        assertEquals(null, actual.getParameter("empty"));
    }

    @Test
    public void shouldReturnUrlParam_4_oe() {
        final Token actual = new OAuth1AccessToken("acccess", "secret",
                "user_id=3107154759&screen_name=someuser&empty=&=");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, actual.getParameter(null));
    }

}
