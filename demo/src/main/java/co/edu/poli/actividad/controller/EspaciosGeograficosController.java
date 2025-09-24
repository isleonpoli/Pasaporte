package co.edu.poli.actividad.controller;

import co.edu.poli.actividad.model.Ciudad;
import co.edu.poli.actividad.servicios.CiudadAdapter;
import co.edu.poli.actividad.servicios.EspaciosGeograficos;
import co.edu.poli.actividad.servicios.Regiones;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class EspaciosGeograficosController {

    @FXML
    private TreeView<String> treeView;

    @FXML
    public void initialize() {
        EspaciosGeograficos raiz = DatosDeEjemplo();
        TreeItem<String> rootItem = construirTreeItem(raiz);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true); // Ocultar la raíz "Colombia" si deseas
    }

    private TreeItem<String> construirTreeItem(EspaciosGeograficos espacio) {
        TreeItem<String> item = new TreeItem<>(espacio.getNombre());
        for (EspaciosGeograficos hijo : espacio.getHijos()) {
            item.getChildren().add(construirTreeItem(hijo));
        }
        return item;
    }

    private EspaciosGeograficos DatosDeEjemplo() {
        // Ciudades
        CiudadAdapter bogota = new CiudadAdapter(new Ciudad("11001", "Bogota"));
        CiudadAdapter mosquera = new CiudadAdapter(new Ciudad("25086", "Mosquera"));
        CiudadAdapter madrid = new CiudadAdapter(new Ciudad("25430", "Madrid"));
        CiudadAdapter medellin = new CiudadAdapter(new Ciudad("05001", "Medellin"));
        CiudadAdapter cali = new CiudadAdapter(new Ciudad("76001", "Cali"));
        CiudadAdapter buenaventura = new CiudadAdapter(new Ciudad("76109", "Buenaventura"));

        // Subregiones
        Regiones cundinamarca = new Regiones("Cundinamarca");
        cundinamarca.agregar(bogota);
        cundinamarca.agregar(mosquera);
        cundinamarca.agregar(madrid);

        Regiones antioquia = new Regiones("Antioquia");
        antioquia.agregar(medellin);

        Regiones andina = new Regiones("Andina");
        andina.agregar(cundinamarca);
        andina.agregar(antioquia);

        Regiones pacifica = new Regiones("Pacifica");
        pacifica.agregar(cali);
        pacifica.agregar(buenaventura);

        // Región raíz (Colombia)
        Regiones colombia = new Regiones("Colombia");
        colombia.agregar(andina);
        colombia.agregar(pacifica);

        return colombia;
    }
}
