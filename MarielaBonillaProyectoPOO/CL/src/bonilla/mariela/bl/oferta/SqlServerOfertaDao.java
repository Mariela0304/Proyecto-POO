package bonilla.mariela.bl.oferta;

import bonilla.mariela.accesobd.Conector;
import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Esta clase va a heredar todos los métodos del IOfertaDao, siendo este el que ejecuta todos los
 * query y el que tiene acceso a la base de datos.
 * @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerOfertaDao implements IOfertaDao{

    /**
     * Constructor por defecto de SqlServerOfertaDao
     * @author Mariela Bonilla
     * @version 1.0
     */
    public SqlServerOfertaDao()  {
    }


    /**
     * Método obtenido de la interface IOfertaDao, va a permitir el registro de una oferta
     * @author Mariela Bonilla
     * @param idSubasta = Obtiene el valor del id de la subasta
     * @param oferente = Recibe la identificacion del que hace la oferta
     * @param oferta = Recibe el valor que se ofertó para poder registrar la oferta
     * @version 1.0
     */
    public void registrarOferta(int idSubasta, String oferente, double oferta)  throws SQLException, Exception {
        String sql;
        try {
            sql = "EXEC pa_registrar_oferta " +
                    idSubasta + ",'" +
                    oferente + "'," +
                    oferta;
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    /**
     * Método obtenido de la interface IOfertaDao, valida que el coleccionista actual haya
     * ofertado o no
     * @author Mariela Bonilla
     * @param oferente = Recibe la identificacion del usuario que se encuentra en la sesión para validar
     *                 si ese usuario ya realizó la oferta en una subasta específica
     * @param id_subasta = Recibe el identificador de la subasta actual
     * @version 1.0
     */
    public boolean validarOfertaColeccionista(String oferente, int id_subasta)  throws SQLException, Exception {
        String sql;
        ResultSet rs = null;
        boolean ofertado = false;
        try {
            sql = "SELECT Count(id_oferente) ha_ofertado FROM vw_lista_ofertas_subasta WHERE id_oferente ='"+oferente+"' AND " +
                    "idSubasta = "+ id_subasta;

            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
                    while (rs.next()) {
                        if (rs.getString("ha_ofertado").equals("1")) {
                            ofertado = true;
                        }
                    }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return ofertado;
    }





    /**
     * Método obtenido de IOfertaDao, que va a listar la ofertas de una subasta en específico.
     * @author Mariela Bonilla
     * @param id = Recibe el identificador de la subasta actual para poder realizar la lista de
     *           todas sus ofertas.
     * @version 1.0
     */
    @Override
    public ArrayList<Oferta> listarOfertasSubasta(int id) throws SQLException, Exception {
        ArrayList<Oferta> listaOfertas = new ArrayList<>();
        ResultSet rs = null;
        String sql;

        Item tmpItem;
        sql = "SELECT idOferta, idSubasta, precio, id_oferente, nombre FROM vw_lista_ofertas_subasta where idSubasta = "
                + id;

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {


                listaOfertas.add(new Oferta(Integer.parseInt(rs.getString("idOferta")),
                        rs.getString("nombre"), rs.getString("id_oferente"),
                        Double.parseDouble(rs.getString("precio"))));

            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();
        }
        return listaOfertas;
    }


    /**
     * Método obtenido de IOfertaDao que va a obtener la mejor oferta que obtuvo la subasta
     * @author Mariela Bonilla
     * @param id_subasta = Recibe el identificador de la subasta actual para poder obtener los datos
     *                   de la mejor oferta durante el tiempo de la subasta.
     * @version 1.0
     */    public String[] obtenerMejorOferta(int id_subasta) throws SQLException, Exception{

        String sql;
        String[] mejor_oferta = new String[4];
        ResultSet rs = null;

        sql = "SELECT id, mejor_precio, id_oferente, id_subasta FROM dbo.fn_obtener_ganador_subasta " +
                "(" + id_subasta+")";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                mejor_oferta[0]= rs.getString("id");
                mejor_oferta[1]= rs.getString("mejor_precio");
                mejor_oferta[2]= rs.getString("id_oferente");
                mejor_oferta[3]= rs.getString("id_subasta");
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return mejor_oferta;
    }



}
