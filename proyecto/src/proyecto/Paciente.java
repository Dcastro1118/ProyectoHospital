
package proyecto;

public class Paciente {
    String nombre;
    String apellidos;
    String causa;
    char sexo;
    int edad;
    int cedula;
    
    public Paciente() {
    }
    
    public Paciente(String nombre, String apellidos, String causa, char sexo, int edad, int cedula){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.causa = causa;
        this.sexo = sexo;
        this.edad = edad;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
}

