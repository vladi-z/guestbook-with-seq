package ru.zmff.petprojects.guestbook;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;

public class FunctionalTest {

    @SuppressWarnings("unused")
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
}
