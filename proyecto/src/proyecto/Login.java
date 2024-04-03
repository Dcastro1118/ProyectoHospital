package proyecto;

import java.util.ArrayList;

public class Login {


    public static void iniciarSesion(String user, String password) {
        boolean autenticado = false;
        int intentos = 0;
        
        Medico newMedico = new Medico();
        newMedico.usuario = "admin";
        newMedico.password = "admin";
        Crud.usuarioDefault(newMedico);
        
        while (intentos < 3 && !autenticado) {

            for (Medico medico : Crud.getListaMedicos()) {
                if (medico.getUsuario().equals(user) && medico.getPassword().equals(password)) {
                    autenticado = true;
                    Hospital.menu();
                }
            }
            if (!autenticado) {
                intentos++;
            }
        }

    }

}
