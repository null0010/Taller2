package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaAsignaturas;

public class Estudiante extends Usuario {
    private int nivel;
    private int credito;
    private ListaAsignaturas listaAsignaturasInscritas;
    private ListaAsignaturas listaAsignaturasCursadas;

    public Estudiante(String rut, String correo, String contrasena, int nivel) {
        super(rut, correo, contrasena);
        this.nivel = nivel;
        this.credito = 0;
        this.listaAsignaturasInscritas = new ListaAsignaturas(1000);
        this.listaAsignaturasCursadas = new ListaAsignaturas(1000);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public ListaAsignaturas getListaAsignaturasInscritas() {
        return listaAsignaturasInscritas;
    }

    public void setListaAsignaturasInscritas(ListaAsignaturas listaAsignaturasInscritas) {
        this.listaAsignaturasInscritas = listaAsignaturasInscritas;
    }

    public ListaAsignaturas getListaAsignaturasCursadas() {
        return listaAsignaturasCursadas;
    }

    public void setListaAsignaturasCursadas(ListaAsignaturas listaAsignaturasCursadas) {
        this.listaAsignaturasCursadas = listaAsignaturasCursadas;
    }
}