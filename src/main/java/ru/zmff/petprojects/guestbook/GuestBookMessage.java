package ru.zmff.petprojects.guestbook;

import lombok.Getter;
import lombok.Setter;

public class GuestBookMessage {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String message;
}
