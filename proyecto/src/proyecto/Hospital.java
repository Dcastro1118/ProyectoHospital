package proyecto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Hospital {

    public static Medico activeUser; // Variable que lleva el registro de que usuario esta activo en el momento //

    public static void main(String[] args) {

        ///////////////////////////Inicio de sesion////////////////////////////////////
        // Creando usuario default //
        Medico newMedico = new Medico();
        newMedico.setUsuario("admin");
        newMedico.setPassword("admin");
        newMedico.setAdministrador(true);
        newMedico.setId(0);
        newMedico.setNombre("Administrador");
        newMedico.setApellidos("del Sistema");
        Crud.usuarioDefault(newMedico);

        for (int i = 0; i < 3; i++) {

            String user = JOptionPane.showInputDialog("Ingrese el nombre de usuario");
            String password = JOptionPane.showInputDialog("Ingrese la contraseña");
            boolean autenticado = Login.iniciarSesion(user, password);

            if (autenticado) {
                activeUser = Login.getActiveUser();
                menu();
                break;

            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }

        }
    }

    ////////////////////////////Menu Principal////////////////////////////////
    public static void menu() {
        int opcionMenu = 0;
        do {
            try {
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(
                        "Elija el numero segun lo que desea hacer: \n"
                        + "1. Crear paciente\n"
                        + "2. Actualizar paciente\n"
                        + "3. Eliminar paciente\n"
                        + "4. Consultar registro\n"
                        + "5. Agregar entrada a paciente\n"
                        + "6. Consultar entradas por paciente\n"
                        + "7. Funciones administrativas\n"
                        + "8. Cerrar Sesion\n"
                        + "9. Salir\n"
                        + "Que desea hacer?:"
                ));

                switch (opcionMenu) {
                    case 1:
                        int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
                        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente");
                        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del paciente");
                        String correo = JOptionPane.showInputDialog("Ingrese el correo del paciente");
                        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del paciente:"));
                        char sexo = JOptionPane.showInputDialog("Ingrese el sexo del paciente en \n"
                                + "'M' para masciluno \n"
                                + "'F' para femenino").toUpperCase().charAt(0);
                        Crud.crearPaciente(cedula, nombre, apellidos, edad, sexo, correo);

                        break;
                    case 2:

                        int cedula2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
                        Paciente pacienteActualizar = Crud.consultarRegistroPaciente(cedula2);
                        if (pacienteActualizar != null) {
                            JOptionPane.showMessageDialog(null, "Paciente encontrado: " + pacienteActualizar.getNombre() + " " + pacienteActualizar.getApellidos());
                            String nombreActualizar = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                            String apellidosActualizar = JOptionPane.showInputDialog("Ingrese los nuevos apellidos");
                            String correo2 = JOptionPane.showInputDialog("Ingrese el correo del paciente");
                            char sexoActualizar = JOptionPane.showInputDialog("Ingrese el sexo del paciente\n"
                                    + "M para masculino\n"
                                    + "F para femenino:").toUpperCase().charAt(0);
                            int edadActualizar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad correcta del paciente:"));
                            Crud.actualizarDatosPaciente(cedula2, nombreActualizar, apellidosActualizar, edadActualizar, sexoActualizar, correo2);
                            JOptionPane.showMessageDialog(null, "El paciente ha sido actualizado!");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro ningun paciente con ese numero de cedula", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 3:

                        Paciente paciente;

                        int cedula3 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del paciente a eliminar:"));
                        paciente = Crud.consultarRegistroPaciente(cedula3);
                        if (paciente != null) {
                            int confirmar = JOptionPane.showConfirmDialog(null, "El paciente a eliminar es: \n" + paciente.getNombre() + " " + paciente.getApellidos());
                            if (confirmar == JOptionPane.YES_OPTION) {
                                Crud.eliminarPaciente(cedula3);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontro el paciente, favor corroborar la informacion");
                        }
                        break;
                    case 4:

                        int cedula4 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de cedula del paciente:"));
                        Paciente pacienteConsultado = Crud.consultarRegistroPaciente(cedula4);
                        List historiales = Crud.buscarHistorial(cedula4);
                        if (pacienteConsultado != null) {
                            JOptionPane.showMessageDialog(null, "Nombre: " + pacienteConsultado.getNombre() + "\n"
                                    + "Apellidos: " + pacienteConsultado.getApellidos() + "\n"
                                    + "Sexo: " + pacienteConsultado.getSexo() + "\n"
                                    + "Edad: " + pacienteConsultado.getEdad() + "\n"
                                    + "Correo: " + pacienteConsultado.getCorreo() + "\n"
                                    + "Cantidad de registros del paciente: " + historiales.size());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado ningun paciente con ese numero de cedula");
                        }
                        break;
                    case 5:
                        int cedula5 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del paciente a consultar:"));
                        Paciente paciente2 = Crud.consultarRegistroPaciente(cedula5);

                        if (paciente2 == null) {
                            JOptionPane.showMessageDialog(null, "No existe ningun paciente con ese numero de Cedula\n "
                                    + "favor crear primero el paciente o revisar que este digitado correctamente");
                        } else {
                            int confirmacion = JOptionPane.showConfirmDialog(null, "El paciente es: " + paciente2.getNombre() + " " + paciente2.getApellidos());
                            if (confirmacion == JOptionPane.YES_OPTION) {
                                int idMedico = activeUser.getId();
                                LocalDate fecha = LocalDate.now();
                                String entrada = JOptionPane.showInputDialog("Ingrese la entrada a el historial del paciente:");
                                Crud.agregarEntrada(cedula5, idMedico, fecha, entrada);
                            }
                        }
                        break;
                    case 6:

                        int cedula6 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cedula del paciente a consultar:"));
                        JOptionPane.showMessageDialog(null, Crud.obtenerEntradas(cedula6));

                        break;
                    case 7:
                        if (activeUser.isAdministrador()) {

                            menuAdmin();
                        } else {
                            JOptionPane.showMessageDialog(null, "Usted no tiene permiso para entrar al menu de funciones administrativas", "Acceso denegado", JOptionPane.ERROR_MESSAGE);

                        }
                        break;
                    case 8:

                        String user = JOptionPane.showInputDialog("Ingrese el usuario:");
                        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
                        Login.iniciarSesion(user, password);
                        activeUser = Login.getActiveUser();
                        menu();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, selecciona una opción válida.", "Error", JOptionPane.ERROR_MESSAGE);

                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor numerico segun la opcion escogida en el menu");
            }

        } while (opcionMenu != 8); // Salir del bucle cuando el usuario elija la opción "Salir"

    }

    //////////////////////////Menu administrativo (Require rol administrador)////////////////////
    public static void menuAdmin() {
        int opcionMenuAdm;
        do {
            opcionMenuAdm = Integer.parseInt(JOptionPane.showInputDialog("Elija el numero segun lo que desea hacer: \n"
                    + "1. Crear Medico\n"
                    + "2. Actualizar Medico\n"
                    + "3. Eliminar Medico\n"
                    + "4. Consultar registro de Medico por ID\n"
                    + "5. Consultar registro de Medico por nombre\n"
                    + "6. Otorgar permisos administrativos a un medico\n"
                    + "7. Quitar permisos administrativos a un medico\n"
                    + "8. Salir\n"
                    + "Que desea hacer?:"
            ));
            switch (opcionMenuAdm) {
                case 1:
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico");
                    String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del medico");
                    String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del medico:");
                    String usuario = JOptionPane.showInputDialog("Ingrese el nombre de usuario para el medico:");
                    String password = JOptionPane.showInputDialog("Ingrese la contraseña para el usuario del medico:");
                    int consultaRol = JOptionPane.showConfirmDialog(null, "¿Quieres que este medico tenga permisos administrativos en el sistema?");
                    boolean administrador = false;
                    if (consultaRol == JOptionPane.YES_OPTION) {
                        administrador = true;
                    }
                    Crud.agregarMedico(nombre, apellidos, especialidad, administrador, usuario, password);

                    break;
                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID del medico:"));
                    if (Crud.consultarRegistroMedicoId(id) != null) {
                        int opcionMenuAdm2;

                        do {
                            opcionMenuAdm2 = Integer.parseInt(JOptionPane.showInputDialog("Medico encontrado encontrado, que desea hacer:\n"
                                    + "1. Actualizar la informacion basica del medico\n"
                                    + "2. Actualizar el usuario\n"
                                    + "2. Actualizar la contraseña"));

                            switch (opcionMenuAdm2) {
                                case 1:
                                    String nombreActualizar = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                                    String apellidosActualizar = JOptionPane.showInputDialog("Ingrese los nuevos apellidos");
                                    String especialidadActualizar = JOptionPane.showInputDialog("Actualice o agregue la nueva especialidad del medico");
                                    Crud.actualizarDatosMedico(id, nombreActualizar, apellidosActualizar, especialidadActualizar);
                                    break;
                                case 2:
                                    String usuarioActualizar = JOptionPane.showInputDialog("Ingrese el nuevo nombre de usuario:");
                                    Crud.actualizarUserMedico(id, usuarioActualizar);
                                    break;
                                case 3:
                                    String passwordActualizar = JOptionPane.showInputDialog("Ingrese el nuevo password para el usuario:");
                                    Crud.actualizarUserMedico(id, passwordActualizar);
                                    break;
                                case 4:

                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Ingrese una de las opciones del menul");
                                    break;
                            }
                        } while (opcionMenuAdm2 != 4);
                        {
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun medico con ese numero de ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:

                    int idMedicoEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID de el Medico:"));
                    Medico medicoAEliminar = Crud.consultarRegistroMedicoId(idMedicoEliminar);
                    int confirmacion;
                    if (medicoAEliminar != null) {
                        confirmacion = JOptionPane.showConfirmDialog(null, "El medico a eliminar es: " + medicoAEliminar.getNombre() + " " + medicoAEliminar.getApellidos() + "\nSeguro que desea eliminarlo?");
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            Crud.eliminarMedico(idMedicoEliminar);
                        } else {
                            break;
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontro ningun medico con ese ID");
                    }
                    break;
                case 4:
                    int idConsulta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de ID del medico a consultar:"));
                    Medico medicoConsultadoId = Crud.consultarRegistroMedicoId(idConsulta);
                    if (medicoConsultadoId != null && medicoConsultadoId.isAdministrador() == true) {
                        JOptionPane.showMessageDialog(null, "El medico es un administrador.\n"
                                + "Nombre: " + medicoConsultadoId.getNombre() + "\n"
                                + "Apellidos: " + medicoConsultadoId.getApellidos() + "\n"
                                + "Especialidad: " + medicoConsultadoId.getEspecialidad() + "\n");
                    } else if (medicoConsultadoId != null && medicoConsultadoId.isAdministrador() == false) {
                        JOptionPane.showMessageDialog(null, "El medico no es un administrador.\n"
                                + "Nombre: " + medicoConsultadoId.getNombre() + "\n"
                                + "Apellidos: " + medicoConsultadoId.getApellidos() + "\n"
                                + "Especialidad: " + medicoConsultadoId.getEspecialidad() + "\n");

                    } else {
                        JOptionPane.showMessageDialog(null, "El ID no coincide con ningun medico");

                    }
                    break;
                case 5:
                    int contador = 0;
                    String nombreConsulta = JOptionPane.showInputDialog("Ingrese el Nombre del medico a consultar:");
                    ArrayList<Medico> coincidencias = Crud.consultarRegistroMedicoNombre(nombreConsulta);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Medico coincidencia : coincidencias) {
                        stringBuilder.append("\n\nMedico ").append(contador).append(": ").append(coincidencia.getNombre()).append(" "
                        ).append(coincidencia.getApellidos()).append("\nEspecialidad: ").append(coincidencia.getEspecialidad()
                        ).append("\nID: ").append(coincidencia.getId());
                        contador++;
                    }
                    JOptionPane.showMessageDialog(null, "Coincidencias: " + stringBuilder.toString());
                    break;
                case 6:
                    int idNuevoAdm = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del medico al que desea dar permisos administrativos:"));
                    Medico medicoNuevoAdm = Crud.consultarRegistroMedicoId(idNuevoAdm);
                    int upgradeConfirmation = JOptionPane.showConfirmDialog(null, "El medico escogido es:" + "\n"
                            + "Nombre: " + medicoNuevoAdm.getNombre() + "\n"
                            + "Apellidos: " + medicoNuevoAdm.getApellidos() + "\n"
                            + "Especialidad: " + medicoNuevoAdm.getEspecialidad() + "\n"
                            + "ID:" + medicoNuevoAdm.getId() + "\n"
                            + "Esta seguro que desea hacerlo administrador?");
                    if (upgradeConfirmation == JOptionPane.YES_OPTION) {
                        medicoNuevoAdm.setAdministrador(true);

                    }
                    break;
                case 7:
                    int idQuitarAdm = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del medico al que desea dar permisos administrativos:"));
                    Medico medicoQuitarAdm = Crud.consultarRegistroMedicoId(idQuitarAdm);
                    int downGradeConfirmation = JOptionPane.showConfirmDialog(null, "El medico escogido es:\n"
                            + "Nombre: " + medicoQuitarAdm.getNombre() + "\n"
                            + "Apellidos: " + medicoQuitarAdm.getApellidos() + "\n"
                            + "Especialidad: " + medicoQuitarAdm.getEspecialidad() + "\n"
                            + "ID:" + medicoQuitarAdm.getId() + "\n"
                            + "Esta seguro que desea hacerlo administrador?");
                    if (downGradeConfirmation == JOptionPane.YES_OPTION) {
                        medicoQuitarAdm.setAdministrador(false);

                    }
                    break;
                case 8:
                    menu();
                    break;

                default:
                    throw new AssertionError();
            }
        } while (opcionMenuAdm != 7);

    }

}
