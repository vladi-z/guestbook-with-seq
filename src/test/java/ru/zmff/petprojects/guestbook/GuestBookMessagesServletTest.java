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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Arquillian.class)
public class GuestBookMessagesServletTest extends FunctionalTest {

    @Inject
    GuestBookMessagesService messagesService;

    @Test
    public void servletGetTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/guestbook.jsp")).thenReturn(dispatcher);

        GuestBookServlet servlet = new GuestBookServlet(messagesService);
        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/guestbook.jsp");
    }

    @Test
    public void servletPostTest() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("testUser");
        when(request.getUserPrincipal()).thenReturn(principal);
        when(request.getParameterValues("text")).thenReturn(new String[]{"test message"});

        int messagesCount = messagesService.getMessages().size();
        GuestBookServlet servlet = new GuestBookServlet(messagesService);
        servlet.doPost(request, response);
        messagesCount++;
        assertEquals(messagesService.getMessages().size(), messagesCount);

        GuestBookMessage message = messagesService.getMessages().get(messagesCount - 1);
        assertEquals("testUser", message.getUsername());
        assertEquals("test message", message.getMessage());

        verify(response, times(1)).sendRedirect("gb");
    }
}
