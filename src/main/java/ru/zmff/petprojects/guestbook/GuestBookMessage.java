/*
 * Copyright (C) 2019, Vladislav Ziminov <zmff@zmff.ru>. All Rights Reserved.
 * This file is a part of project guestbook-with-seq.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package ru.zmff.petprojects.guestbook;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class GuestBookMessage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String message;
}
