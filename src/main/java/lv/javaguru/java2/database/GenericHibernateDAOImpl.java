package lv.javaguru.java2.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Vitolds on 12/24/2016.
 */
public class GenericHibernateDAOImpl<T> implements GenericHibernateDAO<T>{

    protected Class<T> persistentClass;

    public GenericHibernateDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void create(T obj)  throws DBException {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public T getById(int id)  throws DBException  {
        return (T)sessionFactory.getCurrentSession().get(persistentClass, id);
    }

    @Override
    public void delete(int id)   throws DBException {
        Session session = sessionFactory.getCurrentSession();
        T obj = (T) session.get(persistentClass, id);
        session.delete(obj);
    }

    @Override
    public void update(T obj)  throws DBException  {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public List<T> getAll()  throws DBException  {
        return sessionFactory.getCurrentSession().createCriteria(persistentClass).list();
    }

}
