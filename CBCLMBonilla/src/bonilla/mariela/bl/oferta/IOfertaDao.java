package bonilla.mariela.bl.oferta;

import bonilla.mariela.bl.item.Item;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Esta interface corresponde al Dao de las Ofertas, donde va a obligar a quien lo implemente a
 * llevar los métodos, que esta contiene
 * @author Mariela Bonilla
 * @version 1.0
 */
public interface IOfertaDao {

    /**
     * Método abstracto que permite registrar una oferta, que va a ser heredado en la clase
     * SqlServerOfertaDao
     * @author Mariela Bonilla
     * @param id = Obtiene el valor del id de la subasta
     * @param oferente = Recibe la identificacion del que hace la oferta
     * @param oferta = Recibe el valor que se ofertó para poder registrar la oferta
     * @version 1.0
     */
    public void registrarOferta(int id, String oferente, double oferta)  throws SQLException, Exception;



    /**
     * Método abstracto, que permite la validación de sí el coleccionista ya realizó la
     * oferta
     * @author Mariela Bonilla
     * @param oferente = Recibe la identificacion del usuario que se encuentra en la sesión para validar
     *                 si ese usuario ya realizó la oferta en una subasta específica
     * @param id_subasta = Recibe el identificador de la subasta actual
     * @version 1.0
     */
    public boolean validarOfertaColeccionista(String oferente, int id_subasta)  throws SQLException, Exception;

    /**
     * Método abstracto
     * @author Mariela Bonilla
     * @param id = Recibe el identificador de la subasta actual para poder realizar la lista de
     *           todas sus ofertas
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public ArrayList<Oferta> listarOfertasSubasta(int id) throws SQLException, Exception;


    /**
     * Método abstacto
     * @author Mariela Bonilla
     * @param id_subasta = Recibe el identificador de la subasta actual para poder obtener los datos
     *                   de la mejor oferta durante el tiempo de la subasta
     * @version 1.0
     */
    public String[] obtenerMejorOferta(int id_subasta) throws SQLException, Exception;
}
