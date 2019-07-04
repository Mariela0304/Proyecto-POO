package bonilla.mariela.tl;

import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.usuario.IUsuarioDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerInicioSesion extends Controller{



    public ControllerInicioSesion() {
    }

    public void usuarioModeradorIniciado(String correo) {

        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            dao.inicioUsuarioModerador(correo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void usuarioVendedorIniciado(String correo) {

        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            dao.inicioUsuarioVendedor(correo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void usuarioColeccionistaIniciado(String correo) {

        try {
            DaoFactory factory =  DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            dao.inicioUsuarioColeccionista(correo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<UsuarioIniciado> obtenerDatosUsuarioActual() {
        ArrayList<UsuarioIniciado> usuarioActual = new ArrayList<>();

        UsuarioIniciado tmpUsuarioIniciado = new UsuarioIniciado();

        usuarioActual.add(tmpUsuarioIniciado);

        return usuarioActual;
    }

    /**
     * Este método va a obtener el correo del usuario que está deseando iniciar sesión
     * y va conectar con el DAO del los usuarios para saber si está o no eliminado
     * @author Mariela Bonilla
     * @param correo : Valor que recibe del usuario que desea iniciar sesión
     * @return
     * @version 1.0
     */
    public boolean validarEstadoUsusario(String correo) {
        boolean eliminado = false;

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IUsuarioDao dao = factory.getUsuarioDao();
            eliminado = dao.obtenerEstado(correo);

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return eliminado;
    }

}
