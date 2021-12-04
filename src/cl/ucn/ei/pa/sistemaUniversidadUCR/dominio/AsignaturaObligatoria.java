package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaAsignaturas;
import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaParalelos;

public class AsignaturaObligatoria extends Asignatura {
    private int nivel;
    private ListaAsignaturas listaAsignaturasPrerrequisitos;
    private ListaParalelos listaParalelos;

    public AsignaturaObligatoria(int codigo, String nombre, int creditos, int nivel) {
        super(codigo, nombre, creditos);
        this.nivel = nivel;
        this.listaAsignaturasPrerrequisitos = new ListaAsignaturas(1000);
        this.listaParalelos = new ListaParalelos(4);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ListaAsignaturas getListaAsignaturasPrerrequisitos() {
        return listaAsignaturasPrerrequisitos;
    }

    public void setListaAsignaturasPrerrequisitos(ListaAsignaturas listaAsignaturasPrerrequisitos) {
        this.listaAsignaturasPrerrequisitos = listaAsignaturasPrerrequisitos;
    }

    public ListaParalelos getListaParalelos() {
        return listaParalelos;
    }

    public void setListaParalelos(ListaParalelos listaParalelos) {
        this.listaParalelos = listaParalelos;
    }
}