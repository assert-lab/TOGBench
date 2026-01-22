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
package org.apache.commons.configuration2.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.io.FileBased;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.io.FileLocationStrategy;
import org.apache.commons.configuration2.io.FileSystem;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Test class for {@code FileBasedBuilderParametersImpl}.
 *
 */
public class TestFileBasedBuilderParameters_OE25Dev {
    /**
     * Tests whether reflection-based property access through BeanUtils is possible.
     */

    /**
     * Tests a clone operation.
     */

    /**
     * Tests whether an instance can be created from a map.
     */

    /**
     * Tests fromMap() for null input.
     */

    /**
     * Tests whether fromParameters() can return a default instance if the map does not contain an instance.
     */

    /**
     * Tests whether an instance can be extracted from a parameters map.
     */

    /**
     * Tests fromParameters() if the map does not contain an instance.
     */

    /**
     * Tries to obtain an instance from a null parameters map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFromParametersNull() {
        FileBasedBuilderParametersImpl.fromParameters(null);
    }

    /**
     * Tests whether a map with parameters can be queried.
     */

    /**
     * Tests whether properties can be inherited from another object.
     */

    /**
     * Tests inheritFrom() if no parameters object can be found in the map.
     */

    /**
     * Tests that missing properties in the passed in map are skipped by inheritFrom().
     */

    /**
     * Tests the standard constructor.
     */

    /**
     * Tests whether a file handler is accepted by the constructor.
     */

    /**
     * Tests whether a base path can be set.
     */

    /**
     * Tests whether an encoding can be set.
     */

    /**
     * Tests whether a file can be set.
     */

    /**
     * Tests whether a file name can be set.
     */

    /**
     * Tests whether a file system can be set.
     */

    /**
     * Tests whether a location strategy can be set.
     */

    /**
     * Tests whether a path can be set.
     */

    /**
     * Tests whether a factory for reloading detectors can be set.
     */

    /**
     * Tests whether the refresh delay can be set.
     */

    /**
     * Tests whether a URL can be set.
     */

    @Test
    public void testBeanPropertiesAccess_1_oe() throws Exception {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        assertEquals("File name not set", "test.xml", params.getFileHandler().getFileName());
    }

    @Test
    public void testBeanPropertiesAccess_2_oe() throws Exception {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        // removed other assertion
        final Map<String, Object> map = params.getParameters();
        assertEquals("Property not stored", Boolean.TRUE, map.get("throwExceptionOnMissing"));
    }

    @Test
    public void testClone_1_oe() {
        final FileBased content = EasyMock.createMock(FileBased.class);
        EasyMock.replay(content);
        final FileHandler fh = new FileHandler(content);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl(fh);
        params.setThrowExceptionOnMissing(true);
        params.setFileName("test.xml");
        final FileBasedBuilderParametersImpl clone = params.clone();
        assertEquals("Wrong exception flag", Boolean.TRUE, clone.getParameters().get("throwExceptionOnMissing"));
    }

    @Test
    public void testClone_2_oe() {
        final FileBased content = EasyMock.createMock(FileBased.class);
        EasyMock.replay(content);
        final FileHandler fh = new FileHandler(content);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl(fh);
        params.setThrowExceptionOnMissing(true);
        params.setFileName("test.xml");
        final FileBasedBuilderParametersImpl clone = params.clone();
        // removed other assertion
        assertEquals("File name not copied", "test.xml", clone.getFileHandler().getFileName());
    }

    @Test
    public void testClone_3_oe() {
        final FileBased content = EasyMock.createMock(FileBased.class);
        EasyMock.replay(content);
        final FileHandler fh = new FileHandler(content);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl(fh);
        params.setThrowExceptionOnMissing(true);
        params.setFileName("test.xml");
        final FileBasedBuilderParametersImpl clone = params.clone();
        // removed other assertion
        // removed other assertion
        assertSame("Content not copied", content, clone.getFileHandler().getContent());
    }

