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
package org.apache.commons.imaging.formats.png.transparencyfilters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.imaging.ImagingConstants;
import org.junit.jupiter.api.Test;

public class TransparencyFilterIndexedColorTest_OE25Dev{

    @Test
    public void testFilterWithNegativeAndNegative_2_oe() {
        final byte[] byteArray = ImagingConstants.EMPTY_BYTE_ARRAY;
        final TransparencyFilterIndexedColor transparencyFilterIndexedColor = new TransparencyFilterIndexedColor(byteArray);

        try {
            transparencyFilterIndexedColor.filter((-416), (-398));
            // removed other assertion
        } catch(final Exception e) {
            assertEquals("TransparencyFilterIndexedColor index: -398, bytes.length: 0",e.getMessage());
    }
    }

    @Test
    public void testFilterWithNegativeAndNegative_3_oe() {
        final byte[] byteArray = ImagingConstants.EMPTY_BYTE_ARRAY;
        final TransparencyFilterIndexedColor transparencyFilterIndexedColor = new TransparencyFilterIndexedColor(byteArray);

        try {
            transparencyFilterIndexedColor.filter((-416), (-398));
            // removed other assertion
        } catch(final Exception e) {
            // removed other assertion
            assertEquals(TransparencyFilterIndexedColor.class.getName(), e.getStackTrace()[0].getClassName());
    }
    }

}