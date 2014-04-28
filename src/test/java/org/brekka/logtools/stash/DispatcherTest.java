/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.brekka.logtools.stash;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.SocketException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Description of DispatcherTest
 *
 * @author Andrew Taylor (andrew@brekka.org)
 */
public class DispatcherTest {
    
    private Client client;
    private Dispatcher dispatcher;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        client = mock(Client.class);
        dispatcher = new Dispatcher(client, 10, 4);
    }
    
    @After
    public void tearDown() throws Exception {
        dispatcher.close();
    }

    /**
     * Test method for {@link org.brekka.logtools.stash.Dispatcher#dispatchMessage(java.lang.String)}.
     */
    @Test
    public void testNormal() throws Exception {
        dispatcher.dispatchMessage("{ }");
        verify(client).writeEvent("{ }");
    }
    
    @Test
    public void testNoConnection() throws Exception {
        doThrow(new SocketException()).when(client).writeEvent("{ }");
        dispatcher.dispatchMessage("{ }");
    }

}