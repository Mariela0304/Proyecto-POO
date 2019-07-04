package bonilla.mariela.bl.item;

import bonilla.mariela.bl.dao.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Esta interface va a obligar a la clase SqlServerItemDao a contener todos estos métodos,
 * para permitir que se pueda realizar las consultas con las bases de datos
 * @author Mariela Bonilla
 * @version 1.0
 */
public interface IItemDao  {

    /**
     * Método de interface de registrar ítem por un coleccionista en específico
     * @author Mariela Bonila
     * @param nombre : Nombre del ítem a registrar
     * @param descripcion : descripción del ítem a registrar
     * @param estado : Estado del +item a registrar
     * @param fechaCompra : La fecha en el que el usuario compro el ítem
     * @param identificacion : identifiacion de coleccionista que está realizando el registro del ítem
     * @version 1.0
     */
    public boolean registrarItem(String nombre, String descripcion, String estado, LocalDate fechaCompra, String identificacion)
            throws SQLException, Exception;


    /**
     * Método de interface que va a heredar para poder listar los ítems de un coleccionista en
     * específico.
     *  @author Mariela Bonilla
     * @param identificacion : Identificación del coleccionista que quiere obtener sus ítems
     * @version 1.0
     */
    public ArrayList<Item> listarItemsColeccionista (String identificacion);

    /**
     * Método de interface que va a heredar para poder listar los ítems de una subasta en
     *  específico.
     *  @author Mariela Bonilla
     * @param idSubasta : Identificador de la subasta, de la que se quiere obtener los ítems
     * @version 1.0
     */
    public ArrayList<Item> listarItemsSubasta(int idSubasta) throws SQLException, Exception;

    /**
     * Método de interface que permite el cálculo de la antigüedad de los días entre
     * la fecha de la compra y la fecha actual
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @version 1.0
     */
    public int calcularAntiguedadDias(LocalDate fechaCompra);


    /**
     * Método de interface que va a permitir el cálculo de la antigüedad de los meses
     * entre la fecha de la compra y la fecha
     * actual
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @version 1.0
     */
    public int calcularAntiguedadMeses(LocalDate fechaCompra);


    /**
     * Método de interface que va a permitir el cálculo de la antigüedad
     * de los años entre la fecha de la compra y la fecha actual.
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @version 1.0
     */
    public int calcularAntiguedadAnnos(LocalDate fechaCompra);


    /**
     * Método e interface que va a permitir la modificación de los ítems del coleccionista
     * que realiza una subasta, ya que cada vez que un coleccionista subasta uno de sus ítems,
     * éste pierde el derecho de propiedad de ese ítem, por ende, no debe estar en su lista
     * de ítems.
     * @author Mariela Bonilla
     * @param identificacion : Identificación del coleccionista que realiza la subasta y
     *                       debe de eliminarse el ítem de su lista.
     * @version 1.0
     */
    public void eliminarItemsColeccionita(String identificacion);





}
