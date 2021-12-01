package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

import cl.ucn.ei.pa.sistemaUniversidadUCR.dominio.Paralelo;

public class ListaParalelos {
    private int cantidad;
    private int maximo;
    private Paralelo[] lista;

    public ListaParalelos(int maximo) {
        this.maximo = maximo;
        this.lista = new Paralelo[this.maximo];
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

    public boolean agregarParalelo(Paralelo paralelo) {
        if (cantidad < maximo) {
            this.lista[cantidad++] = paralelo;
            return true;
        }

        return false;
    }

    public Paralelo buscarParalelo(int numero) {
        int i;
        for (i = 0; i < cantidad && lista[i].getNumero() != numero; i++);

        if (i != cantidad) {
            return lista[i];
        }

        return null;
    }

    public Paralelo getParaleloI(int indice) {
        if (indice < maximo) {
            return lista[indice];
        }

        return null;
    }

    public boolean eliminarParalelo(int numero) {
        int i;
        for (i = 0; i < cantidad && lista[i].getNumero() != numero; i++);

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