package proyecto;

import java.util.ArrayList;

public class Login {
    
    private static Medico activeUser;
            
    public static boolean iniciarSesion(String user, String password) {


        Medico newMedico = new Medico();
        newMedico.setUsuario("admin");
        newMedico.setPassword("admin");
        newMedico.setAdministrador(true);
        Crud.usuarioDefault(newMedico);


            for (Medico medico : Crud.getListaMedicos()) {
                if (medico.getUsuario().equals(user) && medico.getPassword().equals(password)) {
                    setActiveUser(medico);
                    return true;
                }

            }
        return false;

    }
    
    public static void setActiveUser (Medico user){
    
    Login.activeUser = user;   
    
    }
    
    public static Medico getActiveUser(){
    return Login.activeUser;
    
    }

}
