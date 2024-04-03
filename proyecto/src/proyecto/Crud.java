package proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class Crud {
    
    private static int contadorIds = 0;
    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static ArrayList<Medico> listaMedicos = new ArrayList<>();

    
    // Agregar el primer usuario
    public static void usuarioDefault(Medico medico){
        
        listaMedicos.add(medico);
    }
    
    public static ArrayList<Medico> getListaMedicos(){
        return listaMedicos;

    }

    public static void agregarMedico(String nombre, String apellidos, String especialidad, String usuario, String password){

        Medico newMedico = new Medico();
        newMedico.nombre = nombre;
        newMedico.apellidos = apellidos;
        newMedico.especialidad = especialidad;
        newMedico.usuario = usuario;
        newMedico.password = password;
        newMedico.id = contadorIds++;
        contadorIds++;
        
    
    
    
    }
    
    
    
    
//Metodo para verificar la existencia de un paciente basado en su numero de cedula
    
    
    public static boolean verificarExistencia(int Cedula) {

        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == Cedula) {
                return true;
            }
        }
        return false;
    }

//metodo para crear paciente
    public static void crearPaciente(int Cedula) {
        
        if (!verificarExistencia(Cedula)) {
            String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
            String apellidosNuevos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
            String historialNuevo = JOptionPane.showInputDialog("Adicione la causa al historial clinico");
            int edadNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente"));
            char sexoNuevo = JOptionPane.showInputDialog("Ingrese el sexo del paciente en \n"
                    + "'M' para masciluno \n"
                    + "'F' para femenino").toUpperCase().charAt(0);
            Paciente nuevoPaciente = new Paciente(nombreNuevo, apellidosNuevos, historialNuevo, sexoNuevo, edadNueva, Cedula);
            listaPacientes.add(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "El Paciente a sido creado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe, porfavor actualizarlo.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //metodo para actualizar paciente Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
    public static void actualizarPaciente(int cedula) {
        boolean encontrado = false;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedula) {
                encontrado = true;
                int opcionMenu2 = Integer.parseInt(JOptionPane.showInputDialog("Paciente encontrado, que desea hacer:\n"
                        + "1. Actualizar todos los datos\n"
                        + "2. Actualizar solo el historial"));

                switch (opcionMenu2) {
                    case 1:
                        actualizarDatosPaciente(paciente);
                        break;
                    case 2:
                        actualizarHistorialPaciente(paciente);
                        break;
                    default:
                        throw new AssertionError();
                }

            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontro ningun paciente con ese numero de cedula", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void actualizarDatosPaciente(Paciente paciente) {
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente");
        String apellidos = JOptionPane.showInputDialog("Ingrese los nuevos apellidos del paciente");
        String historial = JOptionPane.showInputDialog("Ingrese el nuevo historial clínico del paciente");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad del paciente"));
        char sexo = JOptionPane.showInputDialog("Ingrese el nuevo sexo del paciente (M/F)").charAt(0);

        // Actualizar los datos del paciente
        paciente.setNombre(nombre);
        paciente.setApellidos(apellidos);
        paciente.setCausa(historial);
        paciente.setEdad(edad);
        paciente.setSexo(sexo);
    }

    public static void actualizarHistorialPaciente(Paciente paciente) {
        String historial = JOptionPane.showInputDialog("Ingrese el nuevo historial clínico del paciente");

        paciente.setCausa(historial);

    }

    public static void eliminarPaciente(int cedula) {

        
        List<Paciente> pacientesAEliminar = new ArrayList<>();
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedula) {
                pacientesAEliminar.add(paciente); // Agrega el paciente a la lista temporal
            }
        }
        listaPacientes.removeAll(pacientesAEliminar); // Elimina todos los pacientes de la lista original

    }

    public static void consultarRegistro(int cedula) {

        for (Paciente paciente : listaPacientes) {

            if (paciente.getCedula() == cedula) {
                JOptionPane.showMessageDialog(null, "Paciente: " + paciente.getNombre() + " " + paciente.getApellidos() + ". \n"
                        + "Cedula: " + paciente.getCedula() + " \n"
                        + "Sexo: " + paciente.getSexo() + " \n"
                        + "Edad: " + paciente.getEdad() + " \n"
                        + "Historial Clinico: " + paciente.getCausa());

            }

        }
    }

}
