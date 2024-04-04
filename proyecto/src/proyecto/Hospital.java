package proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Hospital {

    public static void main(String[] args) {

      
        for (int i = 0; i < 3 ; i++) {
            
        String user = JOptionPane.showInputDialog("Ingrese el nombre de usuario");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña");
        boolean autenticado = Login.iniciarSesion(user, password);
        
        if (autenticado) {
            menu();
            
        } else 

            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            
        
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
                    + "4. Consultar registro\n "
                    + "5. Funciones administrativas\n"
                    + "6. Salir \n"
                    + "Que desea hacer?:"
            ));

            switch (opcionMenu1) {
                case 1:
                    int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
                    String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente"));
                    char sexo = JOptionPane.showInputDialog("Ingrese el sexo del paciente en \n"
                            + "'M' para masciluno \n"
                            + "'F' para femenino").toUpperCase().charAt(0);
                    Crud.crearPaciente(cedula, nombre, apellidos, edad, sexo);
                    break;
                case 2:
                    int cedula2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente"));
                    if (Crud.verificarExistencia(cedula2) != null) {
                        int opcion = Integer.parseInt(JOptionPane.showInputDialog("Paciente encontrado, que desea hacer:\n"
                                + "1. Actualizar todos los datos\n"
                                + "2. Actualizar solo el historial"));
                        if (opcion == 1) {
                            String nombreActualizar = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                            String apellidosActualizar = JOptionPane.showInputDialog("Ingrese los nuevos apellidos");
                            char sexoActualizar = JOptionPane.showInputDialog("Ingrese el sexo del paciente\n"
                                    + "M para masculino\n"
                                    + "F para femenino:").toUpperCase().charAt(0);
                            int edadActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente:"));
                            Crud.menuActualizar(cedula2, opcion, nombreActualizar, apellidosActualizar, edadActualizar, sexoActualizar);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun paciente con ese numero de cedula", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:
                    Crud.eliminarPaciente(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula de el paciente:")));
                    break;
                case 4:
                    int cedula3 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente a consultar:"));
                    Paciente pacienteConsultado = Crud.consultarRegistro(cedula3);
                    if (pacienteConsultado != null) {
                        JOptionPane.showMessageDialog(null, "Nombre: " + pacienteConsultado.getNombre() + "\n"
                                + "Apellidos: " + pacienteConsultado.getApellidos() + "\n"
                                + "Sexo: " + pacienteConsultado.getSexo() + "\n"
                                + "Edad: " + pacienteConsultado.getEdad());

                    }
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

}
