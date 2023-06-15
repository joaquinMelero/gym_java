//Clase para guargar las funciones de validación de datos del curso
package gymtonificate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class ValidarDatos {

    public static boolean validaNIF(String nif) {

        //expresión regular: que obligatoriamente empiece por un número de 0-9, se repita 8 veces y
        //obligatoriamente acabe en una letra y se repita una vez
        return nif.matches("^([0-9]{8}[A-Z]{1}$)");
    }
    
    public static String pedirValidarNif() {

        Scanner teclado = new Scanner(System.in);

        //creo variable boolean
        boolean nifCorrecto = false;

        //creo la variable nif para guardar lo introducido por teclado
        String nif;

        System.out.println("\nIntroduce tu DNI (formato NIF:00000000X): ");

        do {
            nif = teclado.nextLine();

            nifCorrecto = validaNIF(nif);

            if (nifCorrecto == true) {

                return nif;

            } else if (nifCorrecto == false) {

                System.out.println("Error, DNI con formato incorrecto.\nIntroduce un nuevo DNI (formato NIF:00000000X): ");
            }

        } while (nifCorrecto == false);

        return nif;

    }

    public static boolean validaCIF(String cif) {

        //expresión regular: que obligatoriamente empiece por un número de 0-9, se repita 8 veces y
        //obligatoriamente acabe en una letra y se repita una vez
        return cif.matches("^([A-Z]{1}[0-9]{8}$)");
    }

    public static boolean validaNIE(String nie) {
        //expresión regular: que obligatoriamente empiece por un número de 0-9, se repita 8 veces y
        //obligatoriamente acabe en una letra y se repita una vez
        return nie.matches("^([X,T]{1}[0-9]{7}[A-Z]{1})$");
    }

    public static boolean validarTitular(String nombre) {

        //expresión regular: obligatoriamente empiece por un una letra mayuscula, siga por almenos una
        //minúscula, siga un espacio que puede aparecer o no, y todo esto se puede repetir 1,2,3 
        return nombre.matches("^([A-Za-z]+[ ]?){1,3}$");
    }
    
    public static String pedirValidarNombre(String mensaje) {

        Scanner teclado = new Scanner(System.in);

        //creo variable boolean
        boolean nombreCorrecto = false;

        //creo la variable nif para guardar lo introducido por teclado
        String nombre;

        System.out.println(mensaje);

        do {
            nombre = teclado.nextLine();

            nombreCorrecto = validarTitular(nombre);

            if (nombreCorrecto == true) {

                return nombre;

            } else if (nombreCorrecto == false) {

                System.out.println("Error, formato incorrecto.\n" + mensaje);
            }

        } while (nombreCorrecto == false);

        return nombre;

    }

    public static boolean validarContraseña(String contraseña) {

        //expresión regular: longitud mínima de 10,al menos una letra mayúscula, al menos una minúscula
        //y dos dígitos
        return contraseña.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2,})[a-zA-Z\\d]{8,}$");
    }
    
