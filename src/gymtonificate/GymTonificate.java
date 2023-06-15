//GymTonificate implementa la gestión de un gimnasio. 
//El programa principal muestra un menú opciones
/*
El programa principal se encarga de gestionar la solicitud de datos al usuario y la creación de los
objetos necesarios para gestionar el gimnasio. Para ello, el programa inicialmente creará un objeto
ArrayList<Persona> donde se almacenarán los diferentes objetos de tipo Persona que se vayan
creando (monitores, socios y trabajadores). En este caso, se está empleando la propiedad polimórfica de
que un objeto de la subclase también es un objeto de la superclase.
Además, se crearán sendos ArrayList<String> para gestionar la lista de trabajos y la lista de
especialidades que pueden tener los empleados y monitores, respectivamente.
Por tanto, la clase contendrá los siguientes atributos privados y estáticos:
• ArrayList<Persona> listaPersonal.
• ArrayList<String> listaEspecialidades.
• ArrayList<String> listaTrabajos. 
*/

package gymtonificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


public class GymTonificate {
        
            
        //atributos
    
        //Declaramos y construimos el ArrayList que contendrá objetos Persona
        private static final ArrayList<Persona> listaPersonal = new ArrayList<>();
        
         //Declaramos y construimos el ArrayList que contendrá objetos String Especialidades
        private static final ArrayList<String> listaEspecialidades = new ArrayList<>();
        
         //Declaramos y construimos el ArrayList que contendrá objetos String Trabajos
        private static final ArrayList<String> listaTrabajos = new ArrayList<>();
        

   
    public static void main(String[] args) {

        //función que carga por defecto los trabajos a la lista
        precargaTrabajos();

        //función que carga las especilidades por defecto a la lsita
        precargaEspecialidades();

        //función que crea personas por defecto
        precargaPersonas();

        int opcion;//varibale guarda opciones
        
        
        System.out.println("BIENVENIDO A LA APLICACIÓN DE GYM TONIFÍCATE");
        System.out.println("Desarrollado por Joaquín Melero");
        System.out.println("IES Zaidín Vergeles");
        System.out.println("Ciclo Formativo de Desarrollo de Aplicaciones Web");
        System.out.println("(c) 2022");
        System.out.println("-------------------------------------------------\n");


        //Menú principal 
        do {

            //texto por pantalla
            System.out.println("-----MENÚ PRINCIPAL-----\n");

            System.out.println("1.Alta personas (socios, empleados, monitores). \n2.Baja personas. \n3.Modificar Personas."
                    + "\n4.Listar personas existentes. \n5.Listar personas por tipo. \n6.Gestionar especialidades. \n7.Gestionar trabajos. \n8.Salir del Programa.");

            opcion = ValidarDatos.validarIntCompleta("Introduce un número del 1 al 8: ", 1, 8);

            //switch para actuar dependiendo de la opción elegida
            switch (opcion) {

                //alta de personas
                case 1:

                    //función alta personas
                    altaPersonas();
                    
                    System.out.println("\nALTA REALIZADA CON ÉXITO\n");

                    break;

                //Baja de personas
                case 2:

                    //función baja de personas
                    bajaPersonas();
                    
                    System.out.println("\nBAJA REALIZADA CON ÉXITO\n");

                    break;
                    
                //modificar personas    
                case 3:
                    //llama a la función que modifica personas
                    modificarPersonas();
                    
                    System.out.println("\nPERSONA MODIFICADA CON ÉXITO\n");

                    break;
                
                //listar todas las personas de la lista    
                case 4:
                    //listar personas
                    listar("Personas: \n", listaPersonal);

                    break;
                
                //listar personas por tipo     
                case 5:
                    //función listar personas por tipo
                    listarPersonasTipo();

                    break;
                
                //gestionar especialidades    
                case 6:
                    
                    //función para gestionar especialidades
                    gestionarEspecilidades();

                    break;
                 
                //gestionar trabajos    
                case 7:
                    
                    //función para gestionar trabajos
                    gestionarTrabajos();

                    break;
            }

        } while (opcion != 8);

    }

