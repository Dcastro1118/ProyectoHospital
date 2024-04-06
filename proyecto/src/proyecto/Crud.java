package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Crud {

    ///// Declaracion y getters de listas/////
    private static int contadorCodigos;
    private static int contadorIds = 1;
    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static ArrayList<Medico> listaMedicos = new ArrayList<>();
    private static ArrayList<Historial> listaHistoriales = new ArrayList<>();

    public static ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public static ArrayList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    // Agregar el primer usuario por default //
    public static void usuarioDefault(Medico medico) {

        listaMedicos.add(medico);
    }

    ////////////////////////////Logica de medicos ////////////////////////////////
    //Crear medico
    public static void agregarMedico(String nombre, String apellidos, String especialidad, boolean administrador, String usuario, String password) {

        Medico newMedico = new Medico();
        newMedico.setNombre(nombre);
        newMedico.setApellidos(apellidos);
        newMedico.setEspecialidad(especialidad);
        newMedico.setAdministrador(administrador);
        newMedico.setUsuario(usuario);
        newMedico.setPassword(password);
        newMedico.setId(contadorIds);
        contadorIds++;
        listaMedicos.add(newMedico);
    }

    public static void actualizarDatosMedico(int id, String nombre, String apellidos, String especialidad) {

        for (Medico medico : listaMedicos) {
            if (medico.getId() == id) {
                medico.setNombre(nombre);
                medico.setApellidos(apellidos);
                medico.setEspecialidad(especialidad);

            }
        }

    }

    public static void actualizarUserMedico(int id, String usuario) {

        for (Medico medico : listaMedicos) {
            if (medico.getId() == id) {
                medico.setUsuario(usuario);
            }
        }

    }

    public static void actualizarPasswordMedico(int id, String password) {

        for (Medico medico : listaMedicos) {
            if (medico.getId() == id) {
                medico.setUsuario(password);
            }
        }

    }

    public static void eliminarMedico(int id) {

        List<Medico> medicosAEliminar = new ArrayList<>();
        for (Medico medico : listaMedicos) {
            if (medico.getId() == id) {
                medicosAEliminar.add(medico); // Agrega el paciente a la lista temporal
            }
        }
        listaMedicos.removeAll(medicosAEliminar); // Elimina todos los pacientes de la lista original

    }

    public static Medico consultarRegistroMedicoId(int id) {

        for (Medico medico : listaMedicos) {

            if (medico.getId() == id) {
                return medico;
            }
        }
        return null;
    }

    public static Medico consultarRegistroMedicoNombre(String nombre) {

        for (Medico medico : listaMedicos) {

            if (medico.getNombre() == nombre) {
                return medico;
            }
        }
        return null;
    }

    ////////////////////////////////Logica de pacientes/////////////////////////
//metodo para crear paciente
    public static void crearPaciente(int Cedula, String nombre, String apellidos, int edad, char sexo) {

        if (consultarRegistroPaciente(Cedula) == null) {

            Paciente nuevoPaciente = new Paciente(nombre, apellidos, sexo, edad, Cedula);
            listaPacientes.add(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "El Paciente a sido creado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe, porfavor actualizarlo.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //metodo para actualizar paciente Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
    public static void actualizarDatosPaciente(int cedula, String nombre, String apellidos, int edad, char sexo) {

        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedula) {
                paciente.setNombre(nombre);
                paciente.setApellidos(apellidos);
                paciente.setEdad(edad);
                paciente.setSexo(sexo);

            }
        }

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

    public static Paciente consultarRegistroPaciente(int cedula) {

        for (Paciente paciente : listaPacientes) {

            if (paciente.getCedula() == cedula) {
                return paciente;
            }
        }
        return null;
    }

    /////////////////////////Logica de historiales ////////////////////////////
    public static List buscarHistorial(int cedula) {
        List<Historial> historialesEncontrados = new ArrayList<>();
        for (Historial historial : listaHistoriales) {
            if (historial.getCedulaPaciente() == cedula) {
                historialesEncontrados.add(historial);

            }

        }

        return historialesEncontrados;

    }

    public static void agregarEntrada(int cedulaPaciente, int idMedico, LocalDate fecha, String entrada) {

        Historial newEntrada = new Historial();
        newEntrada.setCedulaPaciente(cedulaPaciente);
        newEntrada.setIdMedico(idMedico);
        newEntrada.setFecha(fecha);
        newEntrada.setEntrada(entrada);
        newEntrada.setCodigo(contadorCodigos);
        contadorCodigos++;
        listaHistoriales.add(newEntrada);

    }

}
