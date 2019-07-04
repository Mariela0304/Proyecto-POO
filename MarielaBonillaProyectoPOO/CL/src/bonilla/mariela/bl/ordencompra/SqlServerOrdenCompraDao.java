package bonilla.mariela.bl.ordencompra;

import bonilla.mariela.accesobd.Conector;
import bonilla.mariela.bl.dao.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Esta clase va a obtener todos los métodos de la interface IOrdenCompraDao,
 * va a ser la responsable de tener contacto con la base de datos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerOrdenCompraDao implements IOrdenCompraDao{


    /**
     * Constructor por defecto de SqlServerOrdenCompraDao
     * @author Mariela Bonilla
     * @version 1.0
     */
    public SqlServerOrdenCompraDao() {
    }



    /**
     * Método obtenido de la interface IOrdenCompraDao
     * @author Mariela Bonilla
     * @param idColeccionista = Obtiene el valor del id del coleccionista para la orden de compra.
     * @param fechaOrden = Recibe la fecha en la que fue generada la orden.
     * @param detalles = Recibe en un tipo de dato String todos la información necesaria de los
     *                 ítems,
     * @param precioTotal = Recibe el valor que debe de pagar el coleccionista por la subasta
     * @version 1.0
     */
    @Override
    public int guardarOrdenCompra(int idColeccionista, LocalDate fechaOrden, String detalles, double precioTotal) {
        String sql;
        String sql2;
        ResultSet rs = null;
        int id_orden = 0;
        try {
            sql = "EXEC pa_registrar_orden_compra " +
                    idColeccionista + ",'" +
                    fechaOrden + "','" +
                    detalles + "'," +
                    precioTotal;
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);

            sql2 = "SELECT TOP(1) id FROM ordenCompra ORDER BY (id) DESC";

            while (rs.next()) {
                id_orden = Integer.parseInt(rs.getString("id"));
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return id_orden;
    }

    @Override
    public ArrayList<String> generarOrdenCompra(String idSubasta) {
        return null;
    }
}
