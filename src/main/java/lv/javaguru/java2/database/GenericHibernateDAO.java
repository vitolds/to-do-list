package lv.javaguru.java2.database;

import java.util.List;

/**
 * Created by Vitolds on 12/24/2016.
 */
public interface GenericHibernateDAO<T> {
    void create(T obj);
    T getById(int id);
    void delete(int id);
    void update(T obj);
    List<T> getAll();
}
