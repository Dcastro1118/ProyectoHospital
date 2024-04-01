package proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Proyecto {

    public static void main(String[] args) {
        iniciarSesion();

    }

    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    private static final String user = "admin";
    private static final String password = "admin";

    //Metodo para iniciar sesion    
    public static void iniciarSesion() {

        boolean autenticado = false;
        int intentos = 0;

        while (intentos < 3 && !autenticado) {

            String inputUser = JOptionPane.showInputDialog("Ingrese su nombre de Usuario");
            String inputPassword = JOptionPane.showInputDialog("Ingrese su contraseña");

            if (inputUser.equals(user) && inputPassword.equals(password)) {
                autenticado = true;
                menu();
            } else {
                JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos");
                intentos++;
            }
        }

    }

    //Metodo para mostrar el menu
    public static void menu() {
        int opcionMenu1;
        do {
            opcionMenu1 = Integer.parseInt(JOptionPane.showInputDialog(
                    "Elija el numero segun lo que desea hacer: \n "
                    + "1. Crear paciente\n "
                    + "2. Actualizar paciente\n "
                    + "3. Eliminar paciente\n "
                    + "4. Consultar registros\n "
                    + "5. Funciones administrativas\n"
                    + "6. Salir \n"
                    + "Que desea hacer?:"
            ));

            switch (opcionMenu1) {
                case 1:
                    crearPaciente();

                    break;
                case 2:
                    actualizarPaciente();
                    break;
                case 3:
                    eliminarPaciente();

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");

            }
        } while (opcionMenu1 != 6); // Salir del bucle cuando el usuario elija la opción "Salir"

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
    public static void crearPaciente() {
        int cedulaNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente"));
        if (!verificarExistencia(cedulaNueva)) {
            String nombreNuevo = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
            String apellidosNuevos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
            String historialNuevo = JOptionPane.showInputDialog("Adicione la causa al historial clinico");
            int edadNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente"));
            char sexoNuevo = JOptionPane.showInputDialog("Ingrese el sexo del paciente en \n"
                    + "'M' para masciluno \n"
                    + "'F' para femenino").charAt(0);
            Paciente nuevoPaciente = new Paciente(nombreNuevo, apellidosNuevos, historialNuevo, sexoNuevo, edadNueva, cedulaNueva);
            listaPacientes.add(nuevoPaciente);
            JOptionPane.showMessageDialog(null, "El Paciente a sido creado con exito!");
        } else {
            JOptionPane.showMessageDialog(null, "El paciente ya existe, porfavor actualizarlo.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    //metodo para actualizar paciente
    public static void actualizarPaciente() {
        int cedulaNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
        boolean encontrado = false;
        for (Paciente paciente : listaPacientes) {
            if (paciente.getCedula() == cedulaNueva) {
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

    public static void eliminarPaciente() {

        int cedulaNueva = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula de el Paciente que desea eliminar:"));
        List<Paciente> pacientesAEliminar = new ArrayList<>();
            for (Paciente paciente : listaPacientes) {
                if (paciente.getCedula() == cedulaNueva) {
                    pacientesAEliminar.add(paciente); // Agrega el paciente a la lista temporal
                }
            }
            listaPacientes.removeAll(pacientesAEliminar); // Elimina todos los pacientes de la lista original

    }

}
