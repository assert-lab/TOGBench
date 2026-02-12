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
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DateUtilsFragmentTest_OE25Dev {

    private static final int months = 7;   // second final prime before 12
    private static final int days = 23;    // second final prime before 31 (and valid)
    private static final int hours = 19;   // second final prime before 24
    private static final int minutes = 53; // second final prime before 60
    private static final int seconds = 47; // third final prime before 60
    private static final int millis = 991; // second final prime before 1000

    private Date aDate;
    private Calendar aCalendar;


    @BeforeEach
    public void setUp() {
        aCalendar = Calendar.getInstance();
        aCalendar.set(2005, months, days, hours, minutes, seconds);
        aCalendar.set(Calendar.MILLISECOND, millis);
        aDate = aCalendar.getTime();
    }

    //Calendar.SECOND as useful fragment

    //Calendar.MINUTE as useful fragment

    //Calendar.DATE and Calendar.DAY_OF_YEAR as useful fragment

    //Calendar.HOUR_OF_DAY as useful fragment

    //Calendar.MONTH as useful fragment

    //Calendar.YEAR as useful fragment

    @Test
    public void testInvalidFragmentWithCalendar_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds(aCalendar, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_2_oe() throws Exception {
        // removed other assertion
        try {
    DateUtils.getFragmentInSeconds(aCalendar, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInMinutes(aCalendar, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInHours(aCalendar, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithCalendar_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInDays(aCalendar, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds(aDate, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_2_oe() throws Exception {
        // removed other assertion
        try {
    DateUtils.getFragmentInSeconds(aDate, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_3_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInMinutes(aDate, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_4_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInHours(aDate, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testInvalidFragmentWithDate_5_oe() throws Exception {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        try {
    DateUtils.getFragmentInDays(aDate, 0);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds((Calendar) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_2_oe() throws Exception {
        // removed other assertion

        try {
    DateUtils.getFragmentInSeconds((Calendar) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInMinutes((Calendar) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInHours((Calendar) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullCalendar_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInDays((Calendar) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testNullDate_1_oe() throws Exception {
        try {
    DateUtils.getFragmentInMilliseconds((Date) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_2_oe() throws Exception {
        // removed other assertion

        try {
    DateUtils.getFragmentInSeconds((Date) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_3_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInMinutes((Date) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_4_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInHours((Date) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testNullDate_5_oe() throws Exception {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        try {
    DateUtils.getFragmentInDays((Date) null, Calendar.MILLISECOND);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
