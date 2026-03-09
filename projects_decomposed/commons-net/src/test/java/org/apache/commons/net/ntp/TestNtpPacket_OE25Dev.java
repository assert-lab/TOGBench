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
package org.apache.commons.net.ntp;

import java.net.DatagramPacket;

import org.junit.Assert;
import org.junit.Test;

public class TestNtpPacket_OE25Dev {

    // pre-canned NTP packet
    // [version:3, mode:4, poll:4, refId=0x81531472, precision:-17, delay:100, dispersion(ms):51.605224609375,
    // id:129.83.20.114, xmitTime:Thu, May 30 2013 17:46:01.295, etc. ]
    static final byte[] ntpPacket = hexStringToByteArray(
            "1c0304ef0000006400000d3681531472d552447fec1d6000d5524718ac49ba5ed55247194b6d9000d55247194b797000");

    @Test
    public void testCreate() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(-17, message.getPrecision());
        Assert.assertEquals(4, message.getPoll());
        Assert.assertEquals(100, message.getRootDelay());
        Assert.assertEquals(3382, message.getRootDispersion());
        Assert.assertEquals(0x81531472, message.getReferenceId());
        Assert.assertNotNull(message.getReferenceTimeStamp());
        Assert.assertEquals("NTP", message.getType());
        Assert.assertEquals("Server", message.getModeName());
        Assert.assertEquals("129.83.20.114", message.getReferenceIdString());
        Assert.assertEquals(51, message.getRootDispersionInMillis());
        Assert.assertEquals(message.getRootDelay() / 65.536, message.getRootDelayInMillisDouble(), 1e-13);

        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        Assert.assertNotNull(dp);
        Assert.assertEquals(48,dp.getLength());// fixed 48-byte length 
        final NtpV3Packet message2 = new NtpV3Impl();
        final DatagramPacket dp2 = new DatagramPacket(ntpPacket, ntpPacket.length);
        message2.setDatagramPacket(dp2);