    private static char pedirSexo() {
        Scanner teclado = new Scanner(System.in);

        String respuesta;

        char sexo;

        do {

            System.out.println("¿Mujer? \n(Si o No):");

            respuesta = teclado.nextLine();

            validaRespuesta(respuesta);

        } while (validaRespuesta(respuesta) == false);

        if (respuesta.equals("si") || respuesta.equals("Si") || respuesta.equals("SI")) {

            sexo = 'M';

        } else {

            sexo = 'H';

        }

        return sexo;

    }

    private static boolean validaRespuesta(String respuesta) {

        return respuesta.matches("^([Si,No,si,no]{2})$");
    }

    private static String tipoTrabajo() {

        String especialidad = "";
        

        System.out.println("Selecciona departamento de trabajao \n" +  listar("Trabajos: \n",listaTrabajos));

        int opcion = ValidarDatos.validarIntCompleta("Introduce una opción del 1 al 5 :", 1, 5);

        switch (opcion) {

            case 1:
                especialidad = "Administrativo";

                break;

            case 2:
                especialidad = "Limpieza";

                break;
            case 3:
                especialidad = "Mantenimiento";

                break;

            case 4:
                especialidad = "Seguridad";

                break;

            case 5:
                especialidad = "Proveedor";

                break;

        }

        return especialidad;
    }

    private static float sueldo(String puesto) {

        float sueldo = 0;

        if (puesto.equals("Administrativo") || puesto.equals("Seguridad")) {
            sueldo = 2052;
        } else if (puesto.equals("Limpieza") || puesto.equals("Mantenimiento")) {
            sueldo = 1652;
        } else if (puesto.equals("Proveedor")) {
            sueldo = 6000;
        }

        return sueldo;
    }

    private static String generaExtension() {
        String cadena = "";

        for (int i = 0; i < 3; i++) {
            cadena += (int) (Math.random() * 10);
        }

        return cadena;

    }

    private static String[] addEspecialidad() {

        String lista[] = new String[3];
        lista[0] = "";
        lista[1] = "";
        lista[2] = "";

        int cont = 0;

        System.out.println("Selecciona al menos 3 especialidades sin repetir \n" + listar("Especialidades: \n",listaEspecialidades));

        do {
            int opcion = ValidarDatos.validarIntCompleta("Introduce una opción del 1 al 5 (1.Aerobic, 2.Step, 3.Bodypum, 4.Spinning, 5.Vacía):", 1, 5);

            switch (opcion) {

                case 1:

                    if (!lista[0].equals("Aerobic") && !lista[1].equals("Aerobic") && !lista[2].equals("Aerobic")) {

                        lista[cont] = "Aerobic";
                        cont++;
                    } else {
                        System.out.println("No se puede repetir especialidad");
                    }
                    break;

                case 2:

                    if (!lista[0].equals("Step") && !lista[1].equals("Step") && !lista[2].equals("Step")) {

                        lista[cont] = "Step";
                        cont++;
                    } else {
                        System.out.println("No se puede repetir especialidad");
                    }
                    break;

                case 3:

                    if (!lista[0].equals("Bodypum") && !lista[1].equals("Bodypum") && !lista[2].equals("Bodypum")) {

                        lista[cont] = "Bodypum";
                        cont++;
                    } else {
                        System.out.println("No se puede repetir especialidad");
                    }
                    break;

                case 4:

                    if (!lista[0].equals("Spinning") && !lista[1].equals("Spinning") && !lista[2].equals("Spinning")) {

                        lista[cont] = "Spinning";
                        cont++;
                    } else {
                        System.out.println("No se puede repetir especialidad");
                    }
                    break;
                case 5:

                        lista[cont] = "Vacía";
                        cont++;
                    break;    
            }

        } while (cont <= 2);

        return lista;
    }

