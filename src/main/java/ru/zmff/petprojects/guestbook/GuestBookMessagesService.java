package ru.zmff.petprojects.guestbook;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.LinkedList;

@Stateful
public class GuestBookMessagesService {
    @Getter
    private LinkedList<GuestBookMessage> messages;

    @PostConstruct
    private void init () {
        messages = new LinkedList<>();
    }

    @SuppressWarnings("WeakerAccess")
    public void addMessage (GuestBookMessage message) {
        messages.add(message);
    }
}
