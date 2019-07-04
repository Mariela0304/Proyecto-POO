package bonilla.mariela.accesobd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Esta clase permite la conexión al motor de la base de datos, retornando la conexion de esta
 * @author Mariela Bonilla
 * @version 1.0
 */
public class Conector {

    private static  AccesoBD connectorBD = null;

    /**
     *
     * @param factory: Valor que determina el motor de base de datos que se va a utilizar
     * @return Retorna la conexion al motor de la base de datos
     * @version 1.0
     */

    public static AccesoBD getConnectorBD(int factory) throws SQLException, Exception {
        String [] datos = archivoConectorBD();
        if (connectorBD == null) {
            switch (factory) {
                case 1://SQLSERVER
                    connectorBD = new SqlServerAccesoBD("com.microsoft.sqlserver.jdbc.SQLServerDriver"
                            ,"jdbc:sqlserver://"+datos[0]+";DatabaseName="+datos[1]+";user="+datos[2]+";password="+datos[3]);
                    return connectorBD;
//                case 2://MYSQL
//                    connectorBD = new MySqlAccesoDB(""
//                            ,"", "", "");
//                    return connectorBD;
                    default:
                        connectorBD = null;
            }
        }
        return connectorBD;
    }

    /**
     * @author Mariela Bonilla
     * @version 1.0
     */
    public static void cerrarConexion() {
        connectorBD.conn = null;
        if (connectorBD.conn == null) {
            System.out.println("Se cerró la base de datos");
        }
    }


    /**
     * @author Mariela Bonilla
     * @version 1.0
     */
    public static void cerrarConexionBaseDatos() throws SQLException {
        if (connectorBD.conn!= null && !connectorBD.conn.isClosed()) {
            System.out.println("Se cerró la base de datos");
            connectorBD.conn.close();
            Conector.connectorBD.stmt.close();
        }
    }

    /**
     * @author Mariela Bonilla
     * @return Retorna los valores adquiridos del archivo de texto Conector
     * @version 1.0
     */
    public static String[] archivoConectorBD () {
         String [] datosArray = new String[5];
        try
        {
            FileReader reader = new FileReader("ConectorBD.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String datos;
            //el bufferedReader extrae cada línea, y verrifica si el resultado es nulo. Si es nulo es que ya llegó al final del texto.
            //De ahi la condición del ciclo while.
            while((datos = buffer.readLine()) != null) // Ciclo que imprime cada una de las lineas, que se almacenan en la variable datos.
            {
                String[] partes = datos.split(",");
                datosArray[0] = partes[0];
                datosArray[1] = partes[1];
                datosArray[2] = partes[2];
                datosArray[3] = partes[3];
            }
            reader.close();//se cierra el reader.
        }
        catch(IOException e)
        {
            e.printStackTrace();//se imprime la pila de llamadas para ver dónde está el error.
        }

        return datosArray;
    }
}