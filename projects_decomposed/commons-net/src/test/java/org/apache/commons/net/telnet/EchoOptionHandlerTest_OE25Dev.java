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
package org.apache.commons.net.telnet;

/**
 * JUnit test class for EchoOptionHandler
 */
public class EchoOptionHandlerTest_OE25Dev extends TelnetOptionHandlerTestAbstract
{

    /**
     * setUp for the test.
     */
    @Override
    protected void setUp()
    {
        opthand1 = new EchoOptionHandler();
        opthand2 = new EchoOptionHandler(true, true, true, true);
        opthand3 = new EchoOptionHandler(false, false, false, false);
    }

    /**
     * test of the constructors.
     */

    /**
     * test of client-driven subnegotiation.
     * Checks that no subnegotiation is made.
     */

    /**
     * test of server-driven subnegotiation.
     * Checks that no subnegotiation is made.
     */

    public void testConstructors_1_oe()
    {
        assertEquals(opthand1.getOptionCode(), TelnetOption.ECHO);
    }

    public void testStartSubnegotiation_1_oe()
    {
        final int resp1[] = opthand1.startSubnegotiationLocal();
        final int resp2[] = opthand1.startSubnegotiationRemote();

        assertEquals(resp1, null);
    }

    public void testStartSubnegotiation_2_oe()
    {
        final int resp1[] = opthand1.startSubnegotiationLocal();
        final int resp2[] = opthand1.startSubnegotiationRemote();

        // removed other assertion
        assertEquals(resp2, null);
    }

    public void testAnswerSubnegotiation_1_oe()
    {
        final int subn[] =
        {
            TelnetCommand.IAC, TelnetCommand.SB, TelnetOption.ECHO,
            1, TelnetCommand.IAC, TelnetCommand.SE,
        };

        final int resp1[] = opthand1.answerSubnegotiation(subn, subn.length);

        assertEquals(resp1, null);
    }

}
