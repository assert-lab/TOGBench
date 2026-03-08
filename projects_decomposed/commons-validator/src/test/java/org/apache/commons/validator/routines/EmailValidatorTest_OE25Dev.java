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

import org.apache.commons.validator.ResultPair;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Performs Validation Test for e-mail validations.
 *
 *
 * @version $Revision$
 */
public class EmailValidatorTest_OE25Dev {

    /**
     * The key used to retrieve the set of validation
     * rules from the xml file.
     */
    protected static String FORM_KEY = "emailForm";

   /**
    * The key used to retrieve the validator action.
    */
   protected static String ACTION = "email";

   private EmailValidator validator;

   @Before
   public void setUp() {
        validator = EmailValidator.getInstance();
   }

   /**
    * Tests the e-mail validation.
    */

   /**
    * Tests the email validation with numeric domains.
    */

    /**
     * Tests the e-mail validation.
     */

   /**
    * <p>Tests the e-mail validation with a dash in
    * the address.</p>
    */

   /**
    * Tests the e-mail validation with a dot at the end of
    * the address.
    */

    /**
     * Tests the e-mail validation with an RCS-noncompliant character in
     * the address.
     */

    @Test
    public void testVALIDATOR_315() {
        assertFalse(validator.isValid("me@at&t.net"));
        assertTrue(validator.isValid("me@att.net")); // Make sure TLD is not the cause of the failure
    }

    @Test
    public void testVALIDATOR_278() {
        assertFalse(validator.isValid("someone@-test.com"));// hostname starts with dash/hyphen
        assertFalse(validator.isValid("someone@test-.com"));// hostname ends with dash/hyphen
    }

    /**
    * Tests the email validation with commas.
    */

   /**
    * Tests the email validation with spaces.
    */

   /**
    * Tests the email validation with ascii control characters.
    * (i.e. Ascii chars 0 - 31 and 127)
    */
    
    /**
     * Test that @localhost and @localhost.localdomain
     *  addresses are declared as valid when requested. 
     */
    
    /**
     * VALIDATOR-296 - A / or a ! is valid in the user part,
     *  but not in the domain part 
     */

    /**
     * Write this test according to parts of RFC, as opposed to the type of character
     * that is being tested.
     */

