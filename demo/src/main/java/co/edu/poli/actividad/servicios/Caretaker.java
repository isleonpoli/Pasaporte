package co.edu.poli.actividad.servicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Caretaker {

    private final Map<String, List<Memento>> historial = new HashMap<>();

    public void addMemento(String idPasaporte, Memento memento) {
        historial.computeIfAbsent(idPasaporte, k -> new ArrayList<>()).add(memento);
    }

    public List<Memento> getHistorial(String idPasaporte) {
        return historial.getOrDefault(idPasaporte, Collections.emptyList());
    }
}
