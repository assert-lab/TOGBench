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
package org.apache.commons.configuration2.tree.xpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;

import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.NodeStructureHelper;
import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.compiler.ProcessingInstructionTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ConfigurationNodeIteratorChildren.
 *
 */
public class TestConfigurationNodeIteratorChildren_OE25Dev extends AbstractXPathTest {
    /** Constant for a namespace prefix. */
    private static final String PREFIX = "commons";

    /** Constant for the name of a node with a namespace. */
    private static final String PREFIX_NODE = "configuration";

    /** Stores the node pointer to the root node. */
    private ConfigurationNodePointer<ImmutableNode> rootPointer;

    /**
     * Helper method for checking the values of the nodes returned by an iterator. Because the values indicate the order of
     * the child nodes with this test it can be checked whether the nodes were returned in the correct order.
     *
     * @param iterator the iterator
     * @param expectedIndices an array with the expected indices
     */
    private void checkValues(final NodeIterator iterator, final int... expectedIndices) {
        final List<NodePointer> nodes = iterationElements(iterator);
        for (int i = 0; i < expectedIndices.length; i++) {
            final ImmutableNode child = (ImmutableNode) nodes.get(i).getImmediateNode();
            assertTrue("Wrong index value for child " + i, child.getValue().toString().endsWith(String.valueOf(expectedIndices[i])));
        }
    }

    /**
     * Helper method for creating a node pointer for a given node.
     *
     * @param node the node the pointer points to
     * @return the node pointer
     */
    private ConfigurationNodePointer<ImmutableNode> createPointer(final ImmutableNode node) {
        return new ConfigurationNodePointer<>(node, Locale.getDefault(), handler);
    }

    /**
     * Creates a node pointer to a node which also contains a child node with a namespace prefix.
     *
     * @return the node pointer
     */
    private ConfigurationNodePointer<ImmutableNode> createPointerWithNamespace() {
        final ImmutableNode node = new ImmutableNode.Builder(2).addChild(root).addChild(NodeStructureHelper.createNode(PREFIX + ':' + PREFIX_NODE, "test"))
            .create();
        return createPointer(node);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        rootPointer = createPointer(root);
    }

    /**
     * Tests to iterate over all children of the root node.
     */

    /**
     * Tests a reverse iteration.
     */

    /**
     * Tests defining a start node for the iteration.
     */

    /**
     * Tests iteration with an invalid start node. This should cause the iteration to start at the first position.
     */

    /**
     * Tests defining a start node for a reverse iteration.
     */

    /**
     * Tests whether nodes with a matching namespace prefix can be obtained.
     */

    /**
     * Tests using a node test that selects a certain sub node name.
     */

    /**
     * Tests using a type test for nodes. This should return all nodes.
     */

    /**
     * Tests using a node test that defines a namespace prefix. Because namespaces are not supported, no elements should be
     * in the iteration.
     */

    /**
     * Tests using a not supported test class. This should yield an empty iteration.
     */

    /**
     * Tests using a type test for a non supported type. This should return an empty iteration.
     */

    /**
     * Tests using a node test with a wildcard name.
     */

    /**
     * Tests whether all nodes with a specific prefix can be obtained.
     */

