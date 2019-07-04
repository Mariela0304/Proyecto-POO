package bonilla.mariela.bl.usuario;

import bonilla.mariela.bl.usuario.Usuario;

import java.time.LocalDate;

public class Vendedor extends Usuario {
    private double puntuacion;
    private String provincia;
    private String canton;
    private String distrito;
    private String direccion;


    public Vendedor() {
        super();
    }


    /**
     * Constructor de Vendedor con parámetros
     * @author Mariela Bonilla
     * @param nombre : Nombre de vendedor
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de vendedor
     * @param correo : Correo de vendedor
     * @param contrasenna : Contraseña de vendedor
     * @param provincia : Provincia de vendedor
     * @param canton : Cantón de vendedor
     * @param distrito : Distrito de vendedor
     * @param direccion : Dirección de vendedor
     * @version 1.0
     */
    public Vendedor( String nombre,
                    LocalDate fechaNacimiento,
                    int edad, String correo, String contrasenna, String provincia, String canton, String distrito,
                    String direccion) {
        super(nombre, fechaNacimiento, edad, correo, contrasenna);
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
    }

    /**
     * Constructor de Vendedor con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de vendedor
     * @param tipoUsuario : Tipo de ususario
     * @param nombre : Nombre de vendedor
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del vendedor
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de vendedor
     * @param correo : Correo de vendedor
     * @param provincia : Provincia de vendedor
     * @param canton : Cantón de vendedor
     * @param distrito : Distrito de vendedor
     * @param direccion : Dirección de vendedor
     * @param puntuacion : Puntuación de vendedor
     * @version 1.0
     */
    public Vendedor(String estado, String tipoUsuario, String nombre, String tipoIdentificacion,String identificacion,
                    LocalDate fechaNacimiento,
                         int edad, String correo, String provincia, String canton, String distrito,
                    String direccion, double puntuacion) {
        super(estado,tipoUsuario,nombre, tipoIdentificacion,identificacion, fechaNacimiento, edad, correo);
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
    }

    /**
     * Constructor de Vendedor con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de vendedor
     * @param tipoUsuario : Tipo de usuario
     * @param nombre : Nombre de vendedor
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del vendedor
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de vendedor
     * @param correo : Correo de vendedor
     * @param contrasenna : Contraseña de vendedor
     * @param provincia : Provincia de vendedor
     * @param canton : Cantón de vendedor
     * @param distrito : Distrito de vendedor
     * @param direccion : Dirección de vendedor
     * @param puntuacion : Puntuación de vendedor
     * @version 1.0
     */
    public Vendedor(String estado, String tipoUsuario, String nombre,String tipoIdentificacion, String identificacion,
                    LocalDate fechaNacimiento,
                    int edad, String correo,
                    String contrasenna, String provincia, String canton, String distrito, String direccion, double puntuacion) {
        super(estado, tipoUsuario, nombre, tipoIdentificacion, identificacion, fechaNacimiento, edad, correo, contrasenna);
        this.puntuacion = puntuacion;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
    }

    /**
     * Método get de la puntuación del vendedor
     * @author Mariela Bonilla
     * @return Retorna la puntuación para el vendedor
     * @version 1.0
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * Método set de la puntuacion obtenida para el vendedor
     * @author Mariela Bonilla
     * @param puntuacion : nueva puntuacion para el vendedor
     * @version 1.0
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Método get de la provincia del vendedor
     * @author Mariela Bonilla
     * @return Retorna la provincia del vendedor
     * @version 1.0
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Método set de la provincia obtenida para el vendedor
     * @author Mariela Bonilla
     * @param provincia : nueva provincia para el vendedor
     * @version 1.0
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Método get del cantón del vendedor
     * @author Mariela Bonilla
     * @return Retorna el cantón del vendedor
     * @version 1.0
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Método set del cantón obtenido para el vendedor
     * @author Mariela Bonilla
     * @param canton : nuevo cantón para el vendedor
     * @version 1.0
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     * Método get del distrito del vendedor
     * @author Mariela Bonilla
     * @return Retorna el distrito del vendedor
     * @version 1.0
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Método set del distrito obtenido para el vendedor
     * @author Mariela Bonilla
     * @param distrito : nuevo distrito para el vendedor
     * @version 1.0
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Método get de la dirección del vendedor
     * @author Mariela Bonilla
     * @return Retorna la dirección del vendedor
     * @version 1.0
     */
    public String getDireccion() {
        return direccion;
    }


    /**
     * Método set de la dirección obtenida para el vendedor
     * @author Mariela Bonilla
     * @param direccion : nueva dirección para el vendedor
     * @version 1.0
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    /**
     * Método toString() de la clase Vendedor
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return  "-------- VENDEDOR: -------------------------------------------\n" +
                "\n INFORMACIÓN PERSONAL: " +
                "\n\n Tipo de usuario: " + tipoUsuario +
                "\n Tipo de identificación: " + tipoIdentificacion +
                "\n Nombre: " + nombre +
                "\n Identificación: " + identificacion +
                "\n Fecha de nacimiento: " + fechaNacimiento +
                "\n Edad: " + edad +
                "\n Correo electrónico: " + correo +
                "\n Contrasenna: " + contrasenna  + //no es correcto que se pueda imprimir la contrasenna, sin embargo,
                //                                         la agrego para comprobar que se imprime de manera
                //                                          correcta.
                "\n\n RESIDENCIA: " +
                "\n Provincia: " + provincia +
                "\n Cantón: " + canton +
                "\n Distrito: " + distrito +
                "\n Dirección exacta: " + direccion +
                "\n\n Puntuación: "+ puntuacion +
                "\n\n-------------------------------------------------------------\n\n";
    }
}
