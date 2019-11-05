/*******************************************************************************
 * Copyright (c) 2019 Edgeworx, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Saeid Baghbidi
 * Kilton Hopkins
 * Neha Naithani
 *******************************************************************************/
package org.eclipse.iofog.message_bus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * @author nehanaithani
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MessageIdGenerator.class})
public class MessageIdGeneratorTest {
    private MessageIdGenerator messageIdGenerator;

    @Before
    public void setUp() throws Exception {
        messageIdGenerator = spy(new MessageIdGenerator());
    }

    @After
    public void tearDown() throws Exception {
        reset(messageIdGenerator);
    }

    /**
     * Test generate
     */
    @Test
    public void testGenerate() {
        try {
            assertNotNull("Message Id not null",
                    messageIdGenerator.generate(currentTimeMillis()));
            PowerMockito.verifyPrivate(messageIdGenerator, times(2))
                    .invoke("toBase58", anyLong());
        } catch (Exception e) {
            fail("This should not happen");
        }
    }

    /**
     * Test getNextId
     */
    @Test
    public void testGetNextId() {
        assertNotNull("Next Id", messageIdGenerator.getNextId());
        assertFalse(messageIdGenerator.getNextId().contains("?"));
    }
}