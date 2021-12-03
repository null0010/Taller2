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
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);
        int indiceEstudiante = listaUsuarios.getCantidad() - 1;
        Estudiante estudiante = (Estudiante) listaUsuarios.getUsuarioI(indiceEstudiante);
        boolean isAsignaturaAgregada = false;
        if (asignatura instanceof AsignaturaObligatoria) {
            AsignaturaObligatoria asignaturaPlantilla = (AsignaturaObligatoria) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int nivel = asignaturaPlantilla.getNivel();
            int creditos = asignaturaPlantilla.getCreditos();
            AsignaturaObligatoria asignaturaObligatoriaCursada = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
            asignaturaObligatoriaCursada.setNotaFinal(nota);
            isAsignaturaAgregada = estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignaturaObligatoriaCursada);
        }
        else {
            AsignaturaOpcional asignaturaPlantilla = (AsignaturaOpcional) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int creditos = asignaturaPlantilla.getCreditos();
            int creditosPrerrequisitos = asignaturaPlantilla.getCreditosPrerrequisitos();
            AsignaturaOpcional asignaturaOpcionalCursada = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
            isAsignaturaAgregada = estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignaturaOpcionalCursada);
        }

        return isAsignaturaAgregada;
    }

    public boolean asociarAsignaturaCursadaEstudiante(int codigoAsignatura, double nota, String correoEstudiante) {
        return false;
    }

    public boolean ingresarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo) {
        Paralelo paralelo = listaParalelos.buscarParalelo(numeroParalelo);
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);
        int indiceEstudiante = listaUsuarios.getCantidad() - 1;
        Estudiante estudiante = (Estudiante) listaUsuarios.getUsuarioI(indiceEstudiante);
        boolean isAsignaturaAgregada = false;
        if (asignatura instanceof AsignaturaObligatoria) {
            AsignaturaObligatoria asignaturaPlantilla = (AsignaturaObligatoria) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int nivel = asignaturaPlantilla.getNivel();
            int creditos = asignaturaPlantilla.getCreditos();
            AsignaturaObligatoria asignaturaObligatoriaCursada = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
            isAsignaturaAgregada = estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignaturaObligatoriaCursada);
        }
        else {
            AsignaturaOpcional asignaturaPlantilla = (AsignaturaOpcional) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int creditos = asignaturaPlantilla.getCreditos();
            int creditosPrerrequisitos = asignaturaPlantilla.getCreditosPrerrequisitos();
            AsignaturaOpcional asignaturaOpcionalCursada = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
            isAsignaturaAgregada = estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignaturaOpcionalCursada);
        }

        paralelo.getListaEstudiantes().agregarUsuario(estudiante);
        return isAsignaturaAgregada;
    }

    public boolean asociarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo, String correoEstudiante) {
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        Paralelo paralelo = listaParalelos.buscarParalelo(numeroParalelo);
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);
        if (asignatura == null || paralelo == null) {
            throw new NullPointerException("La asignatura y/o paralelo no existen.");
        }

        AsignaturaObligatoria asignaturaPlantilla = (AsignaturaObligatoria) asignatura;
        String nombreAsignatura = asignaturaPlantilla.getNombre();
        int nivel = asignaturaPlantilla.getNivel();
        int creditos = asignaturaPlantilla.getCreditos();
        AsignaturaObligatoria asignaturaInscrita = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
        asignaturaInscrita.getListaParalelos().agregarParalelo(paralelo);

        return estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaInscrita);
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
        AsignaturaObligatoria asignaturaPlantilla = (AsignaturaObligatoria) listaAsignaturas.buscarAsignatura(codigo);
        String nombreAsignatura = asignaturaPlantilla.getNombre();
        int nivel = asignaturaPlantilla.getNivel();
        int creditos = asignaturaPlantilla.getCreditos();
        AsignaturaObligatoria asignaturaPrerrequisito = new AsignaturaObligatoria(codigo, nombreAsignatura, creditos, nivel);
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
        asignatura.getListaParalelos().agregarParalelo(paralelo);
        paralelo.setProfesor(profesor);
        profesor.getListaParalelos().agregarParalelo(paralelo);
        return listaParalelos.agregarParalelo(paralelo);
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

    public String obtenerDatosAsignaturasInscribiblesEstudiante(String correoEstudiante) {
        String salida = "";
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        for (int i = 0; i < listaAsignaturas.getCantidad(); i++) {
            Asignatura asignatura = listaAsignaturas.getAsignaturaI(i);
            if (asignatura instanceof AsignaturaObligatoria) {
                AsignaturaObligatoria asignaturaObligatoria = (AsignaturaObligatoria) asignatura;
                if (estudiante.getNivel() <= asignaturaObligatoria.getNivel() && estudiante.getCredito() >= asignaturaObligatoria.getCreditos()) {
                    salida += asignaturaObligatoria.getCodigo()
                            + "\n"
                            + asignaturaObligatoria.getNombre()
                            +"\n\n";
                }
            }
        }

        return salida;
    }

    public String obtenerDatosParalelosAsignatura(int codigoAsignatura) {
        String salida = "";
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);
        if (asignatura == null) {
            throw new NullPointerException("La asignatura ingresada no existe.");
        }

        for (int i = 0; i < asignatura.getListaParalelos().getCantidad(); i++) {
            Paralelo paralelo = asignatura.getListaParalelos().getParaleloI(i);
            if (paralelo.getListaEstudiantes().getCantidad() < 100) {
                salida += paralelo.getNumero() + "\n\n";
            }
        }

        return salida;
    }

    public String obtenerDatosAsignaturasInscritasEstudiante(String correoEstudiante) {
        String salida = "";
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        ListaAsignaturas listaAsignaturasInscritas = estudiante.getListaAsignaturasInscritas();
        for (int i = 0; i < listaAsignaturasInscritas.getCantidad(); i++) {
            Asignatura asignatura = listaAsignaturasInscritas.getAsignaturaI(i);
            salida += asignatura.getCodigo()
                    + "\n"
                    + asignatura.getNombre()
                    +"\n\n";
        }

        return salida;
    }

    public boolean eliminarAsignaturaInscritaEstudiante(String correoEstudiante, int codigoAsignatura) {
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        return estudiante.getListaAsignaturasInscritas().eliminarAsignatura(codigoAsignatura);
    }

    public String obtenerParalelosProfesor(String correoProfesor) {
        String salida = "";
        Profesor profesor = (Profesor) listaUsuarios.buscarUsuarioPorCorreo(correoProfesor);
        ListaParalelos listaParalelos = profesor.getListaParalelos();
        for (int i = 0; i < listaParalelos.getCantidad(); i++) {
            Paralelo paralelo = listaParalelos.getParaleloI(i);
            salida += paralelo.getNumero() + "\n";
        }

        return salida;
    }


    public String obtenerEstudiantesParaleloProfesor(String correoProfesor, int numeroParalelo) {
        String salida = "";
        Profesor profesor = (Profesor) listaUsuarios.buscarUsuarioPorCorreo(correoProfesor);
        Paralelo paralelo = profesor.getListaParalelos().buscarParalelo(numeroParalelo);
        if (paralelo == null) {
            throw new NullPointerException("Que el paralelo no existe");
        }

        ListaUsuarios listaEstudiantes = paralelo.getListaEstudiantes();
        for (int i = 0; i < listaEstudiantes.getCantidad(); i++) {
            salida += listaEstudiantes.getUsuarioI(i).getCorreo() + "\n";
        }

        return salida;
    }
}