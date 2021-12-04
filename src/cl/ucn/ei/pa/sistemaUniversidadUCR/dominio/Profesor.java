package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaAsignaturas;
import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaParalelos;

public class Profesor extends Usuario {
    private int salario;
    private ListaParalelos listaParalelos;
    private ListaAsignaturas listaAsignaturas;

    public Profesor(String rut, String correo, String contrasena, int salario) {
        super(rut, correo, contrasena);
        this.salario = salario;
        this.listaParalelos = new ListaParalelos(1000);
        this.listaAsignaturas = new ListaAsignaturas(1000);
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public ListaParalelos getListaParalelos() {
        return listaParalelos;
    }

    public void setListaParalelos(ListaParalelos listaParalelos) {
        this.listaParalelos = listaParalelos;
    }

    public ListaAsignaturas getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(ListaAsignaturas listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }
}