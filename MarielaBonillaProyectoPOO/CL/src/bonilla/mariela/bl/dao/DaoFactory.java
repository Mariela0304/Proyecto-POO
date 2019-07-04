/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.bl.dao;

import bonilla.mariela.bl.item.IItemDao;
import bonilla.mariela.bl.oferta.IOfertaDao;
import bonilla.mariela.bl.ordencompra.IOrdenCompraDao;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.usuario.IUsuarioDao;


/**
 * Clase DaoFactory que va a contener la opciones de motores de bases de datos va a permitir
 * que se conecte
 * @author Mariela Bonilla
 * @version 1.0
 */

public abstract  class DaoFactory {
    public static final int SQLSERVER = 1;

    /**
     * @author Mariela Bonilla
     * @param factory : recibe el número con el que se va a saber cuál base de datos se va a utilizar
     * @return Retorna un constructor que va permitir que se realicen las conexiones con
     * SqlServer
     */
    public static DaoFactory getDaoFactory(int factory) {
        switch (factory) {
            case SQLSERVER:
                return new SqlServerDaoFactory();
            default:
                return null;
        }
    }

    /**
     * Método abstracto que va permitir la conexión al DAO del Usuario
     * @author Mariela Bonilla
     * @version 1.0
     */

    public abstract IUsuarioDao getUsuarioDao();

    /**
     * Método abstracto que va permitir la conexión al DAO de la Subasta
     * @author Mariela Bonilla
     * @version 1.0
     */
    public abstract ISubastaDao getSubastaDao();

    /**
     * Método abstracto que va permitir la conexión al DAO del Item
     * @author Mariela Bonilla
     * @version 1.0
     */
    public abstract IItemDao getItemDao();

    /**
     * Método abstracto que va permitir la conexión al DAO de la oferta
     * @author Mariela Bonilla
     * @version 1.0
     */
   public abstract IOfertaDao getOfertaDao();

   /**
    * Método abstracto que va permitir la conexión al DAO de la orden de compra
     * @author Mariela Bonilla
     * @version 1.0
     */
   public abstract IOrdenCompraDao getOrdenCompraDao();


}