    /**
     * These test values derive directly from RFC 822 &
     * Mail::RFC822::Address & RFC::RFC822::Address perl test.pl
     * For traceability don't combine these test values with other tests.
     */
    private static final ResultPair[] testEmailFromPerl = {
        new ResultPair("abigail@example.com", true),
        new ResultPair("abigail@example.com ", true),
        new ResultPair(" abigail@example.com", true),
        new ResultPair("abigail @example.com ", true),
        new ResultPair("*@example.net", true),
        new ResultPair("\"\\\"\"@foo.bar", true),
        new ResultPair("fred&barny@example.com", true),
        new ResultPair("---@example.com", true),
        new ResultPair("foo-bar@example.net", true),
        new ResultPair("\"127.0.0.1\"@[127.0.0.1]", true),
        new ResultPair("Abigail <abigail@example.com>", true),
        new ResultPair("Abigail<abigail@example.com>", true),
        new ResultPair("Abigail<@a,@b,@c:abigail@example.com>", true),
        new ResultPair("\"This is a phrase\"<abigail@example.com>", true),
        new ResultPair("\"Abigail \"<abigail@example.com>", true),
        new ResultPair("\"Joe & J. Harvey\" <example @Org>", true),
        new ResultPair("Abigail <abigail @ example.com>", true),
        new ResultPair("Abigail made this <  abigail   @   example  .    com    >", true),
        new ResultPair("Abigail(the bitch)@example.com", true),
        new ResultPair("Abigail <abigail @ example . (bar) com >", true),
        new ResultPair("Abigail < (one)  abigail (two) @(three)example . (bar) com (quz) >", true),
        new ResultPair("Abigail (foo) (((baz)(nested) (comment)) ! ) < (one)  abigail (two) @(three)example . (bar) com (quz) >", true),
        new ResultPair("Abigail <abigail(fo\\(o)@example.com>", true),
        new ResultPair("Abigail <abigail(fo\\)o)@example.com> ", true),
        new ResultPair("(foo) abigail@example.com", true),
        new ResultPair("abigail@example.com (foo)", true),
        new ResultPair("\"Abi\\\"gail\" <abigail@example.com>", true),
        new ResultPair("abigail@[example.com]", true),
        new ResultPair("abigail@[exa\\[ple.com]", true),
        new ResultPair("abigail@[exa\\]ple.com]", true),
        new ResultPair("\":sysmail\"@  Some-Group. Some-Org", true),
        new ResultPair("Muhammed.(I am  the greatest) Ali @(the)Vegas.WBA", true),
        new ResultPair("mailbox.sub1.sub2@this-domain", true),
        new ResultPair("sub-net.mailbox@sub-domain.domain", true),
        new ResultPair("name:;", true),
        new ResultPair("':;", true),
        new ResultPair("name:   ;", true),
        new ResultPair("Alfred Neuman <Neuman@BBN-TENEXA>", true),
        new ResultPair("Neuman@BBN-TENEXA", true),
        new ResultPair("\"George, Ted\" <Shared@Group.Arpanet>", true),
        new ResultPair("Wilt . (the  Stilt) Chamberlain@NBA.US", true),
        new ResultPair("Cruisers:  Port@Portugal, Jones@SEA;", true),
        new ResultPair("$@[]", true),
        new ResultPair("*()@[]", true),
        new ResultPair("\"quoted ( brackets\" ( a comment )@example.com", true),
        new ResultPair("\"Joe & J. Harvey\"\\x0D\\x0A     <ddd\\@ Org>", true),
        new ResultPair("\"Joe &\\x0D\\x0A J. Harvey\" <ddd \\@ Org>", true),
        new ResultPair("Gourmets:  Pompous Person <WhoZiWhatZit\\@Cordon-Bleu>,\\x0D\\x0A" +
            "        Childs\\@WGBH.Boston, \"Galloping Gourmet\"\\@\\x0D\\x0A" +
            "        ANT.Down-Under (Australian National Television),\\x0D\\x0A" +
            "        Cheapie\\@Discount-Liquors;", true),
        new ResultPair("   Just a string", false),
        new ResultPair("string", false),
        new ResultPair("(comment)", false),
        new ResultPair("()@example.com", false),
        new ResultPair("fred(&)barny@example.com", false),
        new ResultPair("fred\\ barny@example.com", false),
        new ResultPair("Abigail <abi gail @ example.com>", false),
        new ResultPair("Abigail <abigail(fo(o)@example.com>", false),
        new ResultPair("Abigail <abigail(fo)o)@example.com>", false),
        new ResultPair("\"Abi\"gail\" <abigail@example.com>", false),
        new ResultPair("abigail@[exa]ple.com]", false),
        new ResultPair("abigail@[exa[ple.com]", false),
        new ResultPair("abigail@[exaple].com]", false),
        new ResultPair("abigail@", false),
        new ResultPair("@example.com", false),
        new ResultPair("phrase: abigail@example.com abigail@example.com ;", false),
        new ResultPair("invalid�char@example.com", false)
    };

    /**
     * Write this test based on perl Mail::RFC822::Address
     * which takes its example email address directly from RFC822
     *
     * This test fails so disable it
     * The real solution is to fix the email parsing.
     */
    @Ignore("VALIDATOR-267")
    @Test
    public void testEmailFromPerl()  {
        int errors = 0;
        for (int index = 0; index < testEmailFromPerl.length; index++) {
            String item = testEmailFromPerl[index].item;
            boolean exp =  testEmailFromPerl[index].valid;
            boolean act = validator.isValid(item);
            if (act != exp) {
                System.out.printf("%s: expected %s actual %s%n", item, exp, act);
                errors += 1;
            }
        }
        assertEquals("Expected 0 errors", 0, errors);
    }

    /**
     * Tests the e-mail validation with a user at a TLD
     *
     * http://tools.ietf.org/html/rfc5321#section-2.3.5
     * (In the case of a top-level domain used by itself in an
     * email address, a single string is used without any dots)
     */

