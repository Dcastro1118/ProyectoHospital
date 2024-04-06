package proyecto;

import java.util.ArrayList;

public class Login {
    
    // obtencion active user ///
    private static Medico activeUser;
            
    public static boolean iniciarSesion(String user, String password) {
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
