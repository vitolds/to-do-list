package lv.javaguru.java2.database;

import java.util.List;

/**
 * Created by Vitolds on 12/24/2016.
 */
public interface GenericHibernateDAO<T> {
    void create(T obj);
    T getById(long id);
    void delete(long id);
    void update(T obj);
    List<T> getAll();
}
