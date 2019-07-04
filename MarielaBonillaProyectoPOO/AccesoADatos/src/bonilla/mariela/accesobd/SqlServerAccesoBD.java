package bonilla.mariela.accesobd;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Esta clase obtiene el driver de SQLserver y la conexión, además que permite las ejecuciones
 * de las consultas de sql realizadas en el momento que la aplicación lo ocupe
 * @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerAccesoBD extends AccesoBD{


    /**
     * @author Mariela Bonilla
     * @param driver : Driver de Microsoft SQL server, permite la conexion con la base de datos
     * @param conexion : Determina a cual host se va a conectar, además de cual usuario y su contraseña
     * @version 1.0
     */
    public SqlServerAccesoBD(String driver, String conexion) throws SQLException,Exception {
        super(driver, conexion);
    }

    /**
     *  @author Mariela Bonilla
     * @param driver : Driver de Microsoft SQL server, permite la conexion con la base de datos
     * @param url : URL que determina el enlace con sql server
     * @param user : Usuario que se utilizará para obtener la conexión
     * @param password : Contraseña del usuario utilizado para obtener la conexión
     * @version 1.0
     */
    public SqlServerAccesoBD(String driver, String url, String user, String password) throws SQLException,Exception {
        super(driver, url,user, password);
    }

    /**
     * @author Mariela Bonilla
     * @param query : Valor de consulta que la aplicación le hace a la base de datos
     * @version 1.0
     */
    @Override
    public void ejecutarSQL(String query) throws SQLException,Exception{
        stmt.execute(query);
    }

    /**
     * @author Mariela Bonilla
     * @param query : Valor de consulta que la aplicación le hace a la base de datos
     * @param retorno : Valor que va a retorna al realizar una consulta por medio de un ResultSet
     * @version 1.0
     */
    @Override
    public ResultSet ejecutarSQL (String query, boolean retorno)throws SQLException,Exception {
        ResultSet rs;

        rs = stmt.executeQuery(query);
        return rs;
    }

}
