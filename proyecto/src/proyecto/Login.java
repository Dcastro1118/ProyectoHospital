package proyecto;

import java.util.ArrayList;

public class Login {

    public static boolean iniciarSesion(String user, String password) {

        Medico newMedico = new Medico();
        newMedico.setUsuario("admin");
        newMedico.setPassword("admin");
        Crud.usuarioDefault(newMedico);


            for (Medico medico : Crud.getListaMedicos()) {
                if (medico.getUsuario().equals(user) && medico.getPassword().equals(password)) {
                    return true;
                }

            }
        return false;

    }

}
