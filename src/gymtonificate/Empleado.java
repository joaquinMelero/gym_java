//La clase Empleado hereda de Persona

package gymtonificate;

import java.util.Calendar;


public class Empleado extends Persona {
    
    //atributos 
    private String tipoTrabajo;//Tipo de trabajo que realiza (administrativo, limpieza, mantenimiento, etc.). Para realizar un trabajo, ha de existir en la lista de trabajos.
    private float sueldo;//Como mínimo cobrará el salario mínimo interprofersional (SMI), establecido en 950€ en el año 2021.
    private String extension;//Indica la extensión numérica del teléfono del empleado para llamadas internas

   
    public Empleado(String nombre, String dni, String direccion, String localidad, String provincia, String cp, String tfo, Calendar fAlta, Calendar fNa, char sexo, String tipoTrabajo, float sueldo, String extension) {
        super(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo);
        
        this.tipoTrabajo = tipoTrabajo;
        this.sueldo = sueldo;
        this.extension = extension;
                       
    }

    @Override
    public String toString() {
        
        String cadena = super.toString();
        return cadena + "\nEmpleado:" + "\ntipoTrabajo=" + tipoTrabajo + "\nsueldo=" + sueldo + "\nextension=" + extension;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    
    
    
    
}
