package org.example.interfaces;

import java.util.List;

public interface IDAO<T> {

    void begin();

    boolean create(T o);

    boolean update(T o);

    boolean delete(T o);

    T findById(int id);

    void send();

    void close();

    List<T> findAll();

}