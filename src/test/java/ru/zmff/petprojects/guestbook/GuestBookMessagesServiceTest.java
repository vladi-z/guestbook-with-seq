package ru.zmff.petprojects.guestbook;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GuestBookMessagesServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(GuestBookMessagesService.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    GuestBookMessagesService messagesService;

    @Test
    public void addMessage() {
        assertEquals(messagesService.getMessages().size(), 0);
        GuestBookMessage msg = new GuestBookMessage();
        msg.setUsername("test");
        msg.setMessage("test message");
        messagesService.addMessage(msg);
        assertEquals(messagesService.getMessages().size(), 1);
    }
}
