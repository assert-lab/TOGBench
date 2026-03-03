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
package org.apache.commons.validator;

import junit.framework.TestCase;

/**
 * Test <code>Field</code> objects.
 *
 * @version $Revision$
 */
public class FieldTest_OE25Dev extends TestCase {


    protected Field field;

    /**
     * FieldTest_OE25Dev constructor.
     */
    public FieldTest_OE25Dev() {
        super();
    }

    /**
     * FieldTest_OE25Dev constructor.
     * @param name
     */
    public FieldTest_OE25Dev(String name) {
        super(name);
    }

    /**
     * Test setup
     */
    @Override
    public void setUp() {
        field = new Field();
    }

    /**
     * Test clean up
     */
    @Override
    public void tearDown() {
        field = null;
    }

    /**
     * test Field with no arguments
     */
    /**
     * test Field with only 'default' arguments, no positions specified.
     */

    /**
     * test Field with only 'default' arguments, positions specified.
     */

    /**
     * test Field with only 'default' arguments, position specified for one argument
     */

    /**
     * test Field with only 'default' arguments, some position specified.
     */

    /**
     * test Field with a 'default' argument overriden using 'position' property
     */

    /**
     * test Field with a 'default' argument overriden using 'position' property
     */

    /**
     * test Field with a 'default' argument overriden without positions specified.
     */

    /**
     * test Field with a 'default' argument overriden with some positions specified
     */

    /**
     * Convenience Method - create argument (no name or position specified)
     */
    private Arg createArg(String key) {
        Arg arg = new Arg();
        arg.setKey(key);
        return arg; 
    }

    /**
     * Convenience Method - create argument (no name, position specified)
     */
    private Arg createArg(String key, int position) {
        Arg arg = createArg(key);
        arg.setPosition(position);
        return arg; 
    }

    /**
     * Convenience Method - create argument (name specified, no position)
     */
    private Arg createArg(String key, String name) {
        Arg arg = createArg(key);
        arg.setName(name);
        return arg; 
    }

    /**
     * Convenience Method - create argument (name & position specified)
     */
    private Arg createArg(String key, String name, int position) {
        Arg arg = createArg(key, name);
        arg.setPosition(position);
        return arg; 
    }

    public void testEmptyArgs_1_oe() {

        assertEquals("Empty Args(1) ", 0, field.getArgs("required").length);
    }

