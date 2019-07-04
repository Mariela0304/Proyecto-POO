package bonilla.mariela.bl.oferta;

/**
 * Esta clase va a representar al objeto Oferta, además de contener todos los atributos
 * de una oferta
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Oferta {

    private int idOferta;
    private double precio;
    private String nomOferente;
    private String identificacionOferente;

    /**
     * Contructor por defecto del objeto oferta
     * @author Mariela Bonilla
     * @version 1.0
     */
    public Oferta() {

    }

    /**
     * Constructor de la clase Oferta que recibe parámetros
     * @author Mariela Bonilla
     * @param idOferta : Identificador de la oferta
     * @param nomOferente : Nombre del usuario que hizo la oferta
     * @param identificacionOferente : Identificación del usuario que hizo la oferta
     * @param precio : Precio ofrecido en la oferta
     * @version 1.0
     */
    public Oferta(int idOferta, String nomOferente, String identificacionOferente, double precio) {
        this.idOferta = idOferta;
        this.nomOferente = nomOferente;
        this.identificacionOferente = identificacionOferente;
        this.precio = precio;
    }

    /**
     * Método get del id de la oferta
     * @author Mariela Bonilla
     * @return Retorna el id de la oferta
     * @version 1.0
     */
    public int getIdOferta() {
        return idOferta;
    }

    /**
     * Método set del id de la oferta, dónde se va a insertar un nuevo valor al id
     * @author Mariela Bonilla
     * @param idOferta : nuevo id para la oferta
     * @version 1.0
     */
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }


    /**
     * Método get del nombre del oferente de la oferta
     * @author Mariela Bonilla
     * @return Retorna el nombre del oferente de la oferta
     * @version 1.0
     */
    public String getNomOferente() {
        return nomOferente;
    }

    /**
     * Método set del nombre del oferente de la oferta, dónde se va a insertar un nuevo valor al
     * nombre de oferente de la oferta
     * @author Mariela Bonilla
     * @param nomOferente : nuevo nombre del oferente para la oferta
     * @version 1.0
     */
    public void setNomOferente(String nomOferente) {
        this.nomOferente = nomOferente;
    }


    /**
     * Método get de la identificación del oferente de la oferta
     * @author Mariela Bonilla
     * @return Retorna la identificación del oferente de la oferta
     * @version 1.0
     */
    public String getIdentificacionOferente() {
        return identificacionOferente;
    }

    /**
     * Método set de la identificación del oferente de la oferta, dónde se va a
     * insertar un nuevo valor a la identificación del oferente de la oferta
     * @author Mariela Bonilla
     * @param identificacionOferente : nueva identificación del oferente para la oferta
     * @version 1.0
     */
    public void setIdentificacionOferente(String identificacionOferente) {
        this.identificacionOferente = identificacionOferente;
    }

    /**
     * Método get del precio ofrecido a la oferta
     * @author Mariela Bonilla
     * @return Retorna el precio ofrecido a la oferta
     * @version 1.0
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método set del precio ofrecido por oferente de la oferta, dónde se va a
     * insertar un nuevo valor al precio ofrecido por el oferente de la oferta
     * @author Mariela Bonilla
     * @param precio : nuevo precio por oferente para la oferta
     * @version 1.0
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método toString() de la clase Oferta
     * @author Mariela Bonilla
     * @return
     * @version 1.0
     */
    @Override
    public String toString() {
        return "------ OFERTA: -------------------------\n" +
                "\n IdOferta: " + idOferta +
                "\n Nombre de oferente: " + nomOferente +
                "\n Identificacion de oferente: " + identificacionOferente +
                "\n Precio a ofrecer: " + precio +
                "\n\n----------------------------------------\n\n";
    }
}
