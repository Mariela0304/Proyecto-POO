package bonilla.mariela.bl.subasta;

import bonilla.mariela.accesobd.Conector;
import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;


/**
 * Esta clase va a heredar todos los métodos del ISubastaDao, siendo este el que ejecuta todos los
 * query y el que tiene acceso a la base de datos.
 * @author Mariela Bonilla
 * @version 1.0
 */
public class SqlServerSubastaDao implements ISubastaDao  {


    /**
     * Constructor por defecto de SqlServerSubastaDao
     * @author Mariela Bonilla
     * @version 1.0
     */
    public SqlServerSubastaDao() {
    }


    /**
     * Método que va permitir registrar una subasta de un vendedor.
     * @author Mariela Bonilla
     * @param fechaVencimiento : Valor que obtiene la fecha en la que la subasta va a vencer.
     * @param precioMinimo : Valor que obtiene el precio mínimo que pueden hacer la ofertas.
     * @param items : ArrayList de items que contiene los items que se van a subastar.
     * @param cantidad_items: Cantidad de ítems que contiene el ArrayList de items.
     * @param identificacion : Valor que contiente la identificacion del usuario que registra.
     *                       la subasta
     * @return : El valor retornado valida si se registró la subasta o no, con éxito.
     */
    @Override
    public boolean registrarSubasta(LocalDateTime fechaVencimiento, double precioMinimo, ArrayList<Item> items,
                                    int cantidad_items,
                                    String identificacion) throws SQLException, Exception {
        boolean registrado = false;
        String sql;
        String sql2;
        String sql3;
        ResultSet rsSubasta;

        LocalDate hoy = LocalDate.now();
        LocalTime tiempoHoy = LocalTime.now();
        LocalDateTime fechaHoyFinal = LocalDateTime.of(hoy,tiempoHoy);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try {
            sql = "EXEC pa_registrar_subasta '"+ fechaHoyFinal.format(formato) +"','"+fechaVencimiento.format(formato)
                    +"',"+precioMinimo+",'" + identificacion + "'," +
                    cantidad_items;
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            sql2 = "SELECT top(1)id FROM vw_lista_subastas ORDER BY (id) DESC ";
            rsSubasta = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql2, false);
            while (rsSubasta.next()){
                for (Item dato: items) {
                    sql3 = "EXEC pa_registrar_item_subasta '"+dato.getNombre()+"','" +dato.getDescripcion()+
                    "','"+ dato.getEstado() + "','" + dato.getFechaCompra() + "'," +
                            Integer.parseInt(rsSubasta.getString("id"));
                    Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql3);
                }
                registrado = true;
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return registrado;
    }


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
    @Override
    public boolean registrarSubasta(LocalDateTime fechaVencimiento, double precioMinimo, ArrayList<Item> items,
                                    int cantidad_items,
                                    String identificacion, int idColeccionista) throws SQLException, Exception {
        boolean registrado = false;
        LocalDate hoy = LocalDate.now();
        LocalTime tiempoHoy = LocalTime.now();
        LocalDateTime fechaHoyFinal = LocalDateTime.of(hoy,tiempoHoy);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String sql;
        String sql2;
        String sql3;
        ResultSet rsSubasta;
        sql = "EXEC pa_registrar_subasta '"+ fechaHoyFinal.format(formato) +"','"+fechaVencimiento.format(formato)
                +"',"+precioMinimo+",'" + identificacion + "'," +
                cantidad_items + "," + idColeccionista;
        try {
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);
            Conector.cerrarConexion();
            sql2 = "SELECT top(1)id FROM vw_lista_subastas ORDER BY (id) DESC";
            rsSubasta = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql2, false);
            Conector.cerrarConexion();
            while (rsSubasta.next()){
                System.out.println(rsSubasta.getString("id"));
                for (Item dato: items) {

                    sql3 = "EXEC pa_registrar_item_subasta '"+dato.getNombre()+"','" +dato.getDescripcion()+
                            "','"+ dato.getEstado() + "','" + dato.getFechaCompra() + "'," +
                            Integer.parseInt(rsSubasta.getString("id"));
                    Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql3);
                }
                registrado = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return registrado;
    }

    /**
     * Métodos que valida la cantidad de los ítems
     * @author Mariela Bonilla
     * @param cantidad : Cantidad de los ítems
     * @return Retorna valor booleando dependiendo del éxito del proceso
     */
    @Override
    public boolean validarCantidadItems(int cantidad) throws SQLException, Exception {
        boolean cantidadMayor = false;

        if (cantidad>0||!(cantidad==0)) {
            cantidadMayor = true;
        }

        return cantidadMayor;
    }


    /**
     * Método que va a retornar la lista de todas las subastas
     * @author Mariela Bonilla
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    @Override
    public TreeMap<String, Subasta> listarSubastas() throws SQLException, Exception {
        TreeMap<String, Subasta> subastas = new TreeMap<>();
        ResultSet rs = null;
        String sql;
        Subasta tmpSubasta;
        sql = "SELECT id, fechaInicio, fechaVencimiento, precioMinimo, nombre_vendedor, identificacion_vendedor" +
                ", cantidad_items, estado FROM vw_lista_subastas";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");



            while (rs.next()) {

                String fechaInicioTotal = rs.getString("fechaInicio");
                String fechaVencimientoTotal = rs.getString("fechaVencimiento");
                String fechaInicio = fechaInicioTotal.substring(0,10);
                String tiempoInicio = fechaInicioTotal.substring(11,19);
                String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                String tiempoVencimiento= fechaInicioTotal.substring(11,19);
                subastas.put(rs.getString("id"), new Subasta(Integer.parseInt(rs.getString("id")),
                        LocalDate.parse(fechaInicio,formatoFecha),
                        LocalDate.parse(fechaVencimiento, formatoFecha),
                        LocalTime.parse(tiempoInicio,formatoHora),
                        LocalTime.parse(tiempoVencimiento,formatoHora),
                        rs.getString("nombre_vendedor"),
                        rs.getString("identificacion_vendedor")
                        ,Double.parseDouble(rs.getString("precioMinimo")),
                        Integer.parseInt(rs.getString("cantidad_items")),
                        rs.getString("estado")
                ));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();
        }
        return subastas;
    }

    /**
     * Método que va a retornar la lista de todas las subastas de un usuario
     * @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    @Override
    public ArrayList<Subasta> listarSubastasUsuario(String pidentificacion) throws SQLException, Exception {
        ArrayList<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql;
        Subasta tmpSubasta;
        sql = "SELECT id, fechaInicio, fechaVencimiento, precioMinimo, identificacion_vendedor, nombre_vendedor," +
                " cantidad_items, estado FROM vw_lista_subastas" +
                " where identificacion_vendedor = '" + pidentificacion + "'";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");



            while (rs.next()) {
                String fechaInicioTotal = rs.getString("fechaInicio");
                String fechaVencimientoTotal = rs.getString("fechaVencimiento");
                String fechaInicio = fechaInicioTotal.substring(0,10);
                String tiempoInicio = fechaInicioTotal.substring(11,19);
                String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                String tiempoVencimiento= fechaInicioTotal.substring(11,19);


                subastas.add(new Subasta(Integer.parseInt(rs.getString("id")),
                        LocalDate.parse(fechaInicio,formatoFecha),
                        LocalDate.parse(fechaVencimiento, formatoFecha),
                        LocalTime.parse(tiempoInicio,formatoHora),
                        LocalTime.parse(tiempoVencimiento,formatoHora),
                        rs.getString("nombre_vendedor"),
                        rs.getString("identificacion_vendedor")
                        ,Double.parseDouble(rs.getString("precioMinimo")),
                        Integer.parseInt(rs.getString("cantidad_items")),
                        rs.getString("estado")
                ));
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();

            rs.close();
        }
        return subastas;

    }


    /**
     * Método que va a devolver las adjudicaciones del coleccionista
     *  @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario coleccionista
     * @return Retorna lista de todas las adjudicaciones
     * @version 1.0
     */
    @Override
    public ArrayList<Subasta> listarAdjudicacionesSubastas(String pidentificacion) throws SQLException, Exception {
        ArrayList<Subasta> subastas = new ArrayList<>();
        ResultSet rs = null;
        String sql;
        Subasta tmpSubasta;
        sql = "SELECT id, fechaInicio, fechaVencimiento, precioMinimo, nombre_vendedor, identificacion_vendedor, cantidad_items, identificacionGanador, idColeccionista" +
                " FROM dbo.fn_tabla_adjudicaciones ('" + pidentificacion + "')";

        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");



            while (rs.next()) {
                String fechaInicioTotal = rs.getString("fechaInicio");
                String fechaVencimientoTotal = rs.getString("fechaVencimiento");
                String fechaInicio = fechaInicioTotal.substring(0,10);
                String tiempoInicio = fechaInicioTotal.substring(11,19);
                String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                String tiempoVencimiento= fechaInicioTotal.substring(11,19);
                subastas.add(new Subasta(Integer.parseInt(rs.getString("id")),
                        LocalDate.parse(fechaInicio,formatoFecha),
                        LocalDate.parse(fechaVencimiento, formatoFecha),
                        LocalTime.parse(tiempoInicio,formatoHora),
                        LocalTime.parse(tiempoVencimiento,formatoHora),
                        rs.getString("nombre_vendedor"),
                        rs.getString("identificacion_vendedor")
                        ,Double.parseDouble(rs.getString("precioMinimo")),
                        Integer.parseInt(rs.getString("cantidad_items")),
                        rs.getString("estado")
                ));
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            Conector.cerrarConexion();

            rs.close();
        }
        return subastas;
    }

    /**
     * Método que va a retornar la lista de todas las subastas en las que participa un usuario
     * @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas
     * @version 1.0
     */
    @Override
    public TreeMap<String, Subasta> listarSubastasUsuarioParticipadas(String pidentificacion) throws SQLException, Exception {
        TreeMap<String, Subasta> subastas = new TreeMap<>();
        ResultSet rs = null;
        int cont = 0;
        String [] idsSubastas = obtenerIdsSubastas(pidentificacion);
        String sql;
        String sql2;
        Subasta tmpSubasta;

        for (int i = 0; i< idsSubastas.length; i++) {
            try {
                sql = "SELECT id, fechaInicio, fechaVencimiento, precioMinimo, nombre_vendedor, identificacion_vendedor," +
                        " cantidad_items, estado FROM vw_lista_subastas" +
                        " where id = " + idsSubastas[i];
                rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");



                while (rs.next()) {
                    String fechaInicioTotal = rs.getString("fechaInicio");
                    String fechaVencimientoTotal = rs.getString("fechaVencimiento");
                    String fechaInicio = fechaInicioTotal.substring(0,10);
                    String tiempoInicio = fechaInicioTotal.substring(11,19);
                    String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                    String tiempoVencimiento= fechaInicioTotal.substring(11,19);

                    tmpSubasta = new Subasta(Integer.parseInt(rs.getString("id")),
                            LocalDate.parse(fechaInicio,formatoFecha),
                            LocalDate.parse(fechaVencimiento, formatoFecha),
                            LocalTime.parse(tiempoInicio,formatoHora),
                            LocalTime.parse(tiempoVencimiento,formatoHora),
                            rs.getString("nombre_vendedor"),
                            rs.getString("identificacion_vendedor")
                            ,Double.parseDouble(rs.getString("precioMinimo")),
                            Integer.parseInt(rs.getString("cantidad_items")),
                            rs.getString("estado")
                    );
                    cont++;
                    subastas.put(rs.getString("identificacion_vendedor"), tmpSubasta);
                }

            }catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                Conector.cerrarConexion();
            }
        }

        return subastas;

    }


    /**
     * Método que va a devolver las subastas disponibles para ese usuario
     *  @author Mariela Bonilla
     * @param pidentificacion : Identificación del usuario coleccionista
     * @return Retorna lista de subastas dispobles
     * @version 1.0
     */
    @Override
    public ArrayList<Subasta> listarSubastasVigentes(String pidentificacion) throws SQLException, Exception {
        ArrayList<Subasta> subastasVigentes = new ArrayList<>();
        ResultSet rs = null;
        String sql = "";

        Subasta tmpSubasta;

        sql = "SELECT id, fechaInicio, fechaVencimiento, precioMinimo, nombre_vendedor, identificacion_vendedor," +
        " cantidad_items, estado FROM vw_lista_subastas" +
                " where estado = 'Vigente' AND identificacion_vendedor != '"+pidentificacion+"'";
        try {

            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");



            while (rs.next()) {
                String fechaInicioTotal = rs.getString("fechaInicio");
                String fechaVencimientoTotal = rs.getString("fechaVencimiento");
                String fechaInicio = fechaInicioTotal.substring(0,10);
                String tiempoInicio = fechaInicioTotal.substring(11,19);
                String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                String tiempoVencimiento= fechaInicioTotal.substring(11,19);


                subastasVigentes.add(new Subasta(Integer.parseInt(rs.getString("id")),
                        LocalDate.parse(fechaInicio,formatoFecha),
                        LocalDate.parse(fechaVencimiento, formatoFecha),
                        LocalTime.parse(tiempoInicio,formatoHora),
                        LocalTime.parse(tiempoVencimiento,formatoHora),
                        rs.getString("nombre_vendedor"),
                        rs.getString("identificacion_vendedor")
                        ,Double.parseDouble(rs.getString("precioMinimo")),
                        Integer.parseInt(rs.getString("cantidad_items")),
                        rs.getString("estado")
                    ));

                }



        }catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subastasVigentes;

    }

    /**
     * Este metodo va a valir si el usuario actual ha  ofertado en la subasta recibida;
     * @author Mariela Bonilla
     * @param pidentificacion: Identificacion del coleccionista a comparar
     *
     * @version 1.0
     */
    public ArrayList<Subasta> listarSubastasDisponibles(String pidentificacion) throws Exception {
        String sql;
        ResultSet rs2 = null;
        ArrayList<Subasta> listaSubastasDisponibles = new ArrayList<>();
        for (Subasta dato: listarSubastasVigentes(pidentificacion)) {
            sql = "SELECT dbo.fn_validar_coleccionista_subasta (" + dato.getId() + ",'"+pidentificacion+"') as respuesta";
            try {
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
                rs2 = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql,false);

                while (rs2.next()) {
                    String fechaInicioTotal = rs2.getString("fechaInicio");
                    String fechaVencimientoTotal = rs2.getString("fechaVencimiento");
                    String fechaInicio = fechaInicioTotal.substring(0,10);
                    String tiempoInicio = fechaInicioTotal.substring(11,19);
                    String fechaVencimiento = fechaVencimientoTotal.substring(0,10);
                    String tiempoVencimiento= fechaInicioTotal.substring(11,19);
                    if (Integer.parseInt(rs2.getString("respuesta"))==0)
                    {
                        dato.setFechaInicio(LocalDate.parse(fechaInicio, formatoFecha));
                        dato.setFechaVencimiento(LocalDate.parse(fechaVencimiento, formatoFecha));
                        dato.setTiempoInicio(LocalTime.parse(tiempoInicio,formatoHora));
                        dato.setTiempoFin(LocalTime.parse(tiempoVencimiento, formatoHora));
                        listaSubastasDisponibles.add(dato);
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                rs2.close();
                Conector.cerrarConexionBaseDatos();
            }
        }

       return listaSubastasDisponibles;
    }

    /**
     * Método que va a permitir vencer una subasta
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta a vencer
     * @version 1.0
     */
    public void vencerSubasta(int idSubasta) throws  SQLException, Exception {
        String sql;

        sql = "UPDATE subastas SET estado = 'Vencida' WHERE id = "+idSubasta;
        try {
            Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql);

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
    }


    /**
     * Método que va a retornar un número entero de la cantidad de subastas a las que ha
     * participado
     * @author Mariela Bonilla
     * @param identificacion : Identificación del usuario que desea ver sus subastas
     * @return Retorna lista de todas las subastas participadas
     * @version 1.0
     */
    public int obtenerCantidadSubastasParticipadas(String identificacion) throws  SQLException, Exception{
        String sql;
        int cantidadSubastas= 0;
        ResultSet rs = null;
        sql = "SELECT COUNT(idSubasta) as cantidad_subastas FROM dbo.obtenerIdsSubastas('"+identificacion+"')";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
               cantidadSubastas = Integer.parseInt(rs.getString("cantidad_subastas"));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return cantidadSubastas;
    }


    /**
     * Método que va a retornar la lista de los ids de las subastas de un usuario
     *  @author Mariela Bonilla
     * @param identificacionColeccionista : Identificación del usuario que desea ver sus subastas
     * @return Retorna número entero
     * @version 1.0
     */
    public String[] obtenerIdsSubastas(String identificacionColeccionista) throws  SQLException, Exception {
        String sql;
        String[] idsSubastas = new String[obtenerCantidadSubastasParticipadas(identificacionColeccionista)];
        ResultSet rs = null;
        int cont = 0;
        sql = "SELECT idSubasta FROM dbo.obtenerIdsSubastas('"+identificacionColeccionista+"')";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                idsSubastas[cont]= rs.getString("idSubasta");
                cont++;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return idsSubastas;
    }


    /**
     * Método que va a permitir validar si un usuario es ganador
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta
     * @param identificacionColeccionista : Identificación del usuario que desea validar si es
     *                                    o no ganador
     * @return Retorna número entero
     * @version 1.0
     */
    public boolean validarEsGanador(int idSubasta, String identificacionColeccionista) throws  SQLException, Exception {
        String sql;
        ResultSet rs = null;
        boolean ganador = false;

        sql = "SELECT dbo.fn_validar_ganador ( "+idSubasta+ ",'" +identificacionColeccionista+"') AS ganador ";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                if (rs.getString("ganador").equals("1")) {
                    ganador = true;

                }
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return ganador;
    }



    /**
     * Método que va a retornar una lista con los datos del usaurio ganador de la subasta
     *  @author Mariela Bonilla
     * @param identificacionVendedor : Identificación del usuario
     * @return Retorna lista de datos de usuario
     * @version 1.0
     */



    public String[] obtenerGanador(String identificacionVendedor) throws  SQLException, Exception {
        String sql;
        String[] infoGanador = new String[6];
        ResultSet rs = null;

        sql = "SELECT nombre, identificacion, provincia, canton, distrito, direccion FROM vw_lista_coleccionistas " +
                "WHERE identificacion ='"+identificacionVendedor +"'";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                infoGanador[0]= rs.getString("nombre");
                infoGanador[1]= rs.getString("identificacion");
                infoGanador[2]= rs.getString("provincia");
                infoGanador[3]= rs.getString("canton");
                infoGanador[4]= rs.getString("distrito");
                infoGanador[5]= rs.getString("direccion");
            }


        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return infoGanador;
    }



    /**
     * Método que va a permitir validar si un usuario es vendedor
     *  @author Mariela Bonilla
     * @param idSubasta : identificador de subasta
     * @param identificacionColeccionista : Identificación del usuario
     * @return Retorna un booleano
     * @version 1.0
     */
    public boolean validarVendedor(int idSubasta, String identificacionColeccionista) throws  SQLException, Exception {
        String sql;
        ResultSet rs = null;
        boolean vendedor = false;

        sql = "SELECT COUNT(identificacion_vendedor) as vendedor FROM vw_lista_subastas WHERE id =" + idSubasta +",'"+identificacionColeccionista+"'";
        try {
            rs = Conector.getConnectorBD(DaoFactory.SQLSERVER).ejecutarSQL(sql, false);

            while (rs.next()) {
                if (rs.getString("vendedor").equals("1")) {
                    vendedor = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            Conector.cerrarConexion();
        }
        return vendedor;
    }
}
