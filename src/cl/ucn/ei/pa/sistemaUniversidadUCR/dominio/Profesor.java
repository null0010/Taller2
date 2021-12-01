package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

public class Profesor extends Usuario {
    private int salario;
    //private ListaParalelos listaParalelos
    //private ListaAsignaturas listaAsignaturasDictando

    public Profesor(String rut, String correo, String contrasena, int salario) {
        super(rut, correo, contrasena);
        this.salario = salario;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}