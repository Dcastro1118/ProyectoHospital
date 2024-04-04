package proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Crud {

    private static int contadorIds = 0;
    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static ArrayList<Medico> listaMedicos = new ArrayList<>();
    private static ArrayList<Historial> listaHistoriales = new ArrayList<>();

    // Agregar el primer usuario por default
    public static void usuarioDefault(Medico medico) {

        listaMedicos.add(medico);
    }

    //obtener lista de pacientes
    public static ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;

    }

    //obtener lista de medicos
    public static ArrayList<Medico> getListaMedicos() {
        return listaMedicos;

    }

    //Crear medico
    public static void agregarMedico(String nombre, String apellidos, String especialidad, String usuario, String password) {

        Medico newMedico = new Medico();
        newMedico.setNombre(nombre);
        newMedico.setApellidos(apellidos);
        newMedico.setEspecialidad(especialidad);
        newMedico.setUsuario(usuario);
        newMedico.setPassword(password);
        newMedico.setId(contadorIds);
        contadorIds++;

    }

//Metodo para verificar la existencia de un paciente basado en su numero de cedula
    public static Paciente verificarExistencia(int Cedula) {

        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == Cedula) {
                return paciente;
            }
        }
        return null;
    }

//metodo para crear paciente
    public static void crearPaciente(int Cedula, String nombre, String apellidos, int edad, char sexo) {

        if (verificarExistencia(Cedula) == null) {

            Paciente nuevoPaciente = new Paciente(nombre, apellidos, sexo, edad, Cedula);
            listaPacientes.add(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "El Paciente a sido creado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe, porfavor actualizarlo.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //metodo para actualizar paciente Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
    public static void menuActualizar(int cedula, int opcion, String nombre, String apellidos, int edad, char sexo) {

        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedula) {
                switch (opcion) {
                    case 1:
                        actualizarDatosPaciente(paciente, nombre, apellidos, edad, sexo);
                        break;
                    case 2:
                        //actualizarHistorialPaciente(paciente);
                        break;
                    default:
                        throw new AssertionError("Ingrese una de las opciones validas!");
                }

            }
        }

    }

    public static void actualizarDatosPaciente(Paciente paciente, String nombre, String apellidos, int edad, char sexo) {
        paciente.setNombre(nombre);
        paciente.setApellidos(apellidos);
        paciente.setEdad(edad);
        paciente.setSexo(sexo);
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

    public static Paciente consultarRegistro(int cedula) {

        for (Paciente paciente : listaPacientes) {

            if (paciente.getCedula() == cedula) {
                return paciente;
            }
        }
        return null;
    }

}
