package ru.zmff.petprojects.guestbook;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateful
public class GuestBookMessagesService {
    @PersistenceContext(unitName = "default")
    EntityManager entityManager;

    @SuppressWarnings("WeakerAccess")
    public List<GuestBookMessage> getMessages() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GuestBookMessage> q = cb.createQuery(GuestBookMessage.class);
        Root<GuestBookMessage> root = q.from(GuestBookMessage.class);

        Order o = null;
        try {
            o = cb.asc(root.get(GuestBookMessage.class.getDeclaredField("id").getName()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        q.select(root).orderBy(o);
        TypedQuery<GuestBookMessage> query = entityManager.createQuery(q);
        return query.getResultList();
    }

    @SuppressWarnings("WeakerAccess")
    public void addMessage (GuestBookMessage message) {
        entityManager.persist(message);
    }
}
