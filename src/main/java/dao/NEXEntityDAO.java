package dao;

import logic.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;

public class NEXEntityDAO<Bean> implements NEXEntityDAOInterface<Bean> {
    protected final Class<Bean> typeParameterClass;


    public NEXEntityDAO(Class<Bean> typeParameterClass)
    {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Bean del = (Bean) session.get(typeParameterClass, id);
        session.delete(del);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Bean> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = String.format("from %s",typeParameterClass.getCanonicalName());
        Query SQLQuery = session.createQuery(hql);
        ArrayList<Bean> result = (ArrayList<Bean>) SQLQuery.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return result;
    }

    @Override
    public Bean getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Bean result = (Bean) session.get(typeParameterClass, id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return result;
    }

    @Override
    public void update(Bean object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void add(Bean object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Deprecated
    public void clear()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = String.format("delete from %s",typeParameterClass.getCanonicalName());
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
