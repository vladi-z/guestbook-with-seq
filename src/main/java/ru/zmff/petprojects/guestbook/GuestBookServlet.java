/*
 * Copyright (C) 2019, Vladislav Ziminov <zmff@zmff.ru>. All Rights Reserved.
 * This file is a part of project guestbook-with-seq.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package ru.zmff.petprojects.guestbook;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gb")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"ADMIN", "USER"})
)
public class GuestBookServlet extends HttpServlet {

    private final GuestBookMessagesService messagesService;

    @Inject
    public GuestBookServlet(GuestBookMessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("messages", messagesService.getMessages());
        req.getRequestDispatcher("/guestbook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GuestBookMessage message = new GuestBookMessage();
        message.setUsername(req.getUserPrincipal().getName());
        message.setMessage(req.getParameterValues("text")[0]);
        messagesService.addMessage(message);
        resp.sendRedirect("gb");
    }
}
