package cl.ucn.ei.pa.sistemaUniversidadUCR.dominio;

public class AsignaturaOpcional extends Asignatura {
    private int creditosPrerrequisitos;
    private Paralelo paralelo;

    public AsignaturaOpcional(int codigo, String nombre, int creditos, int creditosPrerrequisitos) {
        super(codigo, nombre, creditos);
        this.creditosPrerrequisitos = creditosPrerrequisitos;
    }

    public int getCreditosPrerrequisitos() {
        return creditosPrerrequisitos;
    }

    public void setCreditosPrerrequisitos(int creditosPrerrequisitos) {
        this.creditosPrerrequisitos = creditosPrerrequisitos;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }
}