package co.edu.poli.actividad.repositorio;

import java.util.List;


public interface FiltrableRepository<T> {


    List<T> findByIdContains(String criterio);
}