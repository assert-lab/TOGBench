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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestTimeInfo_OE25Dev {

    @Test(expected=IllegalArgumentException.class)
    public void testException() {
        final NtpV3Packet packet = null;
        new TimeInfo(packet, 1L);
    }

    @Test
    public void testEquals_1_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);
        info.addComment("this is a comment");
        final TimeInfo other = new TimeInfo(packet, returnTime);
        other.addComment("this is a comment");
        Assert.assertEquals(info,other);// fails Assert.assertEquals(info.hashCode(),other.hashCode());
    }

    @Test
    public void testEquals_2_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);
        info.addComment("this is a comment");
        final TimeInfo other = new TimeInfo(packet, returnTime);
        other.addComment("this is a comment");
        other.addComment("another comment");

        final TimeInfo another = new TimeInfo(packet, returnTime, new ArrayList<String>());
        Assert.assertEquals(info, another);
    }

    @Test
    public void testComputeDetails_1_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTimeMillis = System.currentTimeMillis();


        packet.setOriginateTimeStamp(TimeStamp.getNtpTime(returnTimeMillis + 1000));
        packet.setReceiveTimeStamp(packet.getOriginateTimeStamp());
        packet.setTransmitTime(packet.getOriginateTimeStamp());
        packet.setReferenceTime(packet.getOriginateTimeStamp());


        final TimeInfo info = new TimeInfo(packet, returnTimeMillis);
        info.computeDetails();

        Assert.assertSame(packet, info.getMessage());
    }

    @Test
    public void testComputeDetails_2_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTimeMillis = System.currentTimeMillis();


        packet.setOriginateTimeStamp(TimeStamp.getNtpTime(returnTimeMillis + 1000));
        packet.setReceiveTimeStamp(packet.getOriginateTimeStamp());
        packet.setTransmitTime(packet.getOriginateTimeStamp());
        packet.setReferenceTime(packet.getOriginateTimeStamp());


        final TimeInfo info = new TimeInfo(packet, returnTimeMillis);
        info.computeDetails();

        Assert.assertEquals(returnTimeMillis, info.getReturnTime());
    }

    @Test
    public void testComputeDetails_3_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTimeMillis = System.currentTimeMillis();


        packet.setOriginateTimeStamp(TimeStamp.getNtpTime(returnTimeMillis + 1000));
        packet.setReceiveTimeStamp(packet.getOriginateTimeStamp());
        packet.setTransmitTime(packet.getOriginateTimeStamp());
        packet.setReferenceTime(packet.getOriginateTimeStamp());


        final TimeInfo info = new TimeInfo(packet, returnTimeMillis);
        info.computeDetails();

        Assert.assertEquals(Long.valueOf(500), info.getOffset());
    }

    @Test
    public void testComputeDetails_4_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTimeMillis = System.currentTimeMillis();


        packet.setOriginateTimeStamp(TimeStamp.getNtpTime(returnTimeMillis + 1000));
        packet.setReceiveTimeStamp(packet.getOriginateTimeStamp());
        packet.setTransmitTime(packet.getOriginateTimeStamp());
        packet.setReferenceTime(packet.getOriginateTimeStamp());


        final TimeInfo info = new TimeInfo(packet, returnTimeMillis);
        info.computeDetails();

        Assert.assertEquals(Long.valueOf(-1000), info.getDelay());
    }

    @Test
    public void testComputeDetails_5_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTimeMillis = System.currentTimeMillis();


        packet.setOriginateTimeStamp(TimeStamp.getNtpTime(returnTimeMillis + 1000));
        packet.setReceiveTimeStamp(packet.getOriginateTimeStamp());
        packet.setTransmitTime(packet.getOriginateTimeStamp());
        packet.setReferenceTime(packet.getOriginateTimeStamp());


        final TimeInfo info = new TimeInfo(packet, returnTimeMillis);
        info.computeDetails();


        Assert.assertEquals(2, info.getComments().size());
    }

    @Test
    public void testAddress_1_oe() throws UnknownHostException {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, System.currentTimeMillis());
        Assert.assertNull(info.getAddress());
    }

    @Test
    public void testAddress_2_oe() throws UnknownHostException {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, System.currentTimeMillis());
        packet.getDatagramPacket().setAddress(InetAddress.getByAddress("loopback", new byte[]{127, 0, 0, 1}));
        Assert.assertNotNull(info.getAddress());
    }

    @Test
    public void testZeroTime_1_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, 0);
        info.computeDetails();
        Assert.assertNull(info.getDelay());
    }

    @Test
    public void testZeroTime_2_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, 0);
        info.computeDetails();
        Assert.assertNull(info.getOffset());
    }

    @Test
    public void testZeroTime_3_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, 0);
        info.computeDetails();
        Assert.assertEquals(0L, info.getReturnTime());
    }

    @Test
    public void testZeroTime_4_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, 0);
        info.computeDetails();
        final List<String> comments = info.getComments();
        Assert.assertEquals(1, comments.size());
    }

    @Test
    public void testZeroTime_5_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final TimeInfo info = new TimeInfo(packet, 0);
        info.computeDetails();
        final List<String> comments = info.getComments();
        Assert.assertTrue(comments.get(0).contains("zero orig time"));
    }

    @Test
    public void testNotEquals_1_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);

        final NtpV3Packet packet2 = new NtpV3Impl();
        Assert.assertEquals(packet, packet2);
    }

    @Test
    public void testNotEquals_2_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);

        final NtpV3Packet packet2 = new NtpV3Impl();
        final TimeInfo info2 = new TimeInfo(packet2, returnTime + 1);
        Assert.assertFalse(info.equals(info2));
    }

    @Test
    public void testNotEquals_3_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);

        final NtpV3Packet packet2 = new NtpV3Impl();
        final TimeInfo info2 = new TimeInfo(packet2, returnTime + 1);

        packet2.setStratum(3);
        packet2.setRootDelay(25);
        final TimeInfo info3 = new TimeInfo(packet2, returnTime);
        Assert.assertFalse(info.equals(info3));
    }

    @Test
    public void testNotEquals_4_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);

        final NtpV3Packet packet2 = new NtpV3Impl();
        final TimeInfo info2 = new TimeInfo(packet2, returnTime + 1);

        packet2.setStratum(3);
        packet2.setRootDelay(25);
        final TimeInfo info3 = new TimeInfo(packet2, returnTime);

        Object  other = this;
        Assert.assertFalse(info.equals(other));
    }

    @Test
    public void testNotEquals_5_oe() {
        final NtpV3Packet packet = new NtpV3Impl();
        final long returnTime = System.currentTimeMillis();
        final TimeInfo info = new TimeInfo(packet, returnTime);

        final NtpV3Packet packet2 = new NtpV3Impl();
        final TimeInfo info2 = new TimeInfo(packet2, returnTime + 1);

        packet2.setStratum(3);
        packet2.setRootDelay(25);
        final TimeInfo info3 = new TimeInfo(packet2, returnTime);

        Object  other = this;

        other = null;
        Assert.assertFalse(info.equals(other));
    }

}
