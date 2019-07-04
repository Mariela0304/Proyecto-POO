package bonilla.mariela.bl.usuario;

import java.time.LocalDate;


/**
 * Clase que va a representar el objeto Moderador y todos sus atributos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Moderador extends Usuario{

    /**
     * Constructor por defecto de Moderador
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Moderador() {
        super();
    }

    /**
     * Constructor de Moderador con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de coleccionitsa
     * @param tipoUsuario : Tipo de ususario
     * @param nombre : Nombre de coleccionista
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del coleccionista
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de coleccionista
     * @param correo : Correo de coleccionista
     * @param contrasenna : Contraseña de usuario
     * @version 1.0
     */

    public Moderador(String estado, String tipoUsuario, String nombre, String tipoIdentificacion, String identificacion,
                     LocalDate fechaNacimiento, int edad, String correo, String contrasenna) {
        super(estado,tipoUsuario, nombre, tipoIdentificacion,identificacion, fechaNacimiento, edad, correo, contrasenna);

    }


    /**
     * Método toString() de la clase Moderador
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "-------- MODERADOR: -------------------------------------------\n" +
                "\n Nombre: " + nombre +
                "\n Tipo de identificación: " + tipoIdentificacion +
                "\n Identificación: " + identificacion +
                "\n Fecha de nacimiento: " + fechaNacimiento +
                "\n Edad: " + edad +
                "\n Correo electrónico: " + correo +
                "\n Contrasenna: " + contrasenna  + //no es correcto que se pueda imprimir la contrasenna, sin embargo,
                //                                         la agrego para comprobar que se imprime de manera
                //                                          correcta.
                "\n\n-------------------------------------------------------------\n\n";
    }
}
