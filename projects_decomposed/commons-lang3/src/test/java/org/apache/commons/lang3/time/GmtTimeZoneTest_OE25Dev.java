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
package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for GmtTimeZone
 */
public class GmtTimeZoneTest_OE25Dev {

    @Test
    public void hoursOutOfRange_1_oe() throws Exception {
        try {
    new GmtTimeZone(false, 24, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void minutesOutOfRange_1_oe() throws Exception {
        try {
    new GmtTimeZone(false, 0, 60);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void setRawOffset_1_oe() throws Exception {
        try {
    new GmtTimeZone(false, 0, 0).setRawOffset(0);
    org.junit.jupiter.api.Assertions.fail("UnsupportedOperationException");
} catch (UnsupportedOperationException e) {
}
    }

}
