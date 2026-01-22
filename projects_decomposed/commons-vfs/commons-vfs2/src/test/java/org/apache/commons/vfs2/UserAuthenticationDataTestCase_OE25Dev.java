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
package org.apache.commons.vfs2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class UserAuthenticationDataTestCase_OE25Dev {

    @Test
    public void testCharacterBasedData_1_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        assertSame(array, data.getData(UserAuthenticationData.USERNAME));
    }

    @Test
    public void testCharacterBasedData_2_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        assertArrayEquals("Apache".toCharArray(), data.getData(UserAuthenticationData.DOMAIN));
    }

    @Test
    public void testCharacterBasedData_3_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        // removed other assertion
        data.setData(UserAuthenticationData.DOMAIN, "Apache Commons".toCharArray());
        assertArrayEquals("Apache Commons".toCharArray(), data.getData(UserAuthenticationData.DOMAIN));
    }

    @Test
    public void testCharacterBasedData_4_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        // removed other assertion
        data.setData(UserAuthenticationData.DOMAIN, "Apache Commons".toCharArray());
        // removed other assertion
        assertNull(data.getData(UserAuthenticationData.PASSWORD));
    }

    @Test
    public void testCharacterBasedData_5_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        // removed other assertion
        data.setData(UserAuthenticationData.DOMAIN, "Apache Commons".toCharArray());
        // removed other assertion
        // removed other assertion

        data.cleanup();
        assertNull(data.getData(UserAuthenticationData.USERNAME));
    }

    @Test
    public void testCharacterBasedData_6_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        // removed other assertion
        data.setData(UserAuthenticationData.DOMAIN, "Apache Commons".toCharArray());
        // removed other assertion
        // removed other assertion

        data.cleanup();
        // removed other assertion
        assertNull(data.getData(UserAuthenticationData.DOMAIN));
    }

    @Test
    public void testCharacterBasedData_7_oe() {
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "PMC".toCharArray();
        data.setData(UserAuthenticationData.USERNAME, array);
        data.setData(UserAuthenticationData.DOMAIN, "Apache".toCharArray());
        // removed other assertion
        // removed other assertion
        data.setData(UserAuthenticationData.DOMAIN, "Apache Commons".toCharArray());
        // removed other assertion
        // removed other assertion

        data.cleanup();
        // removed other assertion
        // removed other assertion
        final char[] nulls = { 0, 0, 0 };
        assertArrayEquals(nulls, array);
    }

    @Test
    public void testCustomType_1_oe() {
        final UserAuthenticationData.Type type = new UserAuthenticationData.Type("JUNIT");
        final UserAuthenticationData data = new UserAuthenticationData();
        final char[] array = "test".toCharArray();
        data.setData(type, array);
        assertSame(array, data.getData(type));
    }

}
