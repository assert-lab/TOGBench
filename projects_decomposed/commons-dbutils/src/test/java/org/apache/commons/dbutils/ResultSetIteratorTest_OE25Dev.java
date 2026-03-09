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
package org.apache.commons.dbutils;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import static org.mockito.Mockito.mock;

/**
 * ResultSetIteratorTest_OE25Dev
 */
public class ResultSetIteratorTest_OE25Dev extends BaseTestCase {

    public void testNext() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        assertTrue(iter.hasNext());
        row = iter.next();
        assertEquals(COLS, row.length);
        assertEquals("1", row[0]);
        assertEquals("2", row[1]);
        assertEquals("THREE", row[2]);

        assertTrue(iter.hasNext());
        row = iter.next();
        assertEquals(COLS, row.length);

        assertEquals("4", row[0]);
        assertEquals("5", row[1]);
        assertEquals("SIX", row[2]);

        assertFalse(iter.hasNext());
    }

    @Test
    public void testRethrowThrowsRuntimeException() {

        ResultSetIterator resultSetIterator = new ResultSetIterator((ResultSet) null);
        Throwable throwable = new Throwable();
        SQLException sQLException = new SQLException(throwable);

        try {
            resultSetIterator.rethrow(sQLException);
            fail("Expecting exception: RuntimeException");
        } catch(RuntimeException e) {
            assertEquals(ResultSetIterator.class.getName(), e.getStackTrace()[0].getClassName());
        }

    }

    @Test
    public void testCreatesResultSetIteratorTakingThreeArgumentsAndCallsRemove() {

        ResultSet resultSet = mock(ResultSet.class);
        ResultSetIterator resultSetIterator = new ResultSetIterator(resultSet,null);
        resultSetIterator.remove();

    }


    public void testNext_1_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        assertTrue(iter.hasNext());
    }

    public void testNext_2_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();
        assertEquals(COLS, row.length);
    }

    public void testNext_3_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();
        assertEquals("1", row[0]);
    }

    public void testNext_4_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();
        assertEquals("2", row[1]);
    }

    public void testNext_5_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();
        assertEquals("THREE", row[2]);
    }

    public void testNext_6_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        assertTrue(iter.hasNext());
    }

    public void testNext_7_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        row = iter.next();
        assertEquals(COLS, row.length);
    }

    public void testNext_8_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        row = iter.next();

        assertEquals("4", row[0]);
    }

    public void testNext_9_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        row = iter.next();

        assertEquals("5", row[1]);
    }

    public void testNext_10_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        row = iter.next();

        assertEquals("SIX", row[2]);
    }

    public void testNext_11_oe() {

        Iterator<Object[]> iter = new ResultSetIterator(this.rs);

        Object[] row = null;
        row = iter.next();

        row = iter.next();


        assertFalse(iter.hasNext());
    }

    @Test
    public void testRethrowThrowsRuntimeException_2_oe() {

        ResultSetIterator resultSetIterator = new ResultSetIterator((ResultSet) null);
        Throwable throwable = new Throwable();
        SQLException sQLException = new SQLException(throwable);

        try {
            resultSetIterator.rethrow(sQLException);
            fail("Expecting exception: RuntimeException");
        } catch(RuntimeException e) {
            assertEquals(ResultSetIterator.class.getName(), e.getStackTrace()[0].getClassName());
    }
    }

}
