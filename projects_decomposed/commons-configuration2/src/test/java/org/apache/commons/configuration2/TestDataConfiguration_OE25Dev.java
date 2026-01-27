/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.configuration2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.apache.commons.configuration2.convert.DefaultConversionHandler;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConversionException;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import jakarta.mail.internet.InternetAddress;
import junitx.framework.ArrayAssert;
import junitx.framework.ListAssert;

/**
 */
public class TestDataConfiguration_OE25Dev {
    /** Constant for the date pattern used by tests. */
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Returns the expected test date.
     *
     * @return the expected test date
     * @throws ParseException if the date cannot be parsed
     */
    private static Date expectedDate() throws ParseException {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        return format.parse("2004-01-01");
    }

    /** The test instance. */
    private DataConfiguration conf;

    @Before
    public void setUp() throws Exception {
        final BaseConfiguration baseConfig = new BaseConfiguration();
        baseConfig.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        conf = new DataConfiguration(baseConfig);

        // empty value
        conf.addProperty("empty", "");

        // lists of boolean
        conf.addProperty("boolean.list1", "true");
        conf.addProperty("boolean.list1", "false");
        conf.addProperty("boolean.list2", "true, false");
        conf.addProperty("boolean.list3", Boolean.TRUE);
        conf.addProperty("boolean.list3", Boolean.FALSE);
        conf.addPropertyDirect("boolean.list4", new Boolean[] {Boolean.TRUE, Boolean.FALSE});
        conf.addPropertyDirect("boolean.list5", new boolean[] {true, false});
        final List<Object> booleans = new ArrayList<>();
        booleans.add(Boolean.TRUE);
        booleans.add(Boolean.FALSE);
        conf.addProperty("boolean.list6", booleans);
        conf.addProperty("boolean.string", "true");
        conf.addProperty("boolean.object", Boolean.TRUE);
        conf.addProperty("boolean.list.interpolated", "${boolean.string},false");

        // lists of bytes
        conf.addProperty("byte.list1", "1");
        conf.addProperty("byte.list1", "2");
        conf.addProperty("byte.list2", "1, 2");
        conf.addProperty("byte.list3", Byte.valueOf("1"));
        conf.addProperty("byte.list3", Byte.valueOf("2"));
        conf.addPropertyDirect("byte.list4", new Byte[] {Byte.valueOf("1"), Byte.valueOf("2")});
        conf.addPropertyDirect("byte.list5", new byte[] {1, 2});
        final List<Object> bytes = new ArrayList<>();
        bytes.add(Byte.valueOf("1"));
        bytes.add(Byte.valueOf("2"));
        conf.addProperty("byte.list6", bytes);
        conf.addProperty("byte.string", "1");
        conf.addProperty("byte.object", Byte.valueOf("1"));
        conf.addProperty("byte.list.interpolated", "${byte.string},2");

        // lists of shorts
        conf.addProperty("short.list1", "1");
        conf.addProperty("short.list1", "2");
        conf.addProperty("short.list2", "1, 2");
        conf.addProperty("short.list3", Short.valueOf("1"));
        conf.addProperty("short.list3", Short.valueOf("2"));
        conf.addPropertyDirect("short.list4", new Short[] {Short.valueOf("1"), Short.valueOf("2")});
        conf.addPropertyDirect("short.list5", new short[] {1, 2});
        final List<Object> shorts = new ArrayList<>();
        shorts.add(Short.valueOf("1"));
        shorts.add(Short.valueOf("2"));
        conf.addProperty("short.list6", shorts);
        conf.addProperty("short.string", "1");
        conf.addProperty("short.object", Short.valueOf("1"));
        conf.addProperty("short.list.interpolated", "${short.string},2");

        // lists of integers
        conf.addProperty("integer.list1", "1");
        conf.addProperty("integer.list1", "2");
        conf.addProperty("integer.list2", "1, 2");
        conf.addProperty("integer.list3", Integer.valueOf("1"));
        conf.addProperty("integer.list3", Integer.valueOf("2"));
        conf.addPropertyDirect("integer.list4", new Integer[] {Integer.valueOf("1"), Integer.valueOf("2")});
        conf.addPropertyDirect("integer.list5", new int[] {1, 2});
        final List<Object> integers = new ArrayList<>();
        integers.add(Integer.valueOf("1"));
        integers.add(Integer.valueOf("2"));
        conf.addProperty("integer.list6", integers);
        conf.addProperty("integer.string", "1");
        conf.addProperty("integer.object", Integer.valueOf("1"));
        conf.addProperty("integer.list.interpolated", "${integer.string},2");

        // lists of longs
        conf.addProperty("long.list1", "1");
        conf.addProperty("long.list1", "2");
        conf.addProperty("long.list2", "1, 2");
        conf.addProperty("long.list3", Long.valueOf("1"));
        conf.addProperty("long.list3", Long.valueOf("2"));
        conf.addPropertyDirect("long.list4", new Long[] {Long.valueOf("1"), Long.valueOf("2")});
        conf.addPropertyDirect("long.list5", new long[] {1, 2});
        final List<Object> longs = new ArrayList<>();
        longs.add(Long.valueOf("1"));
        longs.add(Long.valueOf("2"));
        conf.addProperty("long.list6", longs);
        conf.addProperty("long.string", "1");
        conf.addProperty("long.object", Long.valueOf("1"));
        conf.addProperty("long.list.interpolated", "${long.string},2");

        // lists of floats
        conf.addProperty("float.list1", "1");
        conf.addProperty("float.list1", "2");
        conf.addProperty("float.list2", "1, 2");
        conf.addProperty("float.list3", Float.valueOf("1"));
        conf.addProperty("float.list3", Float.valueOf("2"));
        conf.addPropertyDirect("float.list4", new Float[] {Float.valueOf("1"), Float.valueOf("2")});
        conf.addPropertyDirect("float.list5", new float[] {1, 2});
        final List<Object> floats = new ArrayList<>();
        floats.add(Float.valueOf("1"));
        floats.add(Float.valueOf("2"));
        conf.addProperty("float.list6", floats);
        conf.addProperty("float.string", "1");
        conf.addProperty("float.object", Float.valueOf("1"));
        conf.addProperty("float.list.interpolated", "${float.string},2");

        // lists of doubles
        conf.addProperty("double.list1", "1");
        conf.addProperty("double.list1", "2");
        conf.addProperty("double.list2", "1, 2");
        conf.addProperty("double.list3", Double.valueOf("1"));
        conf.addProperty("double.list3", Double.valueOf("2"));
        conf.addPropertyDirect("double.list4", new Double[] {Double.valueOf("1"), Double.valueOf("2")});
        conf.addPropertyDirect("double.list5", new double[] {1, 2});
        final List<Object> doubles = new ArrayList<>();
        doubles.add(Double.valueOf("1"));
        doubles.add(Double.valueOf("2"));
        conf.addProperty("double.list6", doubles);
        conf.addProperty("double.string", "1");
        conf.addProperty("double.object", Double.valueOf("1"));
        conf.addProperty("double.list.interpolated", "${double.string},2");

        // lists of big integers
        conf.addProperty("biginteger.list1", "1");
        conf.addProperty("biginteger.list1", "2");
        conf.addProperty("biginteger.list2", "1, 2");
        conf.addProperty("biginteger.list3", new BigInteger("1"));
        conf.addProperty("biginteger.list3", new BigInteger("2"));
        conf.addPropertyDirect("biginteger.list4", new BigInteger[] {new BigInteger("1"), new BigInteger("2")});
        final List<Object> bigintegers = new ArrayList<>();
        bigintegers.add(new BigInteger("1"));
        bigintegers.add(new BigInteger("2"));
        conf.addProperty("biginteger.list6", bigintegers);
        conf.addProperty("biginteger.string", "1");
        conf.addProperty("biginteger.object", new BigInteger("1"));
        conf.addProperty("biginteger.list.interpolated", "${biginteger.string},2");

        // lists of big decimals
        conf.addProperty("bigdecimal.list1", "1");
        conf.addProperty("bigdecimal.list1", "2");
        conf.addProperty("bigdecimal.list2", "1, 2");
        conf.addProperty("bigdecimal.list3", new BigDecimal("1"));
        conf.addProperty("bigdecimal.list3", new BigDecimal("2"));
        conf.addPropertyDirect("bigdecimal.list4", new BigDecimal[] {new BigDecimal("1"), new BigDecimal("2")});
        final List<Object> bigdecimals = new ArrayList<>();
        bigdecimals.add(new BigDecimal("1"));
        bigdecimals.add(new BigDecimal("2"));
        conf.addProperty("bigdecimal.list6", bigdecimals);
        conf.addProperty("bigdecimal.string", "1");
        conf.addProperty("bigdecimal.object", new BigDecimal("1"));
        conf.addProperty("bigdecimal.list.interpolated", "${bigdecimal.string},2");

        // URIs
        final String uri1 = "http://jakarta.apache.org";
        final String uri2 = "http://www.apache.org";
        conf.addProperty("uri.string", uri1);
        conf.addProperty("uri.string.interpolated", "${uri.string}");
        conf.addProperty("uri.object", new URI(uri1));
        conf.addProperty("uri.list1", uri1);
        conf.addProperty("uri.list1", uri2);
        conf.addProperty("uri.list2", uri1 + ", " + uri2);
        conf.addProperty("uri.list3", new URI(uri1));
        conf.addProperty("uri.list3", new URI(uri2));
        conf.addPropertyDirect("uri.list4", new URI[] {new URI(uri1), new URI(uri2)});
        final List<Object> uris = new ArrayList<>();
        uris.add(new URI(uri1));
        uris.add(new URI(uri2));
        conf.addProperty("uri.list6", uris);
        conf.addProperty("uri.list.interpolated", "${uri.string}," + uri2);

        // URLs
        final String url1 = "http://jakarta.apache.org";
        final String url2 = "http://www.apache.org";
        conf.addProperty("url.string", url1);
        conf.addProperty("url.string.interpolated", "${url.string}");
        conf.addProperty("url.object", new URL(url1));
        conf.addProperty("url.list1", url1);
        conf.addProperty("url.list1", url2);
        conf.addProperty("url.list2", url1 + ", " + url2);
        conf.addProperty("url.list3", new URL(url1));
        conf.addProperty("url.list3", new URL(url2));
        conf.addPropertyDirect("url.list4", new URL[] {new URL(url1), new URL(url2)});
        final List<Object> urls = new ArrayList<>();
        urls.add(new URL(url1));
        urls.add(new URL(url2));
        conf.addProperty("url.list6", urls);
        conf.addProperty("url.list.interpolated", "${url.string}," + url2);

        // Locales
        conf.addProperty("locale.string", "fr");
        conf.addProperty("locale.string.interpolated", "${locale.string}");
        conf.addProperty("locale.object", Locale.FRENCH);
        conf.addProperty("locale.list1", "fr");
        conf.addProperty("locale.list1", "de");
        conf.addProperty("locale.list2", "fr, de");
        conf.addProperty("locale.list3", Locale.FRENCH);
        conf.addProperty("locale.list3", Locale.GERMAN);
        conf.addPropertyDirect("locale.list4", new Locale[] {Locale.FRENCH, Locale.GERMAN});
        final List<Object> locales = new ArrayList<>();
        locales.add(Locale.FRENCH);
        locales.add(Locale.GERMAN);
        conf.addProperty("locale.list6", locales);
        conf.addProperty("locale.list.interpolated", "${locale.string},de");

        // Colors
        final String color1 = "FF0000";
        final String color2 = "0000FF";
        conf.addProperty("color.string", color1);
        conf.addProperty("color.string.interpolated", "${color.string}");
        conf.addProperty("color.object", Color.red);
        conf.addProperty("color.list1", color1);
        conf.addProperty("color.list1", color2);
        conf.addProperty("color.list2", color1 + ", " + color2);
        conf.addProperty("color.list3", Color.red);
        conf.addProperty("color.list3", Color.blue);
        conf.addPropertyDirect("color.list4", new Color[] {Color.red, Color.blue});
        final List<Object> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.blue);
        conf.addProperty("color.list6", colors);
        conf.addProperty("color.list.interpolated", "${color.string}," + color2);

