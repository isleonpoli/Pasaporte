package co.edu.poli.actividad.repositorio;

import java.util.List;

public interface FiltrableRepository<T> extends Repository<T, String> {
    List<T> findByIdContains(String criterio);
}