package bonilla.mariela.bl.usuario;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta clase representa al objeto Coleccionista, adquiriendo todos sus atributos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Coleccionista extends Usuario{
    private double puntuacion;
    private String provincia;
    private String canton;
    private String distrito;
    private String direccion;
    private ArrayList<String> intereses;
    private ArrayList<String> items;

    /**
     * Constructor por defecto de Coleccionista
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Coleccionista() {
        super();
    }

    /**
     * Constructor de Coleccionista con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de coleccionitsa
     * @param tipoUsuario : Tipo de ususario
     * @param nombre : Nombre de coleccionista
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del coleccionista
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de coleccionista
     * @param correo : Correo de coleccionista
     * @param provincia : Provincia de coleccionista
     * @param canton : Cantón de coleccionista
     * @param distrito : Distrito de coleccionista
     * @param direccion : Dirección de coleccionista
     * @param puntuacion : Puntuación de coleccionista
     * @version 1.0
     */
    public Coleccionista(String estado, String tipoUsuario, String nombre,String tipoIdentificacion, String identificacion,
                         LocalDate fechaNacimiento, int edad, String correo,  String provincia, String canton, String distrito, String direccion, double puntuacion) {
        super(estado,tipoUsuario, nombre, tipoIdentificacion, identificacion, fechaNacimiento, edad, correo);
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
    }

    /**
     * Constructor de Coleccionista con parámetros
     * @author Mariela Bonilla
     * @param estado : Estado de coleccionitsa
     * @param tipoUsuario : Tipo de ususario
     * @param nombre : Nombre de coleccionista
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación del coleccionista
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad de coleccionista
     * @param correo : Correo de coleccionista
     * @param provincia : Provincia de coleccionista
     * @param canton : Cantón de coleccionista
     * @param distrito : Distrito de coleccionista
     * @param direccion : Dirección de coleccionista
     * @param puntuacion : Puntuación de coleccionista
     * @param intereses : Lista de interéses del coleccionista
     * @param items : Lista de items de usuarios
     * @version 1.0
     */
    public Coleccionista(String estado,String tipoUsuario, String nombre,String tipoIdentificacion, String identificacion,
                         LocalDate fechaNacimiento,int edad, String correo, String contrasenna,
                         String provincia, String canton, String distrito,
                         String direccion, double puntuacion, ArrayList<String> intereses, ArrayList<String> items) {
        super(estado,tipoUsuario,nombre, tipoIdentificacion,identificacion, fechaNacimiento, edad, correo, contrasenna);
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.direccion = direccion;
        this.puntuacion = puntuacion;
        this.intereses = intereses;
        this.items = items;
    }

    /**
     * Método get de la puntuación del coleccionista
     * @author Mariela Bonilla
     * @return Retorna la puntuación para el coleccionista
     * @version 1.0
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * Método set de la puntuacion obtenida para el coleccionista
     * @author Mariela Bonilla
     * @param puntuacion : nueva puntuacion para el coleccionista
     * @version 1.0
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Método get de la provincia del coleccionista
     * @author Mariela Bonilla
     * @return Retorna la provincia del coleccionista
     * @version 1.0
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Método set de la provincia obtenida para el coleccionista
     * @author Mariela Bonilla
     * @param provincia : nueva provincia para el coleccionista
     * @version 1.0
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Método get del cantón del coleccionista
     * @author Mariela Bonilla
     * @return Retorna el cantón del coleccionista
     * @version 1.0
     */
    public String getCanton() {
        return canton;
    }

    /**
     * Método set del cantón obtenido para el coleccionista
     * @author Mariela Bonilla
     * @param canton : nuevo cantón para el coleccionista
     * @version 1.0
     */
    public void setCanton(String canton) {
        this.canton = canton;
    }

    /**
     * Método get del distrito del coleccionista
     * @author Mariela Bonilla
     * @return Retorna el distrito del coleccionista
     * @version 1.0
     */
    public String getDistrito() {
        return distrito;
    }

    /**
     * Método set del distrito obtenido para el coleccionista
     * @author Mariela Bonilla
     * @param distrito : nuevo distrito para el coleccionista
     * @version 1.0
     */
    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Método get de la dirección del coleccionista
     * @author Mariela Bonilla
     * @return Retorna la dirección del coleccionista
     * @version 1.0
     */
    public String getDireccion() {
        return direccion;
    }


    /**
     * Método set de la dirección obtenida para el coleccionista
     * @author Mariela Bonilla
     * @param direccion : nueva dirección para el coleccionista
     * @version 1.0
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Método get de los interéses del coleccionista
     * @author Mariela Bonilla
     * @return Retorna los interéses del coleccionista
     * @version 1.0
     */
    public ArrayList<String> getIntereses() {
        return intereses;
    }

    /**
     * Método set de los interéses obtenidos para el coleccionista
     * @author Mariela Bonilla
     * @param intereses : nuevos interéses para el coleccionista
     * @version 1.0
     */
    public void setIntereses(ArrayList intereses) {
        this.intereses = intereses;
    }

    /**
     * Método get de los ítems del coleccionista
     * @author Mariela Bonilla
     * @return Retorna los ítems del coleccionista
     * @version 1.0
     */
    public ArrayList<String> getItems() {
        return items;
    }


    /**
     * Método set de los ítems obtenidos para el coleccionista
     * @author Mariela Bonilla
     * @param items : nuevos ítems para el coleccionista
     * @version 1.0
     */
    public void setItems(ArrayList<String> items) {
        this.items = items;
    }


    /**
     * Método toString() de la clase Coleccionista
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "-------- COLECCIONISTA: -------------------------------------------\n" +
                "\n INFORMACIÓN PERSONAL: " +
                "\n\n Tipo de usuario: " + tipoUsuario +
                "\n Tipo de identificación: " + tipoIdentificacion +
                "\n\n Nombre: " + nombre +
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
                "\n Interéses: " + intereses +
                "\n Items: " + items +
                "\n\n------------------------------------------------------------\n\n";
    }
}
