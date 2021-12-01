package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

import cl.ucn.ei.pa.sistemaUniversidadUCR.dominio.Asignatura;

public class ListaAsignaturas {
    private int cantidad;
    private int maximo;
    private Asignatura[] lista;

    public ListaAsignaturas(int maximo) {
        this.maximo = maximo;
        this.lista = new Asignatura[this.maximo];
        this.cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public boolean agregarAsignatura(Asignatura asignatura) {
        if (cantidad < maximo) {
            this.lista[cantidad++] = asignatura;
            return true;
        }

        return false;
    }

    public Asignatura buscarAsignatura(int codigo) {
        int i;
        for (i = 0; i < cantidad && lista[i].getCodigo() != codigo; i++);

        if (i != cantidad) {
            return lista[i];
        }

        return null;
    }

    public Asignatura getAsignaturaI(int indice) {
        if (indice < maximo) {
            return lista[indice];
        }

        return null;
    }

    public boolean eliminarAsignatura(int codigo) {
        int i;
        for (i = 0; i < cantidad && lista[i].getCodigo() != codigo; i++);

        if (i != cantidad) {
            for (int j = i; j < cantidad - 1; j++) {
                lista[j] = lista[j + 1];
            }

            cantidad--;
            return true;
        }

        return false;
    }
}