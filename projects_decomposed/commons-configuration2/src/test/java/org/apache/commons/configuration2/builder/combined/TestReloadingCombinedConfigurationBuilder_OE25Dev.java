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
package org.apache.commons.configuration2.builder.combined;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.CombinedReloadingController;
import org.apache.commons.configuration2.reloading.ReloadingController;
import org.apache.commons.configuration2.reloading.ReloadingControllerSupport;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code ReloadingCombinedConfigurationBuilder}.
 *
 */
public class TestReloadingCombinedConfigurationBuilder_OE25Dev {
    /** The builder to be tested. */
    private ReloadingCombinedConfigurationBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new ReloadingCombinedConfigurationBuilder();
    }

    /**
     * Tests whether the failOnInit flag is passed to the super constructor.
     */

    /**
     * Tests whether initialization parameters are correctly processed.
     */

    /**
     * Tests whether a nested combined configuration definition can be loaded with reloading support.
     */

    /**
     * Tests a definition configuration which does not contain sources with reloading support.
     */

    /**
     * Tests whether the definition builder created by default supports reloading.
     */

    @Test
    public void testInitWithFailOnInitFlag_1_oe() {
        builder = new ReloadingCombinedConfigurationBuilder(null, true);
        assertTrue("Flag not set", builder.isAllowFailOnInit());
    }

    @Test
    public void testInitWithParameters_1_oe() throws ConfigurationException {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setFile(ConfigurationAssert.getTestFile("testDigesterConfiguration.xml"));
        builder = new ReloadingCombinedConfigurationBuilder(params.getParameters());
        final CombinedConfiguration cc = builder.getConfiguration();
        assertTrue("Property not found", cc.getBoolean("test.boolean"));
    }

    @Test
    public void testNestedReloadableSources_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCReloadingNested.xml");
        builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        final Collection<ReloadingController> subControllers = rc.getSubControllers();
        assertEquals("Wrong number of sub controllers", 2, subControllers.size());
    }

    @Test
    public void testNestedReloadableSources_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCReloadingNested.xml");
        builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        final Collection<ReloadingController> subControllers = rc.getSubControllers();
        final ReloadingControllerSupport ccBuilder = (ReloadingControllerSupport) builder.getNamedBuilder("cc");
        assertTrue("Sub controller not found", subControllers.contains(ccBuilder.getReloadingController()));
    }

    @Test
    public void testNestedReloadableSources_3_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCReloadingNested.xml");
        builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        final Collection<ReloadingController> subControllers = rc.getSubControllers();
        final ReloadingControllerSupport ccBuilder = (ReloadingControllerSupport) builder.getNamedBuilder("cc");
        final CombinedReloadingController rc2 = (CombinedReloadingController) ccBuilder.getReloadingController();
        assertEquals("Wrong number of sub controllers (2)", 3, rc2.getSubControllers().size());
    }

    @Test
    public void testNoReloadableSources_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testDigesterConfiguration.xml");
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(new FileBasedConfigurationBuilder<>(XMLConfiguration.class))
            .setDefinitionBuilderParameters(new FileBasedBuilderParametersImpl().setFile(testFile)));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        assertTrue("Got sub reloading controllers", rc.getSubControllers().isEmpty());
    }

    @Test
    public void testReloadableDefinitionBuilder_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testDigesterConfiguration.xml");
        final ReloadingCombinedConfigurationBuilder confBuilder = builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        assertSame("Wrong configured builder instance", builder, confBuilder);
    }

    @Test
    public void testReloadableDefinitionBuilder_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testDigesterConfiguration.xml");
        final ReloadingCombinedConfigurationBuilder confBuilder = builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        final Collection<ReloadingController> subControllers = rc.getSubControllers();
        assertEquals("Wrong number of sub controllers", 1, subControllers.size());
    }

    @Test
    public void testReloadableDefinitionBuilder_3_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testDigesterConfiguration.xml");
        final ReloadingCombinedConfigurationBuilder confBuilder = builder.configure(new FileBasedBuilderParametersImpl().setFile(testFile));
        builder.getConfiguration();
        final CombinedReloadingController rc = (CombinedReloadingController) builder.getReloadingController();
        final Collection<ReloadingController> subControllers = rc.getSubControllers();
        final ReloadingController subctrl = ((ReloadingControllerSupport) builder.getDefinitionBuilder()).getReloadingController();
        assertSame("Wrong sub controller", subctrl, subControllers.iterator().next());
    }

}
