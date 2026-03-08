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
package org.apache.commons.imaging.formats.jpeg.segments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class SosSegmentTest_OE25Dev{

  @Test
  public void testGetComponentsTakingNoArguments() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);
      sosSegment.getComponents();

      assertEquals(0, sosSegment.successiveApproximationBitHigh);
      assertEquals(0, sosSegment.successiveApproximationBitLow);
  }

  @Test
  public void testCreatesSosSegmentTakingThreeArguments() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);

      assertEquals(0, sosSegment.successiveApproximationBitLow);
      assertEquals(0, sosSegment.successiveApproximationBitHigh);

      assertEquals("[Segment: SOS (Unknown)]", sosSegment.toString());
  }

  @Test
  public void testGetComponentsTakingNoArguments_1_oe() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);
      sosSegment.getComponents();

      assertEquals(0, sosSegment.successiveApproximationBitHigh);
  }

  @Test
  public void testGetComponentsTakingNoArguments_2_oe() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);
      sosSegment.getComponents();

      // removed other assertion
      assertEquals(0, sosSegment.successiveApproximationBitLow);
  }

  @Test
  public void testCreatesSosSegmentTakingThreeArguments_1_oe() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);

      assertEquals(0, sosSegment.successiveApproximationBitLow);
  }

  @Test
  public void testCreatesSosSegmentTakingThreeArguments_2_oe() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);

      // removed other assertion
      assertEquals(0, sosSegment.successiveApproximationBitHigh);
  }

  @Test
  public void testCreatesSosSegmentTakingThreeArguments_3_oe() throws IOException {
      final byte[] byteArray = new byte[5];
      final SosSegment sosSegment = new SosSegment((-1044), byteArray);

      // removed other assertion
      // removed other assertion

      assertEquals("[Segment: SOS (Unknown)]", sosSegment.toString());
  }

}