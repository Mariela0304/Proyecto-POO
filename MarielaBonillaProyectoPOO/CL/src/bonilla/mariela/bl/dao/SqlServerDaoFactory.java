/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.bl.dao;

import bonilla.mariela.bl.item.IItemDao;
import bonilla.mariela.bl.item.SqlServerItemDao;
import bonilla.mariela.bl.oferta.IOfertaDao;
import bonilla.mariela.bl.oferta.SqlServerOfertaDao;
import bonilla.mariela.bl.ordencompra.IOrdenCompraDao;
import bonilla.mariela.bl.ordencompra.SqlServerOrdenCompraDao;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.SqlServerSubastaDao;
import bonilla.mariela.bl.usuario.IUsuarioDao;
import bonilla.mariela.bl.usuario.SqlServerUsuarioDao;

public class SqlServerDaoFactory extends DaoFactory {

    /**
     * @author Mariela Bonilla
     * @version 1.0
     */

    public SqlServerDaoFactory() {
    }


    /**
     * @author Mariela Bonilla
     * @return Retorna el contructor de SqlServerUsuarioDao
     * @version 1.0
     */
    public IUsuarioDao getUsuarioDao() {
        return new SqlServerUsuarioDao();
    }

    /**
     * @author Mariela Bonilla
     * @return Retorna el contructor de SqlServerSubastaDao
     * @version 1.0
     */
    public ISubastaDao getSubastaDao() {
        return new SqlServerSubastaDao();
    }


    /**
     * @author Mariela Bonilla
     * @return Retorna el contructor de SqlServerItemDao
     * @version 1.0
     */
    public IItemDao getItemDao() {
        return new SqlServerItemDao();
    }

    /**
     * @author Mariela Bonilla
     * @return Retorna el contructor de SqlServerOfertaDao
     * @version 1.0
     */
    public IOfertaDao getOfertaDao() {
        return new SqlServerOfertaDao();
    }

    /**
     * @author Mariela Bonilla
     * @return Retorna el contructor de SqlServerOrdenCompraDao
     * @version 1.0
     */
    public IOrdenCompraDao getOrdenCompraDao() {
        return new SqlServerOrdenCompraDao();
    }
}