    @Test
    public void testIterateAllChildren_1_oe() {
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, false, null);
        assertEquals("Wrong number of elements", CHILD_COUNT, iteratorSize(it));
    }

    @Test
    public void testIterateReverse_1_oe() {
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, true, null);
        assertEquals("Wrong number of elements", CHILD_COUNT, iteratorSize(it));
    }

    @Test
    public void testIterateStartsWith_1_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer, root.getChildren().get(2), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, false, childPointer);
        assertEquals("Wrong start position", 0, it.getPosition());
    }

    @Test
    public void testIterateStartsWith_2_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer, root.getChildren().get(2), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, false, childPointer);
        final List<NodePointer> nodes = iterationElements(it);
        assertEquals("Wrong size of iteration", CHILD_COUNT - 3, nodes.size());
    }

    @Test
    public void testIterateStartsWithInvalid_1_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer,
            new ImmutableNode.Builder().name("newNode").create(), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, false, childPointer);
        assertEquals("Wrong size of iteration", CHILD_COUNT, iteratorSize(it));
    }

    @Test
    public void testIterateStartsWithInvalid_2_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer,
            new ImmutableNode.Builder().name("newNode").create(), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, false, childPointer);
        it.setPosition(1);
        final ImmutableNode node = (ImmutableNode) it.getNodePointer().getNode();
        assertEquals("Wrong start node", "1", node.getValue());
    }

    @Test
    public void testIterateStartsWithReverse_1_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer, root.getChildren().get(3), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, true, childPointer);
        int value = 3;
        for (int index = 1; it.setPosition(index); index++, value--) {
            final ImmutableNode node = (ImmutableNode) it.getNodePointer().getNode();
            assertEquals("Incorrect value at index " + index, String.valueOf(value), node.getValue());
    }
    }

    @Test
    public void testIterateStartsWithReverse_2_oe() {
        final ConfigurationNodePointer<ImmutableNode> childPointer = new ConfigurationNodePointer<>(rootPointer, root.getChildren().get(3), handler);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, null, true, childPointer);
        int value = 3;
        for (int index = 1; it.setPosition(index); index++, value--) {
            final ImmutableNode node = (ImmutableNode) it.getNodePointer().getNode();
        }
        assertEquals("Iteration ended not at end node", 0, value);
    }

    @Test
    public void testIterateWithMatchingPrefixTest_1_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(PREFIX, PREFIX_NODE));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(createPointerWithNamespace(), test, false, null);
        assertEquals("Wrong number of elements", 1, iteratorSize(it));
    }

    @Test
    public void testIterateWithMatchingPrefixTest_2_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(PREFIX, PREFIX_NODE));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(createPointerWithNamespace(), test, false, null);
        for (final NodePointer p : iterationElements(it)) {
            assertEquals("Wrong element", PREFIX + ':' + PREFIX_NODE, p.getName().getName());
    }
    }

    @Test
    public void testIterateWithNameTest_1_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(null, CHILD_NAME2));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertTrue("No children found", iteratorSize(it) > 0);
    }

    @Test
    public void testIterateWithNameTest_2_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(null, CHILD_NAME2));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        for (final NodePointer nd : iterationElements(it)) {
            assertEquals("Wrong child element", CHILD_NAME2, nd.getName().getName());
    }
    }

    @Test
    public void testIterateWithNodeType_1_oe() {
        final NodeTypeTest test = new NodeTypeTest(Compiler.NODE_TYPE_NODE);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertEquals("Node type not evaluated", CHILD_COUNT, iteratorSize(it));
    }

    @Test
    public void testIterateWithPrefixTest_1_oe() {
        final NodeNameTest test = new NodeNameTest(new QName("prefix", "*"));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertNull("Undefined node pointer not returned", it.getNodePointer());
    }

    @Test
    public void testIterateWithPrefixTest_2_oe() {
        final NodeNameTest test = new NodeNameTest(new QName("prefix", "*"));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertEquals("Prefix was not evaluated", 0, iteratorSize(it));
    }

    @Test
    public void testIterateWithUnknownTest_1_oe() {
        final NodeTest test = new ProcessingInstructionTest("test");
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertEquals("Unknown test was not evaluated", 0, iteratorSize(it));
    }

    @Test
    public void testIterateWithUnknownType_1_oe() {
        final NodeTypeTest test = new NodeTypeTest(Compiler.NODE_TYPE_COMMENT);
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertEquals("Unknown node type not evaluated", 0, iteratorSize(it));
    }

    @Test
    public void testIterateWithWildcardTest_1_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(null, "*"));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(rootPointer, test, false, null);
        assertEquals("Wrong number of elements", CHILD_COUNT, iteratorSize(it));
    }

    @Test
    public void testIterateWithWildcardTestPrefix_1_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(PREFIX, "*"));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(createPointerWithNamespace(), test, false, null);
        assertEquals("Wrong number of elements", 1, iteratorSize(it));
    }

    @Test
    public void testIterateWithWildcardTestPrefix_2_oe() {
        final NodeNameTest test = new NodeNameTest(new QName(PREFIX, "*"));
        final ConfigurationNodeIteratorChildren<ImmutableNode> it = new ConfigurationNodeIteratorChildren<>(createPointerWithNamespace(), test, false, null);
        for (final NodePointer p : iterationElements(it)) {
            assertEquals("Wrong element", PREFIX + ':' + PREFIX_NODE, p.getName().getName());
    }
    }

}
