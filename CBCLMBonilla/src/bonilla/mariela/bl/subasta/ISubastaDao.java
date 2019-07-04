package bonilla.mariela.bl.subasta;

import bonilla.mariela.bl.item.Item;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Esta interface va a contener todos lo métodos en cuanto a subastas se refiera,
 *  * para los procedimientos y consultas que deban hacerse en el transcurso de ejecución de
 *  * la aplicación, los cuáles van a heredar a quién implemente esta interface
 * @author Mariela Bonilla
 * @version 1.0
 */
public interface ISubastaDao {

    /**
     * Este método va a permitir registrar las subastas de un vendedor
     * @author Mariela Bonilla
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param precioMinimo : Precio mínimo
     * @param items : Lista de ítems subastados
     * @param cantidad_items : Cantidad de ítems subastados
     * @param identificacion : Identificacion de quien subasta
     * @return Retorna valor booleando dependiendo del éxito del registro
     * @version 1.0
     */
    public boolean registrarSubasta(LocalDateTime fechaVencimiento, double precioMinimo, ArrayList<Item> items,
                                    int cantidad_items,
                                    String identificacion) throws
            SQLException, Exception;


    /**
     * Este método va a permitir registrar las subastas de un coleccionista
     * @author Mariela Bonilla
     * @param fechaVencimiento : Fecha de vencimiento de la subasta
     * @param precioMinimo : Precio mínimo
     * @param items : Lista de ítems subastados
     * @param cantidad_items : Cantidad de ítems subastados
     * @param identificacion : Identificacion de quien subasta
     * @param idColeccionista : Identificador del coleccionista
     * @return Retorna valor booleando dependiendo del éxito del registro
     * @version 1.0
     */
    public boolean registrarSubasta(LocalDateTime fechaVencimiento, double precioMinimo, ArrayList<Item> items,
                                    int cantidad_items,
                                    String identificacion, int idColeccionista) throws
            SQLException, Exception;




    /**
     * Métodos que valida la cantidad de los ítems
     * @author Mariela Bonilla
     * @param cantidad : Cantidad de los ítems
     * @return Retorna valor booleando dependiendo del éxito del proceso
     * @version 1.0
     */
    public boolean validarCantidadItems(int cantidad) throws  SQLException, Exception;

    /**
     * Método que va a retornar la lista de todas las subastas
     * @author Mariela Bonilla
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    public TreeMap<String, Subasta> listarSubastas() throws  SQLException, Exception;

    /**
     * Método que va a retornar la lista de todas las subastas de un usuario
     * @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    public ArrayList<Subasta> listarSubastasUsuario(String pidentificacion) throws SQLException, Exception;



    /**
     * Método que va a retornar la lista de todas las subastas en las que participa un usuario
     * @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    public TreeMap<String, Subasta> listarSubastasUsuarioParticipadas(String pidentificacion) throws SQLException, Exception;

    /**
     * Método que va a retornar un número entero de la cantidad de subastas a las que ha
     * participado
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas participadas
     * @version 1.0
     */
    public int obtenerCantidadSubastasParticipadas(String identificacion) throws  SQLException, Exception;

    /**
     * Método que va a retornar la lista de los ids de las subastas de un usuario
     *  @author Mariela Bonilla
     * @param identificacionColeccionista : Identificación del usuario que desea ver sus subastas
     * @return Retorna número entero
     * @version 1.0
     */
    public String[] obtenerIdsSubastas(String identificacionColeccionista) throws  SQLException, Exception;

    /**
     * Método que va a permitir vencer una subasta
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta a vencer
     * @version 1.0
     */
    public void vencerSubasta(int idSubasta) throws  SQLException, Exception;

    /**
     * Método que va a permitir validar si un usuario es ganador
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta
     * @param identificacionColeccionista : Identificación del usuario que desea validar si es
     *                                    o no ganador
     * @return Retorna número entero
     * @version 1.0
     */
    public boolean validarEsGanador(int idSubasta, String identificacionColeccionista) throws  SQLException, Exception;


    /**
     * Método que va a permitir validar si un usuario es vendedor
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta
     * @param identificacionColeccionista : Identificación del usuario
     * @return Retorna un booleano
     * @version 1.0
     */
    public boolean validarVendedor(int idSubasta, String identificacionColeccionista) throws  SQLException, Exception;


    /**
     * Método que va a retornar una lista con los datos del usaurio ganador de la subasta
     *  @author Mariela Bonilla
     * @param identificacionColeccionista : Identificación del usuario
     * @return Retorna lista de datos de usuario
     * @version 1.0
     */
    public String[] obtenerGanador(String identificacionColeccionista) throws  SQLException, Exception;


    /**
     * Método que va a devolver las adjudicaciones del coleccionista
     *  @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario coleccionista
     * @return Retorna lista de todas las adjudicaciones
     * @version 1.0
     */
    public ArrayList<Subasta> listarAdjudicacionesSubastas(String pidentificacion) throws SQLException, Exception;


    /**
     * Método que va a devolver las subastas vigentes
     *  @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario coleccionista
     * @return Retorna lista de subastas vigentes
     * @version 1.0
     */
    public ArrayList<Subasta> listarSubastasVigentes(String pidentificacion) throws SQLException, Exception;


    /**
     * Método que va a devolver las subastas disponibles para ese usuario
     *  @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario coleccionista
     * @return Retorna lista de subastas dispobles
     * @version 1.0
     */
    public ArrayList<Subasta> listarSubastasDisponibles(String pidentificacion) throws SQLException, Exception;

}