package org.apache.commons.jcs3.utils.serialization;



/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import junit.framework.TestCase;

/**
 * Tests the standard serializer.
 *<p>
 * @author Aaron Smuts
 */
public class StandardSerializerUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Test simple back and forth with a string.
     *<p>
     * @throws Exception
     */

    /**
     * Test serialization with a null object. Verify that we don't get an error.
     *<p>
     * @throws Exception
     */

    /**
     * Test simple back and forth with a string.
     *<p>
     * @throws Exception
     */

    public void testSimpleBackAndForth_1_oe()
        throws Exception
    {
        final StandardSerializer serializer = new StandardSerializer();

        final String before = "adsfdsafdsafdsafdsafdsafdsafdsagfdsafdsafdsfdsafdsafsa333 31231";

        final String after = (String) serializer.deSerialize( serializer.serialize( before ), null );

        assertEquals( "Before and after should be the same.", before, after );
    }

    public void testNullInput_1_oe()
        throws Exception
    {
        final StandardSerializer serializer = new StandardSerializer();

        final String before = null;

        final byte[] serialized = serializer.serialize( before );

        final String after = (String) serializer.deSerialize( serialized, null );

        assertNull( "Should have nothing.", after );
    }

    public void testBigStringBackAndForth_1_oe()
        throws Exception
    {
        final StandardSerializer serializer = new StandardSerializer();

        final String string = "This is my big string ABCDEFGH";
        final StringBuilder sb = new StringBuilder();
        sb.append( string );
        for ( int i = 0; i < 4; i++ )
        {
            sb.append( " " + i + sb.toString() ); // big string
        }
        final String before = sb.toString();

        final String after = (String) serializer.deSerialize( serializer.serialize( before ), null );

        assertEquals( "Before and after should be the same.", before, after );
    }

}