    public void testDefaultPositionImplied_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testDefaultPositionImplied(1) ", 3, field.getArgs("required").length);
    }

    public void testDefaultPositionImplied_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testDefaultPositionImplied(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testDefaultPositionImplied_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testDefaultPositionImplied(3) ", "default-position-1", field.getArg("required", 1).getKey());
    }

    public void testDefaultPositionImplied_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testDefaultPositionImplied(4) ", "default-position-2", field.getArg("required", 2).getKey());
    }

    public void testDefaultUsingPositions_1_oe() {

        field.addArg(createArg("default-position-1", 1));
        field.addArg(createArg("default-position-0", 0));
        field.addArg(createArg("default-position-2", 2));

        assertEquals("testDefaultUsingPositions(1) ", 3, field.getArgs("required").length);
    }

    public void testDefaultUsingPositions_2_oe() {

        field.addArg(createArg("default-position-1", 1));
        field.addArg(createArg("default-position-0", 0));
        field.addArg(createArg("default-position-2", 2));

        assertEquals("testDefaultUsingPositions(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testDefaultUsingPositions_3_oe() {

        field.addArg(createArg("default-position-1", 1));
        field.addArg(createArg("default-position-0", 0));
        field.addArg(createArg("default-position-2", 2));

        assertEquals("testDefaultUsingPositions(3) ", "default-position-1", field.getArg("required", 1).getKey());
    }

    public void testDefaultUsingPositions_4_oe() {

        field.addArg(createArg("default-position-1", 1));
        field.addArg(createArg("default-position-0", 0));
        field.addArg(createArg("default-position-2", 2));

        assertEquals("testDefaultUsingPositions(4) ", "default-position-2", field.getArg("required", 2).getKey());
    }

    public void testDefaultOnePosition_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertEquals("testDefaultOnePosition(1) ", 4, field.getArgs("required").length);
    }

    public void testDefaultOnePosition_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertEquals("testDefaultOnePosition(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testDefaultOnePosition_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertNull("testDefaultOnePosition(3) ", field.getArg("required", 1));
    }

    public void testDefaultOnePosition_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertEquals("testDefaultOnePosition(4) ", "default-position-2", field.getArg("required", 2).getKey());
    }

    public void testDefaultOnePosition_5_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));

        assertEquals("testDefaultOnePosition(5) ", "default-position-3", field.getArg("required", 3).getKey());
    }

    public void testDefaultSomePositions_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals("testDefaultSomePositions(1) ", 4, field.getArgs("required").length);
    }

    public void testDefaultSomePositions_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals("testDefaultSomePositions(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testDefaultSomePositions_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals("testDefaultSomePositions(3) ", "default-position-1", field.getArg("required", 1).getKey());
    }

    public void testDefaultSomePositions_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals("testDefaultSomePositions(4) ", "default-position-2", field.getArg("required", 2).getKey());
    }

    public void testDefaultSomePositions_5_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-2", 2));
        field.addArg(createArg("default-position-3"));
        field.addArg(createArg("default-position-1", 1));

        assertEquals("testDefaultSomePositions(5) ", "default-position-3", field.getArg("required", 3).getKey());
    }

    public void testOverrideUsingPositionA_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));

        assertEquals("testOverrideUsingPositionA(1) ", 3, field.getArgs("required").length);
    }

    public void testOverrideUsingPositionA_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));

        assertEquals("testOverrideUsingPositionA(2) ", "required-position-1", field.getArg("required", 1).getKey());
    }

    public void testOverrideUsingPositionA_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));


        assertEquals("testOverrideUsingPositionA(3) ", 3, field.getArgs("mask").length);
    }

    public void testOverrideUsingPositionA_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));


        assertEquals("testOverrideUsingPositionA(4) ", "default-position-1", field.getArg("mask", 1).getKey());
    }

    public void testOverrideUsingPositionA_5_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));



        assertEquals("testOverrideUsingPositionA(5) ", "default-position-1", field.getArg(1).getKey());
    }

    public void testOverrideUsingPositionB_1_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testOverrideUsingPositionB(1) ", 4, field.getArgs("required").length);
    }

    public void testOverrideUsingPositionB_2_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testOverrideUsingPositionB(2) ", "default-position-0",  field.getArg("required", 0).getKey());
    }

    public void testOverrideUsingPositionB_3_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testOverrideUsingPositionB(3) ", "required-position-1", field.getArg("required", 1).getKey());
    }

    public void testOverrideUsingPositionB_4_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testOverrideUsingPositionB(4) ", "default-position-2",  field.getArg("required", 2).getKey());
    }

    public void testOverrideUsingPositionB_5_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));

        assertEquals("testOverrideUsingPositionB(5) ", "required-position-3", field.getArg("required", 3).getKey());
    }

    public void testOverrideUsingPositionB_6_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));


        assertEquals("testOverrideUsingPositionB(6) ", 4, field.getArgs("mask").length);
    }

    public void testOverrideUsingPositionB_7_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));


        assertEquals("testOverrideUsingPositionB(6) ", "default-position-0", field.getArg("mask", 0).getKey());
    }

    public void testOverrideUsingPositionB_8_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));


        assertEquals("testOverrideUsingPositionB(7) ", "default-position-1", field.getArg("mask", 1).getKey());
    }

    public void testOverrideUsingPositionB_9_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));


        assertEquals("testOverrideUsingPositionB(8) ", "default-position-2", field.getArg("mask", 2).getKey());
    }

    public void testOverrideUsingPositionB_10_oe() {

        field.addArg(createArg("required-position-3", "required", 3));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));


        assertNull("testOverrideUsingPositionB(9) ", field.getArg("mask", 3));
    }

    public void testOverridePositionImplied_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));

        assertEquals("testOverridePositionImplied(1) ", 3, field.getArgs("required").length);
    }

    public void testOverridePositionImplied_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));

        assertEquals("testOverridePositionImplied(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testOverridePositionImplied_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));

        assertEquals("testOverridePositionImplied(3) ", "required-position-1", field.getArg("required", 1).getKey());
    }

    public void testOverridePositionImplied_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));

        assertEquals("testOverridePositionImplied(4) ", "required-position-2", field.getArg("required", 2).getKey());
    }

    public void testOverridePositionImplied_5_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));


        assertEquals("testOverridePositionImplied(5) ", 3, field.getArgs("mask").length);
    }

    public void testOverridePositionImplied_6_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));


        assertEquals("testOverridePositionImplied(6) ", "default-position-0", field.getArg("mask", 0).getKey());
    }

    public void testOverridePositionImplied_7_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));


        assertEquals("testOverridePositionImplied(7) ", "mask-position-1", field.getArg("mask", 1).getKey());
    }

    public void testOverridePositionImplied_8_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));


        assertNull("testOverridePositionImplied(8) ", field.getArg("mask", 2));
    }

    public void testOverridePositionImplied_9_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));



        assertEquals("testOverridePositionImplied(9) ",  "default-position-0", field.getArg(0).getKey());
    }

    public void testOverridePositionImplied_10_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));



        assertNull("testOverridePositionImplied(10) ", field.getArg(1));
    }

    public void testOverridePositionImplied_11_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("required-position-1", "required"));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-1", "mask"));



        assertNull("testOverridePositionImplied(11) ", field.getArg(2));
    }

    public void testOverrideSomePosition_1_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertEquals("testOverrideSomePosition(1) ", 4, field.getArgs("required").length);
    }

    public void testOverrideSomePosition_2_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertEquals("testOverrideSomePosition(2) ", "default-position-0", field.getArg("required", 0).getKey());
    }

    public void testOverrideSomePosition_3_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertEquals("testOverrideSomePosition(3) ", "required-position-1", field.getArg("required", 1).getKey());
    }

    public void testOverrideSomePosition_4_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertEquals("testOverrideSomePosition(4) ", "required-position-2", field.getArg("required", 2).getKey());
    }

    public void testOverrideSomePosition_5_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));

        assertNull("testOverrideSomePosition(5) ", field.getArg("required", 3));
    }

    public void testOverrideSomePosition_6_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));


        assertEquals("testOverrideSomePosition(6) ", 4, field.getArgs("mask").length);
    }

    public void testOverrideSomePosition_7_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));


        assertEquals("testOverrideSomePosition(7) ", "default-position-0", field.getArg("mask", 0).getKey());
    }

    public void testOverrideSomePosition_8_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));


        assertEquals("testOverrideSomePosition(8) ", "default-position-1", field.getArg("mask", 1).getKey());
    }

    public void testOverrideSomePosition_9_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));


        assertEquals("testOverrideSomePosition(9) ", "default-position-2", field.getArg("mask", 2).getKey());
    }

    public void testOverrideSomePosition_10_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));


        assertEquals("testOverrideSomePosition(10) ", "mask-position-3", field.getArg("mask", 3).getKey());
    }

    public void testOverrideSomePosition_11_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));



        assertEquals("testOverrideSomePosition(11) ",  "default-position-0", field.getArg(0).getKey());
    }

    public void testOverrideSomePosition_12_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));



        assertEquals("testOverrideSomePosition(12) ", "default-position-1", field.getArg(1).getKey());
    }

    public void testOverrideSomePosition_13_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));



        assertEquals("testOverrideSomePosition(13) ", "default-position-2", field.getArg(2).getKey());
    }

    public void testOverrideSomePosition_14_oe() {

        field.addArg(createArg("default-position-0"));
        field.addArg(createArg("default-position-1"));
        field.addArg(createArg("default-position-2"));
        field.addArg(createArg("required-position-1", "required", 1));
        field.addArg(createArg("required-position-2", "required"));
        field.addArg(createArg("mask-position-3", "mask"));



        assertNull("testOverrideSomePosition(14) ", field.getArg(3));
    }

}
