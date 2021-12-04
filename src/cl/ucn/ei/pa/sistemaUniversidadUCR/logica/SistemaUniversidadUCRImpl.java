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
            AsignaturaObligatoria asignaturaObligatoriaInscritas = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
            asignaturaObligatoriaInscritas.getListaParalelos().agregarParalelo(paralelo);
            estudiante.setCredito(estudiante.getCredito() - asignaturaPlantilla.getCreditos());
            isAsignaturaAgregada = estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaObligatoriaInscritas);
        }
        else {
            AsignaturaOpcional asignaturaPlantilla = (AsignaturaOpcional) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int creditos = asignaturaPlantilla.getCreditos();
            int creditosPrerrequisitos = asignaturaPlantilla.getCreditosPrerrequisitos();
            AsignaturaOpcional asignaturaOpcionalInscritas = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
            asignaturaOpcionalInscritas.setParalelo(paralelo);
            estudiante.setCredito(estudiante.getCredito() - asignaturaPlantilla.getCreditos());
            isAsignaturaAgregada = estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaOpcionalInscritas);
        }

        asignatura.getListaEstudiantes().agregarUsuario(estudiante);
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
        boolean isAsignaturaAgregada = false;
        if (asignatura instanceof AsignaturaObligatoria) {
            AsignaturaObligatoria asignaturaPlantilla = (AsignaturaObligatoria) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int nivel = asignaturaPlantilla.getNivel();
            int creditos = asignaturaPlantilla.getCreditos();
            AsignaturaObligatoria asignaturaObligatoria = new AsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
            asignaturaObligatoria.getListaParalelos().agregarParalelo(paralelo);
            estudiante.setCredito(estudiante.getCredito() - asignaturaPlantilla.getCreditos());
            isAsignaturaAgregada = estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaObligatoria);
        }
        else {
            AsignaturaOpcional asignaturaPlantilla = (AsignaturaOpcional) asignatura;
            String nombreAsignatura = asignaturaPlantilla.getNombre();
            int creditos = asignaturaPlantilla.getCreditos();
            int creditosPrerrequisitos = asignaturaPlantilla.getCreditosPrerrequisitos();
            AsignaturaOpcional asignaturaOpcional = new AsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
            asignaturaOpcional.setParalelo(paralelo);
            estudiante.setCredito(estudiante.getCredito() - asignaturaPlantilla.getCreditos());
            isAsignaturaAgregada = estudiante.getListaAsignaturasInscritas().agregarAsignatura(asignaturaOpcional);
        }

        asignatura.getListaEstudiantes().agregarUsuario(estudiante);
        return isAsignaturaAgregada;
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
        if (asignatura instanceof AsignaturaObligatoria) {
            AsignaturaObligatoria asignaturaObligatoria = (AsignaturaObligatoria) asignatura;
            asignaturaObligatoria.getListaParalelos().agregarParalelo(paralelo);
        }
        else {
            AsignaturaOpcional asignaturaOpcional = (AsignaturaOpcional) asignatura;
            asignaturaOpcional.setParalelo(paralelo);
        }

        Asignatura asignaturaDictada = new Asignatura(asignatura.getCodigo(), asignatura.getNombre(), asignatura.getCreditos());

        profesor.getListaAsignaturas().agregarAsignatura(asignaturaDictada);
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

    public boolean isContrasenaCorrecta(String correo, String contrasena) {
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
                    salida += "Obligatoria\n"
                            + asignaturaObligatoria.getCodigo()
                            + "\n"
                            + asignaturaObligatoria.getNombre()
                            +"\n\n";
                }
            }
            else {
                AsignaturaOpcional asignaturaOpcional = (AsignaturaOpcional) asignatura;
                if (estudiante.getCredito() >= asignaturaOpcional.getCreditosPrerrequisitos() && estudiante.getCredito() >= asignaturaOpcional.getCreditos()) {
                    salida += "Opcional\n"
                            + asignaturaOpcional.getCodigo()
                            + "\n"
                            + asignaturaOpcional.getNombre()
                            + "\n\n";
                }
            }
        }

        return salida;
    }

    public String obtenerDatosParalelosAsignatura(int codigoAsignatura) {
        String salida = "";
        Asignatura asignatura = listaAsignaturas.buscarAsignatura(codigoAsignatura);

        if (asignatura instanceof AsignaturaObligatoria) {
            AsignaturaObligatoria asignaturaObligatoria = (AsignaturaObligatoria) asignatura;
            for (int i = 0; i < asignaturaObligatoria.getListaParalelos().getCantidad(); i++) {
                Paralelo paralelo = asignaturaObligatoria.getListaParalelos().getParaleloI(i);
                if (paralelo.getListaEstudiantes().getCantidad() < 100) {
                    salida += paralelo.getNumero() + "\n\n";
                }
            }
        }
        else {
            AsignaturaOpcional asignaturaOpcional = (AsignaturaOpcional) asignatura;
            Paralelo paralelo = asignaturaOpcional.getParalelo();
            if (paralelo.getListaEstudiantes().getCantidad() < 100) {
                salida += paralelo.getNumero() + "\n\n";
            }

            salida += asignaturaOpcional.getParalelo().getNumero() + "\n";
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

    public String obtenerAsignaturasProfesor(String correoProfesor) {
        String salida = "";
        Profesor profesor = (Profesor) listaUsuarios.buscarUsuarioPorRut(correoProfesor);
        for (int i = 0; i < profesor.getListaAsignaturas().getCantidad(); i++) {
            Asignatura asignatura = profesor.getListaAsignaturas().getAsignaturaI(i);
            salida += asignatura.getCodigo()
                    + "\n"
                    + asignatura.getNombre()
                    + "\n\n";
        }
        return salida;
    }

    public String obtenerEstudiantesAsignatura(String correoProfesor, int codigoAsignatura) {
        String salida = "";
        Profesor profesor = (Profesor) listaUsuarios.buscarUsuarioPorRut(correoProfesor);
        Asignatura asignatura = profesor.getListaAsignaturas().buscarAsignatura(codigoAsignatura);
        if (asignatura == null) {
            throw new NullPointerException("La asignatura no existe");
        }

        for (int i = 0; i < asignatura.getListaEstudiantes().getCantidad(); i++) {
            Estudiante estudiante = (Estudiante) asignatura.getListaEstudiantes().getUsuarioI(i);
            salida += estudiante.getCorreo() + "\n";
        }

        return salida;
    }

    public void ingresarNotaFinalAlumno(String correoEstudiante, int codigoAsignatura, double notaFinal) {
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        Asignatura asignatura = estudiante.getListaAsignaturasInscritas().buscarAsignatura(codigoAsignatura);
        if (asignatura == null) {
            throw new NullPointerException("La asignatura no existe");
        }

        asignatura.setNotaFinal(notaFinal);
        estudiante.getListaAsignaturasCursadas().agregarAsignatura(asignatura);
    }

    public boolean isSubirNivelEstudiante(String correoEstudiante) {
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        boolean isTodasAsignaturasPasadasNivel = true;
        for (int i = 0; i < listaAsignaturas.getCantidad() && isTodasAsignaturasPasadasNivel; i++) {
            int codigoAsignatura = listaAsignaturas.getAsignaturaI(i).getCodigo();
            isTodasAsignaturasPasadasNivel = estudiante.getListaAsignaturasCursadas().buscarAsignatura(codigoAsignatura) != null;
        }

        return isTodasAsignaturasPasadasNivel;
    }

    public void subirNivelEstudiante(String correoEstudiante) {
        Estudiante estudiante = (Estudiante) listaUsuarios.buscarUsuarioPorCorreo(correoEstudiante);
        estudiante.setNivel(estudiante.getNivel() + 1);
    }

    public String obtenerDatosEstudiantes() {
        String salida = "";
        for (int i = 0; i < listaUsuarios.getCantidad(); i++) {
            Usuario usuario = listaUsuarios.getUsuarioI(i);
            if (usuario instanceof Estudiante) {
                Estudiante estudiante = (Estudiante) usuario;
                salida += estudiante.getRut()
                        + ", "
                        + estudiante.getCorreo()
                        + ", "
                        + estudiante.getNivel()

                        + ", "
                        + estudiante.getContrasena()
                        + "\n";

                salida += estudiante.getListaAsignaturasCursadas().getCantidad() + "\n";
                for (int j = 0; j < estudiante.getListaAsignaturasCursadas().getCantidad(); j++) {
                    Asignatura asignaturaCursada = estudiante.getListaAsignaturasCursadas().getAsignaturaI(j);
                    salida += asignaturaCursada.getCodigo()
                            + ","
                            + asignaturaCursada.getNotaFinal()
                            + "\n";
                }

                salida += estudiante.getListaAsignaturasInscritas().getCantidad() + "\n";
                for (int k = 0; k < estudiante.getListaAsignaturasInscritas().getCantidad(); k++) {
                    Asignatura asignaturaInscrita = estudiante.getListaAsignaturasInscritas().getAsignaturaI(i);
                    if (asignaturaInscrita instanceof AsignaturaObligatoria) {
                        AsignaturaObligatoria asignaturaObligatoria = (AsignaturaObligatoria) asignaturaInscrita;
                        salida += asignaturaObligatoria.getListaParalelos().getParaleloI(0).getNumero()
                                + "\n";
                    }
                    else {
                        AsignaturaOpcional asignaturaOpcional = (AsignaturaOpcional) asignaturaInscrita;
                        salida += asignaturaOpcional.getParalelo().getNumero()
                                + "\n";
                    }
                }

            }
        }

        return salida;
    }
}