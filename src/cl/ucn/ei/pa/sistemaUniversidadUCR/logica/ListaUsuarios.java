package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

import cl.ucn.ei.pa.sistemaUniversidadUCR.dominio.Usuario;

public class ListaUsuarios {
    private int cantidad;
    private int maximo;
    private Usuario[] lista;

    public ListaUsuarios(int maximo) {
        this.maximo = maximo;
        this.lista = new Usuario[this.maximo];
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

    public boolean agregarUsuario(Usuario usuario) {
        if (cantidad < maximo) {
            this.lista[cantidad++] = usuario;
            return true;
        }

        return false;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getCorreo().equals(correo); i++);

        if (i != cantidad) {
            return lista[i];
        }

        return null;
    }

    public Usuario buscarUsuarioPorRut(String rut) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getRut().equals(rut); i++);

        if (i != cantidad) {
            return lista[i];
        }

        return null;
    }

    public Usuario getUsuarioI(int indice) {
        if (indice < maximo) {
            return lista[indice];
        }

        return null;
    }

    public boolean eliminarUsuario(String correo) {
        int i;
        for (i = 0; i < cantidad && !lista[i].getCorreo().equals(correo); i++);

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