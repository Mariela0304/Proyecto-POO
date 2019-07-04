package bonilla.mariela.bl.usuario;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

/**
 * Clase que va a representar el objeto Usuario y sus atributos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Usuario {
    protected String estado;
    protected String tipoUsuario;
    protected String nombre;
    protected String tipoIdentificacion;
    protected String identificacion;
    protected LocalDate fechaNacimiento;
    protected int edad;
    protected String correo;
    protected String contrasenna;


    /**
     * Constructor por defecto de Usuario
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Usuario() {

    }


    /**
     * Constructor de Usuario con parámetros
     * @author Mariela Bonilla
     * @param nombre : Nombre de usuario
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de usuario
     * @param correo : Correo de ususario
     * @param contrasenna : Contraseña de usuario
     * @version 1.0
     */
    public Usuario(String nombre,
                   LocalDate fechaNacimiento, int edad, String correo, String contrasenna) {

        this.nombre =nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasenna = contrasenna;
    }

    /**
     * Constructor de Coleccionista con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de usuario
     * @param tipoUsuario : Tipo de usuario
     * @param nombre : Nombre de usuario
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del usuario
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de coleccionista
     * @param correo : Correo de usuario
     * @version 1.0
     */
    public Usuario(String estado, String tipoUsuario, String nombre, String tipoIdentificacion, String identificacion,
                   LocalDate fechaNacimiento, int edad, String correo) {
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.nombre =nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
    }



    /**
     * Constructor de Coleccionista con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de usuario
     * @param tipoUsuario : Tipo de ususario
     * @param nombre : Nombre de usuario
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del usuario
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de usuario
     * @param correo : Correo de usuario
     * @param contrasenna : Contraseña de usuario
     * @version 1.0
     */
    public Usuario(String estado,String tipoUsuario, String nombre,String tipoIdentificacion, String identificacion,
                   LocalDate fechaNacimiento, int edad, String correo, String contrasenna) {
        this.estado = estado;
        this.tipoUsuario = tipoUsuario;
        this.nombre =nombre;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion ;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.correo = correo;
        this.contrasenna = contrasenna;
    }


    /**
     * Método get del estado del usuario
     * @author Mariela Bonilla
     * @return Retorna del estado del usuario
     * @version 1.0
     */
    public String getEstado() {
        return estado;
    }


    /**
     * Método get del tipo de usuario del usuario
     * @author Mariela Bonilla
     * @return Retorna el tipo de usuario del usuario
     * @version 1.0
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }


    /**
     * Método get del nombre del usuario
     * @author Mariela Bonilla
     * @return Retorna el nombre del usuario
     * @version 1.0
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método get del tipo de identificación del usuario
     * @author Mariela Bonilla
     * @return Retorna el tipo de identificación del usuario
     * @version 1.0
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }


    /**
     * Método get de la identificación del usuario
     * @author Mariela Bonilla
     * @return Retorna la identificación del usuario
     * @version 1.0
     */
    public String getIdentificacion() {
        return identificacion;
    }


    /**
     * Método get de la fecha de nacimiento del usuario
     * @author Mariela Bonilla
     * @return Retorna la fecha de nacimiento del usuario
     * @version 1.0
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método set de la fecha de nacimiento para el usuario
     * @author Mariela Bonilla
     * @param fechaNacimiento : nueva fecha de nacimiento para el usuario
     * @version 1.0
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Método get de la edad del usuario
     * @author Mariela Bonilla
     * @return Retorna la edad del usuario
     * @version 1.0
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método get del correo del usuario
     * @author Mariela Bonilla
     * @return Retorna el correo del usuario
     * @version 1.0
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método get de la contraseña del usuario
     * @author Mariela Bonilla
     * @return Retorna la contraseña del usuario
     * @version 1.0
     */
    public String getContrasenna() {
        return contrasenna;
    }


    /**
     * Método set del estado para el usuario
     * @author Mariela Bonilla
     * @param estado : nuevo estado para el usuario
     * @version 1.0
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método set del tipo de usuario para el usuario
     * @author Mariela Bonilla
     * @param tipoUsuario : nuevo tipo de usuario para el usuario
     * @version 1.0
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Método set del nombre para el usuario
     * @author Mariela Bonilla
     * @param nombre : nuevo nombre para el usuario
     * @version 1.0
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método set del tipo de identificación para el usuario
     * @author Mariela Bonilla
     * @param tipoIdentificacion : nuevo tipo de identificación para el usuario
     * @version 1.0
     */
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * Método set de la identificación para el usuario
     * @author Mariela Bonilla
     * @param identificacion : nueva identificación para el usuario
     * @version 1.0
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Método set de edad para el usuario
     * @author Mariela Bonilla
     * @param edad : nueva edad para el usuario
     * @version 1.0
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método set del correo para el usuario
     * @author Mariela Bonilla
     * @param correo : nuevo correo para el usuario
     * @version 1.0
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }


    /**
     * Método set de la contraseña para el usuario
     * @author Mariela Bonilla
     * @param contrasenna : nueva contraseña para el usuario
     * @version 1.0
     */
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    /**
     * Método toString() de la clase Usuario
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "-------- USUARIO: -------------------------------------------\n" +
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
