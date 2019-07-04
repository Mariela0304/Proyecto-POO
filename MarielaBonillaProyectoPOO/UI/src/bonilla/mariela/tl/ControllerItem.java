package bonilla.mariela.tl;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.IItemDao;
import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.usuario.Usuario;
import bonilla.mariela.bl.usuario.UsuarioIniciado;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ControllerItem {

    public boolean registrarItem(String nombre, String descripcion, String estado, LocalDate fechaCompra) {
        boolean registrado = false;

        ControllerInicioSesion gestorInicio = new ControllerInicioSesion();

        UsuarioIniciado tmpUsuario = new UsuarioIniciado();

        String identificacion = "";
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IItemDao dao = factory.getItemDao();
            registrado = dao.registrarItem(nombre, descripcion, estado, fechaCompra, tmpUsuario.getIdentificacion());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return registrado;
    }

    public ArrayList<Item> listarItemsColeccionista() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        ArrayList<Item> listaItems = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IItemDao dao = factory.getItemDao();
            for (Item dato: dao.listarItemsColeccionista(tmpUsuario.getIdentificacion())) {
                listaItems.add(dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaItems;
    }

    public void eliminarTablaItems() {
        UsuarioIniciado tmpUsuario = new UsuarioIniciado();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IItemDao dao = factory.getItemDao();
            dao.eliminarItemsColeccionita(tmpUsuario.getIdentificacion());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Item> listarItemsSubasta(int id) {

        ArrayList<Item> listaItems = new ArrayList<>();
        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            IItemDao dao = factory.getItemDao();
            for (Item dato: dao.listarItemsSubasta(id)){
                listaItems.add(dato);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listaItems;
    }


}