    private static boolean estaActivo() {
        Scanner teclado = new Scanner(System.in);

        boolean activo = false;

        String respuesta;

        do {

            System.out.println("¿El monitor está activo? \n(Si o No):");

            respuesta = teclado.nextLine();

            validaRespuesta(respuesta);

        } while (validaRespuesta(respuesta) == false);

        if (respuesta.equals("si") || respuesta.equals("Si") || respuesta.equals("SI")) {

            activo = true;

        }

        return activo;

    }

    //función que busca la Personona por paámetro NIF y devuelve en que posicion de la lista está
    private static boolean buscarEliminarPersonaDni() {

        boolean coincidencia = false;

        String nif = ValidarDatos.pedirValidarNif();

        for (int i = 0; i < listaPersonal.size(); i++) {

            if (listaPersonal.get(i).getDNI().equals(nif)) {

                coincidencia = true;

                //guardo la posición donde se encuentra la coincidencia
                int posicion = i;

                //elimina la persona de la lista
                listaPersonal.remove(i);

            }

        }
        return coincidencia;

    }

    private static boolean pagarSesiones(int sesiones) {

        String respuesta = "";
        boolean pagado = false;

        while (validaRespuesta(respuesta) == false) {
            //preguntamos si desea pagar las sesiones
            System.out.println("¿Quiere pagar las sesiones ahora, Total: " + sesiones * 8 + "€?");

            respuesta = ValidarDatos.pedirValidarNombre("Introduce Si o No");

            if (respuesta.equals("si") || respuesta.equals("Si") || respuesta.equals("SI")) {
                pagado = true;
            } else {
                pagado = false;
            }

        }
        return pagado;
    }

    private static void modificarPersonas() {

        boolean coincidencia = false;

        do {

            String nif = ValidarDatos.pedirValidarNif();

            for (int i = 0; i < listaPersonal.size(); i++) {

                if (listaPersonal.get(i).getDNI().equals(nif)) {

                    coincidencia = true;

                    //guardo la posición donde se encuentra la coincidencia
                    int posicion = i;
                    
                    //función que inicializa los atributos de persona
                    cambiarPersona(i);

                    //tengo que averiguar con instanceof que clase de objeto es(monitor, empleado, socio)
                    if (listaPersonal.get(i) instanceof Socio) {

                        //si es objeto socio cambiamos sus atributos
                        //convierto persona en socio con un cast
                        Socio s = (Socio) listaPersonal.get(i);

                        //Modificar sesiones
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Sesiones o Pulsa otra tecla para cambiar el campo: ")) {

                            int nuevasSesiones = ValidarDatos.validarIntCompleta("Introduce las sesiones semanales (2-6)", 2, 6);
                            s.setSesionesSemanales(nuevasSesiones);
                        }

                        //Modificar pago sesiones
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener el estado del Pago o Pulsa otra tecla para cambiar el campo: ")) {
                            boolean nuevoPago = GymTonificate.pagarSesiones(s.getSesionesSemanales());
                            s.setPagado(nuevoPago);

                        }

                        //Modificar lesiones
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener las Lesiones o Pulsa otra tecla para cambiar el campo: ")) {
                            String NuevasLesiones = ValidarDatos.pedirValidarNombre("Indroduce tus lesiones: ");
                            s.setLesiones(NuevasLesiones);
                        }

                        //si el objeto es empleado
                    } else if (listaPersonal.get(i) instanceof Empleado) {

                        //si es objeto empleado cambiamos sus atributos
                        //convierto persona en empleado con un cast
                        Empleado e = (Empleado) listaPersonal.get(i);

                        //Modificar tipo de trabajo
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Tipo de Trabajo o Pulsa otra tecla para cambiar el campo: ")) {

                            String nuevoTipoTrabajo = GymTonificate.tipoTrabajo();
                            e.setTipoTrabajo(nuevoTipoTrabajo);
                        }

                        //Modificar sueldo empleado
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Sueldo del Empleado o Pulsa otra tecla para cambiar el campo: ")) {

                            float nuevoSueldo = GymTonificate.sueldo(e.getTipoTrabajo());
                            e.setSueldo(nuevoSueldo);
                        }

                        //Modificar extensión
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Extensión del Empleado o Pulsa otra tecla para cambiar el campo: ")) {

