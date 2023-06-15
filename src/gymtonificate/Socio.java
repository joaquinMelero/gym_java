//La clase Socio hereda de Persona

package gymtonificate;

import java.util.Calendar;


public class Socio extends Persona {
    
    //atributos
    private int sesionesSemanales;//Un mínimo de 2 y un máximo de 6
    private double cuota;//Se calculará según el número de sesiones semanales que haya contratado. El coste de la sesión será de 8€
    private boolean pagado;//Indica si se encuentra al día de los pagos
    private String lesiones;//Registra si tiene algún tipo de lesión
    
    //Constructor que tendrá los parámetros correspondientes a Persona
//más los propios de la clase Socio, salvo el atributo cuota ya que es calculado a partir de otro.
    public Socio(String nombre, String dni, String direccion, String localidad, String provincia, String cp, String tfo, Calendar fAlta, Calendar fNa, char sexo, int sesiones, boolean pagado, String lesiones){

        super(nombre, dni, direccion, localidad, provincia, cp, tfo, fAlta, fNa, sexo);
        
        this.sesionesSemanales = sesiones;
        this.pagado = pagado;
        this.lesiones = lesiones;
        this.cuota = sesionesSemanales*8;
        
    }

    public int getSesionesSemanales() {
        return sesionesSemanales;
    }

    public double getCuota() {
        return cuota;
    }

    public boolean isPagado() {
        return pagado;
    }

    public String getLesiones() {
        return lesiones;
    }

    public void setSesionesSemanales(int sesionesSemanales) {
        if(sesionesSemanales <1 && sesionesSemanales >6){
            throw new IllegalArgumentException("No es una cantidad permitida");
          }   
        this.sesionesSemanales = sesionesSemanales;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setLesiones(String lesiones) {
        if(ValidarDatos.validarTitular(lesiones)== false){
            throw new IllegalArgumentException("No es un formato válido de lesión");
          }       
        this.lesiones = lesiones;
    }
    

    @Override
    public String toString() {
        String cadena = super.toString();
        return  cadena + "\nSocio:" + "\nsesionesSemanales=" + sesionesSemanales + "\ncuota=" + cuota + "\npagado=" + pagado + "\nlesiones=" + lesiones;
    }
    
    
    
}
