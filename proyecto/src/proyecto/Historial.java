
package proyecto;

import java.util.ArrayList;

public class Historial {

    private String codigo;
    private String fecha;
    private int cedulaPaciente;
    private int idMedico;
    private String observaciones;
       
    public Historial() {
    }

    public Historial(String codigo, String fecha, int cedulaPaciente, int idMedico, String observaciones) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cedulaPaciente = cedulaPaciente;
        this.idMedico = idMedico;
        this.observaciones = observaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(int cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    
}
