package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Crud {

    ///// Declaracion y getters de listas/////
    private static int contadorCodigos = 1;
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

    public static ArrayList consultarRegistroMedicoNombre(String nombre) {

        ArrayList<Medico> medicosCoincidencias = new ArrayList<>();
        for (Medico medico : listaMedicos) {
            // Limpiar el nombre antes de comparar //
            String nombreLimpiado = medico.getNombre().trim().toUpperCase(); // Elimina espacios en blanco al inicio y al final //
            if (nombreLimpiado.equals(nombre.trim().toUpperCase())) { // Comparar después de limpiar //
                medicosCoincidencias.add(medico);
            }
        }
        return medicosCoincidencias;

    }
    
    

    ////////////////////////////////Logica de pacientes/////////////////////////
    
    
    
//metodo para crear paciente
    public static void crearPaciente(int cedula, String nombre, String apellidos, int edad, char sexo, String correo) {

        if (consultarRegistroPaciente(cedula) == null) {

            Paciente nuevoPaciente = new Paciente(nombre, apellidos, sexo, edad, cedula, correo);
            listaPacientes.add(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "El Paciente a sido creado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe, porfavor actualizarlo.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //metodo para actualizar paciente Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
    public static void actualizarDatosPaciente(int cedula, String nombre, String apellidos, int edad, char sexo, String correo) {

        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedula) {
                paciente.setNombre(nombre);
                paciente.setApellidos(apellidos);
                paciente.setEdad(edad);
                paciente.setSexo(sexo);
                paciente.setCorreo(correo);

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

    public static String obtenerEntradas(int cedula) {

        String error = "Ningun registro fue encontrado con ese numero de cedula";
        StringBuilder stringBuilder = new StringBuilder();
        for (Historial historial : listaHistoriales) {
            Medico medicoEntrada = null;
            Paciente pacienteConsultado = null;
            if (historial.getCedulaPaciente() == cedula) {

                for (Medico medico : listaMedicos) {
                    if (medico.getId() == historial.getIdMedico()) {
                        medicoEntrada = medico;
                        for (Paciente paciente : listaPacientes) {
                            if (paciente.getCedula() == cedula) {
                                pacienteConsultado = paciente;

                            }

                        }
                    }
                }
            }
            stringBuilder.append("Fecha: ").append(historial.getFecha()
            ).append("\nPaciente: ").append(pacienteConsultado.getNombre()).append(
                    " ").append(pacienteConsultado.getApellidos()).append("\nMedico: "
            ).append(medicoEntrada.getNombre()).append("\nCodigo: ").append(historial.getCodigo()).append("\nObservaciones: \n").append(historial.getEntrada()).append("\n\n");

        }
        if (stringBuilder.length() != 0) {
            return stringBuilder.toString();
        }
        return error;
    }

    public static void modificarEntrada(int codigo, String entrada) {

        for (Historial historial : listaHistoriales) {
            if (codigo == historial.getCodigo()) {
                historial.setEntrada(entrada);

            }
        }

    }

    public static String devolverEntrada(int codigo) {

        for (Historial historial : listaHistoriales) {
            if (codigo == historial.getCodigo()) {
                String entrada = historial.getEntrada();
                return entrada;
            }

        }
        return null;

    }

    public static void eliminarHistorial(int codigo) {

        for (Historial historial : listaHistoriales) {
            if (historial.getCodigo() == codigo) {
                listaHistoriales.remove(historial);
            }
        }
    }

    public static Historial devolverHistorial(int codigo) {
        for (Historial historial : listaHistoriales) {
            if (historial.getCodigo() == codigo) {
                return historial;
            }
        }
        return null;
    }
}
