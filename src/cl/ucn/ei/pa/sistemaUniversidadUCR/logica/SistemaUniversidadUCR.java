package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

public interface SistemaUniversidadUCR {

    /**
     * Se ingresa un estudiante a la lista general de usuarios
     * @param rut Rut del estudiante
     * @param correo Correo del estudiante
     * @param nivel Nivel en el que se encuentra el estudiante
     * @param contrasena Contraseña del estudiante
     * @return boolean
     */
    boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena);

    /**
     * Se ingresa una asignatura a la lista individual de asignaturas cursadas del último estudiante de la lista de usuarios
     * @param codigoAsignatura Codigo de la asignatura
     * @param nota Nota de la asignatura
     * @return boolean
     */
    boolean ingresarAsignaturaCursadaEstudiante(int codigoAsignatura, double nota);

    /**
     * Se ingresa una asignatura a la lista individual de asignaturas cursadas de un estudiante específico
     * @param codigoAsignatura Codigo de la asignatura
     * @param nota Nota de la asignatura
     * @param correoEstudiante Correo del estudiante
     * @return boolean
     */
    boolean asociarAsignaturaCursadaEstudiante(int codigoAsignatura, double nota, String correoEstudiante);

    /**
     * Se ingresa una asignatura a la lista individual de asignaturas inscritas del último estudiante de la lista de estudiantes
     * @param codigoAsignatura Codigo de la asignatura
     * @param numeroParalelo Numero del paralelo de la asignatura
     * @return boolean
     */
    boolean ingresarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo);

    /**
     * Se ingresa una asignatura a la lista individual de asignaturas inscritas de un estudiante específico
     * @param codigoAsignatura Codigo del estudiante
     * @param numeroParalelo Numero del paralelo de la asignatura
     * @param correoEstudiante Correo del estudiante
     * @return boolean
     */
    boolean asociarAsignaturaInscritaEstudiante(int codigoAsignatura, int numeroParalelo, String correoEstudiante);

    /**
     *  Se ingresa una asignatura a la lista general de asignaturas
     * @param codigoAsignatura Codigo de la asignatura
     * @param nombreAsignatura Nombre de la asignatura
     * @param creditos Creditos de la asignatura
     * @param nivel Nivel de la asignatura en la malla
     * @return boolean
     */
    boolean ingresarAsignaturaObligatoria(int codigoAsignatura, String nombreAsignatura, int creditos, int nivel);

    /**
     * Se ingresa una asignatura a la lista general de asignaturas
     * @param codigoAsignatura Codigo de la asignatura
     * @param nombreAsignatura Nombre de la asignatura
     * @param creditos Creditos necesarios para tomar la asignatura
     * @param creditosPrerrequisitos Creditos prerrequisitos para tomar la asignatura
     * @return boolean
     */
    boolean ingresarAsignaturaOpcional(int codigoAsignatura, String nombreAsignatura, int creditos, int creditosPrerrequisitos);

    boolean ingresarAsignaturaPrerrequisito(int codigo);

    boolean ingresarProfesor(String rut, String correo, String contrasena, int salario);

    boolean ingresarParalelo(int numeroParalelo, int codigoAsignatura, String rutProfesor);

    boolean isUsuarioRegistrado(String correo);

    boolean isUsuarioProfesor(String correo);

    boolean isUsuarioAdministrador(String correo, String contrasena);

    boolean isContraseñaCorrecta(String correo, String contrasena);

    boolean isInicioSemestre(String fecha);

    boolean isMitadSemestre(String fecha);

    boolean isFinalSemestre(String fecha);

    boolean isCierreSemestre(String fecha);

    boolean isVacaciones(String fecha);

    String obtenerDatosAsignaturasInscribiblesEstudiante(String correoEstudiante);

}