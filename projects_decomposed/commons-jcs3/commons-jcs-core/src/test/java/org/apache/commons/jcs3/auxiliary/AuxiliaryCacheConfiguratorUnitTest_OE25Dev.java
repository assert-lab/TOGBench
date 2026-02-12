package org.apache.commons.jcs3.auxiliary;

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

import org.apache.commons.jcs3.engine.control.MockElementSerializer;
import org.apache.commons.jcs3.engine.logging.MockCacheEventLogger;
import org.apache.commons.jcs3.engine.behavior.IElementSerializer;
import org.apache.commons.jcs3.utils.serialization.StandardSerializer;

import java.util.Properties;

/** Unit tests for the auxiliary cache configurator. */
public class AuxiliaryCacheConfiguratorUnitTest_OE25Dev
    extends TestCase
{
    /**
     * Verify that we don't get an error.
     */

    /**
     * Verify that we don't get an error.
     */

    /**
     * Verify that we can parse the event logger.
     */

    /**
     * Verify that we can parse the ElementSerializer.
     */

    /**
     * Verify that we can parse the ElementSerializer.
     */

    public void testParseCacheEventLogger_Null_1_oe()
    {
        // SETUP
        final Properties props = new Properties();

        // DO WORK
        final MockCacheEventLogger result = (MockCacheEventLogger) AuxiliaryCacheConfigurator.parseCacheEventLogger( props,
                                                                                                               "junk" );

        // VERIFY
        assertNull( "Should not have a logger.", result );
    }

    public void testParseCacheEventLogger_NullName_1_oe()
    {
        // SETUP
        final Properties props = new Properties();

        // DO WORK
        final MockCacheEventLogger result = (MockCacheEventLogger) AuxiliaryCacheConfigurator.parseCacheEventLogger( props,
                                                                                                               null );

        // VERIFY
        assertNull( "Should not have a logger.", result );
    }

    public void testParseCacheEventLogger_Normal_1_oe()
    {
        // SETUP
        final String auxPrefix = "jcs.auxiliary." + "MYAux";
        final String testPropertyValue = "This is the value";
        final String className = MockCacheEventLogger.class.getName();

        final Properties props = new Properties();
        props.put( auxPrefix + AuxiliaryCacheConfigurator.CACHE_EVENT_LOGGER_PREFIX, className );
        props.put( auxPrefix + AuxiliaryCacheConfigurator.CACHE_EVENT_LOGGER_PREFIX
            + AuxiliaryCacheConfigurator.ATTRIBUTE_PREFIX + ".testProperty", testPropertyValue );

        // DO WORK
        final MockCacheEventLogger result = (MockCacheEventLogger) AuxiliaryCacheConfigurator
            .parseCacheEventLogger( props, auxPrefix );

        // VERIFY
        assertNotNull( "Should have a logger.", result );
    }

    public void testParseCacheEventLogger_Normal_2_oe()
    {
        // SETUP
        final String auxPrefix = "jcs.auxiliary." + "MYAux";
        final String testPropertyValue = "This is the value";
        final String className = MockCacheEventLogger.class.getName();

        final Properties props = new Properties();
        props.put( auxPrefix + AuxiliaryCacheConfigurator.CACHE_EVENT_LOGGER_PREFIX, className );
        props.put( auxPrefix + AuxiliaryCacheConfigurator.CACHE_EVENT_LOGGER_PREFIX
            + AuxiliaryCacheConfigurator.ATTRIBUTE_PREFIX + ".testProperty", testPropertyValue );

        // DO WORK
        final MockCacheEventLogger result = (MockCacheEventLogger) AuxiliaryCacheConfigurator
            .parseCacheEventLogger( props, auxPrefix );

        // VERIFY
        // removed other assertion
        assertEquals( "Property should be set.", testPropertyValue, result.getTestProperty() );
    }

    public void testParseElementSerializer_Normal_1_oe()
    {
        // SETUP
        final String auxPrefix = "jcs.auxiliary." + "MYAux";
        final String testPropertyValue = "This is the value";
        final String className = MockElementSerializer.class.getName();

        final Properties props = new Properties();
        props.put( auxPrefix + AuxiliaryCacheConfigurator.SERIALIZER_PREFIX, className );
        props.put( auxPrefix + AuxiliaryCacheConfigurator.SERIALIZER_PREFIX
            + AuxiliaryCacheConfigurator.ATTRIBUTE_PREFIX + ".testProperty", testPropertyValue );

        // DO WORK
        final MockElementSerializer result = (MockElementSerializer) AuxiliaryCacheConfigurator
            .parseElementSerializer( props, auxPrefix );

        // VERIFY
        assertNotNull( "Should have a Serializer.", result );
    }

    public void testParseElementSerializer_Normal_2_oe()
    {
        // SETUP
        final String auxPrefix = "jcs.auxiliary." + "MYAux";
        final String testPropertyValue = "This is the value";
        final String className = MockElementSerializer.class.getName();

        final Properties props = new Properties();
        props.put( auxPrefix + AuxiliaryCacheConfigurator.SERIALIZER_PREFIX, className );
        props.put( auxPrefix + AuxiliaryCacheConfigurator.SERIALIZER_PREFIX
            + AuxiliaryCacheConfigurator.ATTRIBUTE_PREFIX + ".testProperty", testPropertyValue );

        // DO WORK
        final MockElementSerializer result = (MockElementSerializer) AuxiliaryCacheConfigurator
            .parseElementSerializer( props, auxPrefix );

        // VERIFY
        // removed other assertion
        assertEquals( "Property should be set.", testPropertyValue, result.getTestProperty() );
    }

    public void testParseElementSerializer_Null_1_oe()
    {
        // SETUP
        final Properties props = new Properties();

        // DO WORK
        final IElementSerializer result = AuxiliaryCacheConfigurator
            .parseElementSerializer( props, "junk" );

        // VERIFY
        assertTrue( "Should have the default Serializer.", result instanceof StandardSerializer );
    }

}
