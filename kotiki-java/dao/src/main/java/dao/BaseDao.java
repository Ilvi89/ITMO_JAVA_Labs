package dao;

import model.Cat;
import model.Owner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public abstract class BaseDao<T, Id extends Serializable> {
    protected Session session;
    private Transaction currentTransaction;

    public BaseDao() {
    }

    private SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Owner.class)
                .addAnnotatedClass(Cat.class);
        return cfg.buildSessionFactory();
    }

    public Session openSession() {
        session = getSessionFactory().openSession();
        return session;
    }

    public void closeSession() {
        session.close();
    }

    public void closeSessionWithTransaction() {
        currentTransaction.commit();
        session.close();
    }

    public Session openSessionWithTransaction() {
        session = getSessionFactory().openSession();
        currentTransaction = session.beginTransaction();
        return session;
    }

    protected Session getSession() {
        return session;
    }

    public void save(T entity) {
        getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public abstract T findById(Id id);

    public abstract List<T> findAll();
}
