/*
 * Copyright (C) 2019, Vladislav Ziminov <zmff@zmff.ru>. All Rights Reserved.
 * This file is a part of project guestbook-with-seq.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package ru.zmff.petprojects.guestbook;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GuestBookMessagesServiceTest extends FunctionalTest {

    @Inject
    GuestBookMessagesService messagesService;

    @Test
    public void addMessage() {
        int messagesCount = messagesService.getMessages().size();
        GuestBookMessage msg = new GuestBookMessage();
        msg.setUsername("test");
        msg.setMessage("test message");
        messagesService.addMessage(msg);
        messagesCount++;
        assertEquals(messagesService.getMessages().size(), messagesCount);
    }
}
