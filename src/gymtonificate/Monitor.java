//La clase Monitor hereda de Persona

package gymtonificate;

import java.util.Arrays;
import java.util.Calendar;


public class Monitor extends Persona{
    
    //atributos
    private String [] especialidad; //Podrá tener un máximo de 3 (por ejemplo: aerobic, step y bodypump). Para tener una especialidad, esa especialidad debe existir previamente en la lista de especialidades.
    private float sueldo;//Como mínimo cobrará el salario mínimo interprofersional (SMI), establecido en 950€ en el año 2021.
    private boolean activo;//Indica si el monitor se encuentra activo o no

    public Monitor(String nombre, String dni, String direccion, String localidad, String provincia, String cp, String tfo, Calendar fAlta, Calendar fNa, char sexo, String especialidad [], float sueldo, boolean activo) {
        super(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo);
        
        this.especialidad = especialidad;
        this.activo = activo;
        this.sueldo = sueldo;

    }

    @Override
    public String toString() {
        String especialidades=" ";
        
        for(String elemento:especialidad){
            especialidades += elemento + ", ";
        }
        
        String cadena = super.toString();
        
        
        return cadena + "\nMonitor:" + "\nespecialidades=" + especialidades + "\nsueldo=" + sueldo + "\nactivo=" + activo;
    }

    public String[] getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String[] especialidad) {
        this.especialidad = especialidad;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        if(sueldo<0){
            throw new IllegalArgumentException("No es un sueldo válido");
          }       
        this.sueldo = sueldo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
