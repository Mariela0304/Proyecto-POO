package bonilla.mariela.bl.usuario;

import bonilla.mariela.accesobd.Conector;
import bonilla.mariela.bl.dao.DaoFactory;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Clase que va a recibir todos lo métodos del IUsuarioDao y que va permitir la ejecución
 * de los query de consultas y procedimientos
 * @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerUsuarioDao implements IUsuarioDao {

    /**
     * Constructor por defecto de SqlServerUsuarioDao
     * @author Mariela Bonilla
     * @version 1.0
     */
    public SqlServerUsuarioDao() {

    }

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
                                    String contrasenna) throws SQLException, Exception {
        String sql;
        boolean correcto = false;
        try {
            sql = "EXEC pa_registrar_moderador 'Moderador','" +
                    nombre + "','" +
                    tipoIdentificacion + "','" +
                    identificacion + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            correcto = true;

        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;
    }

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
    public boolean registrarVendedor(String nombre, String tipoIdentificacion,
                                     String identificacion,
                                     LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna, String provincia, String canton, String distrito,
                                     String direccion) throws SQLException, Exception {
        String sql;
        boolean correcto = false;
        try {
            sql = "EXEC pa_registrar_vendedor 'Vendedor','" +
                    nombre + "','" +
                    tipoIdentificacion + "','" +
                    identificacion + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "','" + provincia + "','" +
                    canton + "','" + canton + "','" +
                    direccion + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            correcto = true;

        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;
    }
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
                                          String direccion, ArrayList<String> intereses) throws SQLException {
        String sql;
        boolean correcto = false;
        try {
            sql = "EXEC pa_registrar_coleccionista 'Coleccionista','" +
                    nombre + "','" +
                    tipoIdentificacion + "','" +
                    identificacion + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "','" + provincia + "','" +
                    canton + "','" + canton + "','" +
                    direccion + "','" +
                    intereses + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            correcto = true;

        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;
    }


    /**
     * Método que valida que exista un moderador
     * @author Mariela
     * @return Retorna un valor booleano para determinar si existe o no un moderador
     * en el sistema
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public boolean validarExistenciaModerador() throws SQLException, Exception {
        ResultSet rs =null;
        String sql;
        boolean existencia = false;

        sql = "SELECT COUNT(identificacion) as respuesta FROM vw_lista_usuarios WHERE tipoUsuario = 'Moderador' AND estado = 'Activo'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                if (Integer.parseInt(rs.getString("respuesta")) > 0) {
                    existencia = true;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();
            rs.close();
        }
        return existencia;
    }


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
    public String validarCredenciales(String pcorreo, String pcontrasenna) throws SQLException {
        String respuestaInicio = "";
        String sql;
        ResultSet rs = null;

        try {
            sql = "SELECT [dbo].fn_validar_credenciales('"+pcorreo+"','"+ pcontrasenna+"') AS respuesta ";


            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);


            while (rs.next()) {
                respuestaInicio = rs.getString("respuesta");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }finally {
            rs.close();
        }

        return respuestaInicio;
    }


    /**
     * Método que permite validar que la identificación no se repita
     * @author Mariela Bonilla
     * @param pidentificacion : Identificacion a validar
     * @return Valor booleano que va a comprobar si la identificación está o no repetida
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean validarIdentificacion(String pidentificacion)throws SQLException {
        ResultSet rs =null;
        String sql;
        boolean repetido = false;

        sql = "SELECT [dbo].fn_validar_identificacion('"+pidentificacion+"') as respuesta";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                if (rs.getString("respuesta").equals("1")) {
                    repetido = true;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {

            rs.close();

        }


        return repetido;
    }

    /**
     * Método que permite validar que el correo no se repita
     * @author Mariela Bonilla
     * @param pcorreo : Correo a validar
     * @return Valor booleano que va a comprobar si el correo está o no repetido
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public boolean validarCorreo(String pcorreo)throws SQLException {
        ResultSet rs;
        String sql;
        boolean repetido = false;

        sql = "SELECT [dbo].fn_validar_correo('"+pcorreo+"') as respuesta";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                if (rs.getString("respuesta").equals("1")) {
                    repetido = true;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return repetido;
    }

    /**
     * Método que obtiene el tipo de usuario de quien desea iniciar sesión
     * @author Mariela Bonilla
     * @param correo : Correo que servirá para buscar el tipo de usuario
     * @return Valor booleano que va a comprobar si el correo está o no repetido
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public String obtenerTipoUsuario(String correo) throws SQLException {
        String tipoUsuario = "";
        ResultSet rs = null;
        String sql;

        sql = "SELECT tipoUsuario FROM vw_lista_usuarios where correo = '" + correo + "'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                tipoUsuario = rs.getString("tipoUsuario");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {

            rs.close();

        }

        return tipoUsuario;
    }


    /**
     * Método que obtiene todos los tipos de usuarios
     * @author Mariela Bonilla
     * @return lista de todos los tipos de ususarios de los usuarios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public ArrayList<String> obtenerTiposUsuarios() throws SQLException {
        ArrayList<String> tiposUsuario = new ArrayList<>();
        ResultSet rs = null;
        String sql;
        sql = "SELECT tipoUsuario, identificacion FROM usuarios ORDER BY (posicion) asc";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                tiposUsuario.add(rs.getString("tipoUsuario"));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {

            rs.close();

        }

        return tiposUsuario;
    }


    /**
     * Método que va a permitir que el usuario moderador guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public void inicioUsuarioModerador(String correo) throws SQLException, Exception {
        ResultSet rs = null;
        String sql;
        sql = "SELECT tipoUsuario, nombre, tipoIdentificacion,identificacion, " +
                "fechaNacimiento, edad," +
                " correo, contrasenna," +
                "estado FROM vw_lista_usuarios where " +
                "correo ='"+correo+"'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                new UsuarioIniciado(rs.getString("estado"),rs.getString("tipoUsuario"),
                        rs.getString("nombre"), rs.getString("tipoIdentificacion"),
                        rs.getString("identificacion"),
                        LocalDate.parse(rs.getString("fechaNacimiento")),
                        Integer.parseInt(rs.getString("edad")),
                        rs.getString("correo"),rs.getString("contrasenna"));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {

            rs.close();

        }
    }


    /**
     * Método que va a permitir que el usuario vendedor guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public void inicioUsuarioVendedor(String correo) throws SQLException, Exception {
        ResultSet rs = null;
        String sql;
        sql = "SELECT tipoUsuario, nombre, tipoIdentificacion,identificacion, " +
                "fechaNacimiento, edad," +
                " correo, contrasenna," +
                " provincia, canton, " +
                "distrito, direccion, puntuacion , estado FROM vw_lista_vendedores where " +
                "correo ='"+correo+"'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                new UsuarioIniciado(rs.getString("estado"),
                        rs.getString("tipoUsuario"),
                        rs.getString("nombre"), rs.getString("tipoIdentificacion"),
                        rs.getString("identificacion"),
                        LocalDate.parse(rs.getString("fechaNacimiento")),
                        Integer.parseInt(rs.getString("edad")),
                        rs.getString("correo"), rs.getString("contrasenna"),
                        rs.getString("provincia"),
                        rs.getString("canton"), rs.getString("distrito"),
                        rs.getString("direccion"),
                        Double.parseDouble(rs.getString("puntuacion")));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
    }


    /**
     * Método que va a permitir que el usuario coleccionista guarde sus datos en una clase
     * estática llamada "Usuario Iniciado"
     * @author Mariela Bonilla
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public void inicioUsuarioColeccionista(String correo) throws SQLException, Exception {
        UsuarioIniciado login = new UsuarioIniciado();
        ArrayList<String> intereses = new ArrayList<>();
        ResultSet rs = null;
        String sql;
        sql = "SELECT id,tipoUsuario, nombre, tipoIdentificacion,identificacion, " +
                "fechaNacimiento, edad," +
                " correo, contrasenna," +
                " provincia, canton, " +
                "distrito, direccion, intereses, puntuacion , estado FROM vw_lista_coleccionistas where " +
                "correo = '"+correo+"'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while(rs.next()) {
                intereses.add(rs.getString("intereses"));
                login.setIdColeccionista(Integer.parseInt(rs.getString("id")));
                login.setTipoUsuario(rs.getString("tipoUsuario"));
                login.setNombre(rs.getString("nombre"));
                login.setTipoIdentificacion(rs.getString("tipoIdentificacion"));
                login.setIdentificacion(rs.getString("identificacion"));
                login.setFechaNacimiento(LocalDate.parse(rs.getString("fechaNacimiento")));
                login.setEdad(Integer.parseInt(rs.getString("edad")));
                login.setCorreo(rs.getString("correo"));
                login.setContrasenna(rs.getString("contrasenna"));
                login.setProvincia(rs.getString("provincia"));
                login.setCanton(rs.getString("canton"));
                login.setDistrito(rs.getString("distrito"));
                login.setDireccion(rs.getString("direccion"));
                login.setPuntuacion(Double.parseDouble(rs.getString("puntuacion")));
                login.setIntereses(intereses);
                login.setEstado(rs.getString("estado"));



            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            rs.close();
        }
    }


    /**
     * Método que va a obtener el estado del usuario que desea iniciar sesión y validar,
     * para conocer si puede o no ingresar
     * @author Mariela Bonilla
     * @param correo : Correo del usuario para identificar su estado
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public boolean obtenerEstado(String correo) throws SQLException, Exception {
        boolean eliminado = false;
        String estado = "";
        ResultSet rs = null;
        String sql;
        sql = "SELECT estado FROM vw_lista_usuarios where correo = '" + correo + "')";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);
            while (rs.next()) {
                estado = rs.getString("estado");
                if (estado.equals("Eliminado")) {
                    eliminado = true;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            rs.close();
            Conector.cerrarConexionBaseDatos();
        }
        return eliminado;
    }


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
    @Override
    public boolean modificarModerador(String identificacion, String nombre, LocalDate fechaNacimiento, int edad, String correo, String contrasenna) throws SQLException {
        String sql;

        boolean correcto = false;
        try {
            sql = "EXEC pa_modificar_moderador '"+identificacion+"','" +
                    nombre + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            correcto = true;
            inicioUsuarioModerador(correo);
        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;
    }


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
    @Override
    public boolean modificarVendedor(String identificacion, String nombre, LocalDate fechaNacimiento, int edad, String correo, String contrasenna, String provincia, String canton, String distrito, String direccion) throws SQLException, Exception {
        String sql;

        boolean correcto = false;
        try {
            sql = "EXEC pa_modificar_vendededor '"+identificacion+"','" +
                    nombre + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "','" + provincia + "','" +
                    canton + "','" + canton + "','" +
                    direccion + "'";

            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            inicioUsuarioVendedor(correo);
            correcto = true;

        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;


    }


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
    @Override
    public boolean modificarColeccionista(String identificacion, String nombre, LocalDate fechaNacimiento,
                                          int edad, String correo, String contrasenna, String provincia, String canton, String distrito, String direccion, ArrayList<String> intereses) throws SQLException, Exception {
        String sql;

        boolean correcto = false;
        try {
            sql = "EXEC pa_modificar_coleccionista '"+identificacion+"','" +
                    nombre + "','" +
                    fechaNacimiento + "'," +
                    edad + ",'" +
                    correo + "','" +
                    contrasenna + "','" + provincia + "','" +
                    canton + "','" + canton + "','" +
                    direccion + "','" +
                    intereses +"'";
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);

            correcto = true;
            inicioUsuarioColeccionista(correo);

        } catch (Exception e) {
            System.out.println(e.toString());
            correcto = true;

        }


        return correcto;
    }

    /**
     * Método que va a retornar la lista de todos los usuarios, exceptuando al moderador
     * @author Mariela Bonilla
     * @return Retorna un TreeMap con los usaurios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public TreeMap<String, Usuario> listasDeUsuarios() throws SQLException, Exception {
        TreeMap<String, Usuario> listusuarios = new TreeMap<>();
        ArrayList<String> tiposUsuario;
        Usuario tmpUsuario;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String sql;
        int cont = 1;


        tiposUsuario = obtenerTiposUsuarios();
        for (String tipo : tiposUsuario) {
            if (tipo.equals("Coleccionista")) {
                sql = "SELECT tipoUsuario, nombre, tipoIdentificacion, identificacion, " +
                        "fechaNacimiento, edad," +
                        " correo," +
                        " provincia, canton, " +
                        "distrito, direccion, puntuacion, estado, posicion " +
                        "FROM vw_lista_coleccionistas where posicion=" + cont;

                try {

                    rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);



                    while (rs.next()) {

                        double puntaje = Double.parseDouble(rs.getString("puntuacion"));
                        puntaje = Math.round(puntaje*100) / 1000.0;

                        tmpUsuario = new Coleccionista(rs.getString("estado"), rs.getString("tipoUsuario"),
                                rs.getString("nombre"), rs.getString("tipoIdentificacion"), rs.getString("identificacion"),
                                LocalDate.parse(rs.getString("fechaNacimiento")),
                                Integer.parseInt(rs.getString("edad")),
                                rs.getString("correo"), rs.getString("provincia"),
                                rs.getString("canton"), rs.getString("distrito"),
                                rs.getString("direccion"), puntaje
                                );





                        listusuarios.put(rs.getString("identificacion"), tmpUsuario);
                    }
                    cont++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    rs.close();
                }
            } else if (tipo.equals("Vendedor")) {
                sql = "SELECT tipoUsuario, nombre, tipoIdentificacion,identificacion, " +
                        "fechaNacimiento, edad," +
                        " correo," +
                        " provincia, canton, " +
                        "distrito, direccion, puntuacion , estado, posicion " +
                        "FROM vw_lista_vendedores where posicion ="+cont;

                Vendedor tmpVendedor;

               try {
                   rs2 = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
                   while (rs2.next()) {

                       tmpVendedor = new Vendedor(rs2.getString("estado"),
                               rs2.getString("tipoUsuario"),
                               rs2.getString("nombre"), rs2.getString("tipoIdentificacion"),
                               rs2.getString("identificacion"),
                               LocalDate.parse(rs2.getString("fechaNacimiento")),
                               Integer.parseInt(rs2.getString("edad")),
                               rs2.getString("correo"), rs2.getString("provincia"),
                               rs2.getString("canton"), rs2.getString("distrito"),
                               rs2.getString("direccion"),
                               Double.parseDouble(rs2.getString("puntuacion")));
                       tmpUsuario = (Usuario) tmpVendedor;
                       listusuarios.put(rs2.getString("identificacion"), tmpUsuario);
                   }

                   cont++;

               } catch (Exception e) {
                   System.out.println(e.getMessage());
               } finally {
                   rs2.close();
               }
            } else {
                cont++;
            }
        }
        return listusuarios;
    }


    /**
     * Método que va a permitir realizar una puntuación
     * @author Mariela Bonilla
     * @return Retorna un TreeMap con los usuarios registrados
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    public void realizarPuntuacion(String identificacionSubastador, double puntuacion) throws SQLException, Exception {
        String sql;


                sql = "EXEC pa_realizar_puntuacion_vendedor '" + identificacionSubastador + "'," + puntuacion;

        try {
             Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Método que va a permitir desactivar un usuario
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario a desactivar
     * @throws SQLException
     * @throws Exception
     * @version 1.0
     */
    @Override
    public void desactivarUsuario(String identificacion) throws SQLException, Exception {
        String sql;
        sql = "EXEC pa_desactivar_usuario '"+identificacion+"'";
        try {
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexionBaseDatos();
        }
    }

}



