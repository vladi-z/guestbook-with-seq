package ru.zmff.petprojects.guestbook;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GuestBookMessagesServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(GuestBookMessagesService.class)
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"), "web.xml")
                .addAsWebResource(new File("src/main/webapp/guestbook.jsp"), "guestbook.jsp") // added to avoid file removal
                .addAsWebResource(new File("src/main/webapp/index.jsp"), "index.jsp")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/glassfish-web.xml"), "glassfish-web.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        ;
    }

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
