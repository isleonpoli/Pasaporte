package co.edu.poli.actividad.repositorio;

import java.util.*;

//import co.edu.poli.actividad.model.Entidad;


public interface Interface2 <Entidad> {
    String insert(Entidad e);
    Entidad findById(String id);
    List<Entidad> findAll();
    String update(Entidad e);
    boolean delete(String id);
}