    @Test(expected = IllegalArgumentException.class)
    public void testValidator473_1() { // reject null DomainValidator
        new EmailValidator(false, false, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidator473_2() { // reject null DomainValidator with mismatched allowLocal
        List<DomainValidator.Item> items = new ArrayList<>();
        new EmailValidator(false, false, DomainValidator.getInstance(true, items));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidator473_3() { // reject null DomainValidator with mismatched allowLocal
        List<DomainValidator.Item> items = new ArrayList<>();
        new EmailValidator(true, false, DomainValidator.getInstance(false, items));
    }

    @Test
    public void testValidator473_4() { // Show that can override domain validation
        assertFalse(validator.isValidDomain("test.local"));
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, new String[]{"local"}));
        EmailValidator val = new EmailValidator(true, false, DomainValidator.getInstance(true, items));
        assertTrue(val.isValidDomain("test.local"));
    }

    public static void main(String[] args) {
        EmailValidator validator = EmailValidator.getInstance();
        for(String arg : args) {
            System.out.printf("%s: %s%n", arg, validator.isValid(arg));
        }
    }

    @Test
    public void testEmail_1_oe()  {
       assertTrue(validator.isValid("jsmith@apache.org"));
    }

    @Test
    public void testEmailWithNumericAddress_1_oe()  {
        assertTrue(validator.isValid("someone@[216.109.118.76]"));
    }

    @Test
    public void testEmailWithNumericAddress_2_oe()  {
        // removed other assertion
        assertTrue(validator.isValid("someone@yahoo.com"));
    }

    @Test
    public void testEmailExtension_1_oe()  {
        assertTrue(validator.isValid("jsmith@apache.org"));
    }

    @Test
    public void testEmailExtension_2_oe()  {
        // removed other assertion

        assertTrue(validator.isValid("jsmith@apache.com"));
    }

    @Test
    public void testEmailExtension_3_oe()  {
        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("jsmith@apache.net"));
    }