//función para verificar entrada por teclado de un entero, con minimo 
    public static int verificarInt(String mensaje, int minimo) {
        Scanner teclado = new Scanner(System.in);
        int numero;
        do {
            System.out.println(mensaje);
            while (!teclado.hasNextInt()) {
                System.out.println("Error " + mensaje);
                teclado.next();
            }
            numero = teclado.nextInt();
        } while (numero < minimo);
        return numero;
    }

    //función para verificar letra sin mínimo ni maximo
    public static int verificarIntSin(String mensaje) {
        Scanner teclado = new Scanner(System.in);
        int numero;
        System.out.println(mensaje);
        while (!teclado.hasNextInt()) {
            System.out.println("Error " + mensaje);
            teclado.next();
        }
        numero = teclado.nextInt();
        return numero;
    }

    //funcion validar numero entero y no letras con minimo y maximo 
    public static int validarIntCompleta(String mensaje, int minimo, int maximo) {
        Scanner teclado = new Scanner(System.in);//creo escaner para leer por teclado
        int numero;//definimos la variable numero
        do {// bucle hacer
            System.out.println(mensaje);//mensaje que nos solicitara introducir en el programa
            while (!teclado.hasNextInt()) {//mientras que el dato inntroducido no sea un numero
                System.out.println("Error número no correcto, " + mensaje);//mensaje que se muestra de error
                teclado.next();//solicitamos introducir otro numero
            }
            numero = teclado.nextInt();// indicamos que la variable numero toma el valor introducido por teclado
        } while ((numero > maximo) || (numero < minimo));//definimos el minimo y maximo 
        return numero;
    }

    //fucion para verificar double si minimo ni maximo
    public static double verificarDouble(String mensaje) {
        Scanner teclado = new Scanner(System.in);//crear escaner para teclado
        double numero;//definimos variabe numero
        System.out.println(mensaje);//mensaje que se introduce desde el programa
        while (!teclado.hasNextDouble()) {//mientras el dato introducido no sea double
            System.out.println("Error, número incorrecto");//muestra elmensaje de error
            teclado.next();//pide siguiente entrada
        }
        numero = teclado.nextDouble();//variable numero toma el valor de entrada de teclado
        return numero;
    }

    //funcion verificar double con minimo
    public static double verificarDoubleMin(String mensaje, double minimo) {
        Scanner teclado = new Scanner(System.in);//creamos escaner de teclado
        double numero;//definimos variable numero
        do {
            System.out.println(mensaje);//mensaje que se introduce desde el programa
            while (!teclado.hasNextDouble()) {//mientras que el dato introducido no sea numero double
                System.out.println("Error, número incorrecto, introduce otro: ");//mensaje de error que se muestra
                teclado.next();//se introduce otro dato
            }
            numero = teclado.nextDouble();//variable numero toma valor de dato introducido por teclado   
        } while (numero < minimo);
        return numero;//devuelve la variable numero 
    }

    //funcion verificar Double con min y max
    public static double verificarDoubleMinMax(String mensaje, double minimo, double maximo) {
        Scanner teclado = new Scanner(System.in);//crear escaner de teclado
        double numero;//definir variable 
        do {
            System.out.println(mensaje);//mensaje para programa
            while (!teclado.hasNextDouble()) {//mientras el dato introducido por teclado no sea numero double
                System.out.println("Error, numero incorrecto");//mensaje de error que se muestra
                teclado.next();//pide el siguiente numero
            }
            numero = teclado.nextDouble();//variable numero toma el valor de la entrada del teclado
        } while ((numero < minimo) || (numero > maximo));
        return numero;
    }
    
    public static boolean validaCp(String numero) {
        
        return numero.matches("^([0-9]{5})$");
    }
    
     public static boolean validaTfo(String numero) {
        
        return numero.matches("^([0-9]{9})$");
    }
    
    //función para pedir validar un cp
    public static String pedirValidarCp(){
        
         Scanner teclado = new Scanner(System.in);

        //creo variable boolean
        boolean cpCorrecto = false;

        //creo la variable nif para guardar lo introducido por teclado
        String cp;

        System.out.println("\nIntroduce cp: ");

        do {
            cp = teclado.nextLine();

            cpCorrecto = validaCp(cp);

            if (cpCorrecto == true) {

                return cp;

            } else if (cpCorrecto == false) {

                System.out.println("Error, cp con formato incorrecto.\nIntroduce un nuevo cp: ");
            }

        } while (cpCorrecto == false);

        return cp;

    }
    
    //función para pedir validar un cp
    public static String pedirValidarTfo(){
        
         Scanner teclado = new Scanner(System.in);

        //creo variable boolean
        boolean TfoCorrecto = false;

        //creo la variable nif para guardar lo introducido por teclado
        String tfo;

        System.out.println("\nIntroduce un teléfono: ");

        do {
            tfo = teclado.nextLine();

            TfoCorrecto = validaTfo(tfo);

            if (TfoCorrecto == true) {

                return tfo;

            } else if (TfoCorrecto == false) {

                System.out.println("Error, teléfono con formato incorrecto.\nIntroduce un nuevo teléfono: ");
            }

        } while (TfoCorrecto == false);

        return tfo;

    }
       
    //pedir una fecha
     public static Calendar pedirFecha(String mensaje){
         
         System.out.println(mensaje);
      
        int dia = ValidarDatos.validarIntCompleta("Introduce el día: ", 1, 31);
        int mes = ValidarDatos.validarIntCompleta("Introduce el mes: ", 1, 12);
        int año = ValidarDatos.validarIntCompleta("Introduce el año: ", 1923, 2021);

        Calendar fecha = new GregorianCalendar(año, mes, dia);

        return fecha;
    }
       
     public static boolean respuestaBlanco (String mensaje) {

        Scanner teclado = new Scanner(System.in);
        
          System.out.println(mensaje);
        
     
        boolean enBlanco=false;
        

            String nombre = teclado.nextLine();


            if (nombre.equals("")) {
                
                enBlanco = true;
            } 

        return enBlanco;

    }
      
     

}
