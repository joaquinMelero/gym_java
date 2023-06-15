//Clase Persona

package gymtonificate;

import java.util.Calendar;
import java.util.GregorianCalendar;


public abstract class Persona {
    
    //atributos de la clase persona
    
    private String nombre;//Nombre de la persona
    private String DNI;//DNI de la persona. Debe ser un DNI válido
    private String direccion;//La dirección postal de la persona
    private String localidad;//Localidad de residencia de la persona
    private String provincia;//Provincia de residencia de la persona
    private String codigoPostal;//Cinco dígitos con el código postal
    private String telefono;//Teléfono de contacto de la persona. Constará exactamente de 9 dígitos
    private Calendar fechaAlta;//Indica la fecha de alta del registro
    private Calendar fechaNacimiento;//Indica la fecha de nacimiento. La edad no podrá ser superior a 99 años.
    private char sexo;//Char sexo. 'H' para hombre y 'M' para mujer
    
//Constructor por parámetros (atributos de Persona). Si alguno de los datos no es válido se lanzará la excepción IllegalArgumentException.
    public Persona (String nombre, String dni, String direccion, String localidad, String provincia, String cp, String tfo, Calendar fAlta, Calendar fNa, char sexo){
         if(ValidarDatos.validaNIF(dni)== false){
            throw new IllegalArgumentException("No es un dni válido");
        }    

        this.nombre = nombre;
        this.DNI = dni;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.codigoPostal = cp;
        this.telefono = tfo;
        this.fechaAlta = fAlta;
        this.fechaNacimiento = fNa;
        this.sexo =  sexo;
         
    }

    @Override
    public String toString() {
        
        String feNA = mostrarFecha(this.fechaNacimiento);
        String fecha = mostrarFecha(this.fechaAlta);
        
        return "Persona: " + "\nnombre=" + nombre + "\nDNI=" + DNI + "\ndireccion=" + direccion + "\nlocalidad=" + localidad + "\nprovincia=" + provincia + "\ncodigoPostal=" + codigoPostal + "\ntelefono=" + telefono + "\nfechaAlta=" + fecha + "\nfechaNacimiento=" + feNA+ "\nsexo=" + sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
         if(ValidarDatos.validarTitular(nombre)== false){
            throw new IllegalArgumentException("No es un nombre válido");
          }        
        this.nombre = nombre;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        if(ValidarDatos.validaNIF(DNI)== false){
            throw new IllegalArgumentException("No es un dni válido");
          }            
        this.DNI = DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(ValidarDatos.validarTitular(direccion)== false){
            throw new IllegalArgumentException("No es una dirección válida");
          }       
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        if(ValidarDatos.validarTitular(localidad)== false){
            throw new IllegalArgumentException("No es una localidad válida");
          }       
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        if(ValidarDatos.validarTitular(provincia)== false){
            throw new IllegalArgumentException("No es una provincia válida");
          }   
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        if(ValidarDatos.validaCp(codigoPostal) == false){
            throw new IllegalArgumentException("No es un cp válido");
        }   
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(ValidarDatos.validaTfo(telefono) == false){
            throw new IllegalArgumentException("No es un teléfono válido");
        }  
        this.telefono = telefono;
    }

    public Calendar getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Calendar fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Calendar getFechaNacimiento() {
        
        
        
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        if(sexo !='H' && sexo !='M'){
            throw new IllegalArgumentException("No es un formato correcto");
          }   
        this.sexo = sexo;
    }
    
    //método para calcurar la edad
    public int getEdad(){
        
        int edad;
        Calendar fecha =  Calendar.getInstance();

        
         int añoNa=fechaNacimiento.get(Calendar.YEAR);
         int añoHoy=fecha.get(Calendar.YEAR);
      
        edad =  añoHoy-añoNa;
        
        return edad;
   
    }
    
    //método equals
    public boolean equals(Persona p){
        boolean iguales = false;
        
        if(this.DNI == p.DNI){
            iguales = true;
        }
        
        return iguales;
    }
    
    public int compare(Persona p1, Persona p2) {
        if(p1.getEdad()>p2.getEdad()){
            return -1;
        }else if(p1.getEdad()<p2.getEdad()){
            return 1;
        } else{
            return 0;
        }
    }
    
    public String mostrarFecha(Calendar f){
        

        int dia = f.get(Calendar.DAY_OF_MONTH);
        int mes = f.get(Calendar.MONTH)+1;
        int año = f.get(Calendar.YEAR);
        
        String sDia =String.valueOf(dia);
        String sMes =String.valueOf(mes);
        String sAño =String.valueOf(año);
        
        String cadena = sDia + "/" + sMes + "/" + sAño;
        
        return cadena; 
        
    }
   
 
}
