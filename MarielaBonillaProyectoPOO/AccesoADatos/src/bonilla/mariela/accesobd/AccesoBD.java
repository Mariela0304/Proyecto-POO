package bonilla.mariela.accesobd;


import java.sql.*;

/**
 * Clase abstracta que va a obligar a sus hijos a tener los métodos del acceso
 * a la base de datos
 * @author Mariela Bonilla
 * @version 1.0
 */
public abstract class AccesoBD {
    protected Connection conn = null;
    protected Statement stmt = null;


    /**
     * @author Mariela Bonilla
     * @param driver : Driver de Microsoft SQL server, permite la conexion con la base de datos
     * @param conexion : Determina el enlace de conexión, donde vendrá el nombre de la máquina
     *                a la que se va a conectar, además del usuario y su contraseña a utilizar
     *                 para la conexión
     * @version 1.0
     */
    public AccesoBD(String driver, String conexion) throws SQLException, Exception
    {
        Class.forName(driver);
        conn = DriverManager.getConnection(conexion);
        stmt = conn.createStatement();

    }

    /**
     *  @author Mariela Bonilla
     * @param driver : Driver de Microsoft SQL server, permite la conexion con la base de datos
     * @param url : URL que termina de permitir la conexión con con sql server
     * @param user : Usuario que se utilizará para obtener la conexión
     * @param password : Contraseña del usuario utilizado para obtener la conexión
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public AccesoBD(String driver, String url, String user, String password) throws SQLException, Exception
    {
        Class.forName(driver);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();

    }

    /**
     * Método abstracto para la ejecución de los query que no tengan que devolver ningún valor
     * @author Mariela Bonilla
     * @param query
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public abstract void ejecutarSQL(String query) throws SQLException, Exception;


    /**
     * Método abstracto para las ejecuciones de los query que ocupan devolver el valor de esa consulta
     * @author Mariela Bonilla
     * @param query : Valor de consulta que la aplicación le hace a la base de datos
     * @param retorno : Valor booleano para permitir el retonrno por medio de un ResultSet
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public abstract ResultSet ejecutarSQL(String query, boolean retorno) throws SQLException, Exception;


}
