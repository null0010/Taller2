package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaUsuarios;

public class Asignatura {
    private int codigo;
    private String nombre;
    private int creditos;
    private double notaFinal;
    private ListaUsuarios listaEstudiantes;
    public Asignatura(int codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.notaFinal = 0.0;
        this.listaEstudiantes = new ListaUsuarios(1000);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public ListaUsuarios getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ListaUsuarios listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}