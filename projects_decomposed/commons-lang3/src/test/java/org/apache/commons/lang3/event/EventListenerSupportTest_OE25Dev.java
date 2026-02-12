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

package org.apache.commons.lang3.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

/**
 * @since 3.0
 */
public class EventListenerSupportTest_OE25Dev {

    @Test
    public void testSubclassInvocationHandling() throws PropertyVetoException {

        final
        EventListenerSupport<VetoableChangeListener> eventListenerSupport = new EventListenerSupport<VetoableChangeListener>(
                VetoableChangeListener.class) {
            private static final long serialVersionUID = 1L;

            @Override
            protected java.lang.reflect.InvocationHandler createInvocationHandler() {
                return new ProxyInvocationHandler() {
                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public Object invoke(final Object proxy, final Method method, final Object[] args)
                            throws Throwable {
                        return "vetoableChange".equals(method.getName())
                                && "Hour".equals(((PropertyChangeEvent) args[0]).getPropertyName()) ? null
                                : super.invoke(proxy, method, args);
                    }
                };
            }
        };

        final VetoableChangeListener listener = EasyMock.createNiceMock(VetoableChangeListener.class);
        eventListenerSupport.addListener(listener);
        final Object source = new Date();
        final PropertyChangeEvent ignore = new PropertyChangeEvent(source, "Hour", 5, 6);
        final PropertyChangeEvent respond = new PropertyChangeEvent(source, "Day", 6, 7);
        listener.vetoableChange(respond);
        EasyMock.replay(listener);
        eventListenerSupport.fire().vetoableChange(ignore);
        eventListenerSupport.fire().vetoableChange(respond);
        EasyMock.verify(listener);
    }

    private void addDeregisterListener(final EventListenerSupport<VetoableChangeListener> listenerSupport) {
        listenerSupport.addListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(final PropertyChangeEvent e) {
                listenerSupport.removeListener(this);
            }
        });
    }

    private VetoableChangeListener createListener(final List<VetoableChangeListener> calledListeners) {
        return new VetoableChangeListener() {
            @Override
            public void vetoableChange(final PropertyChangeEvent e) {
                calledListeners.add(this);
            }
        };
    }

    @Test
    public void testAddNullListener_1_oe() throws Exception {
        final EventListenerSupport<VetoableChangeListener> listenerSupport = EventListenerSupport.create(VetoableChangeListener.class);
        try {
    listenerSupport.addListener(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testRemoveNullListener_1_oe() throws Exception {
        final EventListenerSupport<VetoableChangeListener> listenerSupport = EventListenerSupport.create(VetoableChangeListener.class);
        try {
    listenerSupport.removeListener(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

    @Test
    public void testCreateWithNonInterfaceParameter_1_oe() throws Exception {
        try {
    EventListenerSupport.create(String.class);
    org.junit.jupiter.api.Assertions.fail("IllegalArgumentException");
} catch (IllegalArgumentException e) {
}
    }

    @Test
    public void testCreateWithNullParameter_1_oe() throws Exception {
        try {
    EventListenerSupport.create(null);
    org.junit.jupiter.api.Assertions.fail("NullPointerException");
} catch (NullPointerException e) {
}
    }

}
