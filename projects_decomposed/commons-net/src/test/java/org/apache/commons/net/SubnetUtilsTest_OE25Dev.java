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

package org.apache.commons.net;

import org.apache.commons.net.util.SubnetUtils;
import org.apache.commons.net.util.SubnetUtils.SubnetInfo;

import junit.framework.TestCase;

@SuppressWarnings("deprecation") // deliberate use of deprecated methods
public class SubnetUtilsTest_OE25Dev extends TestCase {

    /**
     * Test using the inclusiveHostCount flag, which includes the network and broadcast addresses in host counts
     */

    public void testInvalidMasks() {
        try {
            new SubnetUtils("192.168.0.1/33");
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
            // Ignored
        }
    }

    public void testNET428_31() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/31");
        assertEquals(0, subnetUtils.getInfo().getAddressCount());
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        assertNotNull(address);
        assertEquals(0, address.length);
    }

    public void testNET428_32() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/32");
        assertEquals(0, subnetUtils.getInfo().getAddressCount());
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        assertNotNull(address);
        assertEquals(0, address.length);
    }

    public void testNET624() {
        new SubnetUtils("0.0.0.0/0");
        new SubnetUtils("0.0.0.0","0.0.0.0");
        new SubnetUtils("0.0.0.0","128.0.0.0");
        try {
            new SubnetUtils("0.0.0.0","64.0.0.0");
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
            // Ignored
        }
        try {
            new SubnetUtils("0.0.0.0","0.0.0.1");
            fail("Should have thrown IllegalArgumentException");
        } catch (final IllegalArgumentException expected) {
            // Ignored
        }
    }

    public void testAddresses_1_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        assertTrue(info.isInRange("192.168.0.1"));
    }

    public void testAddresses_2_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        assertTrue(info.isInRange("192.168.0.2"));
    }

    public void testAddresses_3_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        assertTrue(info.isInRange("192.168.0.3"));
    }

    public void testAddresses_4_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(info.isInRange("192.168.0.4"));
    }

    public void testAddresses_5_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(info.isInRange("192.168.0.5"));
    }

    public void testAddresses_6_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(info.isInRange("192.168.0.6"));
    }

    public void testAddresses_7_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // We don't count the broadcast address as usable
        assertFalse(info.isInRange("192.168.0.7"));
    }

    public void testAddresses_8_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // We don't count the broadcast address as usable
        // removed other assertion
        assertFalse(info.isInRange("192.168.0.8"));
    }

    public void testAddresses_9_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // We don't count the broadcast address as usable
        // removed other assertion
        // removed other assertion
        assertFalse(info.isInRange("10.10.2.1"));
    }

    public void testAddresses_10_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // We don't count the broadcast address as usable
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(info.isInRange("192.168.1.1"));
    }

    public void testAddresses_11_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.1/29");
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // We don't count the broadcast address as usable
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(info.isInRange("192.168.0.255"));
    }

    public void testCidrAddresses_1_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        assertEquals("255.0.0.0", info.getNetmask());
    }

    public void testCidrAddresses_2_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        assertEquals(16777216, info.getAddressCount());
    }

    public void testCidrAddresses_3_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("0.0.0.0", info.getNetmask());
    }

    public void testCidrAddresses_4_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(4294967296L, info.getAddressCountLong());
    }

    public void testCidrAddresses_5_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("128.0.0.0", info.getNetmask());
    }

    public void testCidrAddresses_6_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(2147483648L, info.getAddressCountLong());
    }

    public void testCidrAddresses_7_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.128.0.0", info.getNetmask());
    }

    public void testCidrAddresses_8_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(8388608, info.getAddressCount());
    }

    public void testCidrAddresses_9_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.192.0.0", info.getNetmask());
    }

    public void testCidrAddresses_10_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(4194304, info.getAddressCount());
    }

    public void testCidrAddresses_11_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.224.0.0", info.getNetmask());
    }

    public void testCidrAddresses_12_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(2097152, info.getAddressCount());
    }

    public void testCidrAddresses_13_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.240.0.0", info.getNetmask());
    }

    public void testCidrAddresses_14_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(1048576, info.getAddressCount());
    }

    public void testCidrAddresses_15_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.248.0.0", info.getNetmask());
    }

    public void testCidrAddresses_16_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(524288, info.getAddressCount());
    }

    public void testCidrAddresses_17_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.252.0.0", info.getNetmask());
    }

    public void testCidrAddresses_18_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(262144, info.getAddressCount());
    }

    public void testCidrAddresses_19_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.254.0.0", info.getNetmask());
    }

    public void testCidrAddresses_20_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(131072, info.getAddressCount());
    }

    public void testCidrAddresses_21_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.0.0", info.getNetmask());
    }

    public void testCidrAddresses_22_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(65536, info.getAddressCount());
    }

    public void testCidrAddresses_23_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.128.0", info.getNetmask());
    }

    public void testCidrAddresses_24_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(32768, info.getAddressCount());
    }

    public void testCidrAddresses_25_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.192.0", info.getNetmask());
    }

    public void testCidrAddresses_26_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(16384, info.getAddressCount());
    }

    public void testCidrAddresses_27_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.224.0", info.getNetmask());
    }

    public void testCidrAddresses_28_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(8192, info.getAddressCount());
    }

    public void testCidrAddresses_29_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.240.0", info.getNetmask());
    }

    public void testCidrAddresses_30_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(4096, info.getAddressCount());
    }

    public void testCidrAddresses_31_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.248.0", info.getNetmask());
    }

    public void testCidrAddresses_32_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(2048, info.getAddressCount());
    }

    public void testCidrAddresses_33_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.252.0", info.getNetmask());
    }

    public void testCidrAddresses_34_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(1024, info.getAddressCount());
    }

    public void testCidrAddresses_35_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.254.0", info.getNetmask());
    }

    public void testCidrAddresses_36_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(512, info.getAddressCount());
    }

    public void testCidrAddresses_37_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.0", info.getNetmask());
    }

    public void testCidrAddresses_38_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(256, info.getAddressCount());
    }

    public void testCidrAddresses_39_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.128", info.getNetmask());
    }

    public void testCidrAddresses_40_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(128, info.getAddressCount());
    }

    public void testCidrAddresses_41_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.192", info.getNetmask());
    }

    public void testCidrAddresses_42_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(64, info.getAddressCount());
    }

    public void testCidrAddresses_43_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.224", info.getNetmask());
    }

    public void testCidrAddresses_44_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(32, info.getAddressCount());
    }

    public void testCidrAddresses_45_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.240", info.getNetmask());
    }

    public void testCidrAddresses_46_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(16, info.getAddressCount());
    }

    public void testCidrAddresses_47_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.248", info.getNetmask());
    }

    public void testCidrAddresses_48_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(8, info.getAddressCount());
    }

    public void testCidrAddresses_49_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.252", info.getNetmask());
    }

    public void testCidrAddresses_50_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(4, info.getAddressCount());
    }

    public void testCidrAddresses_51_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/31");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.254", info.getNetmask());
    }

    public void testCidrAddresses_52_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/31");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(2, info.getAddressCount());
    }

    public void testCidrAddresses_53_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/31");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/32");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("255.255.255.255", info.getNetmask());
    }

    public void testCidrAddresses_54_oe() {
        SubnetUtils utils = new SubnetUtils("192.168.0.1/8");
        utils.setInclusiveHostCount(true);
        SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/9");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/10");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/11");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/12");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/13");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/14");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/15");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/16");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/17");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/18");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/19");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/20");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/21");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/22");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/23");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/24");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/25");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/26");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/27");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/28");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/29");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/30");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/31");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion

        utils = new SubnetUtils("192.168.0.1/32");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(1, info.getAddressCount());
    }

    public void testNET675_1_oe() {
        final SubnetUtils utils = new SubnetUtils("192.168.0.15/32");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        assertTrue(info.isInRange("192.168.0.15"));
    }

    public void testNET679_1_oe() {
        final SubnetUtils utils = new SubnetUtils("10.213.160.0/16");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        assertTrue(info.isInRange("10.213.0.0"));
    }

    public void testNET679_2_oe() {
        final SubnetUtils utils = new SubnetUtils("10.213.160.0/16");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        assertTrue(info.isInRange("10.213.255.255"));
    }

    public void testNET428_31_1_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/31");
        assertEquals(0, subnetUtils.getInfo().getAddressCount());
    }

    public void testNET428_31_2_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/31");
        // removed other assertion
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        assertNotNull(address);
    }

    public void testNET428_31_3_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/31");
        // removed other assertion
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        // removed other assertion
        assertEquals(0, address.length);
    }

    public void testNET428_32_1_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/32");
        assertEquals(0, subnetUtils.getInfo().getAddressCount());
    }

    public void testNET428_32_2_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/32");
        // removed other assertion
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        assertNotNull(address);
    }

    public void testNET428_32_3_oe() {
        final SubnetUtils subnetUtils = new SubnetUtils("1.2.3.4/32");
        // removed other assertion
        final String[] address = subnetUtils.getInfo().getAllAddresses();
        // removed other assertion
        assertEquals(0, address.length);
    }

    public void testParseSimpleNetmask_1_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            assertEquals(bcastAddresses[i], info.getBroadcastAddress());
    }
    }

    public void testParseSimpleNetmask_2_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            assertEquals(cidrSignatures[i], info.getCidrSignature());
    }
    }

    public void testParseSimpleNetmask_3_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            assertEquals(lowAddresses[i], info.getLowAddress());
    }
    }

    public void testParseSimpleNetmask_4_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(highAddresses[i], info.getHighAddress());
    }
    }

    public void testParseSimpleNetmask_5_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(nextAddresses[i], info.getNextAddress());
    }
    }

    public void testParseSimpleNetmask_6_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(previousAddresses[i], info.getPreviousAddress());
    }
    }

    public void testParseSimpleNetmask_7_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(networkAddresses[i], info.getNetworkAddress());
    }
    }

    public void testParseSimpleNetmask_8_oe() {
        final String address = "192.168.0.1";
        final String masks[] = new String[] { "255.0.0.0", "255.255.0.0", "255.255.255.0", "255.255.255.248" };
        final String bcastAddresses[] = new String[] { "192.255.255.255", "192.168.255.255", "192.168.0.255",
                "192.168.0.7" };
        final String lowAddresses[] = new String[] { "192.0.0.1", "192.168.0.1", "192.168.0.1", "192.168.0.1" };
        final String highAddresses[] = new String[] { "192.255.255.254", "192.168.255.254", "192.168.0.254",
                "192.168.0.6" };
        final String nextAddresses[] = new String[] { "192.168.0.2", "192.168.0.2", "192.168.0.2",
                "192.168.0.2" };
        final String previousAddresses[] = new String[] { "192.168.0.0", "192.168.0.0", "192.168.0.0",
                "192.168.0.0" };
        final String networkAddresses[] = new String[] { "192.0.0.0", "192.168.0.0", "192.168.0.0", "192.168.0.0" };
        final String cidrSignatures[] = new String[] { "192.168.0.1/8", "192.168.0.1/16", "192.168.0.1/24",
                "192.168.0.1/29" };
        final int usableAddresses[] = new int[] { 16777214, 65534, 254, 6 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals(usableAddresses[i], info.getAddressCount());
    }
    }

    public void testParseSimpleNetmaskExclusive_1_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            assertEquals("ci " + masks[i], cidrS[i], info.getCidrSignature());
    }
    }

    public void testParseSimpleNetmaskExclusive_2_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            assertEquals("bc " + masks[i], bcast[i], info.getBroadcastAddress());
    }
    }

    public void testParseSimpleNetmaskExclusive_3_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            assertEquals("nw " + masks[i], netwk[i], info.getNetworkAddress());
    }
    }

    public void testParseSimpleNetmaskExclusive_4_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("ac " + masks[i], usableAd[i], info.getAddressCount());
    }
    }

    public void testParseSimpleNetmaskExclusive_5_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("lo " + masks[i], lowAd[i], info.getLowAddress());
    }
    }

    public void testParseSimpleNetmaskExclusive_6_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.5", "0.0.0.0", "0.0.0.0" };
        final String highA[] = new String[] { "192.168.15.6", "0.0.0.0", "0.0.0.0" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 2, 0, 0 };
        // low and high addresses don't exist

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(false);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("hi " + masks[i], highA[i], info.getHighAddress());
    }
    }

    public void testParseSimpleNetmaskInclusive_1_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            assertEquals("ci " + masks[i], cidrS[i], info.getCidrSignature());
    }
    }

    public void testParseSimpleNetmaskInclusive_2_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            assertEquals("bc " + masks[i], bcast[i], info.getBroadcastAddress());
    }
    }

    public void testParseSimpleNetmaskInclusive_3_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            assertEquals("ac " + masks[i], usableAd[i], info.getAddressCount());
    }
    }

    public void testParseSimpleNetmaskInclusive_4_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("nw " + masks[i], netwk[i], info.getNetworkAddress());
    }
    }

    public void testParseSimpleNetmaskInclusive_5_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("lo " + masks[i], lowAd[i], info.getLowAddress());
    }
    }

    public void testParseSimpleNetmaskInclusive_6_oe() {
        final String address = "192.168.15.7";
        final String masks[] = new String[] { "255.255.255.252", "255.255.255.254", "255.255.255.255" };
        final String bcast[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String netwk[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String lowAd[] = new String[] { "192.168.15.4", "192.168.15.6", "192.168.15.7" };
        final String highA[] = new String[] { "192.168.15.7", "192.168.15.7", "192.168.15.7" };
        final String cidrS[] = new String[] { "192.168.15.7/30", "192.168.15.7/31", "192.168.15.7/32" };
        final int usableAd[] = new int[] { 4, 2, 1 };

        for (int i = 0; i < masks.length; ++i) {
            final SubnetUtils utils = new SubnetUtils(address, masks[i]);
            utils.setInclusiveHostCount(true);
            final SubnetInfo info = utils.getInfo();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertEquals("hi " + masks[i], highA[i], info.getHighAddress());
    }
    }

    public void testZeroAddressAndCidr_1_oe() {
        final SubnetUtils snu = new SubnetUtils("0.0.0.0/0");
        assertNotNull(snu);
    }

    public void testNET521_1_oe() {
        SubnetUtils utils;
        SubnetInfo info;

        utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("0.0.0.0", info.getNetmask());
    }

    public void testNET521_2_oe() {
        SubnetUtils utils;
        SubnetInfo info;

        utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(4294967296L, info.getAddressCountLong());
    }

    public void testNET521_4_oe() {
        SubnetUtils utils;
        SubnetInfo info;

        utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        try {
            info.getAddressCount();
            // removed other assertion
        } catch (final RuntimeException expected) {
            // ignored
        }
        utils = new SubnetUtils("128.0.0.0/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        assertEquals("128.0.0.0", info.getNetmask());
    }

    public void testNET521_5_oe() {
        SubnetUtils utils;
        SubnetInfo info;

        utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        try {
            info.getAddressCount();
            // removed other assertion
        } catch (final RuntimeException expected) {
            // ignored
        }
        utils = new SubnetUtils("128.0.0.0/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        assertEquals(2147483648L, info.getAddressCountLong());
    }

    public void testNET521_7_oe() {
        SubnetUtils utils;
        SubnetInfo info;

        utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        try {
            info.getAddressCount();
            // removed other assertion
        } catch (final RuntimeException expected) {
            // ignored
        }
        utils = new SubnetUtils("128.0.0.0/1");
        utils.setInclusiveHostCount(true);
        info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        try {
            info.getAddressCount();
            // removed other assertion
        } catch (final RuntimeException expected) {
            // ignored
        }
        // if we exclude the broadcast and network addresses, the count is less than Integer.MAX_VALUE
        utils.setInclusiveHostCount(false);
        info = utils.getInfo();
        assertEquals(2147483646, info.getAddressCount());
    }

    public void testNET520_1_oe() {
        final SubnetUtils utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        assertEquals("0.0.0.0",info.getNetworkAddress());
    }

    public void testNET520_2_oe() {
        final SubnetUtils utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        assertEquals("255.255.255.255",info.getBroadcastAddress());
    }

    public void testNET520_3_oe() {
        final SubnetUtils utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        assertTrue(info.isInRange("127.0.0.0"));
    }

    public void testNET520_4_oe() {
        final SubnetUtils utils = new SubnetUtils("0.0.0.0/0");
        utils.setInclusiveHostCount(true);
        final SubnetInfo info = utils.getInfo();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        utils.setInclusiveHostCount(false);
        assertTrue(info.isInRange("127.0.0.0"));
    }

    public void testNET641_1_oe() {
        assertFalse(new SubnetUtils("192.168.1.0/00").getInfo().isInRange("0.0.0.0"));
    }

    public void testNET641_2_oe() {
        // removed other assertion
        assertFalse(new SubnetUtils("192.168.1.0/30").getInfo().isInRange("0.0.0.0"));
    }

    public void testNET641_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse(new SubnetUtils("192.168.1.0/31").getInfo().isInRange("0.0.0.0"));
    }

    public void testNET641_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(new SubnetUtils("192.168.1.0/32").getInfo().isInRange("0.0.0.0"));
    }

}
