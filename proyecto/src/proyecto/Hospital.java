package proyecto;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Hospital {

    public static void main(String[] args) {
        String user = JOptionPane.showInputDialog("Ingrese el nombre de usuario");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña");
        Login.iniciarSesion(user, password);

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
                    Crud.crearPaciente(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:")));
                    break;
                case 2:
                    Crud.actualizarPaciente(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:")));
                    break;
                case 3:
                    Crud.eliminarPaciente(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula de el paciente:")));
                    break;
                case 4:
                    Crud.consultarRegistro(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente a consultar:")));
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
    
    
    



