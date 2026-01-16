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

import java.io.IOException;
import java.util.Map;

/**
 * QueryLoaderTest
 */
public class QueryLoaderTest_OE25Dev extends BaseTestCase {

    private static final String QUERIES =
        "/org/apache/commons/dbutils/TestQueries.properties";

    public void testLoad_1_oe() throws IOException {
        QueryLoader loader = QueryLoader.instance();
        Map<String,String> q = loader.load(QUERIES);
        Map<String,String> q2 = loader.load(QUERIES);
        assertTrue(q == q2); // pointer comparison should return true;
    }

    public void testLoad_2_oe() throws IOException {
        QueryLoader loader = QueryLoader.instance();
        Map<String,String> q = loader.load(QUERIES);
        Map<String,String> q2 = loader.load(QUERIES);
        // removed other assertion
        assertEquals("SELECT * FROM SomeTable", q.get("test.query"));
    }

    public void testLoad_3_oe() throws IOException {
        QueryLoader loader = QueryLoader.instance();
        Map<String,String> q = loader.load(QUERIES);
        Map<String,String> q2 = loader.load(QUERIES);
        // removed other assertion
        // removed other assertion

        loader.unload(QUERIES);
        Map<String,String> q3 = loader.load(QUERIES);
        assertTrue(q != q3); // pointer comparison should return false;
    }

    public void testLoadThrowsIllegalArgumentException_2_oe() throws IOException {

        QueryLoader queryLoader = QueryLoader.instance();

        try {
            queryLoader.load("e");
            // removed other assertion
        } catch(IllegalArgumentException e) {
            assertEquals("e not found.",e.getMessage());
    }
    }

    public void testLoadThrowsIllegalArgumentException_3_oe() throws IOException {

        QueryLoader queryLoader = QueryLoader.instance();

        try {
            queryLoader.load("e");
            // removed other assertion
        } catch(IllegalArgumentException e) {
            // removed other assertion
            assertEquals(QueryLoader.class.getName(), e.getStackTrace()[0].getClassName());
    }
    }

}
