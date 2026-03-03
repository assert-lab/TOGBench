/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.vfs2.auth;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.vfs2.UserAuthenticationData;
import org.apache.commons.vfs2.UserAuthenticator;
import org.junit.Test;

public class StaticUserAuthenticatorTestCase_OE25Dev {

    @Test
    public void testAuthenticationRequest_1_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        assertArrayEquals("DOMAIN".toCharArray(), authenticationData.getData(UserAuthenticationData.DOMAIN));
    }

    @Test
    public void testAuthenticationRequest_2_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        assertNull(authenticationData.getData(UserAuthenticationData.USERNAME));
    }

    @Test
    public void testAuthenticationRequest_3_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        assertNull(authenticationData.getData(UserAuthenticationData.PASSWORD));
    }

    @Test
    public void testAuthenticationRequest_4_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        authenticationData = userAuthenticator.requestAuthentication(
                ArrayUtils.toArray(UserAuthenticationData.USERNAME, UserAuthenticationData.PASSWORD));
        assertNull(authenticationData.getData(UserAuthenticationData.DOMAIN));
    }

    @Test
    public void testAuthenticationRequest_5_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        authenticationData = userAuthenticator.requestAuthentication(
                ArrayUtils.toArray(UserAuthenticationData.USERNAME, UserAuthenticationData.PASSWORD));
        assertArrayEquals("USER".toCharArray(), authenticationData.getData(UserAuthenticationData.USERNAME));
    }

    @Test
    public void testAuthenticationRequest_6_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        UserAuthenticationData authenticationData = userAuthenticator
                .requestAuthentication(ArrayUtils.toArray(UserAuthenticationData.DOMAIN));
        authenticationData = userAuthenticator.requestAuthentication(
                ArrayUtils.toArray(UserAuthenticationData.USERNAME, UserAuthenticationData.PASSWORD));
        assertArrayEquals("PWD".toCharArray(), authenticationData.getData(UserAuthenticationData.PASSWORD));
    }

    @Test
    public void testEquality_1_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        assertEquals(new StaticUserAuthenticator("DOMAIN", "USER", "PWD"), userAuthenticator);
    }

    @Test
    public void testEquality_2_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        assertNotEquals(new StaticUserAuthenticator("DOMAIN", "USER", null), userAuthenticator);
    }

    @Test
    public void testEquality_3_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        assertNotEquals(new StaticUserAuthenticator("DOMAIN", null, "PWD"), userAuthenticator);
    }

    @Test
    public void testEquality_4_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        assertNotEquals(new StaticUserAuthenticator(null, "USER", "PWD"), userAuthenticator);
    }

    @Test
    public void testEquality_5_oe() {
        final UserAuthenticator userAuthenticator = new StaticUserAuthenticator("DOMAIN", "USER", "PWD");
        assertEquals(new StaticUserAuthenticator("DOMAIN", "USER", "PWD").hashCode(), userAuthenticator.hashCode());
    }

}
