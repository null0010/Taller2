package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

import cl.ucn.ei.pa.sistemaUniversidadUCR.logica.ListaUsuarios;

public class Paralelo {
    private int numero;
    private ListaUsuarios listaEstudiantes;
    private Profesor profesor;

    public Paralelo(int numero) {
        this.numero = numero;
        this.listaEstudiantes = new ListaUsuarios(1000);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ListaUsuarios getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ListaUsuarios listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}