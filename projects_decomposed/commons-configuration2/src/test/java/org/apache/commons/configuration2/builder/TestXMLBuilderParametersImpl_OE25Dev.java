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
import static org.junit.Assert.assertSame;

import javax.xml.parsers.DocumentBuilder;
import java.util.Map;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.EntityResolver;

/**
 * Test class for {@code XMLBuilderParametersImpl}.
 *
 */
public class TestXMLBuilderParametersImpl_OE25Dev {
    /** The parameters object to be tested. */
    private XMLBuilderParametersImpl params;

    @Before
    public void setUp() throws Exception {
        params = new XMLBuilderParametersImpl();
    }

    /**
     * Tests whether properties can be set through BeanUtils.
     */

    /**
     * Tests whether properties can be inherited.
     */

    /**
     * Tests whether a document builder can be set.
     */

    /**
     * Tests whether an entity resolver can be set.
     */

    /**
     * Tests whether a public ID can be set.
     */

    /**
     * Tests whether the schema validation flag can be set.
     */

    /**
     * Tests whether a system ID can be set.
     */

    /**
     * Tests whether validating property can be set.
     */

@Test
    public void testBeanPropertiesAccess_1_oe() throws Exception {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        EasyMock.replay(resolver, builder);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        BeanHelper.setProperty(params, "entityResolver", resolver);
        BeanHelper.setProperty(params, "documentBuilder", builder);
        assertEquals("Wrong file name", "test.xml", params.getFileHandler().getFileName());
    }

@Test
    public void testBeanPropertiesAccess_2_oe() throws Exception {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        EasyMock.replay(resolver, builder);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        BeanHelper.setProperty(params, "entityResolver", resolver);
        BeanHelper.setProperty(params, "documentBuilder", builder);
        // removed other assertion
        final Map<String, Object> paramsMap = params.getParameters();
        assertEquals("Wrong exception flag", Boolean.TRUE, paramsMap.get("throwExceptionOnMissing"));
    }

@Test
    public void testBeanPropertiesAccess_3_oe() throws Exception {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        EasyMock.replay(resolver, builder);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        BeanHelper.setProperty(params, "entityResolver", resolver);
        BeanHelper.setProperty(params, "documentBuilder", builder);
        // removed other assertion
        final Map<String, Object> paramsMap = params.getParameters();
        // removed other assertion
        assertSame("Wrong resolver", resolver, paramsMap.get("entityResolver"));
    }

@Test
    public void testBeanPropertiesAccess_4_oe() throws Exception {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        EasyMock.replay(resolver, builder);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.xml");
        BeanHelper.setProperty(params, "entityResolver", resolver);
        BeanHelper.setProperty(params, "documentBuilder", builder);
        // removed other assertion
        final Map<String, Object> paramsMap = params.getParameters();
        // removed other assertion
        // removed other assertion
        assertSame("Wrong builder", builder, paramsMap.get("documentBuilder"));
    }

@Test
    public void testInheritFrom_1_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        params.setDocumentBuilder(builder).setEntityResolver(resolver).setSchemaValidation(true).setValidating(true);
        params.setThrowExceptionOnMissing(true);
        final XMLBuilderParametersImpl params2 = new XMLBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

@Test
    public void testInheritFrom_2_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        params.setDocumentBuilder(builder).setEntityResolver(resolver).setSchemaValidation(true).setValidating(true);
        params.setThrowExceptionOnMissing(true);
        final XMLBuilderParametersImpl params2 = new XMLBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        assertEquals("Entity resolver not set", resolver, parameters.get("entityResolver"));
    }

@Test
    public void testInheritFrom_3_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        params.setDocumentBuilder(builder).setEntityResolver(resolver).setSchemaValidation(true).setValidating(true);
        params.setThrowExceptionOnMissing(true);
        final XMLBuilderParametersImpl params2 = new XMLBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        assertEquals("Document builder not set", builder, parameters.get("documentBuilder"));
    }

@Test
    public void testInheritFrom_4_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        params.setDocumentBuilder(builder).setEntityResolver(resolver).setSchemaValidation(true).setValidating(true);
        params.setThrowExceptionOnMissing(true);
        final XMLBuilderParametersImpl params2 = new XMLBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Validation flag not set", Boolean.TRUE, parameters.get("validating"));
    }

@Test
    public void testInheritFrom_5_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        params.setDocumentBuilder(builder).setEntityResolver(resolver).setSchemaValidation(true).setValidating(true);
        params.setThrowExceptionOnMissing(true);
        final XMLBuilderParametersImpl params2 = new XMLBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Schema flag not set", Boolean.TRUE, parameters.get("schemaValidation"));
    }

@Test
    public void testSetDocumentBuilder_1_oe() {
        final DocumentBuilder builder = EasyMock.createMock(DocumentBuilder.class);
        EasyMock.replay(builder);
        assertSame("Wrong result", params, params.setDocumentBuilder(builder));
    }

@Test
    public void testSetEntityResolver_1_oe() {
        final EntityResolver resolver = EasyMock.createMock(EntityResolver.class);
        EasyMock.replay(resolver);
        assertSame("Wrong result", params, params.setEntityResolver(resolver));
    }

@Test
    public void testSetPublicID_1_oe() {
        final String pubID = "testPublicID";
        assertSame("Wrong result", params, params.setPublicID(pubID));
    }

@Test
    public void testSetSchemaValidation_1_oe() {
        assertSame("Wrong result", params, params.setSchemaValidation(false));
    }

@Test
    public void testSetSystemID_1_oe() {
        final String sysID = "testSystemID";
        assertSame("Wrong result", params, params.setSystemID(sysID));
    }

@Test
    public void testSetValidating_1_oe() {
        assertSame("Wrong result", params, params.setValidating(true));
    }

}
