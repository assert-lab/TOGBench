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
package org.apache.commons.net.pop3;

import java.io.Reader;

import junit.framework.TestCase;

/**
 * The POP3* tests all presume the existence of the following parameters:
 *   mailserver: localhost (running on the default port 110)
 *   account: username=test; password=password
 *   account: username=alwaysempty; password=password.
 *   mail: At least four emails in the test account and zero emails
 *         in the alwaysempty account
 *
 * If this won't work for you, you can change these parameters in the
 * TestSetupParameters class.
 *
 * The tests were originally run on a default installation of James.
 * Your mileage may vary based on the POP3 server you run the tests against.
 * Some servers are more standards-compliant than others.
 */
public class POP3ConstructorTest_OE25Dev extends TestCase
{
    String user = POP3Constants.user;
    String emptyUser = POP3Constants.emptyuser;
    String password = POP3Constants.password;
    String mailhost = POP3Constants.mailhost;

    public POP3ConstructorTest_OE25Dev(final String name)
    {
        super(name);
    }

    /*
     * This test will ensure that the constants are not inadvertently changed.
     * If the constants are changed in org.apache.commons.net.pop3 for some
     * reason, this test will have to be updated.
     */

public void testConstants_1_oe()
    {
        //From POP3
        assertEquals(110, POP3.DEFAULT_PORT);
    }

public void testConstants_2_oe()
    {
        //From POP3
        // removed other assertion
        assertEquals(-1, POP3.DISCONNECTED_STATE);
    }

public void testConstants_3_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        assertEquals(0, POP3.AUTHORIZATION_STATE);
    }

public void testConstants_4_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(1, POP3.TRANSACTION_STATE);
    }

public void testConstants_5_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(2, POP3.UPDATE_STATE);
    }

public void testConstants_6_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        assertEquals(0, POP3Command.USER);
    }

public void testConstants_7_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        assertEquals(1, POP3Command.PASS);
    }

public void testConstants_8_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        assertEquals(2, POP3Command.QUIT);
    }

public void testConstants_9_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, POP3Command.STAT);
    }

public void testConstants_10_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(4, POP3Command.LIST);
    }

public void testConstants_11_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(5, POP3Command.RETR);
    }

public void testConstants_12_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(6, POP3Command.DELE);
    }

public void testConstants_13_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(7, POP3Command.NOOP);
    }

public void testConstants_14_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(8, POP3Command.RSET);
    }

public void testConstants_15_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(9, POP3Command.APOP);
    }

public void testConstants_16_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
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
        assertEquals(10, POP3Command.TOP);
    }

public void testConstants_17_oe()
    {
        //From POP3
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //From POP3Command
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
        assertEquals(11, POP3Command.UIDL);
    }

public void testPOP3DefaultConstructor_1_oe()
    {
        final POP3 pop = new POP3();

        assertEquals(110, pop.getDefaultPort());
    }

public void testPOP3DefaultConstructor_2_oe()
    {
        final POP3 pop = new POP3();

        // removed other assertion
        assertEquals(POP3.DISCONNECTED_STATE, pop.getState());
    }

public void testPOP3DefaultConstructor_3_oe()
    {
        final POP3 pop = new POP3();

        // removed other assertion
        // removed other assertion
        assertNull(pop.reader);
    }

public void testPOP3DefaultConstructor_4_oe()
    {
        final POP3 pop = new POP3();

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(pop.replyLines);
    }

public void testPOP3ClientStateTransition_1_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        assertEquals(110, pop.getDefaultPort());
    }

public void testPOP3ClientStateTransition_2_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        assertEquals(POP3.DISCONNECTED_STATE, pop.getState());
    }

public void testPOP3ClientStateTransition_3_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        assertNull(pop.reader);
    }

public void testPOP3ClientStateTransition_4_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(pop.replyLines);
    }

public void testPOP3ClientStateTransition_5_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        assertEquals(POP3.AUTHORIZATION_STATE, pop.getState());
    }

public void testPOP3ClientStateTransition_6_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }

public void testPOP3ClientStateTransition_7_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }

public void testPOP3ClientStateTransition_8_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }

public void testPOP3ClientStateTransition_9_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_10_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_11_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_12_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_13_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            // removed other assertion

            pop.listUniqueIdentifier(1);
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_14_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            // removed other assertion

            pop.listUniqueIdentifier(1);
            // removed other assertion

            pop.listUniqueIdentifiers();
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_15_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            // removed other assertion

            pop.listUniqueIdentifier(1);
            // removed other assertion

            pop.listUniqueIdentifiers();
            // removed other assertion

            Reader r = pop.retrieveMessage(1);
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_16_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            // removed other assertion

            pop.listUniqueIdentifier(1);
            // removed other assertion

            pop.listUniqueIdentifiers();
            // removed other assertion

            Reader r = pop.retrieveMessage(1);
            // removed other assertion

            //Add some sleep here to handle network latency
            while(!r.ready())
            {
                Thread.sleep(10);
            }
            r.close();
            r = null;

            r = pop.retrieveMessageTop(1, 10);
            assertEquals(POP3.TRANSACTION_STATE, pop.getState());
    }
    }

public void testPOP3ClientStateTransition_17_oe() throws Exception
    {
        final POP3Client pop = new POP3Client();

        //Initial state
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now connect
        pop.connect(mailhost);
        // removed other assertion

        //Now authenticate
        pop.login(user, password);
        // removed other assertion

        //Now do a series of commands and make sure the state stays as it should
        pop.noop();
        // removed other assertion
        pop.status();
        // removed other assertion

        //Make sure we have at least one message to test
        final POP3MessageInfo[] msg = pop.listMessages();

        if (msg.length > 0)
        {
            pop.deleteMessage(1);
            // removed other assertion

            pop.reset();
            // removed other assertion

            pop.listMessage(1);
            // removed other assertion

            pop.listMessages();
            // removed other assertion

            pop.listUniqueIdentifier(1);
            // removed other assertion

            pop.listUniqueIdentifiers();
            // removed other assertion

            Reader r = pop.retrieveMessage(1);
            // removed other assertion

            //Add some sleep here to handle network latency
            while(!r.ready())
            {
                Thread.sleep(10);
            }
            r.close();
            r = null;

            r = pop.retrieveMessageTop(1, 10);
            // removed other assertion

            //Add some sleep here to handle network latency
            while(!r.ready())
            {
                Thread.sleep(10);
            }
            r.close();
            r = null;

        }

        //Now logout
        pop.logout();
        assertEquals(POP3.UPDATE_STATE, pop.getState());
    }

}
