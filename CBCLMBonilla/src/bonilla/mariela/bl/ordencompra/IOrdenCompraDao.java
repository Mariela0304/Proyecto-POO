package bonilla.mariela.bl.ordencompra;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Esta interface contiene todos los métodos que debe contener SqlServerOrdenCompraDao,
 * es la que va conectar los controladores con la capa lógica
 * @author Mariela Bonilla
 */
public interface IOrdenCompraDao {

    /**
     * Método abstracto que va a ser contenido en SqlServerOrdenCompraDao, y va a guardar
     * la orden de compra
     * @author Mariela Bonilla
     * @param idColeccionista = Obtiene el valor del id del coleccionista para la orden de compra.
     * @param fechaOrden = Recibe la fecha en la que fue generada la orden.
     * @param detalles = Recibe en un tipo de dato String todos la información necesaria de los
     *                 ítems,
     * @param precioTotal = Recibe el valor que debe de pagar el coleccionista por la subasta
     * @version 1.0
     */
    public int guardarOrdenCompra(int idColeccionista, LocalDate fechaOrden, String detalles, double precioTotal);



    public ArrayList<String> generarOrdenCompra(String idSubasta);


}
