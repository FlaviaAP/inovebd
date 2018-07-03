package com.i9.daos;

import java.util.List;

public interface GenericDao<T> {

    List<T> getAll();

    boolean save(T object);

    boolean delete(T object);

    boolean update(T object);
}