    @Test
    public void testClone_4_oe() {
        final FileBased content = EasyMock.createMock(FileBased.class);
        EasyMock.replay(content);
        final FileHandler fh = new FileHandler(content);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl(fh);
        params.setThrowExceptionOnMissing(true);
        params.setFileName("test.xml");
        final FileBasedBuilderParametersImpl clone = params.clone();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotSame("No copy of file handler", params.getFileHandler(), clone.getFileHandler());
    }

    @Test
    public void testFromMap_1_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final Map<String, Object> map = new HashMap<>();
        final String fileName = "someFileName";
        final String basePath = "someBasePath";
        final Long refreshDelay = 20140628222302L;
        map.put("basePath", basePath);
        map.put("fileName", fileName);
        map.put("reloadingDetectorFactory", factory);
        map.put("reloadingRefreshDelay", refreshDelay);

        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(map);
        assertEquals("Wrong base path", basePath, params.getFileHandler().getBasePath());
    }

    @Test
    public void testFromMap_2_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final Map<String, Object> map = new HashMap<>();
        final String fileName = "someFileName";
        final String basePath = "someBasePath";
        final Long refreshDelay = 20140628222302L;
        map.put("basePath", basePath);
        map.put("fileName", fileName);
        map.put("reloadingDetectorFactory", factory);
        map.put("reloadingRefreshDelay", refreshDelay);

        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(map);
        // removed other assertion
        assertEquals("Wrong file name", fileName, params.getFileHandler().getFileName());
    }

    @Test
    public void testFromMap_3_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final Map<String, Object> map = new HashMap<>();
        final String fileName = "someFileName";
        final String basePath = "someBasePath";
        final Long refreshDelay = 20140628222302L;
        map.put("basePath", basePath);
        map.put("fileName", fileName);
        map.put("reloadingDetectorFactory", factory);
        map.put("reloadingRefreshDelay", refreshDelay);

        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(map);
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong detector factory", factory, params.getReloadingDetectorFactory());
    }

    @Test
    public void testFromMap_4_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final Map<String, Object> map = new HashMap<>();
        final String fileName = "someFileName";
        final String basePath = "someBasePath";
        final Long refreshDelay = 20140628222302L;
        map.put("basePath", basePath);
        map.put("fileName", fileName);
        map.put("reloadingDetectorFactory", factory);
        map.put("reloadingRefreshDelay", refreshDelay);

        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong refresh delay", refreshDelay, params.getReloadingRefreshDelay());
    }

    @Test
    public void testFromMapNull_1_oe() {
        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(null);
        assertNull("Got refresh delay", params.getReloadingRefreshDelay());
    }

    @Test
    public void testFromMapNull_2_oe() {
        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromMap(null);
        // removed other assertion
        assertNull("Got a file name", params.getFileHandler().getFileName());
    }

    @Test
    public void testFromParametersDefaultInstance_1_oe() {
        final FileBasedBuilderParametersImpl params = FileBasedBuilderParametersImpl.fromParameters(new HashMap<>(), true);
        assertFalse("Got a location", params.getFileHandler().isLocationDefined());
    }

    @Test
    public void testFromParametersExtract_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        final Map<String, Object> map = params.getParameters();
        assertSame("Wrong parameters", params, FileBasedBuilderParametersImpl.fromParameters(map));
    }

    @Test
    public void testFromParametersNotFound_1_oe() {
        assertNull("Got an instance", FileBasedBuilderParametersImpl.fromParameters(new HashMap<>()));
    }

    @Test
    public void testGetParameters_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(1000L);
        params.setThrowExceptionOnMissing(true);
        final Map<String, Object> map = params.getParameters();
        assertTrue("Object not stored", map.containsValue(params));
    }

    @Test
    public void testGetParameters_2_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(1000L);
        params.setThrowExceptionOnMissing(true);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        assertEquals("Wrong exception flag", Boolean.TRUE, params.getParameters().get("throwExceptionOnMissing"));
    }

    @Test
    public void testInheritFrom_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        assertEquals("Encoding not set", params.getFileHandler().getEncoding(), params2.getFileHandler().getEncoding());
    }

    @Test
    public void testInheritFrom_2_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        assertEquals("File system not set", params.getFileHandler().getFileSystem(), params2.getFileHandler().getFileSystem());
    }

    @Test
    public void testInheritFrom_3_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        // removed other assertion
        assertEquals("Location strategy not set", params.getFileHandler().getLocationStrategy(), params2.getFileHandler().getLocationStrategy());
    }

    @Test
    public void testInheritFrom_4_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Detector factory not set", params.getReloadingDetectorFactory(), params2.getReloadingDetectorFactory());
    }

    @Test
    public void testInheritFrom_5_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Refresh delay not set", params.getReloadingRefreshDelay(), params2.getReloadingRefreshDelay());
    }

    @Test
    public void testInheritFrom_6_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("Path was copied", params2.getFileHandler().getPath());
    }

    @Test
    public void testInheritFrom_7_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        params.setEncoding("ISO-8856-1");
        params.setPath("A path");
        params.setReloadingDetectorFactory(EasyMock.createMock(ReloadingDetectorFactory.class));
        params.setFileSystem(EasyMock.createMock(FileSystem.class));
        params.setLocationStrategy(EasyMock.createMock(FileLocationStrategy.class));
        params.setReloadingRefreshDelay(20160213171737L);
        params.setThrowExceptionOnMissing(true);
        final FileBasedBuilderParametersImpl params2 = new FileBasedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Base properties not set", Boolean.TRUE, params2.getParameters().get("throwExceptionOnMissing"));
    }

    @Test
    public void testInheritFromNoParametersObject_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(20160213211429L);

        params.inheritFrom(new HashMap<>());
        assertNotNull("Properties were overwritten", params.getReloadingRefreshDelay());
    }

    @Test
    public void testInheritFromSkipMissingProperties_1_oe() {
        final String encoding = "UTF-16";
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        final Long refreshDelay = 20160213172611L;
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setEncoding(encoding).setReloadingDetectorFactory(factory)
            .setReloadingRefreshDelay(refreshDelay);

        params.inheritFrom(new FileBasedBuilderParametersImpl().getParameters());
        assertEquals("Encoding overwritten", encoding, params.getFileHandler().getEncoding());
    }

    @Test
    public void testInheritFromSkipMissingProperties_2_oe() {
        final String encoding = "UTF-16";
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        final Long refreshDelay = 20160213172611L;
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setEncoding(encoding).setReloadingDetectorFactory(factory)
            .setReloadingRefreshDelay(refreshDelay);

        params.inheritFrom(new FileBasedBuilderParametersImpl().getParameters());
        // removed other assertion
        assertEquals("Detector factory overwritten", factory, params.getReloadingDetectorFactory());
    }

    @Test
    public void testInheritFromSkipMissingProperties_3_oe() {
        final String encoding = "UTF-16";
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        final Long refreshDelay = 20160213172611L;
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl().setEncoding(encoding).setReloadingDetectorFactory(factory)
            .setReloadingRefreshDelay(refreshDelay);

        params.inheritFrom(new FileBasedBuilderParametersImpl().getParameters());
        // removed other assertion
        // removed other assertion
        assertEquals("Refresh delay overwritten", refreshDelay, params.getReloadingRefreshDelay());
    }

    @Test
    public void testInitDefaults_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertFalse("Got a location", params.getFileHandler().isLocationDefined());
    }

    @Test
    public void testInitDefaults_2_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertNull("Got a refresh delay", params.getReloadingRefreshDelay());
    }

    @Test
    public void testInitFileHandler_1_oe() {
        final FileHandler handler = new FileHandler();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl(handler);
        assertSame("Wrong handler", handler, params.getFileHandler());
    }

    @Test
    public void testSetBasePath_1_oe() {
        final String path = ConfigurationAssert.getTestFile("test.properties").getParentFile().getAbsolutePath();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setBasePath(path));
    }

    @Test
    public void testSetBasePath_2_oe() {
        final String path = ConfigurationAssert.getTestFile("test.properties").getParentFile().getAbsolutePath();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertEquals("Wrong path", path, params.getFileHandler().getBasePath());
    }

    @Test
    public void testSetEncoding_1_oe() {
        final String enc = "ISO-8859-1";
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setEncoding(enc));
    }

    @Test
    public void testSetEncoding_2_oe() {
        final String enc = "ISO-8859-1";
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong encoding", enc, params.getFileHandler().getEncoding());
    }

    @Test
    public void testSetFile_1_oe() {
        final File file = ConfigurationAssert.getTestFile("test.properties").getAbsoluteFile();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setFile(file));
    }

    @Test
    public void testSetFile_2_oe() {
        final File file = ConfigurationAssert.getTestFile("test.properties").getAbsoluteFile();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertEquals("Wrong file", file, params.getFileHandler().getFile());
    }

    @Test
    public void testSetFileName_1_oe() {
        final String name = "testConfig.xml";
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setFileName(name));
    }

    @Test
    public void testSetFileName_2_oe() {
        final String name = "testConfig.xml";
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertEquals("Wrong name", name, params.getFileHandler().getFileName());
    }

    @Test
    public void testSetFileSystem_1_oe() {
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        EasyMock.replay(fs);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setFileSystem(fs));
    }

    @Test
    public void testSetFileSystem_2_oe() {
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        EasyMock.replay(fs);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong file system", fs, params.getFileHandler().getFileSystem());
    }

    @Test
    public void testSetLocationStrategy_1_oe() {
        final FileLocationStrategy strat = EasyMock.createMock(FileLocationStrategy.class);
        EasyMock.replay(strat);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setLocationStrategy(strat));
    }

    @Test
    public void testSetLocationStrategy_2_oe() {
        final FileLocationStrategy strat = EasyMock.createMock(FileLocationStrategy.class);
        EasyMock.replay(strat);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong location strategy", strat, params.getFileHandler().getLocationStrategy());
    }

    @Test
    public void testSetPath_1_oe() {
        final String path = ConfigurationAssert.getTestFile("test.properties").getAbsolutePath();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setPath(path));
    }

    @Test
    public void testSetPath_2_oe() {
        final String path = ConfigurationAssert.getTestFile("test.properties").getAbsolutePath();
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertEquals("Wrong path", path, params.getFileHandler().getPath());
    }

    @Test
    public void testSetReloadingDetectorFactory_1_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertNull("Got a factory", params.getReloadingDetectorFactory());
    }

    @Test
    public void testSetReloadingDetectorFactory_2_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong result", params, params.setReloadingDetectorFactory(factory));
    }

    @Test
    public void testSetReloadingDetectorFactory_3_oe() {
        final ReloadingDetectorFactory factory = EasyMock.createMock(ReloadingDetectorFactory.class);
        EasyMock.replay(factory);
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        // removed other assertion
        assertSame("Factory not set", factory, params.getReloadingDetectorFactory());
    }

    @Test
    public void testSetReloadingRefreshDelay_1_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        final Long delay = 10000L;
        assertSame("Wrong result", params, params.setReloadingRefreshDelay(delay));
    }

    @Test
    public void testSetReloadingRefreshDelay_2_oe() {
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        final Long delay = 10000L;
        // removed other assertion
        assertEquals("Wrong delay", delay, params.getReloadingRefreshDelay());
    }

    @Test
    public void testSetURL_1_oe() {
        final URL url = ConfigurationAssert.getTestURL("test.properties");
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setURL(url));
    }

    @Test
    public void testSetURL_2_oe() {
        final URL url = ConfigurationAssert.getTestURL("test.properties");
        final FileBasedBuilderParametersImpl params = new FileBasedBuilderParametersImpl();
        // removed other assertion
        assertEquals("Wrong URL", url.toExternalForm(), params.getFileHandler().getURL().toExternalForm());
    }

}
