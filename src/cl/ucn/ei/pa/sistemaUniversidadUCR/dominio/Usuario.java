package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

public class Usuario {
    private String rut;
    private String correo;
    private String contrasena;

    public Usuario(String rut, String correo, String contrasena) {
        this.rut = rut;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}