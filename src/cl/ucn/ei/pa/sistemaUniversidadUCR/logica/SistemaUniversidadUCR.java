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

    /**
     * Se ingresa una asignatura prerrequisito a la lista de asignaturas prerrequisitos de la asignatura.
     * @param codigo Codigo de la asignatura prerrequisito
     * @return boolean
     */
    boolean ingresarAsignaturaPrerrequisito(int codigo);


    /**
     * Se ingresa un profesor al sistema.
     * @param rut corresponde al rut del profesor.
     * @param correo Corresponde al correo del profesor.
     * @param contrasena Corresponde a la contraseña del profesor.
     * @param salario Corresponde al sueldo del profesor.
     * @return Retorna un boolean que da a conocer si se ingresó con exito o no.
     */
    boolean ingresarProfesor(String rut, String correo, String contrasena, int salario);
    /**
     * Ingresa un paralelo a la lista general de paralelos.
     * @param numeroParalelo Numero del paralelo.
     * @param codigoAsignatura Codigo de la asignatura a la que pertenece el paralelo.
     * @param rutProfesor Rut del profesor al que pertenece el paralelo.
     * @return Retorna un boleano que indica si se ingresó con exito el paralelo.
     */
    boolean ingresarParalelo(int numeroParalelo, int codigoAsignatura, String rutProfesor);
    /**
     * Revisa si un usuario se encuentra registrado.
     * @param correo Correo que se verificará.
     * @return Retorna un booleano que indica si el usuario está registrado o no.
     */
    boolean isUsuarioRegistrado(String correo);
    /**
     * Revisa si el usuario ingresado corresponde a un profesor.
     * @param correo Correo que corresponde al usuario.
     * @return Retorna un booleano uqe indica si es profesor o no.
     */
    boolean isUsuarioProfesor(String correo);
    /**
     * Verifica si el usuario ingresado es administrador.
     * @param correo Correo del ususario ingresado.
     * @param contrasena Contraseña del usuario ingresado.
     * @return Retorna un booleano que indica si el usuario es administrador o no-
     */
    boolean isUsuarioAdministrador(String correo, String contrasena);
    /**
     * Verifica que la contraseña ingresada es correcta,
     * @param correo Correo de el usuario a verificar.
     * @param contrasena Contraseña del usuario a verificar.
     * @return Retorna un booleano que indica si la contraseña del usuario es correcta.
     */
    boolean isContrasenaCorrecta(String correo, String contrasena);
    /**
     * Verifica si la fecha ingresada corresponde a inicio de semestre.
     * @param fecha Fecha ingresada qu se verificará.
     * @return Retorna un booleano que indicará si la fecha corresponde al inicio de semestre.
     */
    boolean isInicioSemestre(String fecha);
    /**
     * Verifica si la fecha ingresada corresponde a mitad de semestre.
     * @param fecha Fecha ingresada qu se verificará.
     * @return Retorna un booleano que indicará si la fecha corresponde a mitad de semestre.
     */
    boolean isMitadSemestre(String fecha);
    /**
     * Verifica si la fecha ingresada corresponde a final de semestre.
     * @param fecha Fecha ingresada qu se verificará.
     * @return Retorna un booleano que indicará si la fecha corresponde al final de semestre.
     */
    boolean isFinalSemestre(String fecha);
    /**
     * Verifica si la fecha ingresada corresponde a cierre de semestre.
     * @param fecha Fecha ingresada qu se verificará.
     * @return Retorna un booleano que indicará si la fecha corresponde al cierre de semestre.
     */
    boolean isCierreSemestre(String fecha);
    /**
     * Verifica si la fecha ingresada corresponde a las vacaciones.
     * @param fecha Fecha ingresada qu se verificará.
     * @return Retorna un booleano que indicará si la fecha corresponde a vacacione.
     */
    boolean isVacaciones(String fecha);
    /**
     * Se obtienen los datos de las asignaturas que podría inscribir el estudiante.
     * @param correoEstudiante correo del estudiante que inscribirá las asignaturas.
     * @return retorna las asignaturas que el estudiante puede inscribir.
     */
    String obtenerDatosAsignaturasInscribiblesEstudiante(String correoEstudiante);
    /**
     * Se obtienen los datos para una asignatura en especifico.
     * @param codigoAsignatura Codigo de la asignatura a de lacual se quieren saber los paralelos.
     * @return  Retorna la informacion de la asignatura consultada.
     */
    String obtenerDatosParalelosAsignatura(int codigoAsignatura);
    /**
     * Se obtienen los datos de las asignaturas que el estudiante ya ha inscrito.
     * @param correoEstudiante Correo del estudiante al que se le consultarán los datos.
     * @return  Retorna las asignaturas que ha ionscrito el estudiante.
     */
    String obtenerDatosAsignaturasInscritasEstudiante(String correoEstudiante);
    /**
     * Se elimina una asignatura inscrita por un estudiante.
     * @param correoEstudiante Correo del estudiante al que se el eliminará la asignatura.
     * @param codigoAsignatura Codigo de la asignatura que desea elminar el estudiante.
     * @return Retorna si se elminó al estudiante de la asignatura.
     */
    boolean eliminarAsignaturaInscritaEstudiante(String correoEstudiante, int codigoAsignatura);
    /**
     * Se obtienen los paralelos que está dictando el profesor.
     * @param correoProfesor Correo del profesor.
     * @return Retorna los paralelos del profesor.
     */
    String obtenerParalelosProfesor(String correoProfesor);
    /**
     * Se obtienen los estudiantes de un paralelo seleccionado por el profesor
     * @param correoProfesor Correo del profesor.
     * @param numeroParalelo Numero del paralelo que digitó el profesor.
     * @return  Retorna los alumnos del paralelo seleccionado por el profesor.
     */
    String obtenerEstudiantesParaleloProfesor(String correoProfesor, int numeroParalelo);

    /**
     * Se obtiene las asignaturas que dicta el profesor
     * @return String
     */
    String obtenerAsignaturasProfesor(String correoProfesor);


    /**
     * Se obtienen los datos de cada uno de los estudiante del profesor
     * @param correoProfesor
     * @return
     */
    String obtenerEstudiantesAsignatura(String correoProfesor, int codigoAsignatura);

    /**
     * Se ingresa la nota final a la asignatura de un estudiante.
     * @param correoEstudiante Correo del estudiante
     * @param codigoAsignatura Codigo del estudiante
     * @param notaFinal Nota final de la asignatura de un estudiante
     */
    void ingresarNotaFinalAlumno(String correoEstudiante, int codigoAsignatura, double notaFinal);

    /**
     * Se obtienen los datos de cada uno de los estudiantes.
     * @return String
     */
    String obtenerDatosEstudiantes();
}