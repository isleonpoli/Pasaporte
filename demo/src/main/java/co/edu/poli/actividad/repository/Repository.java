package co.edu.poli.actividad.repository;

import java.util.List;

public interface Repository <T, ID> {
    String insert(T entity);
    T findById(ID id);
    List<T> findAll();
    String update(T entity);
    boolean delete(ID id);
}