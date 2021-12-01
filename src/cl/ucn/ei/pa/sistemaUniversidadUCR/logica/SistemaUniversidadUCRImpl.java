package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

import cl.ucn.ei.pa.sistemaUniversidadUCR.dominio.*;

import java.time.LocalDate;


public class SistemaUniversidadUCRImpl implements SistemaUniversidadUCR {
    private ListaUsuarios listaUsuarios;
    private ListaAsignaturas listaAsignaturas;
    private ListaParalelos listaParalelos;

    public SistemaUniversidadUCRImpl() {
        this.listaUsuarios = new ListaUsuarios(1000);
        this.listaAsignaturas = new ListaAsignaturas(1000);
        this.listaParalelos = new ListaParalelos(1000);
    }

    public boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena) {
        Estudiante estudiante = new Estudiante(rut, correo, contrasena, nivel);
        return listaUsuarios.agregarUsuario(estudiante);
    }

    public boolean ingresarAsignaturaCursadaEstudiante(int codigoAsignatura, double nota) {
        if (listaAsignaturas.buscarAsignatura(codigoAsignatura) == null) {
            throw new NullPointerException("La asignatura no existe.");
        }

        AsignaturaObligatoria asignaturaObligatoriaCursada = new AsignaturaObligatoria(codigoAsignatura);
        asignaturaObligatoriaCursada.setNotaFinal(nota);
        int indiceEstudiante = listaUsuarios.getCantidad() - 1;
        Estudiante estudiante = (Estudiante) listaUsuarios.getUsuarioI(indiceEstudiante);
        return estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignaturaObligatoriaCursada);
    }

    public boolean asociarAsignaturaCursadaEstudiante(int codigoAsignatura, double nota, String correoEstudiante) {
        return false;
    }

    public boolean ingresarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo) {
        Paralelo paralelo = listaParalelos.buscarParalelo(numeroParalelo);
        if (paralelo == null || listaAsignaturas.buscarAsignatura(codigoAsignatura) == null) {
            throw new NullPointerException("El paralelo y/o la asignatura no existe.");
        }

        AsignaturaObligatoria asignaturaObligatoriaInscrita = new AsignaturaObligatoria(codigoAsignatura);
        int indiceEstudiante = listaUsuarios.getCantidad() - 1;
        Estudiante estudiante = (Estudiante) listaUsuarios.getUsuarioI(indiceEstudiante);
        paralelo.getListaEstudiantes().agregarUsuario(estudiante);
        paralelo.getListaAsignaturas().agregarAsignatura(asignaturaObligatoriaInscrita);//
        return estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaObligatoriaInscrita);
    }

    public boolean asociarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo, String correoEstudiante) {
        return false;
    }

    public boolean ingresarAsignaturaObligatoria(int codigoAsignatura, String nombreAsignatura, int creditos, int nivel) {
        AsignaturaObligatoria asignaturaObligatoria = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
        return  listaAsignaturas.agregarAsignatura(asignaturaObligatoria);
    }

    public boolean ingresarAsignaturaOpcional(int codigoAsignatura, String nombreAsignatura, int creditos, int creditosPrerrequisitos) {
        AsignaturaOpcional asignaturaOpcional = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
        return listaAsignaturas.agregarAsignatura(asignaturaOpcional);
    }

    public boolean ingresarAsignaturaPrerrequisito(int codigo) {
        AsignaturaObligatoria asignaturaPrerrequisito = new AsignaturaObligatoria(codigo);
        int indiceAsignatura = listaAsignaturas.getCantidad() - 1;
        AsignaturaObligatoria asignaturaObligatoria = (AsignaturaObligatoria) listaAsignaturas.getAsignaturaI(indiceAsignatura);
        return asignaturaObligatoria.getListaAsignaturasPrerrequisitos().agregarAsignatura(asignaturaPrerrequisito);
    }

    public boolean ingresarProfesor(String rut, String correo, String contrasena, int salario) {
        Profesor profesor = new Profesor(rut, correo, contrasena, salario);
        return listaUsuarios.agregarUsuario(profesor);
    }

    public boolean ingresarParalelo(int numeroParalelo, int codigoAsignatura, String rutProfesor) {
        Paralelo paralelo = new Paralelo(numeroParalelo);
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);
        Profesor profesor = (Profesor) listaUsuarios.buscarUsuarioPorRut(rutProfesor);
        if (asignatura == null  || profesor == null) {
            throw new NullPointerException("Asignatura y/o Profesor no existe.");
        }
        paralelo.setProfesor(profesor);
        return paralelo.getListaAsignaturas().agregarAsignatura(asignatura);
    }

    public boolean isUsuarioRegistrado(String correo) {
        return listaUsuarios.buscarUsuarioPorCorreo(correo) != null;
    }

    public boolean isUsuarioProfesor(String correo) {
        Usuario usuario = listaUsuarios.buscarUsuarioPorCorreo(correo);
        return usuario instanceof Profesor;
    }

    public boolean isUsuarioAdministrador(String correo, String contrasena) {
        return correo.equals("Admin") && contrasena.equals("GHI_789");
    }

    public boolean isContraseñaCorrecta(String correo, String contrasena) {
        Usuario usuario = listaUsuarios.buscarUsuarioPorCorreo(correo);
        return usuario.getContrasena().equals(contrasena);
    }

    public boolean isInicioSemestre(String fecha) {
        LocalDate fechaInicio = LocalDate.of(2021 , 3 , 7) ;
        LocalDate fechaFinal = LocalDate.of( 2021 , 5 , 3) ;
        LocalDate fechaActual = LocalDate.parse(fecha);
        return fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFinal);
    }

    public boolean isMitadSemestre(String fecha) {
        return false;
    }

    public boolean isFinalSemestre(String fecha) {
        return false;
    }

    public boolean isCierreSemestre(String fecha) {
        LocalDate fechaFinal = LocalDate.of( 2021 , 7 , 26) ;
        LocalDate fechaActual = LocalDate.parse(fecha);
        return fechaActual.isEqual(fechaFinal);
    }

    public boolean isVacaciones(String fecha) {
        LocalDate fechaInicio = LocalDate.of(2021 , 3 , 8) ;
        LocalDate fechaFinal = LocalDate.of( 2021 , 7 , 26) ;
        LocalDate fechaActual = LocalDate.parse(fecha);
        return fechaActual.isBefore(fechaInicio) && fechaActual.isAfter(fechaFinal);
    }
}