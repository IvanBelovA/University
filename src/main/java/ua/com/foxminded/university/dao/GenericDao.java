package ua.com.foxminded.university.dao;

import java.util.List;

public interface GenericDao<T> {
    List<T> findAll();
    T findById(int id);
    void add(T type);
    void delete(T type);
}
