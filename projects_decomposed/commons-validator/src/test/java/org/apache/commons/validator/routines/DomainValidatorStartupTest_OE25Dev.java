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
package org.apache.commons.validator.routines;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.DomainValidator.ArrayType;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.bitstrings.test.junit.runner.ClassLoaderPerTestRunner;

/**
 * Startup Tests for the DomainValidator.
 *
 * @version $Revision$
 */
@RunWith( ClassLoaderPerTestRunner.class )
public class DomainValidatorStartupTest_OE25Dev {

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBaseArrayCC() {
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_RO, new String[]{"com"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBaseArrayGeneric() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_RO, new String[]{"com"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBaseArrayInfra() {
        DomainValidator.updateTLDOverride(ArrayType.INFRASTRUCTURE_RO, new String[]{"com"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBaseArrayLocal() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_RO, new String[]{"com"});
    }

@Test
    public void testUpdateCountryCode1a_1_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidCountryCodeTld("com")); // cannot be valid;
    }

@Test
    public void testUpdateCountryCode1b_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"com"});
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidCountryCodeTld("com")); // it is now!;
    }

@Test
    public void testUpdateCountryCode2_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"com"});
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidCountryCodeTld("com")); // show that minus overrides the rest;
    }

@Test
    public void testUpdateCountryCode3a_1_oe() { // show ch is valid
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidCountryCodeTld("ch"));
    }

@Test
    public void testUpdateCountryCode3b_1_oe() { // show ch can be made invalid
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidCountryCodeTld("ch"));
    }

@Test
    public void testUpdateCountryCode3c_1_oe() { // show ch can be made valid again by replacing the CC array
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"xx"});
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidCountryCodeTld("ch"));
    }

@Test
    public void testUpdateGeneric1_1_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidGenericTld("ch")); // cannot be valid;
    }

@Test
    public void testUpdateGeneric2_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidGenericTld("ch")); // it is now!;
    }

@Test
    public void testUpdateGeneric3_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidGenericTld("ch"));// show that minus overrides the rest assertTrue(validator.isValidGenericTld("com"));
    }

@Test
    public void testUpdateGeneric4_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidGenericTld("com"));
    }

@Test
    public void testUpdateGeneric5_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"ch"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"xx"}); // change the minus list
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidGenericTld("com"));
    }

@Test
    public void testVALIDATOR_412a_1_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        assertFalse(validator.isValidGenericTld("local"));
    }

@Test
    public void testVALIDATOR_412a_2_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        assertFalse(validator.isValid("abc.local"));
    }

@Test
    public void testVALIDATOR_412a_3_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValidGenericTld("pvt"));
    }

@Test
    public void testVALIDATOR_412a_4_oe() {
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("abc.pvt"));
    }

@Test
    public void testVALIDATOR_412b_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance();
        assertTrue(validator.isValidGenericTld("local"));
    }

@Test
    public void testVALIDATOR_412b_2_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        assertTrue(validator.isValid("abc.local"));
    }

@Test
    public void testVALIDATOR_412b_3_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValidGenericTld("pvt"));
    }

@Test
    public void testVALIDATOR_412b_4_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValid("abc.pvt"));
    }

@Test
    public void testVALIDATOR_412c_1_oe() {
        DomainValidator validator = DomainValidator.getInstance(true);
        assertFalse(validator.isValidLocalTld("local"));
    }

@Test
    public void testVALIDATOR_412c_2_oe() {
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        assertFalse(validator.isValid("abc.local"));
    }

@Test
    public void testVALIDATOR_412c_3_oe() {
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValidLocalTld("pvt"));
    }

@Test
    public void testVALIDATOR_412c_4_oe() {
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("abc.pvt"));
    }

@Test
    public void testVALIDATOR_412d_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance(true);
        assertTrue(validator.isValidLocalTld("local"));
    }

@Test
    public void testVALIDATOR_412d_2_oe() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        assertTrue(validator.isValidLocalTld("pvt"));
    }

@Test
    public void testVALIDATOR_412d_3_oe() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValid("abc.local"));
    }

@Test
    public void testVALIDATOR_412d_4_oe() {
        DomainValidator.updateTLDOverride(ArrayType.LOCAL_PLUS, new String[]{"local", "pvt"});
        DomainValidator validator = DomainValidator.getInstance(true);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValid("abc.pvt"));
    }

@Test
    public void testCannotUpdate_1_oe() {
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"ch"}); // OK
        DomainValidator dv = DomainValidator.getInstance();
        assertNotNull(dv);
    }

@Test
    public void testInstanceOverride_1_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        assertTrue(validator.isValidGenericTld("gp"));
    }

@Test
    public void testInstanceOverride_2_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        assertFalse(validator.isValidGenericTld("com"));
    }

@Test
    public void testInstanceOverride_3_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValidCountryCodeTld("cp"));
    }

@Test
    public void testInstanceOverride_4_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValidCountryCodeTld("ch"));
    }

@Test
    public void testInstanceOverride_5_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        assertTrue(validator.isValidGenericTld("gp"));
    }

@Test
    public void testInstanceOverride_6_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        assertTrue(validator.isValidGenericTld("com"));// Should be true again assertTrue(validator.isValidCountryCodeTld("cp"));
    }

@Test
    public void testInstanceOverride_7_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValidCountryCodeTld("ch"));// Should be true again validator = DomainValidator.getInstance(false);
    }

@Test
    public void testInstanceOverride_8_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValidGenericTld("gp"));
    }

@Test
    public void testInstanceOverride_9_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValidGenericTld("com"));
    }

@Test
    public void testInstanceOverride_10_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValidCountryCodeTld("cp"));
    }

@Test
    public void testInstanceOverride_11_oe() { // Show that the instance picks up static values
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_PLUS, new String[]{"gp"});
        DomainValidator.updateTLDOverride(ArrayType.GENERIC_MINUS, new String[]{"com"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_PLUS, new String[]{"cp"});
        DomainValidator.updateTLDOverride(ArrayType.COUNTRY_CODE_MINUS, new String[]{"ch"});
        DomainValidator validator = DomainValidator.getInstance(false);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // show we can override them for a new instance
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(ArrayType.GENERIC_MINUS,new String[]{""}));
        items.add(new DomainValidator.Item(ArrayType.COUNTRY_CODE_MINUS,new String[]{""}));
        validator = DomainValidator.getInstance(false, items);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValidCountryCodeTld("ch"));
    }

}
