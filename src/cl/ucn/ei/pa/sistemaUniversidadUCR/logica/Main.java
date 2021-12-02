package cl.ucn.ei.pa.sistemaUniversidadUCR.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.SequenceInputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SistemaUniversidadUCR sistema = new SistemaUniversidadUCRImpl();
        cargarArchivos(sistema);
        ejecutandoAplicacion(sistema, input);
        input.close();
    }



    private static void ejecutandoAplicacion(SistemaUniversidadUCR sistema, Scanner input) {
        boolean isEjecutandoAplicacion = true;
        boolean isCredencialesCorrectas = false;
        while (isEjecutandoAplicacion) {
            System.out.print("Ingrese su correo: ");
            String correo = input.next();
            input.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contrasena = input.nextLine();
            if (sistema.isUsuarioAdministrador(correo, contrasena)) {
                ejecutarMenuAdmin(sistema, input);
                isEjecutandoAplicacion = false;
            }
            else {
                if (sistema.isUsuarioRegistrado(correo)) {
                    if (sistema.isContraseñaCorrecta(correo, contrasena)) {
                        if (sistema.isUsuarioProfesor(correo)) {
                            ejecutarMenuProfesor(sistema, input);
                        }
                        else {
                            ejecutarMenuEstudiante(sistema, input, correo);
                        }

                        isEjecutandoAplicacion = false;
                    }
                    else {
                        System.out.println("Correo y/o contraseña incorrectos.");
                    }
                }
                else {
                    System.out.println("Correo y/o contraseña incorrectos.");
                }
            }

         /*   if (sistema.isUsuarioRegistrado(correo) || sistema.) {
                System.out.out.println("Correo y/o contraseña incorrectos.");
            }¨*/

        }
    }

    private static void ejecutarMenuProfesor(SistemaUniversidadUCR sistema, Scanner input) {
        System.out.print("Ingrese una fecha [00/00/0000]: ");
        String fecha = obtenerFechaFormateada(input.next());
        while (fecha.equals("")) {
            System.out.println("Formato incorrecto!!");
            System.out.print("Ingrese un fecha [00/00/0000]: ");
            fecha = obtenerFechaFormateada(input.next());
        }

        if (sistema.isInicioSemestre(fecha)) {

        }

    }

    private static void ejecutarMenuEstudiante(SistemaUniversidadUCR sistema, Scanner input, String correoEstudiante) {
        System.out.print("Ingrese una fecha [00/00/0000]: ");
        String fecha = obtenerFechaFormateada(input.next());
        while (fecha.equals("")) {
            System.out.println("Formato incorrecto!!");
            System.out.print("Ingrese un fecha [00/00/0000]: ");
            fecha = obtenerFechaFormateada(input.next());
        }

        if (sistema.isInicioSemestre(fecha)) {
            ejecutarOpcionesInicioSemestre(sistema, input, correoEstudiante);
        }
        else if (sistema.isMitadSemestre(fecha)) {
            ejecutarOpcionesMitadSemestre(sistema, input, correoEstudiante);
        }
        else if (sistema.isVacaciones(fecha)) {
            System.out.println("“Disfrute sus vacaciones.");
        }
        else {
            System.out.println("No hay acciones disponibles.");
        }
    }

    private static void ejecutarOpcionesInicioSemestre(SistemaUniversidadUCR sistema, Scanner input, String correoEstudiante) {
        boolean isCerrarSistema = false;
        while (!isCerrarSistema) {
            System.out.println("[1] Inscribir asignatura");
            System.out.println("[2] Eliminar de asignaturas");
            System.out.println("[3] Cerrar sistema");
            System.out.print("Ingrese una opción: ");
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    String datosAsignaturas = sistema.obtenerDatosAsignaturasInscribiblesEstudiante(correoEstudiante);
                    if (!datosAsignaturas.equals("")) {
                        System.out.println(datosAsignaturas);
                        System.out.print("Ingrese el codigo de la asignatura: ");
                        int codigoAsignatura = input.nextInt();
                        System.out.println();
                        String datosParalelos = sistema.obtenerDatosParalelosAsignatura(codigoAsignatura);
                        if (!datosParalelos.equals("")) {
                            System.out.print("Ingrese el numero del paralelo: ");
                            int numeroParalelo = input.nextInt();
                            sistema.asociarAsignaturaInscritaEstudiante(codigoAsignatura, numeroParalelo, correoEstudiante);
                        }
                        else {
                            System.out.print("Todos los paralelos se encuentran llenos.");
                        }
                    }
                    else {
                        System.out.print("Ninguna asignatura disponible.");
                    }

                    break;

                case 2:
                    String datosAsignaturasInscritas = sistema.obtenerDatosAsignaturasInscritasEstudiante(correoEstudiante);
                    if (!datosAsignaturasInscritas.equals("")) {
                        System.out.print("Ingrese el codigo de la asignatura: ");
                        int codigoAsignatura = input.nextInt();
                        boolean isEliminadaAsignatura = sistema.eliminarAsignaturaInscritaEstudiante(correoEstudiante, codigoAsignatura);
                        if (isEliminadaAsignatura) {
                            System.out.println("La asignatura ha sido eliminada.");
                        }
                        else {
                            System.out.println("La asignatura no pudo eliminadar.");
                        }
                    }
                    else {
                        System.out.print("No tienes ninguna asignatura inscrita.");
                    }

                    break;

                case 3:
                    isCerrarSistema = true;
                    break;

                default:
                    System.out.println("Opción fuera de rango.");
                    break;
            }
        }
    }

    private static void ejecutarOpcionesMitadSemestre(SistemaUniversidadUCR sistema, Scanner input, String correoEstudiante) {
        boolean isCerrarSistema = false;
        while (!isCerrarSistema) {
            System.out.println("[1] Elimar asignatura");
            System.out.println("[2] Cerrar sistema");
            System.out.print("Ingrese una opción: ");
            int opcion = input.nextInt();
            switch (opcion) {
                case 1:
                    String datosAsignaturasInscritas = sistema.obtenerDatosAsignaturasInscritasEstudiante(correoEstudiante);
                    if (!datosAsignaturasInscritas.equals("")) {
                        System.out.print("Ingrese el codigo de la asignatura: ");
                        int codigoAsignatura = input.nextInt();
                        boolean isEliminadaAsignatura = sistema.eliminarAsignaturaInscritaEstudiante(correoEstudiante, codigoAsignatura);
                        if (isEliminadaAsignatura) {
                            System.out.println("La asignatura ha sido eliminada.");
                        }
                        else {
                            System.out.println("La asignatura no pudo eliminadar.");
                        }
                    }
                    else {
                        System.out.print("No tienes ninguna asignatura inscrita.");
                    }

                    break;

                case 2:
                    isCerrarSistema = true;
                    break;

                default:
                    System.out.println("Opción fuera de rango.");
                    break;
            }
        }
    }

    private static void ejecutarMenuAdmin(SistemaUniversidadUCR sistema, Scanner input) {
        System.out.print("Ingrese una fecha [00/00/0000]: ");
        String fecha = obtenerFechaFormateada(input.next());
        while (fecha.equals("")) {
            System.out.println("Formato incorrecto!!");
            System.out.print("Ingrese un fecha [00/00/0000]: ");
            fecha = obtenerFechaFormateada(input.next());
        }

        if (sistema.isCierreSemestre(fecha)) {
            //ejecutarOpcionesMenuAdmin(sistema, input);
        }
        else if (sistema.isVacaciones(fecha)) {
            System.out.println("“Disfrute sus vacaciones.");
        }
        else {
            System.out.println("No hay acciones disponibles.");
        }
    }


    private static void cargarArchivos(SistemaUniversidadUCR sistema) {
        cargarAsignaturas(sistema);
        cargarProfesores(sistema);
        cargarParalelos(sistema);
        cargarEstudiantes(sistema);
    }

    public static void cargarAsignaturas(SistemaUniversidadUCR sistema) {
        File archivoAsignaturas = new File("archivos/asignaturas.txt");
        try (Scanner scannerFile = new Scanner(archivoAsignaturas)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                int codigoAsignatura = Integer.parseInt(partes[0].strip());
                String nombreAsignatura = partes[1].strip();
                int creditos = Integer.parseInt(partes[2].strip());
                String tipoAsignatura = partes[3].strip();
                if (tipoAsignatura.equalsIgnoreCase("obligatoria")) {
                    int nivel = Integer.parseInt(partes[4].strip());
                    int cantidadAsignaturasPrerrequisitos = Integer.parseInt(partes[5].strip());
                    sistema.ingresarAsignaturaObligatoria(codigoAsignatura, nombreAsignatura, creditos, nivel);
                    for (int i = 6; i < cantidadAsignaturasPrerrequisitos + 6; i++) {
                        int codigoAsignaturaPrerrequisito = Integer.parseInt(partes[i].strip());
                        sistema.ingresarAsignaturaPrerrequisito(codigoAsignaturaPrerrequisito);
                    }
                }
                else {
                    int creditosPrerrequisitos = Integer.parseInt(partes[4].strip());
                    sistema.ingresarAsignaturaOpcional(codigoAsignatura, nombreAsignatura, creditos, creditosPrerrequisitos);
                }


            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void cargarProfesores(SistemaUniversidadUCR sistema) {
        File archivoProfesores = new File("archivos/profesores.txt");
        try (Scanner scannerFile = new Scanner(archivoProfesores)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                String rut = partes[0].strip();
                String correo = partes[1].strip();
                String contrasena = partes[2].strip();
                int salario = Integer.parseInt(partes[3].strip());
                sistema.ingresarProfesor(rut, correo, contrasena, salario);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void cargarParalelos(SistemaUniversidadUCR sistema) {
        File archivoParalelos = new File("archivos/paralelos.txt");
        try (Scanner scannerFile = new Scanner(archivoParalelos)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                int numeroParalelo = Integer.parseInt(partes[0].strip());
                int codigoAsignatura = Integer.parseInt(partes[1].strip());
                String rutProfesor = partes[2].strip();
                sistema.ingresarParalelo(numeroParalelo, codigoAsignatura, rutProfesor);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void cargarEstudiantes(SistemaUniversidadUCR sistema) {
        File archivoEstudiantes = new File("archivos/estudiantes.txt");
        try (Scanner scannerFile = new Scanner(archivoEstudiantes)) {
            while (scannerFile.hasNext()) {
                String linea = scannerFile.nextLine();
                String[] partes = linea.split(",");
                String rut = partes[0].strip();
                String correo = partes[1].strip();
                int nivel = Integer.parseInt(partes[2].strip());
                String contrasena = partes[3].strip();
                linea = scannerFile.nextLine();
                int cantidadAsignaturasCursadas = Integer.parseInt(linea);
                linea = scannerFile.nextLine();
                sistema.ingresarEstudiante(rut, correo, nivel, contrasena);
                for (int i = 0; i < cantidadAsignaturasCursadas; i++) {
                    partes = linea.split(",");
                    int codigoAsignaturaCursada = Integer.parseInt(partes[0].strip());
                    double nota = Double.parseDouble(partes[1].strip());
                    sistema.ingresarAsignaturaCursadaEstudiante(codigoAsignaturaCursada, nota);
                    linea = scannerFile.nextLine();
                }

                int cantidadAsignaturasInscritas = Integer.parseInt(linea);
                for (int i = 0; i < cantidadAsignaturasInscritas; i++) {
                    partes = linea.split(",");
                    int codigoAsignaturaInscrita = Integer.parseInt(partes[0].strip());
                    int numeroParalelo = Integer.parseInt(partes[1].strip());
                    sistema.ingresarAsignaturaInscritaEstudiante(codigoAsignaturaInscrita, numeroParalelo);
                    linea = scannerFile.nextLine();
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sobrescribirArchivoEstudiantes(SistemaUniversidadUCR sistemaUniversidadUCR) {

    }

    public static String obtenerFechaFormateada(String fecha) {
        String fechaFormateada = "";
        Pattern DATE_PATTERN = Pattern.compile("(?<day>\\d{2})/(?<month>\\d{2})/(?<year>\\d{4})");
        Matcher matcherDate = DATE_PATTERN.matcher(fecha);
        if (matcherDate.matches()) {
            fechaFormateada = matcherDate.group(3) + "-" + matcherDate.group(2) + "-" + matcherDate.group(1);
        }

        return fechaFormateada;
    }
}