                            String nuevaExtension = GymTonificate.generaExtension();
                            e.setExtension(nuevaExtension);
                        }

                        //si es un objeto Monitor    
                    } else if (listaPersonal.get(i) instanceof Monitor) {

                        //si es objeto monitor cambiamos sus atributos
                        //convierto persona en monitor con un cast
                        Monitor m = (Monitor) listaPersonal.get(i);

                        //Modificar tipo de especialidad
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Especialidades o Pulsa otra tecla para cambiar el campo: ")) {

                            String nuevasEspecialidades[] = addEspecialidad();
                            m.setEspecialidad(nuevasEspecialidades);
                        }

                        //Modificar  sueldo monitor
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener Sueldo del Monitor o Pulsa otra tecla para cambiar el campo: ")) {

                            float nuevoSueldoMonitor = (float) ValidarDatos.verificarDoubleMin("", 950);
                            m.setSueldo(nuevoSueldoMonitor);
                        }

                        //Modificar  está activo monitor
                        if (!ValidarDatos.respuestaBlanco("Pulsa INTRO para mantener el estdado del Monitor o Pulsa otra tecla para cambiar el campo: ")) {

                            boolean nuevoActivo = GymTonificate.estaActivo();
                            m.setActivo(nuevoActivo);
                        }
                    }

                }
            }

            if (coincidencia == false) {
                System.out.println("NIF no registrado en GYM TONIFICATE");
            }

        } while (coincidencia != true);
    }

    private static String modificarNombre(String nombre, String mensaje) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + mensaje + " o Pulsa otra tecla para cambiar el campo: ");

        String nuevoNombre = teclado.nextLine();

        if (!nuevoNombre.equals("")) {
            nuevoNombre = ValidarDatos.pedirValidarNombre("Introduce un nuevo " + mensaje);
            nombre = nuevoNombre;
        }

        return nombre;

    }

    private static String modificarDNI(String dni, String mensaje) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + mensaje + " o Pulsa otra tecla para cambiar el campo: ");

        String nuevoDNI = teclado.nextLine();

        if (!nuevoDNI.equals("")) {
            nuevoDNI = ValidarDatos.pedirValidarNif();
            dni = nuevoDNI;
        }

        return dni;

    }

    private static String modificarCP(String cp, String mensaje) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + mensaje + " o Pulsa otra tecla para cambiar el campo: ");

        String nuevoCP = teclado.nextLine();

        if (!nuevoCP.equals("")) {
            nuevoCP = ValidarDatos.pedirValidarCp();
            cp = nuevoCP;
        }

        return cp;

    }

    private static String modificarTF(String tfo, String mensaje) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + mensaje + " o Pulsa otra tecla para cambiar el campo: ");

        String nuevoTFO = teclado.nextLine();

        if (!nuevoTFO.equals("")) {
            nuevoTFO = ValidarDatos.pedirValidarTfo();
            tfo = nuevoTFO;
        }

        return tfo;

    }

    private static char modificarSexo(char s, String mensaje) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + mensaje + " o Pulsa otra tecla para cambiar el campo: ");

        String resp = teclado.nextLine();

        if (!resp.equals("")) {
            s = GymTonificate.pedirSexo();
        }

        return s;

    }

    private static Calendar modificarFechaNa(Calendar fechaNacimiento, String fecha) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nPulsa INTRO para mantener " + fecha + " o Pulsa otra tecla para cambiar el campo: ");

        String resp = teclado.nextLine();

        if (!resp.equals("")) {
            fechaNacimiento = ValidarDatos.pedirFecha("Introduce una nueva fecha de nacimiento: ");
        }

        return fechaNacimiento;

    }
    
    private static void listarPersonasTipo(){
        
        System.out.println("\n-----LISTA DE PERSONAS DE GYM TONIFICATE------\n");
        System.out.println("1.Listar Socios. \n2.Listar Empleados. \n3.Listar Monitores.");
        
        int opcion;
        

            opcion = ValidarDatos.validarIntCompleta("Introduce una opción (del 1 al 3): ", 1, 3);
            
            switch(opcion){               
                
                //listar Socios
                case 1:
                    
                    for(int i=0; i<listaPersonal.size(); i++){
                        if (listaPersonal.get(i) instanceof Socio) {
                            System.out.println(listaPersonal.get(i) + "\n");
                        }
                    }
                    break;
                    
                 //listar Socios
                case 2:
                    
                    for(int i=0; i<listaPersonal.size(); i++){
                        if (listaPersonal.get(i) instanceof Empleado) {
                            System.out.println(listaPersonal.get(i) + "\n");
                        }
                    }
                     break;
                 //listar Socios
                case 3:
                    
                    for(int i=0; i<listaPersonal.size(); i++){
                        if (listaPersonal.get(i) instanceof Monitor) {
                            System.out.println(listaPersonal.get(i) + "\n");
                        }
                    }
                     break;
                    
            }
        
       
    }
    
    private static void precargaPersonas(){
        
       //Calendar fNa = ValidarDatos.pedirFecha("Fecha Naciemiento:");
        Calendar fAlta =Calendar.getInstance();
        Calendar fNa = new GregorianCalendar(1990, 7, 6);
        Calendar fNa2 = new GregorianCalendar(2000, 8, 25);
        Calendar fNa3 = new GregorianCalendar(1992, 12, 2);
        
     
        String especialidad [] = new String[3];
        especialidad[0] = "Spinning";
        especialidad[1] = "Bodypum";
        especialidad[2] = "Step";
  
        
       Socio s = new Socio("Juan", "15510464S", "Calle Verde", "Granada", "Granada", "12125", "639987895", fAlta, fNa, 'H', 3, true, "no");
       Monitor m = new Monitor("Joaquin", "15510464R", "Calle Roma", "Motril", "Graanda", "12185", "639984758", fAlta, fNa2, 'H', especialidad, 2500, true);
       Empleado e = new Empleado("Maria", "15510464L", "Calle Aguilar", "Segura de la Sierra", "Cortijos Nuevos", "12125", "698786521",  fAlta, fNa3, 'M',"Administración", 2000, "231");
       listaPersonal.add(s);
       listaPersonal.add(m);
       listaPersonal.add(e);
       
       m.setFechaAlta(fAlta);
    }
    
     private static void gestionarEspecilidades(){
        
        System.out.println("\n-----GESTIÓN DE ESPECIALIDADES------\n");
        System.out.println("1.Listar Especialidades. \n2.Añadir Especialidad. \n3.Eliminar Especialidad. \n4.Volver al menú principal.");

        int opcion;

        do {

            opcion = ValidarDatos.validarIntCompleta("Introduce una opción (del 1 al 4): ", 1, 4);

            switch (opcion) {

                //listar especialidades
                case 1:

                    System.out.println("Especialidades:\n");

                    for (int i = 0; i < listaEspecialidades.size(); i++) {

                        System.out.println(i + 1 + "." + listaEspecialidades.get(i) + "\n");

                    }
                    break;

                //Añadir nueva especialidad
                case 2:

                    String nuevaEspecialidad = ValidarDatos.pedirValidarNombre("Introduce la nueva Especialidad:");
                    listaEspecialidades.add(nuevaEspecialidad);
                    System.out.println("Nueva Especialid adañadida");

                    break;

                //Borrar especialidad
                case 3:

                    boolean eliminada = buscarEliminarEspecialidad();

                    if (eliminada == true) {
                        System.out.println("Especilidad Eliminada");
                    } else {
                        System.out.println("Especilidad no encontrada. Vuelva a introducir la opción 3 eliminar");
                    }

                    break;

            }
        } while (opcion != 4);

    }

    private static boolean buscarEliminarEspecialidad() {

        boolean coincidencia = false;

        String especialidad = ValidarDatos.pedirValidarNombre("Introduce el nombre de la especialidad para borrarla: ");

        for (int i = 0; i < listaEspecialidades.size(); i++) {

            if (listaEspecialidades.get(i).equals(especialidad)) {

                coincidencia = true;

                //guardo la posición donde se encuentra la coincidencia
                int posicion = i;

                //elimina la persona de la lista
                listaEspecialidades.remove(i);

            }

        }
        return coincidencia;
    }
    
     private static void gestionarTrabajos() {

        System.out.println("\n-----GESTIÓN DE TRABAJOS------\n");
        System.out.println("1.Listar Trabajos. \n2.Añadir Trabajo. \n3.Eliminar Trabajo. \n4.Volver al menú principal.");

        int opcion;

        do {

            opcion = ValidarDatos.validarIntCompleta("Introduce una opción (del 1 al 4): ", 1, 4);

            switch (opcion) {

                //listar especialidades
                case 1:

                    System.out.println("Trabajos:\n");

                    for (int i = 0; i < listaTrabajos.size(); i++) {

                        System.out.println(i + 1 + "." + listaTrabajos.get(i) + "\n");

                    }
                    break;

                //Añadir nuevo trabajo
                case 2:

                    String nuevoTrabajo = ValidarDatos.pedirValidarNombre("Introduce un nuevo Trabajo:");
                    listaTrabajos.add(nuevoTrabajo);
                    System.out.println("Nuevo Trabajo adañadido");

                    break;

                //Borrar trabajo
                case 3:

                    boolean eliminada = buscarEliminarTrabajo();

                    if (eliminada == true) {
                        System.out.println("Trabajo Eliminado");
                    } else {
                        System.out.println("Trabajo no encontrado. Vuelva a introducir la opción 3 eliminar");
                    }

                    break;

            }
        } while (opcion != 4);

    }

    private static boolean buscarEliminarTrabajo() {

        boolean coincidencia = false;

        String trabajo = ValidarDatos.pedirValidarNombre("Introduce el nombre del trabajo para borrarlo ");

        for (int i = 0; i < listaTrabajos.size(); i++) {

            if (listaTrabajos.get(i).equals(trabajo)) {

                coincidencia = true;

                //guardo la posición donde se encuentra la coincidencia
                int posicion = i;

                //elimina la persona de la lista
                listaTrabajos.remove(i);

            }

        }
        return coincidencia;
    }
    
    private static String listar(String mensaje, ArrayList a){
        
         String cadena="";
        System.out.println("mensaje:\n");

                    for (int i = 0; i < a.size(); i++) {

                        System.out.println(i + 1 + "." + a.get(i) + "\n");

                    }
    return cadena;                
    }
    
    private static void cambiarPersona(int i){
        //Modificar el nombre
                    String nuevoNombre = modificarNombre(listaPersonal.get(i).getNombre(), "Nombre");
                    listaPersonal.get(i).setNombre(nuevoNombre);

                    //Modificar dirección
                    String nuevaDireccion = modificarNombre(listaPersonal.get(i).getDireccion(), "Dirección");
                    listaPersonal.get(i).setDireccion(nuevaDireccion);

                    //Modificar provincia
                    String provincia = modificarNombre(listaPersonal.get(i).getProvincia(), "Provincia");
                    listaPersonal.get(i).setProvincia(provincia);

                    //Modificar localidad
                    String localidad = modificarNombre(listaPersonal.get(i).getLocalidad(), "Localidad");
                    listaPersonal.get(i).setLocalidad(localidad);

                    //Modificar dni
                    String nuevoDNI = modificarDNI(listaPersonal.get(i).getDNI(), "NIF");
                    listaPersonal.get(i).setDNI(nuevoDNI);

                    //Modificar cp
                    String nuevoCP = modificarCP(listaPersonal.get(i).getCodigoPostal(), "CP");
                    listaPersonal.get(i).setCodigoPostal(nuevoCP);

                    //Modificar teléfono
                    String nuevoTFO = modificarTF(listaPersonal.get(i).getTelefono(), "Teléfono");
                    listaPersonal.get(i).setTelefono(nuevoTFO);

                    //Modificar fecha Nacimiento
                    Calendar nuevaFecha = modificarFechaNa(listaPersonal.get(i).getFechaNacimiento(), "Fecha");
                    listaPersonal.get(i).setFechaNacimiento(nuevaFecha);

                    //Modificar sexo
                    char sexo = modificarSexo(listaPersonal.get(i).getSexo(), "Sexo");
                    listaPersonal.get(i).setSexo(sexo);
    }
    
    private static void altaPersonas() {

        System.out.println("------ALTA DE PERSONAS------\n");

        String nombre = ValidarDatos.pedirValidarNombre("Introduce tu nombre: ");
        boolean correctoDNI;
        String dni;
        do {
            dni = ValidarDatos.pedirValidarNif();
            correctoDNI = buscarPersonaDNI(dni);
            if (correctoDNI == true) {
                System.out.println("Ese NIF ya está rgistrado");
            }
        } while (correctoDNI == true);
        //tengo que poner que si el dni existe no se cree
        String direccion = ValidarDatos.pedirValidarNombre("Introduce tu dirección: ");
        String localidad = ValidarDatos.pedirValidarNombre("Introduce tu localidad: ");
        String provincia = ValidarDatos.pedirValidarNombre("Introduce tu provincia: ");
        String cp = ValidarDatos.pedirValidarCp();
        String tfo = ValidarDatos.pedirValidarTfo();

        Calendar fNa = ValidarDatos.pedirFecha("\nIntroduce tu fecha de naciemiento: ");

        Calendar fAlta = new GregorianCalendar();

        char sexo = pedirSexo();

        int numero;

        do {

            System.out.println("------ALTA DE PERSONAS------\n");

            System.out.println("1.Alta Socio. \n2.Alta Empleado. \n3.Alta Monitor\n");

            numero = ValidarDatos.validarIntCompleta("Introduce un número del 1 al 3: ", 1, 3);

            switch (numero) {

                //se da de alta un socio 
                case 1:

                    //sesiones en las que se inscribe el socio
                    int sesiones = ValidarDatos.validarIntCompleta("Introduce las sesiones semanales (2-6)", 2, 6);

                    boolean pagarSesion = pagarSesiones(sesiones);

                    String lesiones = ValidarDatos.pedirValidarNombre("Introduce tus lesiones: ");

                    Socio s = new Socio(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo, sesiones, pagarSesion, lesiones);

                    listaPersonal.add(s);

                    break;

                //se da de alta un empleado
                case 2:

                    //pedimos el tipo de trabajo que tiene 
                    String tipoTrabajo = tipoTrabajo();

                    //se le asigna el sueldo con la función
                    float sueldo = sueldo(tipoTrabajo);

                    //genero una extensión aleatoria para cada nuevo empleado
                    String ext = generaExtension();

                    Empleado e = new Empleado(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo, tipoTrabajo, sueldo, ext);

                    listaPersonal.add(e);

                    break;

                //damos de alta un monitor
                case 3:

                    //pedimos las especialidades del monitor
                    System.out.println("\nIntroduce las especialidades del monitor");

                    //lamo a lafunción de elegir especialidad
                    String especialidades[] = addEspecialidad();

                    //creo el sueldo de minitor
                    float sueldoMonitor = (float) ValidarDatos.verificarDoubleMin("Introduce un sueldo mayor al SMI", 950);

                    //pregunto si estáactivo el monitor
                    boolean activo = estaActivo();

                    Monitor m = new Monitor(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo, especialidades, sueldoMonitor, activo);

                    listaPersonal.add(m);

                    break;

            }

            break;

        } while (numero != 3);

    }
    
    private static void bajaPersonas() {
        System.out.println("\n------Baja de Personas------");

        int salir;
        do {
            boolean coincide = buscarEliminarPersonaDni();

            if (coincide == false) {
                System.out.println("No hay coincidencia.");
                salir = ValidarDatos.validarIntCompleta("Introduce un número mayor a 1 para buscar, Introduce 1 para volver al menú principal: ", 1, 9);
            } else {
                System.out.println("Cuenta Persona eliminada");
                salir = 1;
            }

        } while (salir != 1);

    }
    
    private static void precargaEspecialidades(){
        //se añaden las especialidades a las listaEspecialidades
        listaEspecialidades.add("Aerobic");
        listaEspecialidades.add("Step");
        listaEspecialidades.add("Bodypum");
        listaEspecialidades.add("Spinning");
        listaEspecialidades.add("Vacía");
    }
    
    private static void precargaTrabajos(){
          //se añaden los tipos de trabajo al listaTrabajos
        listaTrabajos.add("Administrativo");
        listaTrabajos.add("Limpieza");
        listaTrabajos.add("Mantenimiento");
        listaTrabajos.add("Seguridad");
        listaTrabajos.add("Proveedor");
    }
   
    public static boolean buscarPersonaDNI(String nif) {
        boolean coincidencia = false;


        for (int i = 0; i < listaPersonal.size(); i++) {

            if (listaPersonal.get(i).getDNI().equals(nif)) {

                coincidencia = true;
            }
        }

        return coincidencia;

      }
     
     
    //función cargar perosnas de un archivo binario
    public static  List<Persona> cargarPersonas(String ubicacionArchivo){
        
         ObjectInputStream flujoEntrada = null;
          List<Persona> tablaPersonas = null;

        try {
            flujoEntrada = new ObjectInputStream(new FileInputStream(ubicacionArchivo));
            tablaPersonas = (List<Persona>) flujoEntrada.readObject();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e2) {
            System.out.println("Exception2");
        } finally {
            if (flujoEntrada != null) {
                try {
                    flujoEntrada.close();
                } catch (Exception e3) {
                    System.out.println("Exception3");
                }
            }
        }

        return tablaPersonas;
    }
    
    
    //función para guardar la lista de perosnal en un archivo binario
     public static void guardarPersonas(List<Persona> tablaPersonas, String ubicacionArchivo) {
        ObjectOutputStream flujoSalida = null;
        
        try{
            flujoSalida = new ObjectOutputStream(new FileOutputStream (ubicacionArchivo));
            flujoSalida.writeObject(tablaPersonas);
            System.out.println("Los datos han sido guardados");    
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }catch(Exception e2){
            System.out.println("Exception2");
        }finally{
            if (flujoSalida!= null){
                try{
                    flujoSalida.close();
                }catch(Exception e3){
                    System.out.println("Exception3");
                }
            }
        }
    }
     
     
     //función para leer el fchero una cadena de especialidades
     public static List<Persona> cargarEspecialidades( String ubicacionArchivo){
       
        BufferedReader in = null;
         List<Persona> tablaespecialidades = null;

        try {
            
            Scanner s;
            
            //1-File reader para leer el fichero donde se guarda 
            in = new BufferedReader(new FileReader(ubicacionArchivo));
            //ready para ver que no está en blanco
            if (in.ready()) {

                String linea = in.readLine();
                while (linea != null) {
                    s = new Scanner(linea);
                    if (s.hasNextLine()) {
                        tablaespecialidades.add((Persona) Paths.get(linea));
                    }
                    linea = in.readLine();
                }

            }          

        } catch (IOException e) {
            System.out.println("ExceptionIO");
        } catch (Exception e2) {
            System.out.println("Exception2");
        } finally {
            try {
                in.close();
            } catch (Exception e3) {
                System.out.println("Exception3");
            }
        }
        
        return tablaespecialidades;

    }
     
     //función para guardar la lista de las especialidades
     
    private static void guardarPartida(String ubicacionArchivo, List<String> listaEspecialidades) {

        BufferedWriter out = null;

        try {
            //prepara el archivo donde escribir 
            out = new BufferedWriter(new FileWriter(ubicacionArchivo));

            //necesito recorrer el array de especialidades y guardar linea a linea 
            for (int i = 0; i < listaEspecialidades.size(); i++) {

                out.write(listaEspecialidades.get(i).toString());

                out.newLine();
            }

            out.close();

        } catch (IOException e) {
            System.out.println("ExceptionIO");
        } catch (Exception e2) {
            System.out.println("Exception2");
        }

    }


  

}
