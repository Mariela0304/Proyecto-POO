package bonilla.mariela.tl;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.usuario.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerUsuario extends Controller {


    public ControllerUsuario() {
    }

    public boolean registrarModerador(String nombre, String tipoIdentificacion,
                                     String identificacion,
                                     LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna) throws SQLException {
        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.registrarModerador(nombre, tipoIdentificacion, identificacion, fechaNacimiento,
                    edad, correo, contrasenna);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }

    public boolean registrarVendedor(String nombre, String tipoIdentificacion,
                                     String identificacion,
                                     LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna, String provincia, String canton, String distrito,
                                     String direccion) throws SQLException {
        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.registrarVendedor( nombre, tipoIdentificacion, identificacion, fechaNacimiento,
                    edad, correo, contrasenna, provincia, canton, distrito, direccion);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }


    public boolean registrarColeccionista(String nombre, String tipoIdentificacion,String identificacion,
                                       LocalDate fechaNacimiento, int edad, String correo,
                                       String contrasenna, String provincia, String canton, String distrito,
                                       String direccion, ArrayList<String> intereses) throws SQLException{

        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.registrarColeccionista(nombre, tipoIdentificacion, identificacion, fechaNacimiento,
                    edad, correo, contrasenna, provincia, canton, distrito, direccion, intereses);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }



    public boolean modificarModerador(String nombre,
                                     LocalDate fechaNacimiento, int edad, String correo,
                                     String contrasenna) throws SQLException{
        UsuarioIniciado login = new UsuarioIniciado();
        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.modificarModerador(login.getIdentificacion(), nombre, fechaNacimiento,
                    edad, correo, contrasenna);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }




    public boolean modificarColeccionista(String nombre,
                                          LocalDate fechaNacimiento, int edad, String correo,
                                          String contrasenna, String provincia, String canton, String distrito,
                                          String direccion, ArrayList<String> intereses) throws SQLException{
        UsuarioIniciado login = new UsuarioIniciado();
        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.modificarColeccionista(login.getIdentificacion(), nombre, fechaNacimiento,
                    edad, correo, contrasenna, provincia, canton, distrito, direccion, intereses);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }


    public boolean modificarVendedor(String nombre,
                                          LocalDate fechaNacimiento, int edad, String correo,
                                          String contrasenna, String provincia, String canton, String distrito,
                                          String direccion) throws SQLException{
        UsuarioIniciado login = new UsuarioIniciado();
        boolean correcto = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            correcto = dao.modificarVendedor(login.getIdentificacion(), nombre, fechaNacimiento,
                    edad, correo, contrasenna, provincia, canton, distrito, direccion);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            correcto = false;
        }

        return correcto;
    }


    /*
    Validación para detectar si existe o no un moderador registrado
     */
    public boolean validarExistenciaModerador() throws SQLException, Exception {
        boolean existencia = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            existencia = dao.validarExistenciaModerador();
        }catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return existencia;

    }



    /*
    Método que va a validar que la identificación no se repita
     */

    public boolean validarIdentificacion(String pidentificacion)throws SQLException {


        boolean duplicado = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            duplicado = dao.validarIdentificacion(pidentificacion);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            duplicado = false;
        }
        return duplicado;
    }

     /*
    Método que va a validar que el correo no se repita
     */
    public boolean validarCorreo(String pcorreo) throws SQLException{
        boolean duplicado = false;
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            duplicado = dao.validarCorreo(pcorreo);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            duplicado = false;
        }
        return duplicado;
    }



    public String obtenerTipoUsuario(String correo) throws SQLException {
        String tipo = "";
       try {

           DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
           IUsuarioDao dao = factory.getUsuarioDao();

           tipo = dao.obtenerTipoUsuario(correo);
       }catch (SQLException e) {
           System.out.println(e.getErrorCode());
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return tipo;
    }


    /*
    Validar inicio de sesión
     */

    public String validarCredenciales(String correo, String contrasenna) {
        String respuesta = "";
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            respuesta = dao.validarCredenciales(correo, contrasenna);
        } catch (SQLException e) {
            System.out.println(  e.getErrorCode());
            System.out.println(  e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }


    public void realizarPuntuacionGanador(double valor){

        try {

            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            dao.realizarPuntuacion(InfoSubasta.getIdentificacionGanador(), valor);
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void realizarPuntuacionVendedor(double valor){
        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();

            dao.realizarPuntuacion(InfoSubasta.getIdentificacionVendedor(), valor);
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void desactivarUsuario() {
        UsuarioIniciado login = new UsuarioIniciado();

        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            dao.desactivarUsuario(login.getIdentificacion());
        } catch (SQLException e) {
            System.out.println(  e.getErrorCode());
            System.out.println(  e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
