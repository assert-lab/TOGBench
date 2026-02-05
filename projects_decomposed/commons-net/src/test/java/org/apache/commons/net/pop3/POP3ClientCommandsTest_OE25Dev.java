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

import java.io.IOException;
import java.io.Reader;
import java.net.InetAddress;

import junit.framework.TestCase;

/**
 *
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
public class POP3ClientCommandsTest_OE25Dev extends TestCase
{
    POP3Client pop3Client;

    String user = POP3Constants.user;
    String emptyUser = POP3Constants.emptyuser;
    String password = POP3Constants.password;
    String mailhost = POP3Constants.mailhost;

    public POP3ClientCommandsTest_OE25Dev(final String name)
    {
        super(name);
    }

    private void reset() throws IOException
    {
        //Case where this is the first time reset is called
        if (pop3Client == null)
        {
            //Do nothing
        }
        else if (pop3Client.isConnected())
        {
            pop3Client.disconnect();
        }
        pop3Client = null;
        pop3Client = new POP3Client();
    }

    private void connect() throws Exception
    {
        pop3Client.connect(InetAddress.getByName(mailhost));
        assertTrue(pop3Client.isConnected());
        assertEquals(POP3.AUTHORIZATION_STATE, pop3Client.getState());
    }

    private void login() throws Exception
    {
        assertTrue(pop3Client.login(user, password));
        assertEquals(POP3.TRANSACTION_STATE, pop3Client.getState());
    }

public void testNoopCommand_1_oe() throws Exception
    {
        reset();
        connect();

        //Should fail before authorization
        assertFalse(pop3Client.noop());
    }

public void testNoopCommand_2_oe() throws Exception
    {
        reset();
        connect();

        //Should fail before authorization
        // removed other assertion

        //Should pass in transaction state
        login();
        assertTrue(pop3Client.noop());
    }

public void testNoopCommand_3_oe() throws Exception
    {
        reset();
        connect();

        //Should fail before authorization
        // removed other assertion

        //Should pass in transaction state
        login();
        // removed other assertion

        //Should fail in update state
        pop3Client.setState(POP3.UPDATE_STATE);
        assertFalse(pop3Client.noop());
    }

public void testStatus_1_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        assertNull(pop3Client.status());
    }

public void testStatus_2_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        assertTrue(msg.number > 0);
    }

public void testStatus_3_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        assertTrue(msg.size > 0);
    }

public void testStatus_4_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        assertNull(msg.identifier);
    }

public void testStatus_5_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should also pass on a mailbox with no mail in it
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testStatus_6_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should also pass on a mailbox with no mail in it
        reset();
        connect();
        // removed other assertion
        final POP3MessageInfo msg2 = pop3Client.status();
        assertEquals(0, msg2.number);
    }

public void testStatus_7_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should also pass on a mailbox with no mail in it
        reset();
        connect();
        // removed other assertion
        final POP3MessageInfo msg2 = pop3Client.status();
        // removed other assertion
        assertEquals(0, msg2.size);
    }

public void testStatus_8_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should also pass on a mailbox with no mail in it
        reset();
        connect();
        // removed other assertion
        final POP3MessageInfo msg2 = pop3Client.status();
        // removed other assertion
        // removed other assertion
        assertNull(msg2.identifier);
    }

public void testStatus_9_oe() throws Exception
    {
        reset();
        connect();

        //Should fail in authorization state
        // removed other assertion

        //Should pass on a mailbox with mail in it
        login();
        final POP3MessageInfo msg = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should also pass on a mailbox with no mail in it
        reset();
        connect();
        // removed other assertion
        final POP3MessageInfo msg2 = pop3Client.status();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        pop3Client.logout();

        //Should fail in the 'update' state
        reset();
        connect();
        login();
        pop3Client.setState(POP3.UPDATE_STATE);
        assertNull(pop3Client.status());
    }

public void testListMessagesOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        assertTrue(msg.length > 0);
    }

public void testListMessagesOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            assertNotNull(msg[i]);
    }
    }

public void testListMessagesOnFullMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            assertEquals(i+1, msg[i].number);
    }
    }

public void testListMessagesOnFullMailbox_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            // removed other assertion
            assertTrue(msg[i].size > 0);
    }
    }

public void testListMessagesOnFullMailbox_5_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertNull(msg[i].identifier);
    }
    }

public void testListMessagesOnFullMailbox_6_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        //Now test from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listMessages();
        assertNull(msg);
    }

public void testListMessageOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        assertNotNull(msg);
    }

public void testListMessageOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        assertEquals(1, msg.number);
    }

public void testListMessageOnFullMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        assertTrue(msg.size > 0);
    }

public void testListMessageOnFullMailbox_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(msg.identifier);
    }

public void testListMessageOnFullMailbox_5_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listMessage(0);
        assertNull(msg);
    }

public void testListMessageOnFullMailbox_6_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listMessage(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listMessage(100000);
        assertNull(msg);
    }

public void testListMessageOnFullMailbox_7_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listMessage(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listMessage(100000);
        // removed other assertion

        //Now retrieve a msg with a negative index
        msg = pop3Client.listMessage(-2);
        assertNull(msg);
    }

public void testListMessageOnFullMailbox_8_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listMessage(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listMessage(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listMessage(100000);
        // removed other assertion

        //Now retrieve a msg with a negative index
        msg = pop3Client.listMessage(-2);
        // removed other assertion

        //Now try to get a valid message from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listMessage(1);
        assertNull(msg);
    }

public void testListMessagesOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testListMessagesOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        POP3MessageInfo[] msg = pop3Client.listMessages();
        assertEquals(0, msg.length);
    }

public void testListMessagesOnEmptyMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        //Now test from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listMessages();
        assertNull(msg);
    }

public void testListMessageOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testListMessageOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        //The first message is always at index 1
        final POP3MessageInfo msg = pop3Client.listMessage(1);
        assertNull(msg);
    }

public void testListUniqueIDsOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        assertTrue(msg.length > 0);
    }

public void testListUniqueIDsOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            assertNotNull(msg[i]);
    }
    }

public void testListUniqueIDsOnFullMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            assertEquals(i + 1, msg[i].number);
    }
    }

public void testListUniqueIDsOnFullMailbox_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            // removed other assertion
            assertNotNull(msg[i].identifier);
    }
    }

public void testListUniqueIDsOnFullMailbox_5_oe() throws Exception
    {
        reset();
        connect();
        login();

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        // removed other assertion

        for(int i = 0; i < msg.length; i++)
        {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        //Now test from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listUniqueIdentifiers();
        assertNull(msg);
    }

public void testListUniqueIDOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        assertNotNull(msg);
    }

public void testListUniqueIDOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        assertEquals(1, msg.number);
    }

public void testListUniqueIDOnFullMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        // removed other assertion
        assertNotNull(msg.identifier);
    }

public void testListUniqueIDOnFullMailbox_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listUniqueIdentifier(0);
        assertNull(msg);
    }

public void testListUniqueIDOnFullMailbox_5_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listUniqueIdentifier(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listUniqueIdentifier(100000);
        assertNull(msg);
    }

public void testListUniqueIDOnFullMailbox_6_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listUniqueIdentifier(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listUniqueIdentifier(100000);
        // removed other assertion

        //Now retrieve a msg with a negative index
        msg = pop3Client.listUniqueIdentifier(-2);
        assertNull(msg);
    }

public void testListUniqueIDOnFullMailbox_7_oe() throws Exception
    {
        reset();
        connect();
        login();

        //The first message is always at index 1
        POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion

        //Now retrieve a message from index 0
        msg = pop3Client.listUniqueIdentifier(0);
        // removed other assertion

        //Now retrieve a msg that is not there
        msg = pop3Client.listUniqueIdentifier(100000);
        // removed other assertion

        //Now retrieve a msg with a negative index
        msg = pop3Client.listUniqueIdentifier(-2);
        // removed other assertion

        //Now try to get a valid message from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listUniqueIdentifier(1);
        assertNull(msg);
    }

public void testListUniqueIDsOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testListUniqueIDsOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        assertEquals(0, msg.length);
    }

public void testListUniqueIDsOnEmptyMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        POP3MessageInfo[] msg = pop3Client.listUniqueIdentifiers();
        // removed other assertion

        //Now test from the update state
        pop3Client.setState(POP3.UPDATE_STATE);
        msg = pop3Client.listUniqueIdentifiers();
        assertNull(msg);
    }

public void testListUniqueIdentifierOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testListUniqueIdentifierOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion

        //The first message is always at index 1
        final POP3MessageInfo msg = pop3Client.listUniqueIdentifier(1);
        assertNull(msg);
    }

public void testRetrieveMessageOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();
        int reportedSize = 0;
        int actualSize = 0;

        final POP3MessageInfo[] msg = pop3Client.listMessages();
        assertTrue(msg.length > 0);
    }

public void testRetrieveMessageOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();
        int reportedSize = 0;
        int actualSize = 0;

        final POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for (int i = msg.length; i > 0; i--)
        {
            reportedSize = msg[i - 1].size;
            final Reader r = pop3Client.retrieveMessage(i);
            assertNotNull(r);
    }
    }

public void testRetrieveMessageOnFullMailbox_3_oe() throws Exception
    {
        reset();
        connect();
        login();
        int reportedSize = 0;
        int actualSize = 0;

        final POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for (int i = msg.length; i > 0; i--)
        {
            reportedSize = msg[i - 1].size;
            final Reader r = pop3Client.retrieveMessage(i);
            // removed other assertion

            int delaycount = 0;
            if (!r.ready())
            {
                //Give the reader time to get the message
                //from the server
                Thread.sleep(500);
                delaycount++;
                //but don't wait too long
                if (delaycount == 4)
                {
                    break;
                }
            }
            while(r.ready())
            {
                r.read();
                actualSize++;
            }
            //Due to variations in line termination
            //on different platforms, the actual
            //size may vary slightly.  On Win2KPro, the
            //actual size is 2 bytes larger than the reported
            //size.
            assertTrue(actualSize >= reportedSize);
    }
    }

public void testRetrieveMessageOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testRetrieveMessageOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion
        assertNull(pop3Client.retrieveMessage(1));
    }

public void testRetrieveMessageShouldFails_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        assertNull(pop3Client.retrieveMessage(0));
    }

public void testRetrieveMessageShouldFails_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        assertNull(pop3Client.retrieveMessage(-2));
    }

public void testRetrieveMessageShouldFails_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        // removed other assertion

        //Try to get a message that is not there
        assertNull(pop3Client.retrieveMessage(100000));
    }

public void testRetrieveMessageShouldFails_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        // removed other assertion

        //Try to get a message that is not there
        // removed other assertion

        //Change states and try to get a valid message
        pop3Client.setState(POP3.UPDATE_STATE);
        assertNull(pop3Client.retrieveMessage(1));
    }

public void testRetrieveMessageTopOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();
        final int numLines = 10;

        final POP3MessageInfo[] msg = pop3Client.listMessages();
        assertTrue(msg.length > 0);
    }

public void testRetrieveMessageTopOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();
        final int numLines = 10;

        final POP3MessageInfo[] msg = pop3Client.listMessages();
        // removed other assertion

        for (int i = 0; i < msg.length; i++)
        {
            Reader r = pop3Client.retrieveMessageTop(i + 1, numLines);
            assertNotNull(r);
    }
    }

public void testRetrieveOverSizedMessageTopOnFullMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        login();
        int actualSize = 0;

        final POP3MessageInfo msg = pop3Client.listMessage(1);
        final int reportedSize = msg.size;

        //Now try to retrieve more lines than exist in the message
        final Reader r = pop3Client.retrieveMessageTop(1, 100000);
        assertNotNull(r);
    }

public void testRetrieveOverSizedMessageTopOnFullMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        login();
        int actualSize = 0;

        final POP3MessageInfo msg = pop3Client.listMessage(1);
        final int reportedSize = msg.size;

        //Now try to retrieve more lines than exist in the message
        final Reader r = pop3Client.retrieveMessageTop(1, 100000);
        // removed other assertion

        int delaycount = 0;
        while(!r.ready())
        {
            //Give the reader time to get the message
            //from the server
            Thread.sleep(500);
            delaycount++;
            //but don't wait too long
            if (delaycount == 4)
            {
                break;
            }
        }
        while(r.ready())
        {
            r.read();
            actualSize++;
        }
        //Due to variations in line termination
        //on different platforms, the actual
        //size may vary slightly.  On Win2KPro, the
        //actual size is 2 bytes larger than the reported
        //size.
        assertTrue(actualSize >= reportedSize);
    }

public void testRetrieveMessageTopOnEmptyMailbox_1_oe() throws Exception
    {
        reset();
        connect();
        assertTrue(pop3Client.login(emptyUser, password));
    }

public void testRetrieveMessageTopOnEmptyMailbox_2_oe() throws Exception
    {
        reset();
        connect();
        // removed other assertion
        assertNull(pop3Client.retrieveMessageTop(1, 10));
    }

public void testRetrieveMessageTopShouldFails_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        assertNull(pop3Client.retrieveMessageTop(0, 10));
    }

public void testRetrieveMessageTopShouldFails_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        assertNull(pop3Client.retrieveMessageTop(-2, 10));
    }

public void testRetrieveMessageTopShouldFails_3_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        // removed other assertion

        //Try to get a message that is not there
        assertNull(pop3Client.retrieveMessageTop(100000, 10));
    }

public void testRetrieveMessageTopShouldFails_4_oe() throws Exception
    {
        reset();
        connect();
        login();

        //Try to get message 0
        // removed other assertion

        //Try to get a negative message
        // removed other assertion

        //Try to get a message that is not there
        // removed other assertion

        //Change states and try to get a valid message
        pop3Client.setState(POP3.UPDATE_STATE);
        assertNull(pop3Client.retrieveMessageTop(1, 10));
    }

public void testDeleteWithReset_1_oe() throws Exception
    {
        reset();
        connect();
        login();
        //Get the original number of messages
        POP3MessageInfo[] msg = pop3Client.listMessages();
        final int numMessages = msg.length;
        int numDeleted = 0;

        //Now delete some and logout
        for (int i = 0; i < numMessages - 1; i ++)
        {
            pop3Client.deleteMessage(i + 1);
            numDeleted++;
        }
        //Check to see that they are marked as deleted
        assertEquals(numMessages, numDeleted + 1);
    }

public void testDeleteWithReset_2_oe() throws Exception
    {
        reset();
        connect();
        login();
        //Get the original number of messages
        POP3MessageInfo[] msg = pop3Client.listMessages();
        final int numMessages = msg.length;
        int numDeleted = 0;

        //Now delete some and logout
        for (int i = 0; i < numMessages - 1; i ++)
        {
            pop3Client.deleteMessage(i + 1);
            numDeleted++;
        }
        //Check to see that they are marked as deleted
        // removed other assertion

        //Now reset to unmark the messages as deleted
        pop3Client.reset();

        //Logout and come back in
        pop3Client.logout();
        reset();
        connect();
        login();

        //Get the new number of messages, because of
        //reset, new number should match old number
        msg = pop3Client.listMessages();
        assertEquals(numMessages, msg.length);
    }

public void testDelete_1_oe() throws Exception
    {
        reset();
        connect();
        login();
        //Get the original number of messages
        POP3MessageInfo[] msg = pop3Client.listMessages();
        final int numMessages = msg.length;
        int numDeleted = 0;

        //Now delete some and logout
        for (int i = 0; i < numMessages - 3; i ++)
        {
            pop3Client.deleteMessage(i + 1);
            numDeleted++;
        }
        //Check to see that they are marked as deleted
        assertEquals(numMessages, numDeleted + 3);
    }

public void testDelete_2_oe() throws Exception
    {
        reset();
        connect();
        login();
        //Get the original number of messages
        POP3MessageInfo[] msg = pop3Client.listMessages();
        final int numMessages = msg.length;
        int numDeleted = 0;

        //Now delete some and logout
        for (int i = 0; i < numMessages - 3; i ++)
        {
            pop3Client.deleteMessage(i + 1);
            numDeleted++;
        }
        //Check to see that they are marked as deleted
        // removed other assertion

        //Logout and come back in
        pop3Client.logout();
        reset();
        connect();
        login();

        //Get the new number of messages, because of
        //reset, new number should match old number
        msg = pop3Client.listMessages();
        assertEquals(numMessages - numDeleted, msg.length);
    }

public void testResetAndDeleteShouldFails_1_oe() throws Exception
    {
        reset();
        connect();
        login();

        pop3Client.setState(POP3.UPDATE_STATE);
        assertFalse(pop3Client.reset());
    }

public void testResetAndDeleteShouldFails_2_oe() throws Exception
    {
        reset();
        connect();
        login();

        pop3Client.setState(POP3.UPDATE_STATE);
        // removed other assertion

        assertFalse(pop3Client.deleteMessage(1));
    }

}
