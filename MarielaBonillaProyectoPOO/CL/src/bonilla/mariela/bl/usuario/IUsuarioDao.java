package bonilla.mariela.bl.usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Esta interface va a contener todos lo métodos en cuanto a usuarios se refiera,
 * para los procedimientos y consultas que deban hacerse en el transcurso de ejecución de
 * la aplicación, los cuáles van a heredar a quién implemente esta interface
 * @author Mariela Bonilla
 * @version 1.0
 */
public interface IUsuarioDao {

    /**
     * Este método va a permitir poder registrar a un usuario moderador
     * @param nombre             : Nombre del moderador
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion     : Identificación
     * @param fechaNacimiento    : Fecha de nacimiento
     * @param edad               : Edad
     * @param correo             : El correo electrónico
     * @param contrasenna:       La contraseña del moderador
     * @return Retorna valor booleando dependiendo del éxito del registro
     * @throws SQLException
     * @throws Exception
     * @author Mariela Bonila
     * @version 1.0
     */
    public boolean registrarModerador(String nombre, String tipoIdentificacion,
                                      String identificacion,
                                      LocalDate fechaNacimiento, int edad, String correo,
                                      String contrasenna) throws SQLException, Exception;


    /**
     * Método de la interface que va a permitir el registro de un usuario vendedor
     * @author Mariela Bonilla
     * @param nombre : Nombre del vendedor
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad
     * @param correo : Correo electrónico
     * @param contrasenna : Contraseña del usuario
     * @param provincia : Provincia
     * @param canton : Cantón
     * @param distrito : Distrito
     * @param direccion : Dirección exacta de su hogar
     * @return  Retorna valor booleando dependiendo del éxito del registro
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean registrarVendedor( String nombre, String tipoIdentificacion,
                                     String identificacion,
                                     LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna, String provincia, String canton, String distrito,
                                     String direccion) throws SQLException, Exception;

    /**
     * Método de la interface que va a permitir el registro de un usuario coleccionista
     * @author Mariela Bonilla
     * @param nombre : Nombre del caoleccionista
     * @param tipoIdentificacion : Tipo de identificación
     * @param identificacion : Identificación
     * @param fechaNacimiento : Fecha de nacimiento
     * @param edad : Edad
     * @param correo : Correo electrónico
     * @param contrasenna : Contraseña del usuario
     * @param provincia : Provincia
     * @param canton : Cantón
     * @param distrito : Distrito
     * @param direccion : Dirección exacta de su hogar
     * @param intereses : Lista de sus intereses
     * @return Retorna valor booleando dependiendo del éxito del registro
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean registrarColeccionista(String nombre, String tipoIdentificacion,
                                          String identificacion,
                                          LocalDate fechaNacimiento, int edad, String correo,
                                          String contrasenna, String provincia, String canton, String distrito,
                                          String direccion, ArrayList<String> intereses) throws SQLException, Exception;

    /**
     * Método que valida que exista un moderador
     * @author Mariela
     * @return Retorna un valor booleano para determinar si existe o no un moderador
     * en el sistema
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean validarExistenciaModerador() throws SQLException, Exception;


    /**
     * Método que permite validar las credenciales que el usuario al iniciar sesión, ingresa
     * @author Mariela Bonilla
     * @param pcorreo : Correo de usuario a validar
     * @param pcontrasenna : Contraseña de usuario a validar
     * @return Valor booleano que va a comprobar si los datos son o no correctos
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public String validarCredenciales(String pcorreo, String pcontrasenna) throws SQLException, Exception;

    /**
     * Método que permite validar que la identificación no se repita
     * @author Mariela Bonilla
     * @param pidentificacion : Identificacion a validar
     * @return Valor booleano que va a comprobar si la identificación está o no repetida
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean validarIdentificacion(String pidentificacion)throws SQLException, Exception;


    /**
     * Método que permite validar que el correo no se repita
     * @author Mariela Bonilla
     * @param pcorreo : Correo a validar
     * @return Valor booleano que va a comprobar si el correo está o no repetido
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean validarCorreo(String pcorreo) throws SQLException, Exception;


    /**
     * Método que obtiene el tipo de usuario de quien desea iniciar sesión
     * @author Mariela Bonilla
     * @param correo : Correo que servirá para buscar el tipo de usuario
     * @return Valor booleano que va a comprobar si el correo está o no repetido
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public String obtenerTipoUsuario(String correo) throws SQLException, Exception;

    /**
     * Método que obtiene todos los tipos de usuarios
     * @author Mariela Bonilla
     * @return lista de todos los tipos de ususarios de los usuarios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public ArrayList<String> obtenerTiposUsuarios() throws SQLException, Exception;


    /**
     * Método que va a permitir que el usuario moderador guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void inicioUsuarioModerador(String correo) throws SQLException, Exception;

    /**
     * Método que va a permitir que el usuario vendedor guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void inicioUsuarioVendedor(String correo) throws SQLException, Exception;

    /**
     * Método que va a permitir que el usuario coleccionista guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void inicioUsuarioColeccionista(String correo) throws SQLException, Exception;


    /**
     * Método que va a obtener el estado del usuario que desea iniciar sesión y validar,
     * para conocer si puede o no ingresar
     * @author Mariela Bonilla
     * @param correo : Correo del usuario para identificar su estado
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean obtenerEstado(String correo) throws SQLException, Exception;

    /**
     * Método que va a permitir modificar un usuario moderador
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario a modificar
     * @param nombre : Nombre del usuario a modificar
     * @param fechaNacimiento : Fecha de nacimiento del usuario a modificar
     * @param edad : Edad del usuario a modificar
     * @param correo : Correo del usuario a modificar
     * @param contrasenna : Contraseña del usuario a modificar
     * @return Retorna valor booleando dependiendo del éxito del proceso
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean modificarModerador(String identificacion, String nombre, LocalDate fechaNacimiento, int edad, String correo,
                                      String contrasenna) throws SQLException, Exception;

    /**
     * Método que va a permitir modificar un usuario vendedor
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario a modificar
     * @param nombre : Nombre del usuario a modificar
     * @param fechaNacimiento : Fecha de nacimiento del usuario a modificar
     * @param edad : Edad del usuario a modificar
     * @param correo : Correo del usuario a modificar
     * @param contrasenna : Contraseña del usuario a modificar
     * @param provincia : Provincia del usuario a modificar
     * @param canton : Cantón del usuario a modificar
     * @param distrito : Distrito del usuario a modificar
     * @param direccion : Dirección del usuario a modificar
     * @return Retorna valor booleando dependiendo del éxito del proceso
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean modificarVendedor(String identificacion,String nombre,LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna, String provincia, String canton, String distrito,
                                     String direccion) throws SQLException, Exception;

    /**
     * Método que va a permitir modificar un usuario coleccionista
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario a modificar
     * @param nombre : Nombre del usuario a modificar
     * @param fechaNacimiento : Fecha de nacimiento del usuario a modificar
     * @param edad : Edad del usuario a modificar
     * @param correo : Correo del usuario a modificar
     * @param contrasenna : Contraseña del usuario a modificar
     * @param provincia : Provincia del usuario a modificar
     * @param canton : Cantón del usuario a modificar
     * @param distrito : Distrito del usuario a modificar
     * @param direccion : Dirección del usuario a modificar
     * @param intereses : Intereses del usaurio a modificar
     * @return Retorna valor booleando dependiendo del éxito del proceso
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean modificarColeccionista(String identificacion,String nombre,LocalDate fechaNacimiento, int edad, String correo,
                                          String contrasenna, String provincia, String canton, String distrito,
                                          String direccion, ArrayList<String> intereses) throws SQLException, Exception;


    /**
     * Método que va a retornar la lista de todos los usuarios, exceptuando al moderador
     * @author Mariela Bonilla
     * @return Retorna un TreeMap con los usaurios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public TreeMap<String, Usuario> listasDeUsuarios() throws SQLException, Exception;

    /**
     * Método que va a permitir realizar una puntuación
     * @author Mariela Bonilla
     * @return Retorna un TreeMap con los usaurios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void realizarPuntuacion(String identificacionACalificar, double puntuacion) throws SQLException, Exception;

    /**
     * Método que va a permitir desactivar un usuario
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario a desactivar
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void desactivarUsuario(String identificacion) throws SQLException, Exception;

}