        Assert.assertEquals(message2, message);
        Assert.assertEquals(message2.hashCode(), message.hashCode());
        Assert.assertEquals(message2.toString(), message.toString());
    }

    @Test
    public void testCreateAndSetByte0() {
        // LI + VN + Mode all part of first byte -- make sure set order does not matter
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());

        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());

        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());

        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());

        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());

        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);
        message.setMode(4);

        Assert.assertEquals(4, message.getMode());
        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateNtpV4() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(3);
        message.setReferenceId(0x81531472);
        // force hex-string reference id string
        Assert.assertEquals("81531472", message.getReferenceIdString());

        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(1);
        message.setReferenceId(0x55534E4F); // USNO
        // force raw-string reference id string
        Assert.assertEquals("USNO", message.getReferenceIdString());

        message.setReferenceId(0x47505300); // GPS
        Assert.assertEquals("GPS", message.getReferenceIdString());
    }

    @Test
    public void testCreateFromBytes() {
        final NtpV3Packet message = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message.setDatagramPacket(dp);
        Assert.assertEquals(4, message.getMode());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateFromBadPacket() {
        final NtpV3Packet message = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length-4); // drop 4-bytes from packet
        message.setDatagramPacket(dp);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateFromNullPacket() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setDatagramPacket(null);
    }

    @Test
    public void testEquals() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);
        Assert.assertEquals("hashCode", message1.hashCode(), message2.hashCode());
        Assert.assertEquals(message1, message2);

        // now change the packet to force equals() => false
        message2.setMode(2);
        Assert.assertTrue(message1.getMode() != message2.getMode());
        Assert.assertFalse(message1.equals(message2));

        final NtpV3Packet message3 = null;
        Assert.assertFalse(message1.equals(message3));
    }

    private static byte[] hexStringToByteArray(final String s) {
        final int len = s.length();
        final byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    @Test
    public void testCreate_1_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(-17, message.getPrecision());
    }

    @Test
    public void testCreate_2_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(4, message.getPoll());
    }

    @Test
    public void testCreate_3_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(100, message.getRootDelay());
    }

    @Test
    public void testCreate_4_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(3382, message.getRootDispersion());
    }

    @Test
    public void testCreate_5_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(0x81531472, message.getReferenceId());
    }

    @Test
    public void testCreate_6_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertNotNull(message.getReferenceTimeStamp());
    }

    @Test
    public void testCreate_7_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals("NTP", message.getType());
    }

    @Test
    public void testCreate_8_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals("Server", message.getModeName());
    }

    @Test
    public void testCreate_9_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals("129.83.20.114", message.getReferenceIdString());
    }

    @Test
    public void testCreate_10_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(51, message.getRootDispersionInMillis());
    }

    @Test
    public void testCreate_11_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));

        Assert.assertEquals(message.getRootDelay() / 65.536, message.getRootDelayInMillisDouble(), 1e-13);
    }

    @Test
    public void testCreate_12_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));


        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        Assert.assertNotNull(dp);
    }

    @Test
    public void testCreate_13_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));


        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        Assert.assertEquals(48,dp.getLength());// fixed 48-byte length;
    }

    @Test
    public void testCreate_14_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));


        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        final NtpV3Packet message2 = new NtpV3Impl();
        final DatagramPacket dp2 = new DatagramPacket(ntpPacket, ntpPacket.length);
        message2.setDatagramPacket(dp2);

        Assert.assertEquals(message2, message);
    }

    @Test
    public void testCreate_15_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));


        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        final NtpV3Packet message2 = new NtpV3Impl();
        final DatagramPacket dp2 = new DatagramPacket(ntpPacket, ntpPacket.length);
        message2.setDatagramPacket(dp2);

        Assert.assertEquals(message2.hashCode(), message.hashCode());
    }

    @Test
    public void testCreate_16_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setLeapIndicator(0);             // byte 0 [bit numbers 7-6]
        message.setVersion(NtpV3Packet.VERSION_3); // byte 0 [bit numbers 5-4]
        message.setMode(4);                         // byte 0 [bit numbers 3-0]
        message.setStratum(3);                     // byte 1
        message.setPoll(4);                          // byte 2
        message.setPrecision(-17);                 // byte 3
        message.setRootDelay(100);                 // bytes 4-7
        message.setRootDispersion(3382);         // bytes 8-11
        message.setReferenceId(0x81531472);         // byte 12-15
        message.setReferenceTime(new TimeStamp(0xd552447fec1d6000L));
        message.setOriginateTimeStamp(new TimeStamp(0xd5524718ac49ba5eL));
        message.setReceiveTimeStamp(new TimeStamp(0xd55247194b6d9000L));
        message.setTransmitTime(new TimeStamp(0xd55247194b797000L));


        final DatagramPacket dp = message.getDatagramPacket(); // this creates a new datagram
        final NtpV3Packet message2 = new NtpV3Impl();
        final DatagramPacket dp2 = new DatagramPacket(ntpPacket, ntpPacket.length);
        message2.setDatagramPacket(dp2);

        Assert.assertEquals(message2.toString(), message.toString());
    }

    @Test
    public void testCreateAndSetByte0_1_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_2_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_3_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateAndSetByte0_4_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_5_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_6_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateAndSetByte0_7_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_8_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_9_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateAndSetByte0_10_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_11_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_12_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateAndSetByte0_13_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_14_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_15_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateAndSetByte0_16_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);
        message.setMode(4);

        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testCreateAndSetByte0_17_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);
        message.setMode(4);

        Assert.assertEquals(NtpV3Packet.VERSION_3, message.getVersion());
    }

    @Test
    public void testCreateAndSetByte0_18_oe() {
        final NtpV3Packet message = new NtpV3Impl();

        message.setLeapIndicator(2);
        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);


        message.setMode(4);
        message.setLeapIndicator(2);
        message.setVersion(NtpV3Packet.VERSION_3);


        message.setMode(4);
        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setMode(4);
        message.setLeapIndicator(2);


        message.setVersion(NtpV3Packet.VERSION_3);
        message.setLeapIndicator(2);
        message.setMode(4);

        Assert.assertEquals(2, message.getLeapIndicator());
    }

    @Test
    public void testCreateNtpV4_1_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(3);
        message.setReferenceId(0x81531472);
        Assert.assertEquals("81531472", message.getReferenceIdString());
    }

    @Test
    public void testCreateNtpV4_2_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(3);
        message.setReferenceId(0x81531472);

        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(1);
        message.setReferenceId(0x55534E4F); // USNO
        Assert.assertEquals("USNO", message.getReferenceIdString());
    }

    @Test
    public void testCreateNtpV4_3_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(3);
        message.setReferenceId(0x81531472);

        message.setVersion(NtpV3Packet.VERSION_4);
        message.setStratum(1);
        message.setReferenceId(0x55534E4F); // USNO

        message.setReferenceId(0x47505300); // GPS
        Assert.assertEquals("GPS", message.getReferenceIdString());
    }

    @Test
    public void testCreateFromBytes_1_oe() {
        final NtpV3Packet message = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message.setDatagramPacket(dp);
        Assert.assertEquals(4, message.getMode());
    }

    @Test
    public void testEquals_1_oe() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);
        Assert.assertEquals("hashCode", message1.hashCode(), message2.hashCode());
    }

    @Test
    public void testEquals_2_oe() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);
        Assert.assertEquals(message1, message2);
    }

    @Test
    public void testEquals_3_oe() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);

        message2.setMode(2);
        Assert.assertTrue(message1.getMode() != message2.getMode());
    }

    @Test
    public void testEquals_4_oe() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);

        message2.setMode(2);
        Assert.assertFalse(message1.equals(message2));
    }

    @Test
    public void testEquals_5_oe() {
        final NtpV3Packet message1 = new NtpV3Impl();
        final DatagramPacket dp = new DatagramPacket(ntpPacket, ntpPacket.length);
        message1.setDatagramPacket(dp);
        final NtpV3Packet message2 = new NtpV3Impl();
        message2.setDatagramPacket(dp);

        message2.setMode(2);

        final NtpV3Packet message3 = null;
        Assert.assertFalse(message1.equals(message3));
    }

}
