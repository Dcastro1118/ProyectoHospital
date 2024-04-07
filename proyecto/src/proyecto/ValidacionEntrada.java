
package proyecto;

import javax.swing.JOptionPane;


public class ValidacionEntrada {
    
public static int validarEntero(String entero) {
        int dato;

            try {
                dato = Integer.parseInt(entero);
                return dato;
            } catch (NumberFormatException e) {
                return 0;
            }

    }

}