    @Test
    public void testEmailExtension_4_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("jsmith@apache.info"));
    }

    @Test
    public void testEmailExtension_5_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("jsmith@apache."));
    }

    @Test
    public void testEmailExtension_6_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("jsmith@apache.c"));
    }

    @Test
    public void testEmailExtension_7_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("someone@yahoo.museum"));
    }

    @Test
    public void testEmailExtension_8_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("someone@yahoo.mu-seum"));
    }

    @Test
    public void testEmailWithDash_1_oe()  {
       assertTrue(validator.isValid("andy.noble@data-workshop.com"));
    }

    @Test
    public void testEmailWithDash_2_oe()  {
       // removed other assertion

       assertFalse(validator.isValid("andy-noble@data-workshop.-com"));
    }

    @Test
    public void testEmailWithDash_3_oe()  {
       // removed other assertion

       // removed other assertion

       assertFalse(validator.isValid("andy-noble@data-workshop.c-om"));
    }

    @Test
    public void testEmailWithDash_4_oe()  {
       // removed other assertion

       // removed other assertion

       // removed other assertion

       assertFalse(validator.isValid("andy-noble@data-workshop.co-m"));
    }

    @Test
    public void testEmailWithDotEnd_1_oe()  {
      assertFalse(validator.isValid("andy.noble@data-workshop.com."));
    }

    @Test
    public void testEmailWithBogusCharacter_1_oe()  {

        assertFalse(validator.isValid("andy.noble@\u008fdata-workshop.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_2_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        assertTrue(validator.isValid("andy.o'reilly@data-workshop.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_3_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        // removed other assertion

        // But not in the domain name.
        assertFalse(validator.isValid("andy@o'reilly.data-workshop.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_4_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        // removed other assertion

        // But not in the domain name.
        // removed other assertion

        // The + character is valid in an email username.
        assertTrue(validator.isValid("foo+bar@i.am.not.in.us.example.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_5_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        // removed other assertion

        // But not in the domain name.
        // removed other assertion

        // The + character is valid in an email username.
        // removed other assertion

        // But not in the domain name
        assertFalse(validator.isValid("foo+bar@example+3.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_6_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        // removed other assertion

        // But not in the domain name.
        // removed other assertion

        // The + character is valid in an email username.
        // removed other assertion

        // But not in the domain name
        // removed other assertion

        // Domains with only special characters aren't allowed (VALIDATOR-286) 
        assertFalse(validator.isValid("test@%*.com"));
    }

    @Test
    public void testEmailWithBogusCharacter_7_oe()  {

        // removed other assertion

        // The ' character is valid in an email username.
        // removed other assertion

        // But not in the domain name.
        // removed other assertion

        // The + character is valid in an email username.
        // removed other assertion

        // But not in the domain name
        // removed other assertion

        // Domains with only special characters aren't allowed (VALIDATOR-286) 
        // removed other assertion
        assertFalse(validator.isValid("test@^&#.com"));
    }

    @Test
    public void testVALIDATOR_315_1_oe() {
        assertFalse(validator.isValid("me@at&t.net"));
    }

    @Test
    public void testVALIDATOR_315_2_oe() {
        // removed other assertion
        assertTrue(validator.isValid("me@att.net")); // Make sure TLD is not the cause of the failure;
    }

    @Test
    public void testVALIDATOR_278_1_oe() {
        assertFalse(validator.isValid("someone@-test.com"));// hostname starts with dash/hyphen;
    }

    @Test
    public void testVALIDATOR_278_2_oe() {
        // removed other assertion
        assertFalse(validator.isValid("someone@test-.com"));// hostname ends with dash/hyphen;
    }

    @Test
    public void testValidator235_1_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        assertTrue("xn--d1abbgf6aiiy.xn--p1ai should validate", validator.isValid("someone@xn--d1abbgf6aiiy.xn--p1ai"));
    }

    @Test
    public void testValidator235_2_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        // removed other assertion
        assertTrue("президент.рф should validate", validator.isValid("someone@президент.рф"));
    }

    @Test
    public void testValidator235_3_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        // removed other assertion
        // removed other assertion
        assertTrue("www.b\u00fccher.ch should validate", validator.isValid("someone@www.b\u00fccher.ch"));
    }

    @Test
    public void testValidator235_4_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("www.\uFFFD.ch FFFD should fail", validator.isValid("someone@www.\uFFFD.ch"));
    }

    @Test
    public void testValidator235_5_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("www.b\u00fccher.ch should validate", validator.isValid("someone@www.b\u00fccher.ch"));
    }

    @Test
    public void testValidator235_6_oe() {
        String version = System.getProperty("java.version");
        if (version.compareTo("1.6") < 0) {
            System.out.println("Cannot run Unicode IDN tests");
            return; // Cannot run the test
        }
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("www.\uFFFD.ch FFFD should fail", validator.isValid("someone@www.\uFFFD.ch"));
    }

    @Test
    public void testEmailWithCommas_1_oe()  {
        assertFalse(validator.isValid("joeblow@apa,che.org"));
    }

    @Test
    public void testEmailWithCommas_2_oe()  {
        // removed other assertion

        assertFalse(validator.isValid("joeblow@apache.o,rg"));
    }

    @Test
    public void testEmailWithCommas_3_oe()  {
        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joeblow@apache,org"));
    }

    @Test
    public void testEmailWithSpaces_1_oe()  {
        assertFalse(validator.isValid("joeblow @apache.org"));
    }

    @Test
    public void testEmailWithSpaces_2_oe()  {
        // removed other assertion

        assertFalse(validator.isValid("joeblow@ apache.org"));
    }

    @Test
    public void testEmailWithSpaces_3_oe()  {
        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid(" joeblow@apache.org"));
    }

    @Test
    public void testEmailWithSpaces_4_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joeblow@apache.org "));
    }

    @Test
    public void testEmailWithSpaces_5_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joe blow@apache.org "));
    }

    @Test
    public void testEmailWithSpaces_6_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joeblow@apa che.org "));
    }

    @Test
    public void testEmailWithSpaces_7_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joeblow \"@apache.org"));
    }

    @Test
    public void testEmailWithSpaces_8_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\" joeblow\"@apache.org"));
    }

    @Test
    public void testEmailWithSpaces_9_oe()  {
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\" joe blow \"@apache.org"));
    }

    @Test
    public void testEmailWithControlChars_1_oe()  {
        for (char c = 0; c < 32; c++) {
            assertFalse("Test control char " + ((int)c), validator.isValid("foo" + c + "bar@domain.com"));
    }
    }

    @Test
    public void testEmailWithControlChars_2_oe()  {
        for (char c = 0; c < 32; c++) {
            // removed other assertion
        }
        assertFalse("Test control char 127", validator.isValid("foo" + ((char)127) + "bar@domain.com"));
    }

    @Test
    public void testEmailLocalhost_1_oe() {
       // Check the default is not to allow
       EmailValidator noLocal = EmailValidator.getInstance(false);
       EmailValidator allowLocal = EmailValidator.getInstance(true);
       assertEquals(validator, noLocal);
    }

    @Test
    public void testEmailLocalhost_2_oe() {
       // Check the default is not to allow
       EmailValidator noLocal = EmailValidator.getInstance(false);
       EmailValidator allowLocal = EmailValidator.getInstance(true);
       // removed other assertion
       
       // Depends on the validator
       assertTrue("@localhost.localdomain should be accepted but wasn't",allowLocal.isValid("joe@localhost.localdomain"));
    }

    @Test
    public void testEmailLocalhost_3_oe() {
       // Check the default is not to allow
       EmailValidator noLocal = EmailValidator.getInstance(false);
       EmailValidator allowLocal = EmailValidator.getInstance(true);
       // removed other assertion
       
       // Depends on the validator
       // removed other assertion
       assertTrue("@localhost should be accepted but wasn't",allowLocal.isValid("joe@localhost"));
    }

    @Test
    public void testEmailLocalhost_4_oe() {
       // Check the default is not to allow
       EmailValidator noLocal = EmailValidator.getInstance(false);
       EmailValidator allowLocal = EmailValidator.getInstance(true);
       // removed other assertion
       
       // Depends on the validator
       // removed other assertion
       // removed other assertion
       
       assertFalse("@localhost.localdomain should be accepted but wasn't",noLocal.isValid("joe@localhost.localdomain"));
    }

    @Test
    public void testEmailLocalhost_5_oe() {
       // Check the default is not to allow
       EmailValidator noLocal = EmailValidator.getInstance(false);
       EmailValidator allowLocal = EmailValidator.getInstance(true);
       // removed other assertion
       
       // Depends on the validator
       // removed other assertion
       // removed other assertion
       
       // removed other assertion
       assertFalse("@localhost should be accepted but wasn't",noLocal.isValid("joe@localhost"));
    }

    @Test
    public void testEmailWithSlashes_1_oe() {
       assertTrue("/ and ! valid in username",validator.isValid("joe!/blow@apache.org"));
    }

    @Test
    public void testEmailWithSlashes_2_oe() {
       // removed other assertion
       assertFalse("/ not valid in domain",validator.isValid("joe@ap/ache.org"));
    }

    @Test
    public void testEmailWithSlashes_3_oe() {
       // removed other assertion
       // removed other assertion
       assertFalse("! not valid in domain",validator.isValid("joe@apac!he.org"));
    }

    @Test
    public void testEmailUserName_1_oe()  {

        assertTrue(validator.isValid("joe1blow@apache.org"));
    }

    @Test
    public void testEmailUserName_2_oe()  {

        // removed other assertion

        assertTrue(validator.isValid("joe$blow@apache.org"));
    }

    @Test
    public void testEmailUserName_3_oe()  {

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("joe-@apache.org"));
    }

    @Test
    public void testEmailUserName_4_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("joe_@apache.org"));
    }

    @Test
    public void testEmailUserName_5_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("joe+@apache.org"));// + is valid unquoted assertTrue(validator.isValid("joe!@apache.org"));// ! is valid unquoted assertTrue(validator.isValid("joe*@apache.org"));// * is valid unquoted assertTrue(validator.isValid("joe'@apache.org"));// ' is valid unquoted assertTrue(validator.isValid("joe%45@apache.org"));// % is valid unquoted assertTrue(validator.isValid("joe?@apache.org"));// ? is valid unquoted assertTrue(validator.isValid("joe&@apache.org"));// & ditto assertTrue(validator.isValid("joe=@apache.org"));// = ditto assertTrue(validator.isValid("+joe@apache.org"));// + is valid unquoted assertTrue(validator.isValid("!joe@apache.org"));// ! is valid unquoted assertTrue(validator.isValid("*joe@apache.org"));// * is valid unquoted assertTrue(validator.isValid("'joe@apache.org"));// ' is valid unquoted assertTrue(validator.isValid("%joe45@apache.org"));// % is valid unquoted assertTrue(validator.isValid("?joe@apache.org"));// ? is valid unquoted assertTrue(validator.isValid("&joe@apache.org"));// & ditto assertTrue(validator.isValid("=joe@apache.org"));// = ditto assertTrue(validator.isValid("+@apache.org"));// + is valid unquoted assertTrue(validator.isValid("!@apache.org"));// ! is valid unquoted assertTrue(validator.isValid("*@apache.org"));// * is valid unquoted assertTrue(validator.isValid("'@apache.org"));// ' is valid unquoted assertTrue(validator.isValid("%@apache.org"));// % is valid unquoted assertTrue(validator.isValid("?@apache.org"));// ? is valid unquoted assertTrue(validator.isValid("&@apache.org"));// & ditto assertTrue(validator.isValid("=@apache.org"));// = ditto assertFalse(validator.isValid("joe.@apache.org"));// . not allowed at end of local part assertFalse(validator.isValid(".joe@apache.org"));// . not allowed at start of local part assertFalse(validator.isValid(".@apache.org"));// . not allowed alone assertTrue(validator.isValid("joe.ok@apache.org"));// . allowed embedded assertFalse(validator.isValid("joe..ok@apache.org"));// .. not allowed embedded assertFalse(validator.isValid("..@apache.org"));// .. not allowed alone assertFalse(validator.isValid("joe(@apache.org"));assertFalse(validator.isValid("joe)@apache.org"));
    }

    @Test
    public void testEmailUserName_6_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joe,@apache.org"));
    }

    @Test
    public void testEmailUserName_7_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("joe;@apache.org"));
    }

    @Test
    public void testEmailUserName_8_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        assertTrue(validator.isValid("\"joe.\"@apache.org"));
    }

    @Test
    public void testEmailUserName_9_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        assertTrue(validator.isValid("\".joe\"@apache.org"));
    }

    @Test
    public void testEmailUserName_10_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe+\"@apache.org"));
    }

    @Test
    public void testEmailUserName_11_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe@\"@apache.org"));
    }

    @Test
    public void testEmailUserName_12_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe!\"@apache.org"));
    }

    @Test
    public void testEmailUserName_13_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe*\"@apache.org"));
    }

    @Test
    public void testEmailUserName_14_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe'\"@apache.org"));
    }

    @Test
    public void testEmailUserName_15_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe(\"@apache.org"));
    }

    @Test
    public void testEmailUserName_16_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe)\"@apache.org"));
    }

    @Test
    public void testEmailUserName_17_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe,\"@apache.org"));
    }

    @Test
    public void testEmailUserName_18_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe%45\"@apache.org"));
    }

    @Test
    public void testEmailUserName_19_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe;\"@apache.org"));
    }

    @Test
    public void testEmailUserName_20_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe?\"@apache.org"));
    }

    @Test
    public void testEmailUserName_21_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe&\"@apache.org"));
    }

    @Test
    public void testEmailUserName_22_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"joe=\"@apache.org"));
    }

    @Test
    public void testEmailUserName_23_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\"..\"@apache.org"));
    }

    @Test
    public void testEmailUserName_24_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        assertTrue(validator.isValid("\"john\\\"doe\"@apache.org"));
    }

    @Test
    public void testEmailUserName_25_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        assertTrue(validator.isValid("john56789.john56789.john56789.john56789.john56789.john56789.john@example.com"));
    }

    @Test
    public void testEmailUserName_26_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("john56789.john56789.john56789.john56789.john56789.john56789.john5@example.com"));
    }

    @Test
    public void testEmailUserName_27_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("\\>escape\\\\special\\^characters\\<@example.com"));
    }

    @Test
    public void testEmailUserName_28_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("Abc\\@def@example.com"));
    }

    @Test
    public void testEmailUserName_29_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertFalse(validator.isValid("Abc@def@example.com"));
    }

    @Test
    public void testEmailUserName_30_oe()  {

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion


        //Quoted Special characters are valid
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // escaped quote character valid in quoted string
        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        // removed other assertion

        assertTrue(validator.isValid("space\\ monkey@example.com"));
    }

    @Test
    public void testValidator293_1_oe(){
        assertTrue(validator.isValid("abc-@abc.com"));
    }

    @Test
    public void testValidator293_2_oe(){
        // removed other assertion
        assertTrue(validator.isValid("abc_@abc.com"));
    }

    @Test
    public void testValidator293_3_oe(){
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValid("abc-def@abc.com"));
    }

    @Test
    public void testValidator293_4_oe(){
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(validator.isValid("abc_def@abc.com"));
    }

    @Test
    public void testValidator293_5_oe(){
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse(validator.isValid("abc@abc_def.com"));
    }

    @Test
    public void testValidator365_1_oe() {
        assertFalse(validator.isValid("Loremipsumdolorsitametconsecteturadipiscingelit.Nullavitaeligulamattisrhoncusnuncegestasmattisleo."+ "Donecnonsapieninmagnatristiquedictumaacturpis.Fusceorciduifacilisisutsapieneuconsequatpharetralectus."+ "Quisqueenimestpulvinarutquamvitaeportamattisex.Nullamquismaurisplaceratconvallisjustoquisportamauris."+ "Innullalacusconvalliseufringillautvenenatissitametdiam.Maecenasluctusligulascelerisquepulvinarfeugiat."+ "Sedmolestienullaaliquetorciluctusidpharetranislfinibus.Suspendissemalesuadatinciduntduisitametportaarcusollicitudinnec."+ "Donecetmassamagna.Curabitururnadiampretiumveldignissimporttitorfringillaeuneque."+ "Duisantetelluspharetraidtinciduntinterdummolestiesitametfelis.Utquisquamsitametantesagittisdapibusacnonodio."+ "Namrutrummolestiediamidmattis.Cumsociisnatoquepenatibusetmagnisdisparturientmontesnasceturridiculusmus."+ "Morbiposueresedmetusacconsectetur.Etiamquisipsumvitaejustotempusmaximus.Sedultriciesplaceratvolutpat."+ "Integerlacuslectusmaximusacornarequissagittissitametjusto."+ "Cumsociisnatoquepenatibusetmagnisdisparturientmontesnasceturridiculusmus.Maecenasindictumpurussedrutrumex.Nullafacilisi."+ "Integerfinibusfinibusmietpharetranislfaucibusvel.Maecenasegetdolorlacinialobortisjustovelullamcorpersem."+ "Vivamusaliquetpurusidvariusornaresapienrisusrutrumnisitinciduntmollissemnequeidmetus."+ "Etiamquiseleifendpurus.Nuncfelisnuncscelerisqueiddignissimnecfinibusalibero."+ "Nuncsemperenimnequesitamethendreritpurusfacilisisac.Maurisdapibussemperfelisdignissimgravida."+ "Aeneanultricesblanditnequealiquamfinibusodioscelerisqueac.Aliquamnecmassaeumaurisfaucibusfringilla."+ "Etiamconsequatligulanisisitametaliquamnibhtemporquis.Nuncinterdumdignissimnullaatsodalesarcusagittiseu."+ "Proinpharetrametusneclacuspulvinarsedvolutpatliberoornare.Sedligulanislpulvinarnonlectuseublanditfacilisisante."+ "Sedmollisnislalacusauctorsuscipit.Inhachabitasseplateadictumst.Phasellussitametvelittemporvenenatisfeliseuegestasrisus."+ "Aliquameteratsitametnibhcommodofinibus.Morbiefficiturodiovelpulvinariaculis."+ "Aeneantemporipsummassaaconsecteturturpisfaucibusultrices.Praesentsodalesmaurisquisportafermentum."+ "Etiamnisinislvenenatisvelauctorutullamcorperinjusto.Proinvelligulaerat.Phasellusvestibulumgravidamassanonfeugiat."+ "Maecenaspharetraeuismodmetusegetefficitur.Suspendisseamet@gmail.com"));
    }

    @Test
    public void testEmailAtTLD_1_oe() {
        EmailValidator val = EmailValidator.getInstance(false, true);
        assertTrue(val.isValid("test@com"));
    }

    @Test
    public void testValidator359_1_oe() {
        EmailValidator val = EmailValidator.getInstance(false, true);
        assertFalse(val.isValid("test@.com"));
    }

    @Test
    public void testValidator374_1_oe() {
        assertTrue(validator.isValid("abc@school.school"));
    }

    @Test
    public void testValidator473_4_1_oe() { // Show that can override domain validation
        assertFalse(validator.isValidDomain("test.local"));
    }

    @Test
    public void testValidator473_4_2_oe() { // Show that can override domain validation
        // removed other assertion
        List<DomainValidator.Item> items = new ArrayList<>();
        items.add(new DomainValidator.Item(DomainValidator.ArrayType.GENERIC_PLUS, new String[]{"local"}));
        EmailValidator val = new EmailValidator(true, false, DomainValidator.getInstance(true, items));
        assertTrue(val.isValidDomain("test.local"));
    }

}