        // Dates & Calendars
        final String pattern = DATE_PATTERN;
        final DateFormat format = new SimpleDateFormat(pattern);
        conf.setProperty(DataConfiguration.DATE_FORMAT_KEY, pattern);

        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        conf.addProperty("date.string", "2004-01-01");
        conf.addProperty("date.string.interpolated", "${date.string}");
        conf.addProperty("date.object", date1);
        conf.addProperty("date.list1", "2004-01-01");
        conf.addProperty("date.list1", "2004-12-31");
        conf.addProperty("date.list2", "2004-01-01, 2004-12-31");
        conf.addProperty("date.list3", date1);
        conf.addProperty("date.list3", date2);
        conf.addPropertyDirect("date.list4", new Date[] {date1, date2});
        conf.addPropertyDirect("date.list5", new Calendar[] {calendar1, calendar2});
        final List<Object> dates = new ArrayList<>();
        dates.add(date1);
        dates.add(date2);
        conf.addProperty("date.list6", dates);
        conf.addProperty("date.list.interpolated", "${date.string},2004-12-31");
        conf.addPropertyDirect("date.list7", new String[] {"2004-01-01", "2004-12-31"});

        conf.addProperty("calendar.string", "2004-01-01");
        conf.addProperty("calendar.string.interpolated", "${calendar.string}");
        conf.addProperty("calendar.object", calendar1);
        conf.addProperty("calendar.list1", "2004-01-01");
        conf.addProperty("calendar.list1", "2004-12-31");
        conf.addProperty("calendar.list2", "2004-01-01, 2004-12-31");
        conf.addProperty("calendar.list3", calendar1);
        conf.addProperty("calendar.list3", calendar2);
        conf.addPropertyDirect("calendar.list4", new Calendar[] {calendar1, calendar2});
        conf.addPropertyDirect("calendar.list5", new Date[] {date1, date2});
        final List<Object> calendars = new ArrayList<>();
        calendars.add(date1);
        calendars.add(date2);
        conf.addProperty("calendar.list6", calendars);
        conf.addProperty("calendar.list.interpolated", "${calendar.string},2004-12-31");
        conf.addPropertyDirect("calendar.list7", new String[] {"2004-01-01", "2004-12-31"});

        // host address
        conf.addProperty("ip.string", "127.0.0.1");
        conf.addProperty("ip.string.interpolated", "${ip.string}");
        conf.addProperty("ip.object", InetAddress.getByName("127.0.0.1"));

