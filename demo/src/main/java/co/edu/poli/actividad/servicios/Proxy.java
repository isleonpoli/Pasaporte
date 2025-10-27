package co.edu.poli.actividad.servicios;

public class Proxy implements PasaporteInterface {
    private PasaporteAdapter pasaporte;
    private String rol;
    
    public Proxy(PasaporteAdapter pasaporte, String rol) {
        this.pasaporte = pasaporte;
        this.rol = rol;
    }
    
    private boolean checkAccess() {
        // Solo admin tiene acceso completo
        return "ADMIN".equals(rol);
    }
     
    private boolean checkBasicAccess() {
        // Admin y TRABAJADOR tienen acceso básico
        return "TRABAJADOR".equals(rol);
    }
  
    @Override
    public String mostrarInformacionCompleta() {
        if (checkAccess()) {
            return pasaporte.mostrarInformacionCompleta();
        } else {
            return "ACCESO DENEGADO: Rol '" + rol + "' no tiene permisos para ver información completa";
        }
    }
    
    @Override
    public String mostrarInformacionBasica() {
        if (checkBasicAccess()) {
            return "=== INFORMACIÓN BÁSICA ===\n" + pasaporte.mostrarInformacionBasica();
        } else {
            return "ACCESO DENEGADO: Rol '" + rol + "' no tiene permisos para ver información básica";
        }
    }
    
    
    // Método adicional para mostrar resumen según rol
    public String mostrarInformacionSegunRol() {
        switch (rol) {
            case "ADMIN":
                return mostrarInformacionCompleta();
            case "TRABAJADOR":
                return mostrarInformacionBasica();
            case "INVITADO":
                return "Rol Invitado - No puede acceder a la informacion";
            default:
                return "Rol no reconocido: " + rol;
        }
    }
}