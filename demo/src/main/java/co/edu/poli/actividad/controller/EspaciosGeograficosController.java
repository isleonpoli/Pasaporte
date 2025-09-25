package co.edu.poli.actividad.controller;

import co.edu.poli.actividad.model.Ciudad;
import co.edu.poli.actividad.servicios.CiudadAdapter;
import co.edu.poli.actividad.servicios.EspaciosGeografico;
import co.edu.poli.actividad.servicios.Region;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class EspaciosGeograficosController {

    @FXML
    private TreeView<String> treeView;

    @FXML
    public void initialize() {
        EspaciosGeografico raiz = DatosDeEjemplo();
        TreeItem<String> rootItem = construirTreeItem(raiz);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(true); // Ocultar la raíz "Colombia" si deseas
    }

    private TreeItem<String> construirTreeItem(EspaciosGeografico espacio) {
        TreeItem<String> item = new TreeItem<>(espacio.getNombre());
        for (EspaciosGeografico hijo : espacio.getHijos()) {
            item.getChildren().add(construirTreeItem(hijo));
        }
        return item;
    }

    private EspaciosGeografico DatosDeEjemplo() {
        // Ciudades
        CiudadAdapter bogota = new CiudadAdapter(new Ciudad("11001", "Bogota"));
        CiudadAdapter mosquera = new CiudadAdapter(new Ciudad("25086", "Mosquera"));
        CiudadAdapter madrid = new CiudadAdapter(new Ciudad("25430", "Madrid"));
        CiudadAdapter medellin = new CiudadAdapter(new Ciudad("05001", "Medellin"));
        CiudadAdapter cali = new CiudadAdapter(new Ciudad("76001", "Cali"));
        CiudadAdapter buenaventura = new CiudadAdapter(new Ciudad("76109", "Buenaventura"));
    
    


        // Subregiones
        Region cundinamarca = new Region("Cundinamarca");
        cundinamarca.agregar(bogota);
        cundinamarca.agregar(mosquera);
        cundinamarca.agregar(madrid);

        Region antioquia = new Region("Antioquia");
        antioquia.agregar(medellin);

        Region andina = new Region("Andina");
        andina.agregar(cundinamarca);
        andina.agregar(antioquia);

        Region pacifica = new Region("Pacifica");
        pacifica.agregar(cali);
        pacifica.agregar(buenaventura);

     
        // Región raíz (Colombia)
        Region colombia = new Region("Colombia");
        colombia.agregar(andina);
        colombia.agregar(pacifica);

        return colombia;
    }
}