        // email address
        conf.addProperty("email.string", "dev@test.org");
        conf.addProperty("email.string.interpolated", "${email.string}");
        conf.addProperty("email.object", new InternetAddress("dev@test.org"));
    }

    /**
     * Tests whether properties can be cleared.
     */

    /**
     * Tests the implementation of clearPropertyDirect().
     */

    /**
     * Tests clearPropertyDirect() if the wrapped configuration does not extend AbstractConfiguration.
     */
    @Test
    public void testClearPropertyDirectNoAbstractConf() {
        final Configuration wrapped = EasyMock.createMock(Configuration.class);
        final String key = "test.property";
        wrapped.clearProperty(key);
        EasyMock.replay(wrapped);
        conf = new DataConfiguration(wrapped);
        conf.clearPropertyDirect(key);
        EasyMock.verify(wrapped);
    }

    @Test
    public void testConversionException() throws Exception {
        conf.addProperty("key1", new Object());
        conf.addProperty("key2", "xxxxxx");

        try {
            conf.getBooleanArray("key1");
            fail("getBooleanArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBooleanArray("key2");
            fail("getBooleanArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBooleanList("key1");
            fail("getBooleanList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBooleanList("key2");
            fail("getBooleanList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getByteArray("key1");
            fail("getByteArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getByteArray("key2");
            fail("getByteArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getByteList("key1");
            fail("getByteList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getByteList("key2");
            fail("getByteList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getShortArray("key1");
            fail("getShortArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getShortArray("key2");
            fail("getShortArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getShortList("key1");
            fail("getShortList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getShortList("key2");
            fail("getShortList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getIntArray("key1");
            fail("getIntArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getIntArray("key2");
            fail("getIntArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getIntegerList("key1");
            fail("getIntegerList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getIntegerList("key2");
            fail("getIntegerList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLongArray("key1");
            fail("getLongArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLongArray("key2");
            fail("getLongArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLongList("key1");
            fail("getLongList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLongList("key2");
            fail("getLongList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getFloatArray("key1");
            fail("getFloatArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getFloatArray("key2");
            fail("getFloatArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getFloatList("key1");
            fail("getFloatList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getFloatList("key2");
            fail("getFloatList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDoubleArray("key1");
            fail("getDoubleArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDoubleArray("key2");
            fail("getDoubleArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDoubleList("key1");
            fail("getDoubleList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDoubleList("key2");
            fail("getDoubleList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigIntegerArray("key1");
            fail("getBigIntegerArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigIntegerArray("key2");
            fail("getBigIntegerArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigIntegerList("key1");
            fail("getBigIntegerList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigIntegerList("key2");
            fail("getBigIntegerList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigDecimalArray("key1");
            fail("getBigDecimalArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigDecimalArray("key2");
            fail("getBigDecimalArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigDecimalList("key1");
            fail("getBigDecimalList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getBigDecimalList("key2");
            fail("getBigDecimalList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getURLArray("key1");
            fail("getURLArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getURLArray("key2");
            fail("getURLArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getURLList("key1");
            fail("getURLList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getURLList("key2");
            fail("getURLList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLocaleArray("key1");
            fail("getLocaleArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLocaleArray("key2");
            fail("getLocaleArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLocaleList("key1");
            fail("getLocaleList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getLocaleList("key2");
            fail("getLocaleList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getColorArray("key1");
            fail("getColorArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getColorArray("key2");
            fail("getColorArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getColorList("key1");
            fail("getColorList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getColorList("key2");
            fail("getColorList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDateArray("key1");
            fail("getDateArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDate("key1", DATE_PATTERN);
            fail("getDate didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDate("key2", DATE_PATTERN);
            fail("getDate didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDateArray("key2");
            fail("getDateArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDateList("key1");
            fail("getDateList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getDateList("key2");
            fail("getDateList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendar("key1", DATE_PATTERN);
            fail("getCalendar didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendar("key2", DATE_PATTERN);
            fail("getCalendar didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendarArray("key1");
            fail("getCalendarArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendarArray("key2");
            fail("getCalendarArray didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendarList("key1");
            fail("getCalendarList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.getCalendarList("key2");
            fail("getCalendarList didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.get(InetAddress.class, "key1");
            fail("getInetAddress didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }

        try {
            conf.get(InternetAddress.class, "key1");
            fail("getInternetAddress didn't throw a ConversionException");
        } catch (final ConversionException e) {
            // expected
        }
    }

    /**
     * Tests that the cause of a conversion exception is kept.
     */

    @Test(expected = IllegalArgumentException.class)
    public void testGetArrayInvalidDefaultType() {
        conf.getArray(Boolean.class, "unknownkey", new URL[] {});
    }

    /**
     * Tests a conversion to a Date if no property is set with the date format, and the format is specified in the
     * conversion handler.
     */

    /**
     * Tests a conversion to a Date if no property is set with the date format, and the format is directly passed in.
     */

    @Test(expected = ConversionException.class)
    public void testGetInetAddressInvalidType() {
        conf.setProperty("ip.unknownhost", "foo");
        conf.get(InetAddress.class, "ip.unknownhost");
    }

    @Test(expected = ConversionException.class)
    public void testGetInternetAddressInvalidType() throws Exception {
        final Object expected = new InternetAddress("dev@test.org");
        conf.setProperty("email.invalid", "dev@test@org");
        conf.get(expected.getClass(), "email.invalid");
    }

    @Test(expected = ConversionException.class)
    public void testGetInvalidType() {
        conf.get(Boolean.class, "url.object", null);
    }

    @Test(expected = ConversionException.class)
    public void testGetPrimitiveArrayInvalidType() {
        conf.getArray(Boolean.TYPE, "calendar.list4");
    }

    /**
     * Tests whether a string property can be obtained through get() if no type conversion is required.
     */

    @Test(expected = NoSuchElementException.class)
    public void testGetUnknownException() {
        conf.setThrowExceptionOnMissing(true);
        conf.get(Object.class, "unknownkey");
    }

    @Test
    public void testClearProperty_1_oe() {
        final String key = "test.property";
        conf.addProperty(key, "someValue");
        conf.clearProperty(key);
        assertFalse("Property still found", conf.containsKey(key));
    }

    @Test
    public void testClearPropertyDirect_1_oe() {
        final String key = "test.property";
        conf.addProperty(key, "someValue");
        conf.clearPropertyDirect(key);
        assertFalse("Property still found", conf.containsKey(key));
    }

    @Test
    public void testContainsKey_1_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        assertFalse(conf.containsKey("foo"));
    }

    @Test
    public void testContainsKey_2_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        // removed other assertion

        baseconf.setProperty("foo", "bar");

        assertTrue(conf.containsKey("foo"));
    }

    @Test
    public void testConversionExceptionCause_2_oe() {
        try {
            conf.get(Integer.TYPE, "uri.string");
            // removed other assertion
        } catch (final ConversionException cex) {
            assertTrue("Wrong cause", cex.getCause() instanceof NumberFormatException);
    }
    }

    @Test
    public void testGetBigDecimalArray_1_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        ArrayAssert.assertEquals(defaultValue, conf.getBigDecimalArray("bigdecimal.list", defaultValue));
    }

    @Test
    public void testGetBigDecimalArray_2_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list1"));
    }

    @Test
    public void testGetBigDecimalArray_3_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list2"));
    }

    @Test
    public void testGetBigDecimalArray_4_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list3"));
    }

    @Test
    public void testGetBigDecimalArray_5_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list4"));
    }

    @Test
    public void testGetBigDecimalArray_6_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list6"));
    }

    @Test
    public void testGetBigDecimalArray_7_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getBigDecimalArray("bigdecimal.list.interpolated"));
    }

    @Test
    public void testGetBigDecimalArray_8_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        ArrayAssert.assertEquals(new BigDecimal[] {new BigDecimal("1")}, conf.getBigDecimalArray("bigdecimal.string"));
    }

    @Test
    public void testGetBigDecimalArray_9_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        // removed other assertion
        ArrayAssert.assertEquals(new BigDecimal[] {new BigDecimal("1")}, conf.getBigDecimalArray("bigdecimal.object"));
    }

    @Test
    public void testGetBigDecimalArray_10_oe() {
        // missing list
        final BigDecimal[] defaultValue = {new BigDecimal("2"), new BigDecimal("1")};
        // removed other assertion

        final BigDecimal[] expected = {new BigDecimal("1"), new BigDecimal("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new BigDecimal[] {}, conf.getBigDecimalArray("empty"));
    }

    @Test
    public void testGetBigDecimalList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getBigDecimalList("bigdecimal.list", null));
    }

    @Test
    public void testGetBigDecimalList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list1"));
    }

    @Test
    public void testGetBigDecimalList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list2"));
    }

    @Test
    public void testGetBigDecimalList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list3"));
    }

    @Test
    public void testGetBigDecimalList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list4"));
    }

    @Test
    public void testGetBigDecimalList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list6"));
    }

    @Test
    public void testGetBigDecimalList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.list.interpolated"));
    }

    @Test
    public void testGetBigDecimalList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.string"));
    }

    @Test
    public void testGetBigDecimalList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getBigDecimalList("bigdecimal.object"));
    }

    @Test
    public void testGetBigDecimalList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        expected.add(new BigDecimal("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // array of BigDecimal objects
        // removed other assertion

        // list of BigDecimal objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigDecimal values
        expected = new ArrayList<>();
        expected.add(new BigDecimal("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getBigDecimalList("empty"));
    }

    @Test
    public void testGetBigIntegerArray_1_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        ArrayAssert.assertEquals(defaultValue, conf.getBigIntegerArray("biginteger.list", defaultValue));
    }

    @Test
    public void testGetBigIntegerArray_2_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list1"));
    }

    @Test
    public void testGetBigIntegerArray_3_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list2"));
    }

    @Test
    public void testGetBigIntegerArray_4_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list3"));
    }

    @Test
    public void testGetBigIntegerArray_5_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list4"));
    }

    @Test
    public void testGetBigIntegerArray_6_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list6"));
    }

    @Test
    public void testGetBigIntegerArray_7_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getBigIntegerArray("biginteger.list.interpolated"));
    }

    @Test
    public void testGetBigIntegerArray_8_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        ArrayAssert.assertEquals(new BigInteger[] {new BigInteger("1")}, conf.getBigIntegerArray("biginteger.string"));
    }

    @Test
    public void testGetBigIntegerArray_9_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        // removed other assertion
        ArrayAssert.assertEquals(new BigInteger[] {new BigInteger("1")}, conf.getBigIntegerArray("biginteger.object"));
    }

    @Test
    public void testGetBigIntegerArray_10_oe() {
        // missing list
        final BigInteger[] defaultValue = {new BigInteger("2"), new BigInteger("1")};
        // removed other assertion

        final BigInteger[] expected = {new BigInteger("1"), new BigInteger("2")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new BigInteger[] {}, conf.getBigIntegerArray("empty"));
    }

    @Test
    public void testGetBigIntegerList_1_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        ListAssert.assertEquals(null, bigIntegerList);
    }

    @Test
    public void testGetBigIntegerList_2_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list1"));
    }

    @Test
    public void testGetBigIntegerList_3_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list2"));
    }

    @Test
    public void testGetBigIntegerList_4_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list3"));
    }

    @Test
    public void testGetBigIntegerList_5_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list4"));
    }

    @Test
    public void testGetBigIntegerList_6_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list6"));
    }

    @Test
    public void testGetBigIntegerList_7_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.list.interpolated"));
    }

    @Test
    public void testGetBigIntegerList_8_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.string"));
    }

    @Test
    public void testGetBigIntegerList_9_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getBigIntegerList("biginteger.object"));
    }

    @Test
    public void testGetBigIntegerList_10_oe() {
        // missing list
        final List<BigInteger> bigIntegerList = conf.getBigIntegerList("biginteger.list", null);
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        expected.add(new BigInteger("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // array of BigInteger objects
        // removed other assertion

        // list of BigInteger objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single BigInteger values
        expected = new ArrayList<>();
        expected.add(new BigInteger("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getBigIntegerList("empty"));
    }

    @Test
    public void testGetBooleanArray_1_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        ArrayAssert.assertEquals(defaultValue, conf.getBooleanArray("boolean.list", defaultValue));
    }

    @Test
    public void testGetBooleanArray_2_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list1"));
    }

    @Test
    public void testGetBooleanArray_3_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list2"));
    }

    @Test
    public void testGetBooleanArray_4_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list3"));
    }

    @Test
    public void testGetBooleanArray_5_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list4"));
    }

    @Test
    public void testGetBooleanArray_6_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list5"));
    }

    @Test
    public void testGetBooleanArray_7_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list6"));
    }

    @Test
    public void testGetBooleanArray_8_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getBooleanArray("boolean.list.interpolated"));
    }

    @Test
    public void testGetBooleanArray_9_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        ArrayAssert.assertEquals(new boolean[] {true}, conf.getBooleanArray("boolean.string"));
    }

    @Test
    public void testGetBooleanArray_10_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        // removed other assertion
        ArrayAssert.assertEquals(new boolean[] {true}, conf.getBooleanArray("boolean.object"));
    }

    @Test
    public void testGetBooleanArray_11_oe() {
        // missing list
        final boolean[] defaultValue = {false, true};
        // removed other assertion

        final boolean[] expected = {true, false};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new boolean[] {}, conf.getBooleanArray("empty"));
    }

    @Test
    public void testGetBooleanList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getBooleanList("boolean.list", null));
    }

    @Test
    public void testGetBooleanList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list1"));
    }

    @Test
    public void testGetBooleanList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list2"));
    }

    @Test
    public void testGetBooleanList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list3"));
    }

    @Test
    public void testGetBooleanList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list4"));
    }

    @Test
    public void testGetBooleanList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list5"));
    }

    @Test
    public void testGetBooleanList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list6"));
    }

    @Test
    public void testGetBooleanList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.list.interpolated"));
    }

    @Test
    public void testGetBooleanList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.string"));
    }

    @Test
    public void testGetBooleanList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getBooleanList("boolean.object"));
    }

    @Test
    public void testGetBooleanList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        expected.add(Boolean.FALSE);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // array of Boolean objects
        // removed other assertion

        // array of boolean primitives
        // removed other assertion

        // list of Boolean objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single boolean values
        expected = new ArrayList<>();
        expected.add(Boolean.TRUE);
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getBooleanList("empty"));
    }

    @Test
    public void testGetByteArray_1_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        ArrayAssert.assertEquals(defaultValue, conf.getByteArray("byte.list", defaultValue));
    }

    @Test
    public void testGetByteArray_2_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list1"));
    }

    @Test
    public void testGetByteArray_3_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list2"));
    }

    @Test
    public void testGetByteArray_4_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list3"));
    }

    @Test
    public void testGetByteArray_5_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list4"));
    }

    @Test
    public void testGetByteArray_6_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list5"));
    }

    @Test
    public void testGetByteArray_7_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list6"));
    }

    @Test
    public void testGetByteArray_8_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getByteArray("byte.list.interpolated"));
    }

    @Test
    public void testGetByteArray_9_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        ArrayAssert.assertEquals(new byte[] {1}, conf.getByteArray("byte.string"));
    }

    @Test
    public void testGetByteArray_10_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        // removed other assertion
        ArrayAssert.assertEquals(new byte[] {1}, conf.getByteArray("byte.object"));
    }

    @Test
    public void testGetByteArray_11_oe() {
        // missing list
        final byte[] defaultValue = {1, 2};
        // removed other assertion

        final byte[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new byte[] {}, conf.getByteArray("empty"));
    }

    @Test
    public void testGetByteList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getByteList("byte.list", null));
    }

    @Test
    public void testGetByteList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getByteList("byte.list1"));
    }

    @Test
    public void testGetByteList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getByteList("byte.list2"));
    }

    @Test
    public void testGetByteList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        ListAssert.assertEquals(expected, conf.getByteList("byte.list3"));
    }

    @Test
    public void testGetByteList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        ListAssert.assertEquals(expected, conf.getByteList("byte.list4"));
    }

    @Test
    public void testGetByteList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        ListAssert.assertEquals(expected, conf.getByteList("byte.list5"));
    }

    @Test
    public void testGetByteList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        ListAssert.assertEquals(expected, conf.getByteList("byte.list6"));
    }

    @Test
    public void testGetByteList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getByteList("byte.list.interpolated"));
    }

    @Test
    public void testGetByteList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getByteList("byte.string"));
    }

    @Test
    public void testGetByteList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getByteList("byte.object"));
    }

    @Test
    public void testGetByteList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        expected.add(Byte.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        expected = new ArrayList<>();
        expected.add(Byte.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getByteList("empty"));
    }

    @Test
    public void testGetCalendar_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        assertEquals(defaultValue, conf.getCalendar("calendar", defaultValue));
    }

    @Test
    public void testGetCalendar_2_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        assertNull("non null object for a missing key", conf.getCalendar("unknownkey", DATE_PATTERN));
    }

    @Test
    public void testGetCalendar_4_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getCalendar("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        final Calendar expected = Calendar.getInstance();
        expected.setTime(format.parse("2004-01-01"));

        // Calendar string
        assertEquals(expected, conf.getCalendar("calendar.string"));
    }

    @Test
    public void testGetCalendar_5_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getCalendar("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        final Calendar expected = Calendar.getInstance();
        expected.setTime(format.parse("2004-01-01"));

        // Calendar string
        // removed other assertion
        assertEquals(expected, conf.getCalendar("calendar.string", DATE_PATTERN));
    }

    @Test
    public void testGetCalendar_6_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getCalendar("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        final Calendar expected = Calendar.getInstance();
        expected.setTime(format.parse("2004-01-01"));

        // Calendar string
        // removed other assertion
        // removed other assertion

        // Calendar object
        assertEquals(expected, conf.getCalendar("calendar.object"));
    }

    @Test
    public void testGetCalendar_7_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getCalendar("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        final Calendar expected = Calendar.getInstance();
        expected.setTime(format.parse("2004-01-01"));

        // Calendar string
        // removed other assertion
        // removed other assertion

        // Calendar object
        // removed other assertion

        // Date object
        assertEquals(expected, conf.getCalendar("date.object"));
    }

    @Test
    public void testGetCalendar_8_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);

        // missing Date
        final Calendar defaultValue = Calendar.getInstance();
        defaultValue.setTime(new Date());
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getCalendar("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        final Calendar expected = Calendar.getInstance();
        expected.setTime(format.parse("2004-01-01"));

        // Calendar string
        // removed other assertion
        // removed other assertion

        // Calendar object
        // removed other assertion

        // Date object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.getCalendar("calendar.string.interpolated"));
    }

    @Test
    public void testGetCalendarArray_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        ArrayAssert.assertEquals(defaultValue, conf.getCalendarArray("calendar.list", defaultValue));
    }

    @Test
    public void testGetCalendarArray_2_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list1"));
    }

    @Test
    public void testGetCalendarArray_3_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list2"));
    }

    @Test
    public void testGetCalendarArray_4_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list3"));
    }

    @Test
    public void testGetCalendarArray_5_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list4"));
    }

    @Test
    public void testGetCalendarArray_6_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list5"));
    }

    @Test
    public void testGetCalendarArray_7_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list6"));
    }

    @Test
    public void testGetCalendarArray_8_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getCalendarArray("calendar.list.interpolated"));
    }

    @Test
    public void testGetCalendarArray_9_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        ArrayAssert.assertEquals(new Calendar[] {calendar1}, conf.getCalendarArray("calendar.string"));
    }

    @Test
    public void testGetCalendarArray_10_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        // removed other assertion
        ArrayAssert.assertEquals(new Calendar[] {calendar1}, conf.getCalendarArray("calendar.object"));
    }

    @Test
    public void testGetCalendarArray_11_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final Calendar[] defaultValue = {calendar2, calendar1};
        // removed other assertion

        final Calendar[] expected = {calendar1, calendar2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new Calendar[] {}, conf.getCalendarArray("empty"));
    }

    @Test
    public void testGetCalendarArrayWithFormat_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        final Date date1 = format.parse("01/01/2004");
        final Date date2 = format.parse("12/31/2004");

        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        final Calendar[] expected = {calendar1, calendar2};

        conf.addProperty("calendar.format", "01/01/2004");
        conf.addProperty("calendar.format", "12/31/2004");
        ArrayAssert.assertEquals("Wrong calendars with format", expected, conf.getCalendarArray("calendar.format", "MM/dd/yyyy"));
    }

    @Test
    public void testGetCalendarList_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        ListAssert.assertEquals(null, conf.getCalendarList("calendar.list", nullList));
    }

    @Test
    public void testGetCalendarList_2_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list1"));
    }

    @Test
    public void testGetCalendarList_3_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getList(Calendar.class, "calendar.list1"));
    }

    @Test
    public void testGetCalendarList_4_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list2"));
    }

    @Test
    public void testGetCalendarList_5_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list3"));
    }

    @Test
    public void testGetCalendarList_6_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list4"));
    }

    @Test
    public void testGetCalendarList_7_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list5"));
    }

    @Test
    public void testGetCalendarList_8_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list6"));
    }

    @Test
    public void testGetCalendarList_9_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of strings
        ListAssert.assertEquals(expected, conf.getList(Calendar.class, "calendar.list7"));
    }

    @Test
    public void testGetCalendarList_10_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getCalendarList("calendar.list.interpolated"));
    }

    @Test
    public void testGetCalendarList_11_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        expected = new ArrayList<>();
        expected.add(calendar1);
        ListAssert.assertEquals(expected, conf.getCalendarList("date.string"));
    }

    @Test
    public void testGetCalendarList_12_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        expected = new ArrayList<>();
        expected.add(calendar1);
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getCalendarList("date.object"));
    }

    @Test
    public void testGetCalendarList_13_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");
        final Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        final Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        // missing list
        final List<Calendar> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(calendar1);
        expected.add(calendar2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Calendar values
        expected = new ArrayList<>();
        expected.add(calendar1);
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getCalendarList("empty"));
    }

    @Test
    public void testGetColor_1_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        assertEquals("color", Color.red, conf.getColor("color"));
    }

    @Test
    public void testGetColor_2_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        assertEquals("color", Color.green, conf.getColor("color"));
    }

    @Test
    public void testGetColor_3_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        assertNotNull("null color", color);
    }

    @Test
    public void testGetColor_4_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        assertEquals("red", 1, color.getRed());
    }

    @Test
    public void testGetColor_5_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        // removed other assertion
        assertEquals("green", 3, color.getGreen());
    }

    @Test
    public void testGetColor_6_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("blue", 5, color.getBlue());
    }

    @Test
    public void testGetColor_7_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("alpha", 7, color.getAlpha());
    }

    @Test
    public void testGetColor_8_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // interpolated value
        assertEquals(Color.red, conf.getColor("color.string.interpolated"));
    }

    @Test
    public void testGetColor_9_oe() {
        // RRGGBB
        conf.setProperty("color", "FF0000");
        // removed other assertion

        // #RRGGBB
        conf.setProperty("color", "#00FF00");
        // removed other assertion

        // #RRGGBBAA
        conf.setProperty("color", "#01030507");
        final Color color = conf.getColor("color");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // interpolated value
        // removed other assertion

        // default value
        assertEquals(Color.cyan, conf.getColor("unknownkey", Color.cyan));
    }

    @Test
    public void testGetColorArray_1_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        ArrayAssert.assertEquals(defaultValue, conf.getColorArray("color.list", defaultValue));
    }

    @Test
    public void testGetColorArray_2_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list1"));
    }

    @Test
    public void testGetColorArray_3_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list2"));
    }

    @Test
    public void testGetColorArray_4_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list3"));
    }

    @Test
    public void testGetColorArray_5_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list4"));
    }

    @Test
    public void testGetColorArray_6_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list6"));
    }

    @Test
    public void testGetColorArray_7_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getColorArray("color.list.interpolated"));
    }

    @Test
    public void testGetColorArray_8_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        ArrayAssert.assertEquals(new Color[] {Color.red}, conf.getColorArray("color.string"));
    }

    @Test
    public void testGetColorArray_9_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        // removed other assertion
        ArrayAssert.assertEquals(new Color[] {Color.red}, conf.getColorArray("color.object"));
    }

    @Test
    public void testGetColorArray_10_oe() throws Exception {
        // missing list
        final Color[] defaultValue = {Color.red, Color.blue};
        // removed other assertion

        final Color[] expected = {Color.red, Color.blue};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new Color[] {}, conf.getColorArray("empty"));
    }

    @Test
    public void testGetColorList_1_oe() throws Exception {
        // missing list
        ListAssert.assertEquals(null, conf.getColorList("color.list", null));
    }

    @Test
    public void testGetColorList_2_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        ListAssert.assertEquals(expected, conf.getColorList("color.list1"));
    }

    @Test
    public void testGetColorList_3_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getColorList("color.list2"));
    }

    @Test
    public void testGetColorList_4_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        ListAssert.assertEquals(expected, conf.getColorList("color.list3"));
    }

    @Test
    public void testGetColorList_5_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        ListAssert.assertEquals(expected, conf.getColorList("color.list4"));
    }

    @Test
    public void testGetColorList_6_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        ListAssert.assertEquals(expected, conf.getColorList("color.list6"));
    }

    @Test
    public void testGetColorList_7_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getColorList("color.list.interpolated"));
    }

    @Test
    public void testGetColorList_8_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        expected = new ArrayList<>();
        expected.add(Color.red);
        ListAssert.assertEquals(expected, conf.getColorList("color.string"));
    }

    @Test
    public void testGetColorList_9_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        expected = new ArrayList<>();
        expected.add(Color.red);
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getColorList("color.object"));
    }

    @Test
    public void testGetColorList_10_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Color.red);
        expected.add(Color.blue);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // array of Color objects
        // removed other assertion

        // list of Color objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Color values
        expected = new ArrayList<>();
        expected.add(Color.red);
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getColorList("empty"));
    }

    @Test
    public void testGetConfiguration_1_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        assertEquals("base configuration", baseconf, conf.getConfiguration());
    }

    @Test
    public void testGetDate_1_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        assertEquals(defaultValue, conf.getDate("date", defaultValue));
    }

    @Test
    public void testGetDate_2_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        assertNull("non null object for a missing key", conf.getDate("unknownkey", DATE_PATTERN));
    }

    @Test
    public void testGetDate_4_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getDate("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        // Date string
        assertEquals(expected, conf.getDate("date.string"));
    }

    @Test
    public void testGetDate_5_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getDate("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        // Date string
        // removed other assertion
        assertEquals(expected, conf.getDate("date.string", DATE_PATTERN));
    }

    @Test
    public void testGetDate_6_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getDate("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        // Date string
        // removed other assertion
        // removed other assertion

        // Date object
        assertEquals(expected, conf.getDate("date.object"));
    }

    @Test
    public void testGetDate_7_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getDate("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        // Date string
        // removed other assertion
        // removed other assertion

        // Date object
        // removed other assertion

        // Calendar object
        assertEquals(expected, conf.getDate("calendar.object"));
    }

    @Test
    public void testGetDate_8_oe() throws Exception {
        final Date expected = expectedDate();

        // missing Date
        final Date defaultValue = new Date();
        // removed other assertion
        // removed other assertion

        conf.setThrowExceptionOnMissing(true);

        try {
            conf.getDate("unknownkey", DATE_PATTERN);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // expected
        }

        // Date string
        // removed other assertion
        // removed other assertion

        // Date object
        // removed other assertion

        // Calendar object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.getDate("date.string.interpolated"));
    }

    @Test
    public void testGetDateArray_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        ArrayAssert.assertEquals(defaultValue, conf.getDateArray("date.list", defaultValue));
    }

    @Test
    public void testGetDateArray_2_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list1"));
    }

    @Test
    public void testGetDateArray_3_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list2"));
    }

    @Test
    public void testGetDateArray_4_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list3"));
    }

    @Test
    public void testGetDateArray_5_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list4"));
    }

    @Test
    public void testGetDateArray_6_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list5"));
    }

    @Test
    public void testGetDateArray_7_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list6"));
    }

    @Test
    public void testGetDateArray_8_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getDateArray("date.list.interpolated"));
    }

    @Test
    public void testGetDateArray_9_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        ArrayAssert.assertEquals(new Date[] {date1}, conf.getDateArray("date.string"));
    }

    @Test
    public void testGetDateArray_10_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        // removed other assertion
        ArrayAssert.assertEquals(new Date[] {date1}, conf.getDateArray("date.object"));
    }

    @Test
    public void testGetDateArray_11_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final Date[] defaultValue = {date2, date1};
        // removed other assertion

        final Date[] expected = {date1, date2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new Date[] {}, conf.getDateArray("empty"));
    }

    @Test
    public void testGetDateArrayWithFormat_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        final Date date1 = format.parse("01/01/2004");
        final Date date2 = format.parse("12/31/2004");
        final Date[] expected = {date1, date2};

        conf.addProperty("date.format", "01/01/2004");
        conf.addProperty("date.format", "12/31/2004");
        ArrayAssert.assertEquals("Wrong dates with format", expected, conf.getDateArray("date.format", "MM/dd/yyyy"));
    }

    @Test
    public void testGetDateList_1_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        ListAssert.assertEquals(null, conf.getDateList("date.list", nullList));
    }

    @Test
    public void testGetDateList_2_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        ListAssert.assertEquals(expected, conf.getDateList("date.list1"));
    }

    @Test
    public void testGetDateList_3_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getList(Date.class, "date.list1"));
    }

    @Test
    public void testGetDateList_4_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getDateList("date.list2"));
    }

    @Test
    public void testGetDateList_5_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        ListAssert.assertEquals(expected, conf.getDateList("date.list3"));
    }

    @Test
    public void testGetDateList_6_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        ListAssert.assertEquals(expected, conf.getDateList("date.list4"));
    }

    @Test
    public void testGetDateList_7_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        ListAssert.assertEquals(expected, conf.getDateList("date.list5"));
    }

    @Test
    public void testGetDateList_8_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        ListAssert.assertEquals(expected, conf.getDateList("date.list6"));
    }

    @Test
    public void testGetDateList_9_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of strings
        ListAssert.assertEquals(expected, conf.getList(Date.class, "date.list7"));
    }

    @Test
    public void testGetDateList_10_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getDateList("date.list.interpolated"));
    }

    @Test
    public void testGetDateList_11_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        expected = new ArrayList<>();
        expected.add(date1);
        ListAssert.assertEquals(expected, conf.getDateList("date.string"));
    }

    @Test
    public void testGetDateList_12_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        expected = new ArrayList<>();
        expected.add(date1);
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getDateList("date.object"));
    }

    @Test
    public void testGetDateList_13_oe() throws Exception {
        final DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        final Date date1 = format.parse("2004-01-01");
        final Date date2 = format.parse("2004-12-31");

        // missing list
        final List<Date> nullList = null;
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(date1);
        expected.add(date2);

        // list of strings
        // removed other assertion
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of Date objects
        // removed other assertion

        // list of Calendar objects
        // removed other assertion

        // list of Date objects
        // removed other assertion

        // array of strings
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Date values
        expected = new ArrayList<>();
        expected.add(date1);
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getDateList("empty"));
    }

    @Test
    public void testGetDateNoFormatPropertyConversionHandler_1_oe() throws Exception {
        conf.clearProperty(DataConfiguration.DATE_FORMAT_KEY);
        final DefaultConversionHandler handler = new DefaultConversionHandler();
        handler.setDateFormat(DATE_PATTERN);
        conf.setConversionHandler(handler);
        assertEquals("Wrong result", expectedDate(), conf.getDate("date.string"));
    }

    @Test
    public void testGetDateNoFormatPropertyDirectlySpecified_1_oe() throws Exception {
        conf.clearProperty(DataConfiguration.DATE_FORMAT_KEY);
        assertEquals("Wrong result", expectedDate(), conf.getDate("date.string", DATE_PATTERN));
    }

    @Test
    public void testGetDoubleArray_1_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        ArrayAssert.assertEquals(defaultValue, conf.getDoubleArray("double.list", defaultValue), 0);
    }

    @Test
    public void testGetDoubleArray_2_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list1"), 0);
    }

    @Test
    public void testGetDoubleArray_3_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list2"), 0);
    }

    @Test
    public void testGetDoubleArray_4_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list3"), 0);
    }

    @Test
    public void testGetDoubleArray_5_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list4"), 0);
    }

    @Test
    public void testGetDoubleArray_6_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list5"), 0);
    }

    @Test
    public void testGetDoubleArray_7_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list6"), 0);
    }

    @Test
    public void testGetDoubleArray_8_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getDoubleArray("double.list.interpolated"), 0);
    }

    @Test
    public void testGetDoubleArray_9_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single double values
        ArrayAssert.assertEquals(new double[] {1}, conf.getDoubleArray("double.string"), 0);
    }

    @Test
    public void testGetDoubleArray_10_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single double values
        // removed other assertion
        ArrayAssert.assertEquals(new double[] {1}, conf.getDoubleArray("double.object"), 0);
    }

    @Test
    public void testGetDoubleArray_11_oe() {
        // missing list
        final double[] defaultValue = {2, 1};
        // removed other assertion

        final double[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of , conf.getDoubleArray("empty"), 0);
    }

    @Test
    public void testGetDoubleList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getDoubleList("double.list", null));
    }

    @Test
    public void testGetDoubleList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list1"));
    }

    @Test
    public void testGetDoubleList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list2"));
    }

    @Test
    public void testGetDoubleList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list3"));
    }

    @Test
    public void testGetDoubleList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list4"));
    }

    @Test
    public void testGetDoubleList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list5"));
    }

    @Test
    public void testGetDoubleList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list6"));
    }

    @Test
    public void testGetDoubleList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getDoubleList("double.list.interpolated"));
    }

    @Test
    public void testGetDoubleList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single double values
        expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getDoubleList("double.string"));
    }

    @Test
    public void testGetDoubleList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single double values
        expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getDoubleList("double.object"));
    }

    @Test
    public void testGetDoubleList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        expected.add(Double.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // array of Double objects
        // removed other assertion

        // array of double primitives
        // removed other assertion

        // list of Double objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single double values
        expected = new ArrayList<>();
        expected.add(Double.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getDoubleList("empty"));
    }

    @Test
    public void testGetFloatArray_1_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        ArrayAssert.assertEquals(defaultValue, conf.getFloatArray("float.list", defaultValue), 0);
    }

    @Test
    public void testGetFloatArray_2_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list1"), 0);
    }

    @Test
    public void testGetFloatArray_3_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list2"), 0);
    }

    @Test
    public void testGetFloatArray_4_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list3"), 0);
    }

    @Test
    public void testGetFloatArray_5_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list4"), 0);
    }

    @Test
    public void testGetFloatArray_6_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list5"), 0);
    }

    @Test
    public void testGetFloatArray_7_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list6"), 0);
    }

    @Test
    public void testGetFloatArray_8_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getFloatArray("float.list.interpolated"), 0);
    }

    @Test
    public void testGetFloatArray_9_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        ArrayAssert.assertEquals(new float[] {1}, conf.getFloatArray("float.string"), 0);
    }

    @Test
    public void testGetFloatArray_10_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        // removed other assertion
        ArrayAssert.assertEquals(new float[] {1}, conf.getFloatArray("float.object"), 0);
    }

    @Test
    public void testGetFloatArray_11_oe() {
        // missing list
        final float[] defaultValue = {2, 1};
        // removed other assertion

        final float[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new float[] {}, conf.getFloatArray("empty"), 0);
    }

    @Test
    public void testGetFloatList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getFloatList("float.list", null));
    }

    @Test
    public void testGetFloatList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getFloatList("float.list1"));
    }

    @Test
    public void testGetFloatList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getFloatList("float.list2"));
    }

    @Test
    public void testGetFloatList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        ListAssert.assertEquals(expected, conf.getFloatList("float.list3"));
    }

    @Test
    public void testGetFloatList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        ListAssert.assertEquals(expected, conf.getFloatList("float.list4"));
    }

    @Test
    public void testGetFloatList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        ListAssert.assertEquals(expected, conf.getFloatList("float.list5"));
    }

    @Test
    public void testGetFloatList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        ListAssert.assertEquals(expected, conf.getFloatList("float.list6"));
    }

    @Test
    public void testGetFloatList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getFloatList("float.list.interpolated"));
    }

    @Test
    public void testGetFloatList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getFloatList("float.string"));
    }

    @Test
    public void testGetFloatList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getFloatList("float.object"));
    }

    @Test
    public void testGetFloatList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        expected.add(Float.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // array of Float objects
        // removed other assertion

        // array of float primitives
        // removed other assertion

        // list of Float objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single float values
        expected = new ArrayList<>();
        expected.add(Float.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getFloatList("empty"));
    }

    @Test
    public void testGetInetAddress_1_oe() throws Exception {
        final InetAddress expected = InetAddress.getByName("127.0.0.1");

        // address as string
        assertEquals(expected, conf.get(InetAddress.class, "ip.string"));
    }

    @Test
    public void testGetInetAddress_2_oe() throws Exception {
        final InetAddress expected = InetAddress.getByName("127.0.0.1");

        // address as string
        // removed other assertion

        // address object
        assertEquals(expected, conf.get(InetAddress.class, "ip.object"));
    }

    @Test
    public void testGetInetAddress_3_oe() throws Exception {
        final InetAddress expected = InetAddress.getByName("127.0.0.1");

        // address as string
        // removed other assertion

        // address object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.get(InetAddress.class, "ip.string.interpolated"));
    }

    @Test
    public void testGetIntegerArray_1_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        ArrayAssert.assertEquals(defaultValue, conf.getIntArray("integer.list", defaultValue));
    }

    @Test
    public void testGetIntegerArray_2_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list1"));
    }

    @Test
    public void testGetIntegerArray_3_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list2"));
    }

    @Test
    public void testGetIntegerArray_4_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list3"));
    }

    @Test
    public void testGetIntegerArray_5_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list4"));
    }

    @Test
    public void testGetIntegerArray_6_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list5"));
    }

    @Test
    public void testGetIntegerArray_7_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list6"));
    }

    @Test
    public void testGetIntegerArray_8_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getIntArray("integer.list.interpolated"));
    }

    @Test
    public void testGetIntegerArray_9_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        ArrayAssert.assertEquals(new int[] {1}, conf.getIntArray("integer.string"));
    }

    @Test
    public void testGetIntegerArray_10_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        // removed other assertion
        ArrayAssert.assertEquals(new int[] {1}, conf.getIntArray("integer.object"));
    }

    @Test
    public void testGetIntegerArray_11_oe() {
        // missing list
        final int[] defaultValue = {2, 1};
        // removed other assertion

        final int[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new int[] {}, conf.getIntArray("empty"));
    }

    @Test
    public void testGetIntegerList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getIntegerList("integer.list", null));
    }

    @Test
    public void testGetIntegerList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list1"));
    }

    @Test
    public void testGetIntegerList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list2"));
    }

    @Test
    public void testGetIntegerList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list3"));
    }

    @Test
    public void testGetIntegerList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list4"));
    }

    @Test
    public void testGetIntegerList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list5"));
    }

    @Test
    public void testGetIntegerList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list6"));
    }

    @Test
    public void testGetIntegerList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.list.interpolated"));
    }

    @Test
    public void testGetIntegerList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.string"));
    }

    @Test
    public void testGetIntegerList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getIntegerList("integer.object"));
    }

    @Test
    public void testGetIntegerList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        expected.add(Integer.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // array of Integer objects
        // removed other assertion

        // array of int primitives
        // removed other assertion

        // list of Integer objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single int values
        expected = new ArrayList<>();
        expected.add(Integer.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getIntegerList("empty"));
    }

    @Test
    public void testGetInternetAddress_1_oe() throws Exception {
        final Object expected = new InternetAddress("dev@test.org");

        // address as string
        assertEquals(expected, conf.get(expected.getClass(), "email.string"));
    }

    @Test
    public void testGetInternetAddress_2_oe() throws Exception {
        final Object expected = new InternetAddress("dev@test.org");

        // address as string
        // removed other assertion

        // address object
        assertEquals(expected, conf.get(expected.getClass(), "email.object"));
    }

    @Test
    public void testGetInternetAddress_3_oe() throws Exception {
        final Object expected = new InternetAddress("dev@test.org");

        // address as string
        // removed other assertion

        // address object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.get(expected.getClass(), "email.string.interpolated"));
    }

    @Test
    public void testGetKeys_1_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        baseconf.setProperty("foo", "bar");

        final Iterator<String> it = conf.getKeys();
        assertTrue("the iterator is empty", it.hasNext());
    }

    @Test
    public void testGetKeys_2_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        baseconf.setProperty("foo", "bar");

        final Iterator<String> it = conf.getKeys();
        // removed other assertion
        assertEquals("unique key", "foo", it.next());
    }

    @Test
    public void testGetLocale_1_oe() {
        // language
        conf.setProperty("locale", "fr");
        assertEquals("language", new Locale("fr", ""), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_2_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        assertEquals("language + variant", new Locale("fr", "", "POSIX"), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_3_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        assertEquals("country", new Locale("", "FR"), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_4_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        assertEquals("country + variant", new Locale("", "FR", "WIN"), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_5_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        // removed other assertion

        // language + country
        conf.setProperty("locale", "fr_FR");
        assertEquals("language + country", new Locale("fr", "FR"), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_6_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        // removed other assertion

        // language + country
        conf.setProperty("locale", "fr_FR");
        // removed other assertion

        // language + country + variant
        conf.setProperty("locale", "fr_FR_MAC");
        assertEquals("language + country + variant", new Locale("fr", "FR", "MAC"), conf.getLocale("locale"));
    }

    @Test
    public void testGetLocale_7_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        // removed other assertion

        // language + country
        conf.setProperty("locale", "fr_FR");
        // removed other assertion

        // language + country + variant
        conf.setProperty("locale", "fr_FR_MAC");
        // removed other assertion

        // default value
        conf.setProperty("locale", "fr");
        assertEquals("Existing key with default value", Locale.FRENCH, conf.getLocale("locale", Locale.GERMAN));
    }

    @Test
    public void testGetLocale_8_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        // removed other assertion

        // language + country
        conf.setProperty("locale", "fr_FR");
        // removed other assertion

        // language + country + variant
        conf.setProperty("locale", "fr_FR_MAC");
        // removed other assertion

        // default value
        conf.setProperty("locale", "fr");
        // removed other assertion
        assertEquals("Missing key with default value", Locale.GERMAN, conf.getLocale("localeNotInConfig", Locale.GERMAN));
    }

    @Test
    public void testGetLocale_9_oe() {
        // language
        conf.setProperty("locale", "fr");
        // removed other assertion

        // language + variant
        conf.setProperty("locale", "fr__POSIX");
        // removed other assertion

        // country
        conf.setProperty("locale", "_FR");
        // removed other assertion

        // country + variant
        conf.setProperty("locale", "_FR_WIN");
        // removed other assertion

        // language + country
        conf.setProperty("locale", "fr_FR");
        // removed other assertion

        // language + country + variant
        conf.setProperty("locale", "fr_FR_MAC");
        // removed other assertion

        // default value
        conf.setProperty("locale", "fr");
        // removed other assertion
        // removed other assertion

        // interpolated value
        assertEquals(Locale.FRENCH, conf.getLocale("locale.string.interpolated"));
    }

    @Test
    public void testGetLocaleArray_1_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        ArrayAssert.assertEquals(defaultValue, conf.getLocaleArray("locale.list", defaultValue));
    }

    @Test
    public void testGetLocaleArray_2_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list1"));
    }

    @Test
    public void testGetLocaleArray_3_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list2"));
    }

    @Test
    public void testGetLocaleArray_4_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list3"));
    }

    @Test
    public void testGetLocaleArray_5_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list4"));
    }

    @Test
    public void testGetLocaleArray_6_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list6"));
    }

    @Test
    public void testGetLocaleArray_7_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getLocaleArray("locale.list.interpolated"));
    }

    @Test
    public void testGetLocaleArray_8_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        ArrayAssert.assertEquals(new Locale[] {Locale.FRENCH}, conf.getLocaleArray("locale.string"));
    }

    @Test
    public void testGetLocaleArray_9_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        // removed other assertion
        ArrayAssert.assertEquals(new Locale[] {Locale.FRENCH}, conf.getLocaleArray("locale.object"));
    }

    @Test
    public void testGetLocaleArray_10_oe() throws Exception {
        // missing list
        final Locale[] defaultValue = {Locale.GERMAN, Locale.FRENCH};
        // removed other assertion

        final Locale[] expected = {Locale.FRENCH, Locale.GERMAN};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new Locale[] {}, conf.getLocaleArray("empty"));
    }

    @Test
    public void testGetLocaleList_1_oe() throws Exception {
        // missing list
        ListAssert.assertEquals(null, conf.getLocaleList("locale.list", null));
    }

    @Test
    public void testGetLocaleList_2_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list1"));
    }

    @Test
    public void testGetLocaleList_3_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list2"));
    }

    @Test
    public void testGetLocaleList_4_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list3"));
    }

    @Test
    public void testGetLocaleList_5_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list4"));
    }

    @Test
    public void testGetLocaleList_6_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list6"));
    }

    @Test
    public void testGetLocaleList_7_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.list.interpolated"));
    }

    @Test
    public void testGetLocaleList_8_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.string"));
    }

    @Test
    public void testGetLocaleList_9_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getLocaleList("locale.object"));
    }

    @Test
    public void testGetLocaleList_10_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        expected.add(Locale.GERMAN);

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // array of Locale objects
        // removed other assertion

        // list of Locale objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single Locale values
        expected = new ArrayList<>();
        expected.add(Locale.FRENCH);
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getLocaleList("empty"));
    }

    @Test
    public void testGetLongArray_1_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        ArrayAssert.assertEquals(defaultValue, conf.getLongArray("long.list", defaultValue));
    }

    @Test
    public void testGetLongArray_2_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list1"));
    }

    @Test
    public void testGetLongArray_3_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list2"));
    }

    @Test
    public void testGetLongArray_4_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list3"));
    }

    @Test
    public void testGetLongArray_5_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list4"));
    }

    @Test
    public void testGetLongArray_6_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list5"));
    }

    @Test
    public void testGetLongArray_7_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list6"));
    }

    @Test
    public void testGetLongArray_8_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getLongArray("long.list.interpolated"));
    }

    @Test
    public void testGetLongArray_9_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        ArrayAssert.assertEquals(new long[] {1}, conf.getLongArray("long.string"));
    }

    @Test
    public void testGetLongArray_10_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        // removed other assertion
        ArrayAssert.assertEquals(new long[] {1}, conf.getLongArray("long.object"));
    }

    @Test
    public void testGetLongArray_11_oe() {
        // missing list
        final long[] defaultValue = {2, 1};
        // removed other assertion

        final long[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new long[] {}, conf.getLongArray("empty"));
    }

    @Test
    public void testGetLongList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getLongList("long.list", null));
    }

    @Test
    public void testGetLongList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getLongList("long.list1"));
    }

    @Test
    public void testGetLongList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getLongList("long.list2"));
    }

    @Test
    public void testGetLongList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        ListAssert.assertEquals(expected, conf.getLongList("long.list3"));
    }

    @Test
    public void testGetLongList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        ListAssert.assertEquals(expected, conf.getLongList("long.list4"));
    }

    @Test
    public void testGetLongList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        ListAssert.assertEquals(expected, conf.getLongList("long.list5"));
    }

    @Test
    public void testGetLongList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        ListAssert.assertEquals(expected, conf.getLongList("long.list6"));
    }

    @Test
    public void testGetLongList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getLongList("long.list.interpolated"));
    }

    @Test
    public void testGetLongList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getLongList("long.string"));
    }

    @Test
    public void testGetLongList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getLongList("long.object"));
    }

    @Test
    public void testGetLongList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        expected.add(Long.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // array of Long objects
        // removed other assertion

        // array of long primitives
        // removed other assertion

        // list of Long objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single long values
        expected = new ArrayList<>();
        expected.add(Long.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getLongList("empty"));
    }

    @Test
    public void testGetPropertyWithoutConversion_1_oe() {
        final String key = "test.str";
        final String value = "someTestValue";
        conf.addProperty(key, value);
        assertEquals("Wrong result", value, conf.get(String.class, key));
    }

    @Test
    public void testGetShortArray_1_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        ArrayAssert.assertEquals(defaultValue, conf.getShortArray("short.list", defaultValue));
    }

    @Test
    public void testGetShortArray_2_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list1"));
    }

    @Test
    public void testGetShortArray_3_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list2"));
    }

    @Test
    public void testGetShortArray_4_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list3"));
    }

    @Test
    public void testGetShortArray_5_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list4"));
    }

    @Test
    public void testGetShortArray_6_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list5"));
    }

    @Test
    public void testGetShortArray_7_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list6"));
    }

    @Test
    public void testGetShortArray_8_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getShortArray("short.list.interpolated"));
    }

    @Test
    public void testGetShortArray_9_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        ArrayAssert.assertEquals(new short[] {1}, conf.getShortArray("short.string"));
    }

    @Test
    public void testGetShortArray_10_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        // removed other assertion
        ArrayAssert.assertEquals(new short[] {1}, conf.getShortArray("short.object"));
    }

    @Test
    public void testGetShortArray_11_oe() {
        // missing list
        final short[] defaultValue = {2, 1};
        // removed other assertion

        final short[] expected = {1, 2};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // array of Byte objects
        // removed other assertion

        // array of byte primitives
        // removed other assertion

        // list of Byte objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single byte values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new short[] {}, conf.getShortArray("empty"));
    }

    @Test
    public void testGetShortList_1_oe() {
        // missing list
        ListAssert.assertEquals(null, conf.getShortList("short.list", null));
    }

    @Test
    public void testGetShortList_2_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getShortList("short.list1"));
    }

    @Test
    public void testGetShortList_3_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getShortList("short.list2"));
    }

    @Test
    public void testGetShortList_4_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        ListAssert.assertEquals(expected, conf.getShortList("short.list3"));
    }

    @Test
    public void testGetShortList_5_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        ListAssert.assertEquals(expected, conf.getShortList("short.list4"));
    }

    @Test
    public void testGetShortList_6_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        ListAssert.assertEquals(expected, conf.getShortList("short.list5"));
    }

    @Test
    public void testGetShortList_7_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        // removed other assertion

        // list of Short objects
        ListAssert.assertEquals(expected, conf.getShortList("short.list6"));
    }

    @Test
    public void testGetShortList_8_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getShortList("short.list.interpolated"));
    }

    @Test
    public void testGetShortList_9_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single short values
        expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        ListAssert.assertEquals(expected, conf.getShortList("short.string"));
    }

    @Test
    public void testGetShortList_10_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single short values
        expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getShortList("short.object"));
    }

    @Test
    public void testGetShortList_11_oe() {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        expected.add(Short.valueOf("2"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // array of Short objects
        // removed other assertion

        // array of short primitives
        // removed other assertion

        // list of Short objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single short values
        expected = new ArrayList<>();
        expected.add(Short.valueOf("1"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getShortList("empty"));
    }

    @Test
    public void testGetUnknown_1_oe() {
        assertNull("non null object for a missing key", conf.get(Object.class, "unknownkey"));
    }

    @Test
    public void testGetURI_1_oe() throws Exception {
        // missing URI
        final URI defaultValue = new URI("http://www.google.com");
        assertEquals(defaultValue, conf.getURI("url", defaultValue));
    }

    @Test
    public void testGetURI_2_oe() throws Exception {
        // missing URI
        final URI defaultValue = new URI("http://www.google.com");
        // removed other assertion

        final URI expected = new URI("http://jakarta.apache.org");

        // URI string
        assertEquals(expected, conf.getURI("uri.string"));
    }

    @Test
    public void testGetURI_3_oe() throws Exception {
        // missing URI
        final URI defaultValue = new URI("http://www.google.com");
        // removed other assertion

        final URI expected = new URI("http://jakarta.apache.org");

        // URI string
        // removed other assertion

        // URI object
        assertEquals(expected, conf.getURI("uri.object"));
    }

    @Test
    public void testGetURI_4_oe() throws Exception {
        // missing URI
        final URI defaultValue = new URI("http://www.google.com");
        // removed other assertion

        final URI expected = new URI("http://jakarta.apache.org");

        // URI string
        // removed other assertion

        // URI object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.getURI("uri.string.interpolated"));
    }

    @Test
    public void testGetURIArray_1_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        ArrayAssert.assertEquals(defaultValue, conf.getURIArray("url.list", defaultValue));
    }

    @Test
    public void testGetURIArray_2_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list1"));
    }

    @Test
    public void testGetURIArray_3_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list2"));
    }

    @Test
    public void testGetURIArray_4_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list3"));
    }

    @Test
    public void testGetURIArray_5_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list4"));
    }

    @Test
    public void testGetURIArray_6_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list6"));
    }

    @Test
    public void testGetURIArray_7_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getURIArray("uri.list.interpolated"));
    }

    @Test
    public void testGetURIArray_8_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        ArrayAssert.assertEquals(new URI[] {new URI("http://jakarta.apache.org")}, conf.getURIArray("uri.string"));
    }

    @Test
    public void testGetURIArray_9_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        // removed other assertion
        ArrayAssert.assertEquals(new URI[] {new URI("http://jakarta.apache.org")}, conf.getURIArray("uri.object"));
    }

    @Test
    public void testGetURIArray_10_oe() throws Exception {
        // missing list
        final URI[] defaultValue = {new URI("http://www.apache.org"), new URI("http://jakarta.apache.org")};
        // removed other assertion

        final URI[] expected = {new URI("http://jakarta.apache.org"), new URI("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new URI[] {}, conf.getURIArray("empty"));
    }

    @Test
    public void testGetURIList_1_oe() throws Exception {
        // missing list
        ListAssert.assertEquals(null, conf.getURIList("uri.list", null));
    }

    @Test
    public void testGetURIList_2_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getURIList("uri.list1"));
    }

    @Test
    public void testGetURIList_3_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getURIList("uri.list2"));
    }

    @Test
    public void testGetURIList_4_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        ListAssert.assertEquals(expected, conf.getURIList("uri.list3"));
    }

    @Test
    public void testGetURIList_5_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        ListAssert.assertEquals(expected, conf.getURIList("uri.list4"));
    }

    @Test
    public void testGetURIList_6_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        ListAssert.assertEquals(expected, conf.getURIList("uri.list6"));
    }

    @Test
    public void testGetURIList_7_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getURIList("uri.list.interpolated"));
    }

    @Test
    public void testGetURIList_8_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        ListAssert.assertEquals(expected, conf.getURIList("uri.string"));
    }

    @Test
    public void testGetURIList_9_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getURIList("uri.object"));
    }

    @Test
    public void testGetURIList_10_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        expected.add(new URI("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // array of URI objects
        // removed other assertion

        // list of URI objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URI values
        expected = new ArrayList<>();
        expected.add(new URI("http://jakarta.apache.org"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getURIList("empty"));
    }

    @Test
    public void testGetURL_1_oe() throws Exception {
        // missing URL
        final URL defaultValue = new URL("http://www.google.com");
        assertEquals(defaultValue, conf.getURL("url", defaultValue));
    }

    @Test
    public void testGetURL_2_oe() throws Exception {
        // missing URL
        final URL defaultValue = new URL("http://www.google.com");
        // removed other assertion

        final URL expected = new URL("http://jakarta.apache.org");

        // URL string
        assertEquals(expected, conf.getURL("url.string"));
    }

    @Test
    public void testGetURL_3_oe() throws Exception {
        // missing URL
        final URL defaultValue = new URL("http://www.google.com");
        // removed other assertion

        final URL expected = new URL("http://jakarta.apache.org");

        // URL string
        // removed other assertion

        // URL object
        assertEquals(expected, conf.getURL("url.object"));
    }

    @Test
    public void testGetURL_4_oe() throws Exception {
        // missing URL
        final URL defaultValue = new URL("http://www.google.com");
        // removed other assertion

        final URL expected = new URL("http://jakarta.apache.org");

        // URL string
        // removed other assertion

        // URL object
        // removed other assertion

        // interpolated value
        assertEquals(expected, conf.getURL("url.string.interpolated"));
    }

    @Test
    public void testGetURLArray_1_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        ArrayAssert.assertEquals(defaultValue, conf.getURLArray("url.list", defaultValue));
    }

    @Test
    public void testGetURLArray_2_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list1"));
    }

    @Test
    public void testGetURLArray_3_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list2"));
    }

    @Test
    public void testGetURLArray_4_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list3"));
    }

    @Test
    public void testGetURLArray_5_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list4"));
    }

    @Test
    public void testGetURLArray_6_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list6"));
    }

    @Test
    public void testGetURLArray_7_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        ArrayAssert.assertEquals(expected, conf.getURLArray("url.list.interpolated"));
    }

    @Test
    public void testGetURLArray_8_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        ArrayAssert.assertEquals(new URL[] {new URL("http://jakarta.apache.org")}, conf.getURLArray("url.string"));
    }

    @Test
    public void testGetURLArray_9_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        // removed other assertion
        ArrayAssert.assertEquals(new URL[] {new URL("http://jakarta.apache.org")}, conf.getURLArray("url.object"));
    }

    @Test
    public void testGetURLArray_10_oe() throws Exception {
        // missing list
        final URL[] defaultValue = {new URL("http://www.apache.org"), new URL("http://jakarta.apache.org")};
        // removed other assertion

        final URL[] expected = {new URL("http://jakarta.apache.org"), new URL("http://www.apache.org")};

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        // removed other assertion
        // removed other assertion

        // empty array
        ArrayAssert.assertEquals(new URL[] {}, conf.getURLArray("empty"));
    }

    @Test
    public void testGetURLList_1_oe() throws Exception {
        // missing list
        ListAssert.assertEquals(null, conf.getURLList("url.list", null));
    }

    @Test
    public void testGetURLList_2_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        ListAssert.assertEquals(expected, conf.getURLList("url.list1"));
    }

    @Test
    public void testGetURLList_3_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        ListAssert.assertEquals(expected, conf.getURLList("url.list2"));
    }

    @Test
    public void testGetURLList_4_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        ListAssert.assertEquals(expected, conf.getURLList("url.list3"));
    }

    @Test
    public void testGetURLList_5_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        ListAssert.assertEquals(expected, conf.getURLList("url.list4"));
    }

    @Test
    public void testGetURLList_6_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        ListAssert.assertEquals(expected, conf.getURLList("url.list6"));
    }

    @Test
    public void testGetURLList_7_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        ListAssert.assertEquals(expected, conf.getURLList("url.list.interpolated"));
    }

    @Test
    public void testGetURLList_8_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        ListAssert.assertEquals(expected, conf.getURLList("url.string"));
    }

    @Test
    public void testGetURLList_9_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        // removed other assertion
        ListAssert.assertEquals(expected, conf.getURLList("url.object"));
    }

    @Test
    public void testGetURLList_10_oe() throws Exception {
        // missing list
        // removed other assertion

        List<Object> expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        expected.add(new URL("http://www.apache.org"));

        // list of strings
        // removed other assertion

        // list of strings, comma separated
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // array of URL objects
        // removed other assertion

        // list of URL objects
        // removed other assertion

        // list of interpolated values
        // removed other assertion

        // single URL values
        expected = new ArrayList<>();
        expected.add(new URL("http://jakarta.apache.org"));
        // removed other assertion
        // removed other assertion

        // empty list
        ListAssert.assertEquals(new ArrayList<>(), conf.getURLList("empty"));
    }

    @Test
    public void testIsEmpty_1_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        assertTrue("not empty", conf.isEmpty());
    }

    @Test
    public void testIsEmpty_2_oe() {
        final Configuration baseconf = new BaseConfiguration();
        final DataConfiguration conf = new DataConfiguration(baseconf);

        // removed other assertion

        baseconf.setProperty("foo", "bar");

        assertFalse("empty", conf.isEmpty());
    }

}
