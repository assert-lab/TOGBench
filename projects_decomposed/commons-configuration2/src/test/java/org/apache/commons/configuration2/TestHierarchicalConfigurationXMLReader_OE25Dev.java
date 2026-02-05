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

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.jxpath.JXPathContext;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * Test class for HierarchicalConfigurationXMLReader.
 *
 */
public class TestHierarchicalConfigurationXMLReader_OE25Dev {
    private static final String TEST_FILE = ConfigurationAssert.getTestFile("testHierarchicalXMLConfiguration.xml").getAbsolutePath();

    private HierarchicalConfigurationXMLReader<ImmutableNode> parser;

    @Before
    public void setUp() throws Exception {
        final XMLConfiguration config = new XMLConfiguration();
        final FileHandler handler = new FileHandler(config);
        handler.load(TEST_FILE);
        parser = new HierarchicalConfigurationXMLReader<>(config);
    }

@Test
    public void testParse_1_oe() throws Exception {
        final SAXSource source = new SAXSource(parser, new InputSource());
        final DOMResult result = new DOMResult();
        final Transformer trans = TransformerFactory.newInstance().newTransformer();
        trans.transform(source, result);
        final Node root = ((Document) result.getNode()).getDocumentElement();
        final JXPathContext ctx = JXPathContext.newContext(root);

        assertEquals("Wrong name of root element", "database", root.getNodeName());
    }

}
