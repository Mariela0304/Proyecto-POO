package bonilla.mariela.bl.item;

import bonilla.mariela.accesobd.Conector;
import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.IUsuarioDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


/**
 * Esta clase va a obtener los métodos que contiene la interface IItemDao, además de que es la que va
 * conectar con la bases de datos para realizar la consultas y procedimientos con SqlServer
 *  @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerItemDao implements IItemDao {


    /**
     * Este método va a permitir que un usuario coleccionista pueda agregar ítems a su colección
     * @author Mariela Bonila
     * @param nombre : Nombre del ítem a registrar
     * @param descripcion : descripción del ítem a registrar
     * @param estado : Estado del +item a registrar
     * @param fechaCompra : La fecha en el que el usuario compro el ítem
     * @param identificacion : identifiacion de coleccionista que está realizando el registro del ítem
     * @return : Valor booleano que para verificar si se realizó el registro correctamente o no
     * @version 1.0
     */
    @Override
    public boolean registrarItem(String nombre, String descripcion, String estado, LocalDate fechaCompra, String identificacion)
            throws SQLException, Exception {
        String sql;
        boolean correcto = false;
        try {
            sql = "EXEC pa_registrar_item_coleccionista '" +
                    nombre + "','" +
                    descripcion + "','" +
                    estado + "','" +
                    fechaCompra + "','" +
                    identificacion + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            correcto = true;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            correcto = false;

        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;

        }
        return correcto;
    }


    /**
     * Método que va retorna un ArrayList de los ítems de un coleccionista en específico.
     *  @author Mariela Bonilla
     * @param pIdentificacion : Identificación del coleccionista que quiere obtener sus ítems
     * @return : Retorna el ArrayList con los ítems del coleccionista
     * @version 1.0
     */
    @Override
    public ArrayList<Item> listarItemsColeccionista(String pIdentificacion) {
        ArrayList<Item> listaItems = new ArrayList<>();
        ResultSet rs = null;
        String sql;

        Item tmpItem;
        sql = "SELECT nombre, descripcion, estado, fechaCompra FROM vw_items_coleccionista where identificacionColeccionista = '"
                + pIdentificacion + "' AND estadistica = 'Inclucído'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                int anno = calcularAntiguedadAnnos(LocalDate.parse(rs.getString("fechaCompra")));
                int mes =calcularAntiguedadMeses(LocalDate.parse(rs.getString("fechaCompra")));
                int dia = calcularAntiguedadDias(LocalDate.parse(rs.getString("fechaCompra")));

               listaItems.add(new Item(rs.getString("nombre"), rs.getString("descripcion"),
                       rs.getString("estado"), LocalDate.parse(rs.getString("fechaCompra")),
                       anno, mes, dia));

            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();
        }
        return listaItems;
    }

    /**
     * Método que va retorna un ArrayList de los ítems de una subasta en específico.
     *  @author Mariela Bonilla
     * @param idSubasta : Identificador de la subasta, de la que se quiere obtener los ítems
     * @return : Retorna el ArrayList con los ítems de la subasta
     * @version 1.0
     */
    @Override
    public ArrayList<Item> listarItemsSubasta(int idSubasta) throws SQLException, Exception {
        ArrayList<Item> listaItems = new ArrayList<>();
        String sql2;

        ResultSet rsItem = null;

        Conector.cerrarConexion();
        Item tmpItem;

        try {
            sql2 = "SELECT nombre, descripcion, estado, fechaCompra FROM items_subastados where idSubasta ="+ idSubasta;

            rsItem = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql2, false);

            while (rsItem.next()) {
                int anno = calcularAntiguedadAnnos(LocalDate.parse(rsItem.getString("fechaCompra")));
                int mes =calcularAntiguedadMeses(LocalDate.parse(rsItem.getString("fechaCompra")));
                int dia = calcularAntiguedadDias(LocalDate.parse(rsItem.getString("fechaCompra")));

                listaItems.add(new Item(rsItem.getString("nombre"), rsItem.getString("descripcion"),
                        rsItem.getString("estado"), LocalDate.parse(rsItem.getString("fechaCompra")),
                        anno, mes, dia));

            }

        }catch (SQLException event) {
            System.out.println(event.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaItems;
    }

    /**
     * Método que calcula la antigüedad de los días entre la fecha de la compra y la fecha
     * actual
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @return : retorna la cantidad de días en el mes que han pasado desde que se compró
     * el ítem
     * @version 1.0
     */
    public int calcularAntiguedadDias(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period dia = Period.between(fechaCompra, fechaActual);
        return dia.getDays();
    }

    /**
     * Método que calcula la antigüedad de los meses entre la fecha de la compra y la fecha
     * actual
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @return : retorna la cantidad de meses en el año que han pasado desde que se compró
     * el ítem
     * @version 1.0
     */
    public int calcularAntiguedadMeses(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period mes = Period.between(fechaCompra, fechaActual);
        return mes.getMonths();
    }

    /**
     * Método que calcula la antigüedad de los años entre la fecha de la compra y la fecha
     * actual
     * @author Mariela Bonilla
     * @param fechaCompra : Fecha del día en el que se compró el ítem
     * @return : retorna la cantidad de años que han pasado desde que se compró
     * el ítem
     * @version 1.0
     */
    public int calcularAntiguedadAnnos(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period anno = Period.between(fechaCompra, fechaActual);
        return anno.getYears();
    }


    /**
     * Método que realiza la modificación de los ítems del coleccionista que realiza
     * una subasta, ya que cada vez que un coleccionista subasta uno de sus ítems, éste
     * pierde el derecho de propiedad de ese ítem, por ende, no debe estar en su lista de ítems.
     * @author Mariela Bonilla
     * @param identificacion : Identificación del coleccionista que realiza la subasta y
     *                       debe de eliminarse el ítem de su lista.
     * @version 1.0
     */
    public void eliminarItemsColeccionita(String identificacion) {
        String sql;


        try{
            sql= "UPDATE items_coleccionista SET estadistica = 'Eliminado' WHERE identificacionColeccionista = '"+identificacion+"'";
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
