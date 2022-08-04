package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T txr(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void txv(final Consumer<Session> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void add(Accident accident) {
        this.txr(
                session -> session.save(accident)
        );
    }

    public void edit(int id, Accident accident) {
        accident.setId(id);
        this.txv(
                session -> session.update(accident)
        );
    }

    public void delete(int id) {
        Accident accident = new Accident();
        accident.setId(id);
        this.txv(
                session -> session.delete(accident)
        );
    }

    public Collection<Accident> getAll() {
        return this.txr(
                session -> session.createQuery("select a from Accident a", Accident.class).list()
        );
    }

    public Accident getById(int id) {
        return this.txr(
                session -> session.createQuery(
                        "select t from Accident t JOIN FETCH t.rules where t.id = :id", Accident.class).
                        setParameter("id", id).uniqueResult()
        );
    }

    public List<AccidentType> getTypes() {
        return this.txr(
                session -> session.createQuery("select t from AccidentType t", AccidentType.class).list()
        );
    }

    public AccidentType getTypeById(int id) {
        return this.txr(
                session -> session.createQuery("select t from AccidentType t where t.id = :id", AccidentType.class).
                        setParameter("id", id).uniqueResult()
        );
    }

    public List<Rule> getRules() {
        return this.txr(
                session -> session.createQuery("select t from Rule t order by t.id", Rule.class).list()
        );
    }

    public Rule getRuleById(int id) {
        return this.txr(
                session -> session.createQuery("select t from Rule t where t.id = :id", Rule.class).
                        setParameter("id", id).uniqueResult()
        );
    }
}
