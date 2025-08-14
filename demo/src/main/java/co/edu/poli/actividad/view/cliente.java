package co.edu.poli.actividad.view;

import java.util.ArrayList;
import java.util.List;

import co.edu.poli.actividad.model.Ciudad;
import co.edu.poli.actividad.model.Pais;
import co.edu.poli.actividad.model.Pasaporte;
import co.edu.poli.actividad.model.Persona;

public class cliente {

 
    
public static void main(String[] args) {

       ArrayList <Ciudad> listaCiudades = new ArrayList<>();
    Ciudad ciudad1 = new Ciudad("CO","Bogotá");
    //Ciudad ciudad2 = new Ciudad("MX","Ciudad de Mexico");

    listaCiudades.add(ciudad1);
    //listaCiudades.add(ciudad2);

    Persona persona1 = new Persona("1","profesor","25/12/2004");

    Pais pais1 = new Pais("1", "Colombia", listaCiudades);

   Pasaporte pasaporte1 = new Pasaporte("1ACO","14/08/25",persona1,pais1);

   pasaporte1.mostrarInfo();

}   


}
