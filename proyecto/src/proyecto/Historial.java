
package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;

public class Historial {

    private int codigo;
    private LocalDate fecha;
    private int cedulaPaciente;
    private int idMedico;
    private String observaciones;
    
    public Historial() {
    }

    public Historial(int codigo, LocalDate fecha, int cedulaPaciente, int idMedico, String observaciones) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cedulaPaciente = cedulaPaciente;
        this.idMedico = idMedico;
        this.observaciones = observaciones;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public String getEntrada() {
        return observaciones;
    }

    public void setEntrada(String observaciones) {
        this.observaciones = observaciones;
    }  
}   